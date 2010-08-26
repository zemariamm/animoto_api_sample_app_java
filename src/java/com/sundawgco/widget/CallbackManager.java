package com.sundawgco.widget;

import java.util.List;
import java.util.ArrayList;

public class CallbackManager {
  private static final List<Callback> callbacks = new ArrayList<Callback>();

  public static void addCallback(String transactionToken, String callbackBody) {
    Callback callback = new Callback(transactionToken, callbackBody);
    callbacks.add(callback);
  }  

  public static List<Callback> getCallbacks() {
    return callbacks;
  }
}
