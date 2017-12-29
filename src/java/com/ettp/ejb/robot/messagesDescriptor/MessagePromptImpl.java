package com.ettp.ejb.robot.messagesDescriptor;


/*
 * class défini un message prompt.
 * on peut accéder a différente forme du message
 * selon son type
 *
 */
public class MessagePromptImpl implements MessagePrompt {
  private String messageCode;
  private String type;
  //private byte[] blobMessage;
  private String stringMessage;
  private String fileMessage;
  private String originTable;
  private String language;
  private String format;
  private String originGroupCode;

  /*public MessagePromptImpl(String type, byte[] blobMessage, String originTable) {
    this.type = type;
    this.originTable = originTable;
    this.blobMessage = blobMessage;
    this.stringMessage = null;
    this.fileMessage = null;
  }*/
  public MessagePromptImpl(String messageCode, String type, String message, String originTable, String language,
    String format, String originGroupCode) {
    this.messageCode = messageCode;
    this.type = type;
    //this.blobMessage = null;
    this.originTable = originTable;
    this.language = language;
    this.format = format;
    this.originGroupCode = originGroupCode;

    if (type.compareTo(MessagesDescriptorBean.STRING_MESSAGE) == 0) {
      this.stringMessage = message;
      this.fileMessage = null;
    }
    else {
      this.stringMessage = null;
      this.fileMessage = null;
    }
  }

  public MessagePromptImpl(String messageCode, String type, String message, String file, String originTable,
    String language, String format, String originGroupCode) {
    this.messageCode = messageCode;
    this.type = type;
    //this.blobMessage = null;
    this.originTable = originTable;
    this.language = language;
    this.format = format;
    this.originGroupCode = originGroupCode;

    if (type.compareTo(MessagesDescriptorBean.STRING_MESSAGE) == 0) {
      this.stringMessage = message;
      this.fileMessage = null;
    }
    else if (type.compareTo(MessagesDescriptorBean.FILE_MESSAGE) == 0) {
      this.stringMessage = message;

      if (file != null) {
        this.fileMessage = file;
      }
      else {
        this.type = MessagesDescriptorBean.STRING_MESSAGE;
        this.fileMessage = null;
      }
    }
    else {
      this.stringMessage = null;
      this.fileMessage = null;
    }
  }

  @Override
  public String getMessageCode() {
    return this.messageCode;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public String getStringMessage() {
    return this.stringMessage;
  }

  @Override
  public String getOriginTable() {
    return this.originTable;
  }

  @Override
  public String getFileMessage() {
    return this.fileMessage;
  }
/*
  public byte[] getBlobMessage() {
    return this.blobMessage;
  }
//*/
  @Override
  public String getLanguage() {
    return language;
  }

  @Override
  public String getOriginGroupCode() {
    return originGroupCode;
  }

  @Override
  public String getFormat() {
    return format;
  }
}
