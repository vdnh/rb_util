/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.sessionManager;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.ejb.Remote;

/**
 *
 * @author vdnh
 */
@Remote
public interface SessionManagerBeanRemote {
    
    public static int SHORT_MESSAGE = 0;
    public static int LONG_MESSAGE = 1;


//    SessionManagerBeanRemote create() throws CreateException, RemoteException;
//
//    SessionManagerBeanRemote create(String sessionId) throws CreateException, RemoteException;
//
//    SessionManagerBeanRemote create(String sessionId, String calledNumber, String callingNumber) throws CreateException, RemoteException;
    public void init();
    public void init(String sessionId);
    public void init(String sessionId, String calledNumber, String callingNumber);
    
    /**
   * termine la session de l'appelant
   */
  public void closeCall() throws RemoteException;

  /*----------------paramètre utilisateur---------------------------------*/

  /**
   * indique le language utilisé par l'appelant
   */
  public String getLanguage() throws RemoteException;

  /**
   * redéfinit le language utilisé par l'appelant
   */
  public void setLanguage(String newLanguage) throws RemoteException;

  /**
   * indique le numero appelé
   */
  public String getCallerNumber() throws RemoteException;

  /**
   * indique le numero appelant
   */
  public String getCalledNumber() throws RemoteException;

  /**
   * indique le dialog de service à jouer
   */
  public Long getServiceDialogId() throws RemoteException;

  /**
   * indique la politique de message de l'utilisateur
   * messages courts 0 ou messages longs 1
   */
  public int getMessagePolicy() throws RemoteException;

  /*--------------------------- Logs des messages ------------------------*/
  /*
   * à REVOIR!!!
   * - redéfinir ce qui doit être logger, à quel moment et sous quelle forme
   * - logs des messages d'erreur
   */

  /**
   * définit le message courant
   */

  //public void addPlayedMessage(Long played_message_id, boolean is_from_hyperlink)    throws RemoteException;

  /**
   * le message courant est considéré comme joué
   */

  // public void setMessageAsPlayed(boolean is_totally_played)    throws RemoteException;

  /**
   * ajoute un enregistrement aux logs
   */
  public Long addRecording(byte[] recording, Double duration, String groupCode, Long doid)
    throws RemoteException;

  /**
   * ajoute la touche aux logs
   */
  public void addPushedKey(String pushedKey, Long did, Long doid)
    throws RemoteException;

  Hashtable setParam(int idProcedure, String value) throws RemoteException;

  Long getServiceId() throws RemoteException;

  void setServiceId(Long serviceId) throws RemoteException;

  void setUserInfos(Long userId, String calledNumber, String languageCode)
    throws RemoteException;

/* mettre en complementaire
 boolean addRecording(Long recordingLogId, Long recordingBlobId, byte[] recording)
    throws RemoteException;
*/
  Collection getRecordingLogs(boolean all) throws RemoteException;

  byte[] getRecordingBlobData(Long recordingLogId) throws RemoteException;

  void validatePushedKey() throws RemoteException;

  void addProceduresLog(String logCall, String logReturn)
    throws RemoteException;

  void addRecordingPushedKey(String pushedKey, Timestamp ts, Long did, Long doid, Long recordingLogId)
    throws RemoteException;

  void setOriginGroupCode(String originGroupCode) throws RemoteException;

  void setCallerNumber(String callerNumber) throws RemoteException;

  void setCalledNumber(String calledNumber) throws RemoteException;

  void setRecordingBlobData(Long recordingLogId, byte[] data)
    throws RemoteException;

  Long addRecording(String transcription, Double duration, String groupCode, Long doid)
    throws RemoteException;

  Long getCallLogId() throws RemoteException;

  Long getUserPersonId() throws RemoteException;

  void createBug(Timestamp date, String description, String auteur, String etat, String originGroupCode)
    throws RemoteException;

  byte[] getRecordingBlobDataDirect(Long recordingBlobId) throws RemoteException;

  byte[] getRecordingBlobDataCommon(String blobCode) throws RemoteException;

    void remove();

    
}
