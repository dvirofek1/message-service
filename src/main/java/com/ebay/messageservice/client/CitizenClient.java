package com.ebay.messageservice.client;

import com.ebay.messageservice.data.Citizen;
import com.ebay.messageservice.service.ConfigurationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CitizenClient {
  public static final String URL_PREFIX = "http://localhost:";
  public static final String URL_SUFFIX = "/citizen/getById";
  public static final String CITIZEN_SERVICE_PORT = "citizenServicePort";
  private static final Logger LOGGER = LogManager.getLogger(CitizenClient.class);
  @Autowired RestTemplate restTemplate;
  @Autowired private ConfigurationService configurationService;

  /**
   * get citizen by id
   *
   * @param citizenID id
   * @return citizen
   */
  public Citizen getById(String citizenID) {

    LOGGER.info("get citizen service port from config server");
    String citizenServicePort = this.configurationService.getPort(CITIZEN_SERVICE_PORT);
    String url = URL_PREFIX + citizenServicePort + URL_SUFFIX;
    LOGGER.info("send request to service");
    return restTemplate.getForObject(url, Citizen.class);
  }
}
