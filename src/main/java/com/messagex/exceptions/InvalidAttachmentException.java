package com.messagex.exceptions;

public class InvalidAttachmentException extends MailSendException {
  public InvalidAttachmentException(String message) {
    super(message);
  }
}
