package com.micro.mail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    private String sendTo;
    private String subject;
    private String mailBody;
    private String[] ccList;
}
