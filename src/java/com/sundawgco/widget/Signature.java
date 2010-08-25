package com.sundawgco.widget;

import java.util.HashMap;
import java.util.Arrays;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.math.BigInteger;

/**
 * Generates a widget signature for use with the Animoto Workflow Application.
 */
public class Signature {

  public static String generate(String partnerId, String partnerSecret, String appId, String nonce) 
    throws NoSuchAlgorithmException {
    HashMap<String, String> args = new HashMap<String, String>();
    args.put("appId", appId);
    args.put("nonce", nonce);
    args.put("partnerId", partnerId);
    args.put("partnerSecret", partnerSecret);
    return generateHash(args);
  }

  /**
   * Generates an MD5 signature.
   */
  private static String generateHash(HashMap<String, String> args) 
    throws NoSuchAlgorithmException {
    StringBuffer buf = new StringBuffer();
    String[] keys = (String[]) args.keySet().toArray(new String[args.size()]);
    MessageDigest messageDigest;
    byte[] bytes;
    BigInteger bi;

    Arrays.sort(keys);
    for (int i = 0; i < keys.length; i++) {
      buf.append("" + keys[i] + "=" + args.get(keys[i])); 
      if (i < keys.length - 1) {
        buf.append("&");
      }
    }
    bytes = buf.toString().getBytes();
    messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update(bytes, 0, bytes.length);
    bi = new BigInteger(1, messageDigest.digest());
    return String.format("%1$032X", bi);
  }
}
