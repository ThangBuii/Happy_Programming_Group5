package com.hp.backend.controller;

import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.hp.backend.entity.Messages;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.service.Account.AccountService;

@Controller
public class MessageControler {

    private SimpMessagingTemplate simpMessagingTemplate;
    private AccountService accountService;
    
    
    @MessageMapping("/message")
    @SendTo("/")
    private Messages receivePrivateMessage(@Payload Messages message) throws MessagingException, CustomBadRequestException{

        simpMessagingTemplate.convertAndSendToUser(accountService.getAccountName(message.getReceiver_id()),"/private",message);
        return message;
    }
}
