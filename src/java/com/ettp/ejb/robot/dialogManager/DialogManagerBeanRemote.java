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
   * les types d'état existant
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
   * positionne sur l'état courant
   */
  public void setDialogOutput(Long dialogOutputId) throws RemoteException;

  /**
   * donne l'état courant
   */
  public Long getDialogOutputId() throws RemoteException;

  /**
   * renvoie le type de l'état courant
   */

  //public String getDialogOutputType() throws RemoteException;
  public Collection getNextDialogInputs() throws RemoteException;

  /**
   * détermine le prochain état après l'échec des procédures d'entrée
   */
  public Long getNextPreKoDialogOutputId() throws RemoteException;

  /**
   * détermine le prochain état après l'échec des procédures de sortie
   */
  public Long getNextPostKoDialogOutputId() throws RemoteException;

  /*----------------------- paramètres de l'état courant ------------------------*/

  /**
   * indique le fichier grammaire pour l'état courant
   */
  public String getGrammarFile() throws RemoteException;

  /**
   * indique la politique au sein du fichier grammaire pour l'état courant
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
   * indique le répertoire de l'enregistrement
   */
  public String getRecordingDirectory() throws RemoteException;

  /*-------------------------Messages pour l'état courant----------------------*/

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
   * indique l'ID du message pour une erreur d'entrée
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
   * indique l'ID de la procedure executée en entrée d'état
   */
  public Long getDOPreProcedureId() throws RemoteException;

  /**
   * indique l'ID de la procedure executée lors de l'échec
   * de la procédure exécuté en entrée d'état
   */
  public Long getDOPreKoProcedureId() throws RemoteException;

  /**
   * indique l'ID de la procedure executée en sortie d'état
   */
  public Long getDOPostProcedureId() throws RemoteException;

  /**
   * indique l'ID de la procedure executée lors de l'échec
   * de la procédure exécuté en sortie d'état
   */
  public Long getDOPostKoProcedureId() throws RemoteException;

  /*
   * à réaliser
   * - mapping sur les paramètres gérant le nombre maximum d'erreur
   * - mapping sur les paramètres indiquant l'éatat suivant dans
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