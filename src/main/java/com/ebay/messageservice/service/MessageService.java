package com.ebay.messageservice.service;

import com.ebay.messageservice.client.CitizenClient;
import com.ebay.messageservice.data.Citizen;
import com.ebay.messageservice.data.MessageToCitizens;
import com.ebay.messageservice.data.Request;
import com.ebay.messageservice.data.RequestList;
import com.ebay.messageservice.model.Message;
import com.ebay.messageservice.repository.MessageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {
  private static final Logger LOGGER = LogManager.getLogger(MessageService.class);
  private static final String FIRST_NAME = "first_name";
  private static final String LAST_NAME = "last_name";
  @Autowired private MessageRepository messageRepository;
  @Autowired private CitizenClient citizenClient;

  /**
   * send message to citizens
   *
   * @param messageToCitizens message and citizens
   * @return request list
   */
  public RequestList sendAlert(MessageToCitizens messageToCitizens) {
    List<Request> ans = new ArrayList<>();
    LOGGER.info("for each citizen send alert and log in database");
    for (Citizen citizen : messageToCitizens.getCitizens()) {
      String message =
          messageToCitizens
              .getMessage()
              .replaceAll(FIRST_NAME, citizen.getFirstName())
              .replaceAll(LAST_NAME, citizen.getLastName());
      ans.add(new Request(citizen, message));
      sendMessageAndSave(citizen, message);
      // if guardian exist send message also
      if (citizen.getGuardianId() != null && !Objects.equals(citizen.getGuardianId(), "")) {
        LOGGER.info("guardian exist - send alert to guardian and log in database ");
        Citizen guardian = citizenClient.getById(citizen.getGuardianId());
        ans.add(new Request(guardian, message));
      }
    }
    LOGGER.info("Sent all messages successfully");

    LOGGER.info("Return new request list");
    return new RequestList(ans);
  }

  /**
   * saend message to phone and log in data base
   *
   * @param citizen citizen
   * @param messageToSend message
   */
  private void sendMessageAndSave(Citizen citizen, String messageToSend) {
    LOGGER.info("send message to phone");
    // here send message to phone
    //
    LOGGER.info("log in database");
    Message message = new Message();
    message.setCitizenPrivateId(citizen.getPrivateId());
    message.setTimestamp(System.currentTimeMillis());
    message.setMessage(messageToSend);
    messageRepository.save(message);
  }
}
