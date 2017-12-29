package com.ettp.plmessage;

import java.io.Serializable;


public class DialogOutputMessagesPK implements Serializable {
  public Long dialogOutputId;
  public Long messageOrder;
  public String messageCode;
  public String origin;
  public Long dialogId;

  public DialogOutputMessagesPK() {
  }

  public DialogOutputMessagesPK(Long dialogId, String origin, String messageCode, Long dialogOutputId, Long messageOrder) {
    this.dialogId = dialogId;
    this.origin = origin;
    this.messageCode = messageCode;
    this.dialogOutputId = dialogOutputId;
    this.messageOrder = messageOrder;
  }

  public DialogOutputMessagesPK(Long dialogId, String origin, String messageCode, Long dialogOutputId) {
    this.dialogId = dialogId;
    this.origin = origin;
    this.messageCode = messageCode;
    this.dialogOutputId = dialogOutputId;
    this.dialogOutputId = dialogOutputId;
  }

  public DialogOutputMessagesPK(Long dialogId, String origin, String messageCode) {
    this.dialogId = dialogId;
    this.origin = origin;
    this.messageCode = messageCode;
  }

  public DialogOutputMessagesPK(Long dialogId, String origin) {
    this.dialogId = dialogId;
    this.origin = origin;
  }

  public boolean equals(Object other) {
    if (other instanceof DialogOutputMessagesPK) {
      final DialogOutputMessagesPK otherDialogOutputMessagesPK = (DialogOutputMessagesPK) other;

      // The following assignment statement is auto-maintained and may be overwritten.
      boolean areEqual = (otherDialogOutputMessagesPK.messageOrder.equals(messageOrder) &&
        otherDialogOutputMessagesPK.messageCode.equals(messageCode) &&
        otherDialogOutputMessagesPK.origin.equals(origin) && otherDialogOutputMessagesPK.dialogId.equals(dialogId) &&
        otherDialogOutputMessagesPK.dialogOutputId.equals(dialogOutputId));

      return areEqual;
    }

    return false;
  }

  public int hashCode() {
    // Add custom hashCode() impl here
    return super.hashCode();
  }
}
