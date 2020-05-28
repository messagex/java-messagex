package com.messagex.api.request;

import com.messagex.exceptions.InvalidAttachmentException;
import com.messagex.exceptions.InvalidContactException;
import com.messagex.exceptions.InvalidContentException;
import com.messagex.exceptions.InvalidHeaderException;
import com.messagex.exceptions.MailSendException;

public class Mail {

  private String contactGroupId;
  private String unsubscribeGroupId;
  private Contact from;
  private Contact[] to;
  private Contact[] cc;
  private Contact[] bcc;
  private Contact replyTo;
  private String subject;
  private Header[] headers;
  private Attachment[] attachments;
  private Content[] content;
  private Analytics analytics;

  public Mail() {}

  public String getContactGroupId() {
    return contactGroupId;
  }

  public void setContactGroupId(String contactGroupId) {
    this.contactGroupId = contactGroupId;
  }

  public String getUnsubscribeGroupId() {
    return unsubscribeGroupId;
  }

  public void setUnsubscribeGroupId(String unsubscribeGroupId) {
    this.unsubscribeGroupId = unsubscribeGroupId;
  }

  public Contact getFrom() {
    return from;
  }

  public void setFrom(Contact from) {
    this.from = from;
  }

  public Contact[] getTo() {
    return to;
  }

  public void setTo(Contact[] to) {
    this.to = to;
  }

  public Contact[] getCc() {
    return cc;
  }

  public void setCc(Contact[] cc) {
    this.cc = cc;
  }

  public Contact[] getBcc() {
    return bcc;
  }

  public void setBcc(Contact[] bcc) {
    this.bcc = bcc;
  }

  public Contact getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(Contact replyTo) {
    this.replyTo = replyTo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Header[] getHeaders() {
    return headers;
  }

  public void setHeaders(Header[] headers) {
    this.headers = headers;
  }

  public Attachment[] getAttachments() {
    return attachments;
  }

  public void setAttachments(Attachment[] attachments) {
    this.attachments = attachments;
  }

  public Content[] getContent() {
    return content;
  }

  public void setContent(Content[] content) {
    this.content = content;
  }

  public Analytics getAnalytics() {
    return analytics;
  }

  public void setAnalytics(Analytics analytics) {
    this.analytics = analytics;
  }

  /**
   * Validator function
   * Compulsory properties:
   * from.address
   * to.address
   * subject
   * replyTo.address
   * content
   */
  public Boolean validate() throws MailSendException {
    if (null == this.from || !this.from.validate()) {
      throw new InvalidContactException("The `from` email is invalid");
    }
    if (null != this.to && this.to.length >= 1) {
      for (Contact toContact : this.to) {
        if (!toContact.validate()) {
          throw new InvalidContactException("One or more `to` addresses are invalid");
        }
      }
    } else {
      throw new InvalidContactException("One or more `to` addresses are invalid");
    }
    //Validate CC and BCC if they are present.
    if (null != this.cc && this.cc.length >=1) {
      for (Contact ccContact : this.cc) {
        if (!ccContact.validate()) {
          throw new InvalidContactException("One or more `cc` addresses are invalid");
        }
      }
    }
    if (null != this.bcc && this.bcc.length >=1) {
      for (Contact bccContact : this.bcc) {
        if (!bccContact.validate()) {
          throw new InvalidContactException("One or more `bcc` addresses are invalid");
        }
      }
    }

    if (null == this.subject || this.subject.isEmpty()) {
      throw new MailSendException("Subject cannot be empty");
    }

    if (null != this.content && this.content.length >= 1) {
      for (Content cont: this.content) {
        if (!cont.validate()) {
          throw new InvalidContentException("One or more `content` schemas are invalid");
        }
      }
    } else {
      throw new MailSendException("Content cannot be empty");
    }

    // Validate attachments and headers if present.
    if (null != this.attachments && this.attachments.length >= 1) {
      for (Attachment att: attachments) {
        if (!att.validate()) {
          throw new InvalidAttachmentException("One or more `attachment` schemas are invalid");
        }
      }
    }

    if (null != this.headers && this.headers.length >= 1) {
      for (Header h: headers) {
        if (!h.validate()) {
          throw new InvalidHeaderException("One or more `header` schemas are invalid");
        }
      }
    }
    return true;
  }
}
