package com.ettp.ejb.robot.messagesDescriptor;

import java.io.Serializable;


public interface MessagePrompt extends Serializable {
  public String getMessageCode();

  public String getType();

  public String getLanguage();

  public String getOriginGroupCode();

  public String getOriginTable();

  public String getFormat();

  public String getStringMessage();

  public String getFileMessage();

//  public byte[] getBlobMessage();
}
