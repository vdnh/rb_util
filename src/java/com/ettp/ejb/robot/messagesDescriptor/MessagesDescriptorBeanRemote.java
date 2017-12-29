/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.messagesDescriptor;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.Remote;

/**
 *
 * @author vdnh
 */
@Remote
public interface MessagesDescriptorBeanRemote {

      /*
   * les différent type de messages
   */
  public static String STRING_MESSAGE = "STRING_MESSAGE";
  public static String BLOB_MESSAGE = "BLOB_MESSAGE";
  public static String FILE_MESSAGE = "FILE_MESSAGE";
  public static String NO_MORE_MESSAGE = "NO_MORE_MESSAGE";



  Integer getMessageLevel() throws RemoteException;

  void setMessageLevel(Integer messageLevel) throws RemoteException;

  String getPromptsUrl() throws RemoteException;

  void setPromptsUrl(String promptsUrl) throws RemoteException;

  String[] getAudio(Long originGroupId, String languageCode, String wavName)
    throws RemoteException;

  String getTts_url() throws RemoteException;

  void setTts_url(String tts_url) throws RemoteException;

  String getTtsdaemon_url() throws RemoteException;

  void setTtsdaemon_url(String ttsdaemon_url) throws RemoteException;

  String getVariableAudio(String messageText, Long groupId, String language)
    throws RemoteException;

  void setDateCreationWav(String messageText, String languageCode, Long groupId, Timestamp ts, String locutor)
    throws RemoteException;



  String getGroupCode(Long groupId) throws RemoteException;

  List getAudiosList(Long originGroupId, String languageCode)
    throws RemoteException;

  String getVariableAudio(String messageText, String groupCode, String language)
    throws RemoteException;

  List getMessageList(String originGroupCode, String languageCode)
    throws RemoteException;
/*
  byte[] getMessageBlob(String messageCode, String originGroupCode, String languageCode)
    throws RemoteException;

  void setMessageBlob(String messageCode, String originGroupCode, String languageCode, byte[] blob)
    throws RemoteException;
//*/

  List getMessageListDO(Long dialogOutputId, String originGroupCode, String languageCode) throws RemoteException;

  List getMessageListMessNum(Long messageNumber, String originGroupCode, String languageCode) throws RemoteException;

  List getMessageListDI(String messageCode, String originGroupCode, String languageCode) throws RemoteException;

  Long getBlobId(String originGroupCode, String languageCode, String messageCode) throws RemoteException;

  String getBlobCode(String blobCode) throws RemoteException;

  //MessagesDescriptorBeanRemote create(String sessionId) throws CreateException, RemoteException;
  public void init(String sessionId);
  void remove();

}
