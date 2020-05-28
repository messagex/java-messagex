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

  public Boolean validate() {
    if (null != this.contentEncoded && !this.contentEncoded.isEmpty()) {
      if (null != this.mimeType && !this.mimeType.isEmpty()) {
        if (null != this.filename && !this.filename.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }
}
