package com.project.bookmyshow.services;

import com.project.bookmyshow.exceptions.InvaldArgumentException;
import com.project.bookmyshow.exceptions.SeatNotAvailableException;
import com.project.bookmyshow.models.*;
import com.project.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private  final TicketRepository ticketRepository;

    @Autowired
    public TicketService(SeatRepository seatRepository,
                         ShowSeatRepository showSeatRepository,
                         ShowRepository showRepository,
                         UserRepository userRepository,
                         TicketRepository ticketRepository) {
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }


    public Ticket bookTicket(List<Long> seatIds, Long showId, Long userId) throws Exception {

        //1.for these seatIds get the corresponding showSeats -> getSeatsForIds(seatIds)
        //2.Check the status of all showSeats -> getShowSeatsForSeats(Seats)
        //2.a every seat are available
        //3.a lock every seat (set status to be locked)
        //4.a create  ticket object and return it
        //2.b some of the seats are not available
        //3.b throw an exception

        //1.-> 1,2,5
        //2.-> 2,3,5



        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw  new InvaldArgumentException("Show by: "+showId+" doesn't exist!");
        }

        //Lock will be taken here on the show seats
        List<ShowSeat> showSeats = getAndLockShowSeats(seats, showOptional);

        //Checking for user
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new InvaldArgumentException("User with id: "+userId+" doesn't exist");
        }

        //Creating a ticket
        Ticket ticket = new Ticket();
        ticket.setBookedBy(userOptional.get());
        ticket.setStatus(TickerStatus.PROCESSING);
        ticket.setShow(showOptional.get());
        ticket.setSeats(seats);
        ticket.setAmount(500);
        ticket.setTimeOfBooking(new Date());

        Ticket savedTicket =  ticketRepository.save(ticket);
        return savedTicket;


    }


    //set tarnsection isolation level serializable -> Autometically
    // start transection -> Autometically
    //Here Method is public -> Transaction can be done only on public methods
    @Transactional(isolation = Isolation.SERIALIZABLE , timeout = 10)// here 2 is given for transaction timeout
    public List<ShowSeat> getAndLockShowSeats(List<Seat> seats, Optional<Show> showOptional) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, showOptional.get());

        //Checking availability & other
        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE)) ||
                    (showSeat.getStatus().equals(ShowSeatStatus.LOCKED))){
                throw new SeatNotAvailableException();
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        return showSeats;
        //commit -> Autometically
        // Lock will be removed -> Autometically
    }
}
