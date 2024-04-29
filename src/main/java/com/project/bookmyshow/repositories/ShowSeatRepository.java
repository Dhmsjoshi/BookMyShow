package com.project.bookmyshow.repositories;

import com.project.bookmyshow.models.Seat;
import com.project.bookmyshow.models.Show;
import com.project.bookmyshow.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    //@Query("SELECT * FROM Shows WHERE startTime  FOR UPDATE")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats,Show show );
     ShowSeat save(ShowSeat showSeat);


}
