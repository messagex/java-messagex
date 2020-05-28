package com.messagex.exceptions;

public class InvalidHeaderException extends MailSendException {

  public InvalidHeaderException(String message) {
    super(message);
  }
}
