package com.ebay.messageservice.service;

import com.ebay.messageservice.client.ConfigurationClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
  private static final Logger LOGGER = LogManager.getLogger(ConfigurationService.class);
  private static final String MESSAGE_FORMAT = "message_format_";
  @Autowired private ConfigurationClient configurationClient;

  /**
   * get port by key
   *
   * @param string key
   * @return port
   */
  public String getPort(String string) {
    LOGGER.info("send request to client");
    return configurationClient.get(string);
  }
}
