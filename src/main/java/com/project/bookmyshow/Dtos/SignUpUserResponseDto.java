package com.project.bookmyshow.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserResponseDto {
    private Long userId;
    private String status;
    private String message;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
