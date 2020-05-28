package com.messagex.exceptions;

public class InvalidContentException extends MailSendException {
  public InvalidContentException(String message) {
    super(message);
  }
}
