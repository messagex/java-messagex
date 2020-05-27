package com.messagex.api.request;

public class Attachment {

  private String contentEncoded;
  private String mimeType;
  private String filename;

  public Attachment() {}

  public String getContentEncoded() {
    return contentEncoded;
  }

  public void setContentEncoded(String contentEncoded) {
    this.contentEncoded = contentEncoded;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}
