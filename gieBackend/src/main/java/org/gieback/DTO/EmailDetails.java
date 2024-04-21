package org.gieback.DTO;


import lombok.Data;

@Data
public class EmailDetails {
    private String to;
    private String subject;
    private String message;
}
