package com.ebay.messageservice.service;

import com.ebay.messageservice.data.Citizen;
import com.ebay.messageservice.data.MessageToCitizens;
import com.ebay.messageservice.data.Request;
import com.ebay.messageservice.data.RequestList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageServiceTest {
  private static final Logger LOGGER = LogManager.getLogger(MessageServiceTest.class);
  private static List<Integer> areaNumbers;
  private static List<Citizen> citizenList1;
  private static List<Citizen> citizenList2;
  private static List<Request> requestList;
  private static MessageToCitizens messageToCitizens;
  private static String message;
  @Autowired MessageService messageService;

  @BeforeAll
  static void init() {
    LOGGER.info("init lists");
    areaNumbers = new ArrayList<>();
    citizenList1 = new ArrayList<>();
    citizenList2 = new ArrayList<>();
    requestList = new ArrayList<>();

    message = "Hey you must to run";

    for (int i = 0; i < 10; i++) {
      Citizen citizen1 =
          new Citizen("123" + i, "dvir" + i, "benita" + i, "Jerusalem", "054260687" + i, "", "1");

      citizenList1.add(citizen1);

      citizenList2.add(
          new Citizen(
              "12" + i + 11,
              "moti" + i + 11,
              "benita" + i + 11,
              "Tel Aviv",
              "054260686" + i + 11,
              "123" + i + 11,
              "2"));

      requestList.add(new Request(citizen1, message));
    }

    messageToCitizens = new MessageToCitizens(citizenList1, message);
  }

  @Test
  void sendAlert() {
    RequestList requestList = messageService.sendAlert(messageToCitizens);

    LOGGER.info("assert equals");
    for (int i = 0; i < requestList.getRequestList().size(); i++) {
      Assertions.assertEquals(
          requestList.getRequestList().get(i).getCitizen().getPrivateId(),
          citizenList1.get(i).getPrivateId());
    }
  }
}
