package com.messagex.api.request;

public class AuthoriseRequest {

  private String apiKey;
  private String apiiSecret;

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiiSecret() {
    return apiiSecret;
  }

  public void setApiiSecret(String apiiSecret) {
    this.apiiSecret = apiiSecret;
  }
}
