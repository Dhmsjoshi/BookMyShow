package com.project.bookmyshow.controllers;

import com.project.bookmyshow.Dtos.BookTicketRequestDto;
import com.project.bookmyshow.Dtos.BookTicketResponseDto;
import com.project.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.TimeoutException;

@Controller
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    BookTicketResponseDto bookTicket(BookTicketRequestDto request){
        List<Long> seatIds = request.getSeatIds();
        Long userId = request.getUserId();
        Long showId = request.getShowId();

        BookTicketResponseDto response = new BookTicketResponseDto();

//        try{
//        //    ticketService.bookTicket(seatIds,showId,userId);
//        }catch(TimeoutException e){
//            response.setStatus("Failure");
//            response.setMessage("Something is wrong!!");
//        }

        return null;
    }
}
