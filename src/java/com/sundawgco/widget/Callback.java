package com.sundawgco.widget;

import java.util.Date;

public class Callback {
  private String transactionToken;
  private String callbackBody;
  private Date createdAt = new Date();

  public Callback(String transactionToken, String callbackBody) {
    this.transactionToken = transactionToken;
    this.callbackBody = callbackBody;
  }

  public String getTransactionToken() {
    return transactionToken; 
  }

  public String getCallbackBody() {
    return callbackBody;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
