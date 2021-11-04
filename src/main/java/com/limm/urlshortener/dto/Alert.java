package com.limm.urlshortener.dto;

import lombok.Data;

@Data
public class Alert {
    String message;

    public Alert(String message) {
        this.message = message;
    }
}
