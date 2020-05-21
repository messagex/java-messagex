package com.messagex.api.response;

public class AuthoriseResponse {
  private Long id;
  private Long apiKeyId;
  private String bearerToken;
  private String refreshToken;
  private String expiresAt;
  private String createdAt;
  private Long createdAtEpoch;
  private Long updatedAtEpoch;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getApiKeyId() {
    return apiKeyId;
  }

  public void setApiKeyId(Long apiKeyId) {
    this.apiKeyId = apiKeyId;
  }

  public String getBearerToken() {
    return bearerToken;
  }

  public void setBearerToken(String bearerToken) {
    this.bearerToken = bearerToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Long getCreatedAtEpoch() {
    return createdAtEpoch;
  }

  public void setCreatedAtEpoch(Long createdAtEpoch) {
    this.createdAtEpoch = createdAtEpoch;
  }

  public Long getUpdatedAtEpoch() {
    return updatedAtEpoch;
  }

  public void setUpdatedAtEpoch(Long updatedAtEpoch) {
    this.updatedAtEpoch = updatedAtEpoch;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(String expiresAt) {
    this.expiresAt = expiresAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
