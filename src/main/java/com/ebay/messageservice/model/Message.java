package com.ebay.messageservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "message")
public class Message {

  @Id private String id;
  private String citizenPrivateId;
  private long timestamp; // in unix timestamp
  private String message;
}
