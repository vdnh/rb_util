/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.dialogManager;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.Remote;

/**
 *
 * @author vdnh
 */
@Remote
public interface DialogManagerBeanRemote {
  public static int NO_NEXT_DIALOG = -1;
  public static int NEXT_DIALOG_IS_RAMDOM = 0;
  public static int NEXT_DIALOG_IS_SPECIAL = -2;

  /*
   * les types d'�tat existant
   */
  public static final String NO_INPUT = "NO_INPUT";
  public static final String NO_TYPE = "NO_TYPE";
  public static final String DTMF = "DTMF";
  public static final String VOCAL = "VOCAL";
  public static final String RECORD = "RECORD";
  public static final String VOCAL_DTMF = "VOCAL_DTMF";
  public static final String VOCAL_RECORD = "VOCAL_RECORD";
  public static final String DTMF_RECORD = "DTMF_RECORD";
  public static final String VOCAL_DTMF_RECORD = "VOCAL_DTMF_RECORD";

  /**
   * positionne sur l'�tat courant
   */
  public void setDialogOutput(Long dialogOutputId) throws RemoteException;

  /**
   * donne l'�tat courant
   */
  public Long getDialogOutputId() throws RemoteException;

  /**
   * renvoie le type de l'�tat courant
   */

  //public String getDialogOutputType() throws RemoteException;
  public Collection getNextDialogInputs() throws RemoteException;

  /**
   * d�termine le prochain �tat apr�s l'�chec des proc�dures d'entr�e
   */
  public Long getNextPreKoDialogOutputId() throws RemoteException;

  /**
   * d�termine le prochain �tat apr�s l'�chec des proc�dures de sortie
   */
  public Long getNextPostKoDialogOutputId() throws RemoteException;

  /*----------------------- param�tres de l'�tat courant ------------------------*/

  /**
   * indique le fichier grammaire pour l'�tat courant
   */
  public String getGrammarFile() throws RemoteException;

  /**
   * indique la politique au sein du fichier grammaire pour l'�tat courant
   */
  public String getGrammarPolicy() throws RemoteException;

  /**
   * donne le timeout pour la reconnaissance
   */
  public float getTimeout() throws RemoteException;

  /**
   * donne le timeout pour l'enregistrement
   */
  public float getRecordingTimeout() throws RemoteException;

  /**
   * indique le fichier de l'enregistrement
   */
  public String getFilename() throws RemoteException;

  /**
   * indique le r�pertoire de l'enregistrement
   */
  public String getRecordingDirectory() throws RemoteException;

  /*-------------------------Messages pour l'�tat courant----------------------*/

  /**
   * indique l'ID du message Long
   */
  public Long getLongMessageId() throws RemoteException;

  /**
   * indique l'ID du message Court
   */
  public Long getShortMessageId() throws RemoteException;

  /**
   * indique l'ID du message d'Aide
   */
  public Long getHelpMessageId() throws RemoteException;

  /**
   * indique l'ID du message pour une erreur d'entr�e
   */
  public Long getInputErrorMessageId() throws RemoteException;

  /**
   * indique l'ID du message pour une erreur de timeout
   */
  public Long getTimeoutErrorMessageId() throws RemoteException;

  /**
   * indique l'ID du message pour une erreur too early
   */
  public Long getTooEarlyErrorMessageId() throws RemoteException;

  /**
   * indique l'ID du message pour une erreur d'enregistrement
   */
  public Long getRecordingErrorMessageId() throws RemoteException;

  /**
   * indique l'ID du message pour une erreur de timeout lors d'un enregistrement
   */
  public Long getRecordingTimeoutErrorMessageId() throws RemoteException;

  /**
   * indique l'ID de la procedure execut�e en entr�e d'�tat
   */
  public Long getDOPreProcedureId() throws RemoteException;

  /**
   * indique l'ID de la procedure execut�e lors de l'�chec
   * de la proc�dure ex�cut� en entr�e d'�tat
   */
  public Long getDOPreKoProcedureId() throws RemoteException;

  /**
   * indique l'ID de la procedure execut�e en sortie d'�tat
   */
  public Long getDOPostProcedureId() throws RemoteException;

  /**
   * indique l'ID de la procedure execut�e lors de l'�chec
   * de la proc�dure ex�cut� en sortie d'�tat
   */
  public Long getDOPostKoProcedureId() throws RemoteException;

  /*
   * � r�aliser
   * - mapping sur les param�tres g�rant le nombre maximum d'erreur
   * - mapping sur les param�tres indiquant l'�atat suivant dans
   *   le cas d'un nombre maximum d'erreur
   */
  public boolean isInputAllowed() throws RemoteException;

  public Long getDINextDialogOutpuId() throws RemoteException;

  boolean isDialogOutputLocal() throws RemoteException;

  String getDONextDialogVariable() throws RemoteException;

  Long getAffectParameterId() throws RemoteException;

  String getDefaultLanguage() throws RemoteException;

  String getDINextDialogVariable() throws RemoteException;

  void setCurrentDI(Long currentDI) throws RemoteException;

  Long getDONextDialog() throws RemoteException;

  Long getDIPostProcedureId() throws RemoteException;

  Long getDIPostKoProcedureId() throws RemoteException;

  Long getDINextPostKoDialogOutputId() throws RemoteException;

  List getDialogInputs() throws RemoteException;

  int getPushedKeysLogLevel() throws RemoteException;

  int getProceduresLogLevel() throws RemoteException;

  String getOriginGroupCode() throws RemoteException;

  boolean isLogin(Long dialogOutputId) throws RemoteException;

  boolean isPassword(Long dialogOutputId) throws RemoteException;

  Long getDINextDialogOutputId(Long dialogInputId) throws RemoteException;

  String getLoginLabel(Long loginDialogOutputId) throws RemoteException;

//  DialogManagerBeanRemote create(String sessionId, Long dialogId) throws CreateException, RemoteException;
  public void init(String sessionId, Long dialogId);

    void remove();
}