package com.messagex.config;

/**
 * MessageXOptions is the configuration entity used for accessing the MessageX API.
 * @author Saurabh Raje
 */
public class MessagexOptions {
  private static String defaultBaseUrl ="http://api.messagex.test:8080";
  private static String defaultApiKey = (System.getenv("MESSAGEX_API_KEY") != null) ? System.getenv("MESSAGEX_API_KEY") : null;
  private static String defaultApiSecret = (System.getenv("MESSAGEX_API_SECRET") != null) ? System.getenv("MESSAGEX_API_SECRET") : null;

  private String baseUrl;
  private String apiKey;
  private String apiSecret;

  /**
   * Class Constructor with default values.
   */
  public MessagexOptions() {
    this.baseUrl = MessagexOptions.defaultBaseUrl;
    this.apiKey = MessagexOptions.defaultApiKey;
    this.apiSecret = MessagexOptions.defaultApiSecret;
  }

  /**
   * Class constructor with configurable API_KEY and API_SECRET.
   * @param apiKey the API KEY available at messagex.com.
   * @param apiSecret the API SECRET available at messagex.com.
   */
  public MessagexOptions(String apiKey, String apiSecret) {
    // Validations
    if (null == apiKey || apiKey.equals("")) {
      throw new IllegalArgumentException("Api Key cannot be empty.");
    }
    if (null == apiSecret || apiSecret.equals("")) {
      throw new IllegalArgumentException("Api Secret cannot be empty.");
    }

    this.baseUrl = MessagexOptions.defaultBaseUrl;
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }

  /**
   * This constructor is only for those clients that have a custom implementation of the
   * MessageX API and should not be used by others.
   * @param baseUrl the API URL provided at messagex.com.
   * @param apiKey the API KEY available at messagex.com.
   * @param apiSecret the API SECRET available at messagex.com.
   */
  public MessagexOptions(String baseUrl, String apiKey, String apiSecret) throws IllegalArgumentException {
    if (null == baseUrl || baseUrl.equals("")) {
      throw new IllegalArgumentException("Base Url cannot be empty.");
    }
    if (null == apiKey || apiKey.equals("")) {
      throw new IllegalArgumentException("Api Key cannot be empty.");
    }
    if (null == apiSecret || apiSecret.equals("")) {
      throw new IllegalArgumentException("Api Secret cannot be empty.");
    }
    this.baseUrl = baseUrl;
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiSecret() {
    return apiSecret;
  }

  public void setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
  }
}
