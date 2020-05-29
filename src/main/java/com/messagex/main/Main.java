package com.messagex.main;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagex.api.request.Contact;
import com.messagex.api.request.Content;
import com.messagex.api.request.Mail;
import com.messagex.api.response.AuthoriseResponse;
import com.messagex.api.response.MailSendResponse;
import com.messagex.client.Messagex;
import com.messagex.config.MessagexOptions;

public class Main {

  private static final String DEFAULT_API_KEY = (System.getenv("MESSAGEX_API_KEY") != null) ? System.getenv("MESSAGEX_API_KEY") : "V8yjr16wySux4143ipmfbo4saEK5Qw5odX1TMOoSG6SVWge8Zg10OIkUAWJQJlew";
  private static final String DEFAULT_API_SECRET = (System.getenv("MESSAGEX_API_SECRET") != null) ? System.getenv("MESSAGEX_API_SECRET") : "vmENsH10SO5QXHDBwoweGw2tczPOn87vir2rbZDskfSyy1yyvcqY33T6PWAZqvVY";

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static void main(String[] args) {
    try {
      MessagexOptions options = new MessagexOptions(DEFAULT_API_KEY, DEFAULT_API_SECRET);
      Messagex client = new Messagex(options);
      System.out.println("Successfully created client");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
