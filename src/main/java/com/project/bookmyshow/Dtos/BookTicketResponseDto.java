package com.project.bookmyshow.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketResponseDto {
    private int amount;
    private Long ticketId;
    private List<String> seatNumbers;
    private String status;
    private String message;

}
