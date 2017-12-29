/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.dialogManager;

import java.io.Serializable;

public interface DialogInputInfos extends Serializable {
  public Long getDialogInputId();

  public Long getDialogOutputId();

  public Long getDialogId();

  public String getInputType();

  public long getMin();

  public long getMax();

  public Long getMessageNumber();

  public String getMessageCode();

  public String getVariableFilter();

  public boolean getConfirmRecording();

  public String getFileWav();

  public String getAnswerLabel();
}
