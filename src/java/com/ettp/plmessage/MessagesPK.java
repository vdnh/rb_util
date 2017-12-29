package com.ettp.plmessage;

import java.io.Serializable;



public class MessagesPK implements Serializable {
  public String languageCode;
  public String originGroupCode;
  public String messageCode;

  public MessagesPK() {
    languageCode=null;
    originGroupCode=null;
    messageCode=null;
  }

  public MessagesPK(String messageCode, String originGroupCode, String languageCode) {
    this.messageCode = messageCode;
    this.originGroupCode = originGroupCode;
    this.languageCode = languageCode;
  }

  public boolean equals(Object other) {
    if (other instanceof MessagesPK) {
      final MessagesPK otherMessagesPK = (MessagesPK) other;

      // The following assignment statement is auto-maintained and may be overwritten.
      boolean areEqual = (otherMessagesPK.languageCode.equals(languageCode) &&
        otherMessagesPK.originGroupCode.equals(originGroupCode) && otherMessagesPK.messageCode.equals(messageCode));

      return areEqual;
    }

    return false;
  }

  public int hashCode() {
    // Add custom hashCode() impl here
    return super.hashCode();
  }
}