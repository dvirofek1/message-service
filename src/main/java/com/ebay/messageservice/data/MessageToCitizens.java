package com.ebay.messageservice.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class MessageToCitizens {

  List<Citizen> citizens;
  String message;
}
