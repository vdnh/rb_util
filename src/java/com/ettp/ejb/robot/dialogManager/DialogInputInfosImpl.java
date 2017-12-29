/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.dialogManager;

public class DialogInputInfosImpl implements DialogInputInfos, Comparable {
  private Long dialogId;
  private Long dialogOutputId;
  private Long dialogInputId;
  private Long messageNumber;
  private String inputType;
  private long min;
  private long max;
  private String variableFilter;
  private boolean confirmRecording;
  private String fileWav;
  private String answerLabel;
  private String messageCode;

  public DialogInputInfosImpl(Long dialogInputId, Long dialogOutputId, Long dialogId, Long messageNumber,
    String inputType, long min, long max, String variableFilter, boolean confirmRecording, String fileWav,
    String answerLabel, String messageCode) {
    this.dialogInputId = dialogInputId;
    this.dialogOutputId = dialogOutputId;
    this.dialogId = dialogId;
    this.messageNumber = messageNumber;
    this.inputType = inputType;
    this.min = min;
    this.max = max;
    this.variableFilter = variableFilter;
    this.confirmRecording = confirmRecording;
    this.fileWav = fileWav;
    this.answerLabel = answerLabel;
    this.messageCode = messageCode;
  }

  @Override
  public int compareTo(Object o) {
    if (this.dialogInputId > ((DialogInputInfos) o).getDialogInputId()) {
      return 1;
    }
    else if (this.dialogInputId < ((DialogInputInfos) o).getDialogInputId()) {
      return -1;
    }

    else {
      return 0;
    }
  }

  @Override
  public Long getDialogInputId() {
    return dialogInputId;
  }

  @Override
  public Long getDialogOutputId() {
    return dialogOutputId;
  }

  @Override
  public Long getDialogId() {
    return dialogId;
  }

  @Override
  public Long getMessageNumber() {
    return messageNumber;
  }

  @Override
  public String getMessageCode() {
    return messageCode;
  }

  @Override
  public String getInputType() {
    return inputType;
  }

  @Override
  public long getMin() {
    return min;
  }

  @Override
  public long getMax() {
    return max;
  }

  @Override
  public String getVariableFilter() {
    return variableFilter;
  }

  @Override
  public boolean getConfirmRecording() {
    return confirmRecording;
  }

  @Override
  public String getFileWav() {
    return fileWav;
  }

  @Override
  public String getAnswerLabel() {
    return answerLabel;
  }
}
