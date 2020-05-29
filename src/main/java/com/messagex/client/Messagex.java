package com.messagex.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.messagex.api.request.AuthoriseRequest;
import com.messagex.api.request.Mail;
import com.messagex.api.response.AuthoriseResponse;
import com.messagex.api.response.MailSendResponse;
import com.messagex.config.MessagexOptions;
import com.messagex.exceptions.MailSendException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Messagex {
  private static ObjectMapper mapper = new ObjectMapper();

  private MessagexOptions messagexOptions;

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public Messagex(MessagexOptions messagexOptions) {
    this.messagexOptions = messagexOptions;
  }

  public AuthoriseResponse authenticate() throws IOException, AuthenticationException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    AuthoriseRequest authoriseRequest = new AuthoriseRequest(messagexOptions);
    String authoriseRequestStr = new String();
    try {
      authoriseRequestStr = mapper.writeValueAsString(authoriseRequest);
    } catch (JsonProcessingException ex) {
      throw ex;
    }
    AuthoriseResponse authoriseResponse = new AuthoriseResponse();
    HttpPost httpRequest = new HttpPost(messagexOptions.getBaseUrl() + "/api/authorise");
    httpRequest.setHeader("Content-Type", "application/json");
    try {
      httpRequest.setEntity(new StringEntity(authoriseRequestStr));
      try {
        CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
          String httpResponseStr = new String();
          httpResponseStr = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
          JsonNode httpResponseNode = mapper.readTree(httpResponseStr);
          authoriseResponse = mapper.readValue(httpResponseNode.get("data").toString(), AuthoriseResponse.class);
          return authoriseResponse;
        } else {
          throw new AuthenticationException("MessageX Authentication Failed");
        }
      } catch (ClientProtocolException ex) {
        throw ex;
      } catch (IOException ex) {
        ex.printStackTrace();
        throw ex;
      }
    } catch (UnsupportedEncodingException ex) {
      throw ex;
    } finally {
      httpClient.close();
    }
  }

  public MailSendResponse sendMail(String bearerToken, Mail mail) throws MailSendException, IOException {
    if (null != bearerToken && !bearerToken.isEmpty()) {
      CloseableHttpClient httpClient = HttpClients.createDefault();
      try {
        mail.validate();
        String mailSendRequestStr = new String();
        try {
          mailSendRequestStr = mapper.writeValueAsString(mail);
        } catch (JsonProcessingException ex) {
          throw ex;
        }
        System.out.println(messagexOptions.getBaseUrl() + "/api/mail/send");
        HttpPost httpRequest = new HttpPost(messagexOptions.getBaseUrl() + "/api/mail/send");
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setHeader("Authorization", "Bearer " + bearerToken);
        try {
          httpRequest.setEntity(new StringEntity(mailSendRequestStr));
          try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
              return new MailSendResponse(true, "Email was sent successfully.");
            } else {
              throw new MailSendException(EntityUtils.toString(httpResponse.getEntity()));
            }
          } catch (ClientProtocolException ex) {
            throw ex;
          }
        } catch (UnsupportedEncodingException ex) {
          throw ex;
        }
      } catch (MailSendException ex) {
        throw ex;
      } finally {
        httpClient.close();
      }
    }
    throw new MailSendException("bearerToken is mandatory for sending emails");
  }

  public MessagexOptions getMessagexOptions() {
    return this.messagexOptions;
  }

}
