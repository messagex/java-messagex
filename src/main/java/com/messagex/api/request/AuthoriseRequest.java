package com.messagex.api.request;

import com.messagex.config.MessagexOptions;

public class AuthoriseRequest {

  private String apiKey;
  private String apiSecret;

  public AuthoriseRequest() {

  }

  public AuthoriseRequest(MessagexOptions messagexOptions) {
    this.apiKey = messagexOptions.getApiKey();
    this.apiSecret = messagexOptions.getApiSecret();
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
