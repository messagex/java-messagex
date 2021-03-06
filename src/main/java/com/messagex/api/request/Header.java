package com.messagex.api.request;

public class Header {

  private String name;
  private String value;

  public Header() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Boolean validate() {
    if (null != this.name && !this.name.isEmpty()) {
      if (null != this.value && !this.value.isEmpty()) {
        return true;
      }
    }
    return false;
  }
}
