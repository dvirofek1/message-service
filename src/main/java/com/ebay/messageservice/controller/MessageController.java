package com.ebay.messageservice.controller;

import com.ebay.messageservice.data.MessageToCitizens;
import com.ebay.messageservice.data.RequestList;
import com.ebay.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
  @Autowired MessageService messageService;

  /**
   * send message to citizens
   *
   * @param messageToCitizens message and citizens
   * @return request list
   */
  @PostMapping("/sendAlert")
  public RequestList sendAlert(@RequestBody(required = false) MessageToCitizens messageToCitizens) {
    return messageService.sendAlert(messageToCitizens);
  }
}
