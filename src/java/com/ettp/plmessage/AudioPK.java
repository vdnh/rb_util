package com.ettp.plmessage;

import java.io.Serializable;


public class AudioPK implements Serializable {
  public String originGroupCode;
  public String languageCode;
  public String messageText;

  public AudioPK() {
  }

  public AudioPK(String messageText, String languageCode, String originGroupCode) {
    this.messageText = messageText;
    this.languageCode = languageCode;
    this.originGroupCode = originGroupCode;
  }

  public boolean equals(Object other) {
    if (other instanceof AudioPK) {
      final AudioPK otherAudioPK = (AudioPK) other;

      // The following assignment statement is auto-maintained and may be overwritten.
      boolean areEqual = (otherAudioPK.languageCode.equals(languageCode) &&
        otherAudioPK.messageText.equals(messageText) && otherAudioPK.originGroupCode.equals(originGroupCode));

      return areEqual;
    }

    return false;
  }

  public int hashCode() {
    // Add custom hashCode() impl here
    return super.hashCode();
  }
}