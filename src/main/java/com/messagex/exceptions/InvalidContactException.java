package com.messagex.exceptions;

public class InvalidContactException extends MailSendException {
  public InvalidContactException(String message) {
    super(message);
  }
}
