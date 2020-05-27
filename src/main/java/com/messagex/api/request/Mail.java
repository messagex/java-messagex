package com.messagex.api.request;

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
}
