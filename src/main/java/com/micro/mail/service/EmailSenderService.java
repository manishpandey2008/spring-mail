package com.micro.mail.service;

import com.micro.mail.model.EmailModel;
import com.micro.mail.model.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public ResponseModel sendMail(EmailModel emailModel){
        SimpleMailMessage message=new SimpleMailMessage();
        ResponseModel responseModel=new ResponseModel();
        message.setTo(emailModel.getSendTo());
        message.setText(emailModel.getMailBody());
        message.setSubject(emailModel.getSubject());
        message.setCc(emailModel.getCcList());
       try{
           javaMailSender.send(message);
           responseModel.setMessage("Mail send successfully to "+emailModel.getSendTo());
           responseModel.setHttpResponse(HttpStatus.OK);
       }catch (Exception e){
           responseModel.setMessage("Failed !! Mail not send to "+emailModel.getSendTo());
           responseModel.setHttpResponse(HttpStatus.FAILED_DEPENDENCY);
       }
        return responseModel;
    }

    public void sendMailWithAttatchment(String toEmail,String body,String subject,String attatchment) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("");
        mimeMessageHelper.setTo("");
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        System.out.println("Check file.........");
        FileSystemResource fileSystemResource=new FileSystemResource(new File(attatchment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

        javaMailSender.send(mimeMessage);
        System.out.println("Finally mail has send.........");
    }
}
