# MessageX SDK - Java

# THIS SDK IS A WIP AND SHOULD NOT BE DOWNLOADED YET

---
![MessageX Logo](https://raw.githubusercontent.com/messagex/node-messagex/master/img/messagex-logo.png "MessageX")

This SDK provides enables node applications with an easy to use interface to the MessageX API.

---

* [Installation](#installation)
* [Examples](#examples)

---

## Installation (Maven)

```xml
<dependency>
  <groupId>com.messagex</groupId>
  <artifactId>messagex</artifactId>
  <version>1.0.0</version>
</dependency>
```

---

## Examples

### Sending email

#### Environment Setup

The MessageX Client can read your API Key and API Secret from the system's environment variables.

```sh
export MESSAGEX_API_KEY="YOUR_MESSAGEX_API_KEY"
export MESSAGEX_API_SECRET="YOUR_MESSAGEX_API_SECRET"
```

#### Initialise the client

```java
MessageXOptions options = new MessageXOptions(System.getenv("MESSAGEX_API_KEY"), System.getenv("MESSAGEX_API_SECRET"));
```

The following example shows how to send an email with the bare minimum required options.

```java
package com.mypackage;
import com.messagex.client.Messagex;
import com.messagex.config.MessagexOptions;
import com.messagex.api.request.Contact;
import com.messagex.api.request.Content;
import com.messagex.api.request.Mail;
import com.messagex.api.response.AuthoriseResponse;
import com.messagex.api.response.MailSendResponse;
import com.messagex.config.MessagexOptions;

public class MyClass {
  public static void main(String[] args) {
    MessageXOptions options = new MessageXOptions(System.getenv("MESSAGEX_API_KEY"), System.getenv("MESSAGEX_API_SECRET"));
    Messagex client = new Messagex(options);
    Mail mail = new Mail();
    Contact from = new Contact();
    from.setAddr("from@messagex.com");
    Contact toAddr = new Contact();
    toAddr.setAddress("to@messagex.com");
    Contact[] to = {toAddr};
    mail.setTo(to);
    mail.setSubject("Test Email from Java SDK");
    Content htmlContent = new Content();
    htmlContent.setType("text/html");
    htmlContent.setBody("<html><head><title>Test HTML email body</title></head><body><p>Test HTML Email body</p></body></html>");
    Content plainContent = new Content();
    plainContent.setType("text/plain");
    plainContent.setBody("Test Plain Email Body");
    mail.setContent(new Content[]{htmlContent, plainContent});
    Contact replyTo = new Contact();
    replyTo.setAddress("replyto@messagex.com");
    mail.setReplyTo(replyTo);
    AuthoriseResponse authResponse = client.authenticate();
    MailSendResponse mailSendResponse = client.sendMail(authResponse.getBearerToken(), mail);
    System.out.println(mailSendResponse.getMessage());
  }
} 
```