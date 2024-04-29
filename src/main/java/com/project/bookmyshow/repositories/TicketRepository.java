package com.project.bookmyshow.repositories;

import com.project.bookmyshow.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Override
    Ticket save(Ticket ticket);

//    @Override
//    void delete(Ticket entity);



}
