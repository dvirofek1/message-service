package com.ebay.messageservice.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CitizenList {
  private final List<Citizen> citizenList;
}
