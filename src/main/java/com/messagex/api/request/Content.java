package com.messagex.api.request;

public class Content {
  private String type;
  private String body;

  public Content() {}

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Boolean validate() {
    if (null != this.type && !this.type.isEmpty()) {
      if (null != this.body && !this.body.isEmpty()) {
        return true;
      }
    }
    return false;
  }
}
