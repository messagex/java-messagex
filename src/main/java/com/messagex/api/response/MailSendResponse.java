package com.messagex.api.response;

import java.util.Map;

public class MailSendResponse {

  private Boolean success;
  private String message;

  public MailSendResponse() {}

  public MailSendResponse(Boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
