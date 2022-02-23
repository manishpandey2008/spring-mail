package com.micro.mail.controller;

import com.micro.mail.model.EmailModel;
import com.micro.mail.model.ResponseModel;
import com.micro.mail.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mail")
@Slf4j
@RequiredArgsConstructor
public class MailController {
    @Autowired
    private final EmailSenderService emailSenderService;

    @GetMapping("/info")
    public ResponseEntity<ResponseModel> mailServiceInfo(){
        log.info("Mail Service Information");
        return ResponseEntity.ok().body(new ResponseModel("Mail Service is active", HttpStatus.OK));
    }

    @PostMapping("/simple")
    public ResponseEntity<ResponseModel> sendSimpleMail(@RequestBody EmailModel emailModel){
        log.info("Mail Send to {}",emailModel.getSendTo());
        return ResponseEntity.ok().body(emailSenderService.sendMail(emailModel));
    }
}
