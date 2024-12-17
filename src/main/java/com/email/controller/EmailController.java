package com.email.controller;

import com.email.model.EmailRequest;
import com.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @RequestMapping("/welcome")
    public  String welcome(){
        return  "this is my email api";
    }

//    api to send email

    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
    {
    boolean result = this.emailService.sendEEmail(request.getSubject(), request.getMessage(), request.getTo());
        System.out.println(request);
        if (result){
            return ResponseEntity.ok("Email is sent Successfully...");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email is not sent...");
        }
    }
}
