/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.dialogManager;

import com.ettp.ejb.util.EJBLog;
import com.ettp.pldialog.DialogInputs_pl;
import com.ettp.pldialog.DialogOutputs_pl;
import com.ettp.pldialog.Dialogs_pl;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.PrePassivate;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
//import javax.ejb.LocalBean;

/**
 *
 * @author vdnh
 */
@Stateful
(mappedName = "dialogManager")
public class DialogManagerBean implements DialogManagerBeanRemote, SessionSynchronization{

public static int NO_NEXT_DIALOG = -1;
  public static int NEXT_DIALOG_IS_RAMDOM = 0;
  public static int NEXT_DIALOG_IS_SPECIAL = -2;
  private static int DEFAULT_MAX_RANDOM_DIALOG_OUTPUTS = 1;
  public final int SEVERE = 10;
  public final int WARNING = 7;
  public final int INFO = 5;
  public final int FINE = 3;
  public final int FINEST = 0;
  private int CURENT_LEVEL = FINEST;
  private SessionContext context;
  //private DialogOutputsLocal currentDialogOutput = null;
  //ajout de Hung
  private DialogOutputs_pl currentDialogOutput = null;
  //private DialogOutputs_pl cdo = new DialogOutputs_pl();
  //boolean exist_data;
  //
  //private DialogsLocal currentDialog = null;
  //ajout de Hung
  private Dialogs_pl currentDialog = null;
  //private Dialogs_pl cd = new Dialogs_pl();
  //
  //private DialogInputsLocal currentDialogInput = null;
  //ajout de Hung
  private DialogInputs_pl currentDialogInput = null;
  //private DialogInputs_pl cdi = new DialogInputs_pl();
  //
  private boolean isFirstDialog = true;
  private int maxRandomDialogOutputs = 0;
  private Random rdm = null;
  private Vector alreadyRandom = null;
  private String promptPath = "";
  private String grammarFile = null;
  private String grammarPolicy = null;
  private EJBLog ejbLog;

//    public DialogManagerBeanRemote create(String sessionId, Long dialogId) throws CreateException, RemoteException {
//        initLogger(sessionId);
//        isFirstDialog = true;
//
//    try {
//      InitialContext ctx = new InitialContext();
//
//      ejbLog.info("DialogManager created");
//
//      /*
//       * on recupere le dialog a jouer
//       */
//      //DialogsLocalHome dialogHome = getDialogsLocalHome();
//      //this.currentDialog = dialogHome.findByPrimaryKey(dialogId);
//      //ajout de Hung
//      Dialogs_pl dialogHome= new Dialogs_pl();
//      //exist_data=this.currentDialog.findByPrimaryKey(dialogId);
//      this.currentDialog= dialogHome.findByPrimaryKey(dialogId);
//      ejbLog.info("Dialog found : " + dialogId);
//
//      /*
//       * on pointe sur le premier dialog output
//       */
//      Long dialogOutputId = this.currentDialog.getFirstDialogOutputId();
//      ejbLog.info("First dialogOutputId : " + dialogOutputId);
//
//      //DialogOutputsLocalHome dialogOutputHome = getDialogOutputsLocalHome();
//      //ajout de Hung
//      ejbLog.fine("dialogOutputhome found");
//
//      //this.currentDialogOutput = dialogOutputHome.findByPrimaryKey(new DialogOutputsPK(dialogOutputId, dialogId));
//      //this.currentDialogOutput = dialogOutputHome.findByPrimaryKey(dialogOutputId);
//      //ajout de Hung
//      DialogOutputs_pl dialogOutputHome = new DialogOutputs_pl();
//      //exist_data= this.currentDialogOutput.findByPrimaryKey(dialogOutputId);
//      this.currentDialogOutput= dialogOutputHome.findByPrimaryKey(dialogOutputId);
//      //
//      ejbLog.info("DialogOutput found");
//
//      /*
//       * initialisation du mode aléatoire
//       */
//      this.maxRandomDialogOutputs = 0;
//      this.rdm = new Random();
//      this.alreadyRandom = new Vector();
//    }
//    catch (Exception ex) {
//      ejbLog.severe(ex);
//    }
//        return this;
//        return null;
//    }

@Override
    public void init(String sessionId, Long dialogId) {
//    }
//  public void ejbCreate(String sessionId, Long dialogId) {
    initLogger(sessionId);
    isFirstDialog = true;

    try {
      InitialContext ctx = new InitialContext();

      ejbLog.info("DialogManager created");

      /*
       * on recupere le dialog a jouer
       */
      //DialogsLocalHome dialogHome = getDialogsLocalHome();
      //this.currentDialog = dialogHome.findByPrimaryKey(dialogId);
      //ajout de Hung
      Dialogs_pl dialogHome= new Dialogs_pl();
      //exist_data=this.currentDialog.findByPrimaryKey(dialogId);
      this.currentDialog= dialogHome.findByPrimaryKey(dialogId);
      ejbLog.info("Dialog found : " + dialogId);

      /*
       * on pointe sur le premier dialog output
       */
      Long dialogOutputId = this.currentDialog.getFirstDialogOutputId();
      ejbLog.info("First dialogOutputId : " + dialogOutputId);

      //DialogOutputsLocalHome dialogOutputHome = getDialogOutputsLocalHome();
      //ajout de Hung
      ejbLog.fine("dialogOutputhome found");

      //this.currentDialogOutput = dialogOutputHome.findByPrimaryKey(new DialogOutputsPK(dialogOutputId, dialogId));
      //this.currentDialogOutput = dialogOutputHome.findByPrimaryKey(dialogOutputId);
      //ajout de Hung
      DialogOutputs_pl dialogOutputHome = new DialogOutputs_pl();
      //exist_data= this.currentDialogOutput.findByPrimaryKey(dialogOutputId);
      this.currentDialogOutput= dialogOutputHome.findByPrimaryKey(dialogOutputId);
      //
      ejbLog.info("DialogOutput found");

      /*
       * initialisation du mode aléatoire
       */
      this.maxRandomDialogOutputs = 0;
      this.rdm = new Random();
      this.alreadyRandom = new Vector();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  public void ejbActivate() {
  }

  public void ejbPassivate() {
  }

//  @PrePassivate
//   public void prePassivate() {
//    ejbLog.close();
//  }

  public void setSessionContext(SessionContext ctx) {
    this.context = ctx;
  }

  private void initLogger(String sessionId) {
    ejbLog = new EJBLog(sessionId);
  }

@Override
  public void afterBegin() {
  }

@Override
  public void beforeCompletion() {
  }

@Override
  public void afterCompletion(boolean committed) {
  }

  /*----------------------------------------------------------------------------
   *
   * Ejbs referencies
   *
   *--------------------------------------------------------------------------*/
  /*----------------------------------------------------------------------------
   *
   * getters for messages
   *
   ----------------------------------------------------------------------------*/
  public void setCurrentDI(Long currentDI) {
    try {
      //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
      //this.currentDialogInput = dialogInputsHome.findByPrimaryKey(currentDI);
      //ajout de Hung
      DialogInputs_pl dialogInputsHome= new DialogInputs_pl();
      //exist_data= this.currentDialogInput.findByPrimaryKey(currentDI);
      this.currentDialogInput= dialogInputsHome.findByPrimaryKey(currentDI);
      //
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  public Long getLongMessageId() {
    try {
      ejbLog.finest("get long message for : " + currentDialogOutput.getDialogOutputId());

      return currentDialogOutput.getMessNumber();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getHelpMessageId() {
    try {
      ejbLog.finest("get help message for : " + currentDialogOutput.getDialogOutputId());

      return currentDialogOutput.getMessNumberHelp();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getShortMessageId() {
    try {
      ejbLog.finest("get short message for : " + currentDialogOutput.getDialogOutputId());

      return currentDialogOutput.getMessNumber();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getTimeoutErrorMessageId() {
    ejbLog.finest("get timeout message for : " + currentDialogOutput.getDialogOutputId());

    try {
      Long messageId = null;

      if (currentDialogOutput != null) {
      //ajout de Hung
      //if (currentDialogOutput.getDialogOutputId().longValue()>0) {
      //
        messageId = currentDialogOutput.getTimeoutErrorMessageId();

        if ((messageId != null) && (messageId.longValue()!=0)) {
        //ajout de Hung
        //if (messageId.longValue()>0) {
        //
          return messageId;
        }
      }

      if (currentDialog != null) {
      //ajout de Hung
      //if (currentDialog.getDialogId().longValue()>0) {
      //
        messageId = currentDialog.getDfltTimeoutErrorMessageId();

        if ((messageId != null) && (messageId!=0)) {
        //ajout de Hung
        //if (messageId.longValue()>0) {
        //
          return messageId;
        }
        else {
          return null;
        }
      }
    }

    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getInputErrorMessageId() {
    try {
      Long messageId = null;

      if (currentDialogOutput != null) {
      //ajout de Hung
      //if (currentDialogOutput.getDialogOutputId().longValue()>0) {
      //
        ejbLog.finest("getting input error message for : " + currentDialogOutput.getDialogOutputId());
        messageId = currentDialogOutput.getInputErrorMessageId();
        ejbLog.finest("messageId input de currentDialogOutput: " + messageId);

        if ((messageId != null) && (messageId!=0)) {
        //ajout de Hung
        //if (messageId.longValue()>0) {
        //
          return messageId;
        }
      }

      if (currentDialog != null) {
      //ajout de Hung
      //if (currentDialog.getDialogId().longValue()>0) {
      //
        messageId = currentDialog.getDfltInputErrorMessageId();
        ejbLog.finest("messageId input de currentDialog: " + messageId);

        if ((messageId != null) && (messageId!=0)) {
        //ajout de Hung
        //if (messageId.longValue()>0) {
        //
          return messageId;
        }
        else {
          return null;
        }
      }
    }
    catch (Exception ex) {
      //trace de Hung
      //System.out.println("DialogManagerBean.getInputErrorMessageId() rencontre probleme.****");
      ejbLog.severe(ex);
    }

    return null;
    //ajout de Hung
    //return new Long(0);
    //
  }

  public Long getTooEarlyErrorMessageId() {
    try {
      Long messageId = null;

      if (currentDialog != null) {
      //ajout de Hung
      //if (currentDialog.getDialogId().longValue()>0) {
      //
        messageId = currentDialog.getDfltInputErrorMessageId();

        if ((messageId != null) && (messageId!=0)) {
        //ajout de Hung
        //if (messageId.longValue()>0) {
        //

          return messageId;
        }
        else {
          return null;
        }
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getRecordingTimeoutErrorMessageId() {
    try {
      return this.currentDialogInput.getRecordingTimeoutErrorMessag();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getRecordingErrorMessageId() {
    try {
      return this.currentDialogInput.getRecordingErrorMessageId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  /*----------------------------------------------------------------------------
   *
   * parameter for state of robot
   *
   ----------------------------------------------------------------------------*/
  public String getGrammarFile() {
    try {
      return this.currentDialogOutput.getGrammarFile();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public String getGrammarPolicy() {
    try {
      return this.currentDialogOutput.getGrammarPolicy();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  private boolean hasGrammar() {
    try {
      this.grammarPolicy = this.currentDialogOutput.getGrammarPolicy();
      this.grammarFile = this.currentDialogOutput.getGrammarFile();

      return ((this.grammarFile != null) & (this.grammarPolicy != null));
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return false;
  }

  public String getFilename() {
    try {
      return this.currentDialogInput.getRecordingDestination();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  //a transferer dans session manager
  public String getRecordingDirectory() {
    return "";
  }

  public float getTimeout() {
    try {
      return this.currentDialogOutput.getTimeoutValue().floatValue();
    }

    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return 0;
  }

  public float getRecordingTimeout() {
    try {
      return Float.parseFloat(this.currentDialogInput.getRecordingTimeout());
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return 40;
  }

  /*----------------------------------------------------------------------------
   *
   * method for routing in the robot
   *
   ----------------------------------------------------------------------------*/
  public void setDialogOutput(Long dialogOutputId) {
    try {
      //DialogOutputsLocalHome dialogOutputHome = getDialogOutputsLocalHome();

      /*
       * on réinitialise les valeurs de l'état courant
       */
      //this.currentDialogOutput = dialogOutputHome.findByPrimaryKey(new DialogOutputsPK(dialogOutputId,this.currentDialog.getDialogId()));
      //this.currentDialogOutput = dialogOutputHome.findByPrimaryKey(dialogOutputId);
      //ajout de Hung
      DialogOutputs_pl dialogOutputHome = new DialogOutputs_pl();
      //exist_data= this.currentDialogOutput.findByPrimaryKey(dialogOutputId);
      this.currentDialogOutput= dialogOutputHome.findByPrimaryKey(dialogOutputId);
      //
      this.currentDialogInput = null;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  public Long getDialogOutputId() {
    try {
      return this.currentDialogOutput.getDialogOutputId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  /* ******************************************************************
   *                   methods define the next dialog output
   ********************************************************************/
  public Collection getNextDialogInputs() {
    try {
      //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
      /* take the dialog inputs referented by the dialogId and the first dialogOupt */
      //return dialogInputsHome.findByDialogOutputId(this.currentDialogOutput.getDialogId());
      //ajout de Hung
      DialogInputs_pl dialogInputsHome = new DialogInputs_pl();
      return dialogInputsHome.findByDialogOutputId(this.currentDialogOutput.getDialogId());
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

  public Long getNextPreKoDialogOutputId() {
    try {
      Long next = this.currentDialogOutput.getPreKoNextDialogOutputId();
      //trace de Hung
      //System.out.println("DialogManagerBean - getNextPreKoDialogOutputId() a retire data.");
      //System.out.println("DialogManagerBean - Valeur de NextPreKoDialogOutputId: "+next.toString());
      //
      //if (next != null) {
      //ajout de Hung
      if (next.intValue() != 0) {
        return next;
      //
        /* mettre en complementaire
        if (next.intValue() == 0) {
          ejbLog.finest("--- next value is random - random counter value is " + this.maxRandomDialogOutputs + " ---");

          //if (this.currentDialog.getMaxRandom() != null) {
          //ajout de Hung
          if (this.currentDialog.getMaxRandom().longValue()>0) {
          //
            if (this.currentDialog.getMaxRandom().intValue() > this.maxRandomDialogOutputs) {
              return getRandomValue();
            }
            else {
              return null;
            }
          }
          else {
            if (this.DEFAULT_MAX_RANDOM_DIALOG_OUTPUTS > this.maxRandomDialogOutputs) {
              return getRandomValue();
            }
            else {
              return null;
            }
          }
        }
        else {
          return next;
        }
        //*/
      }
      else {
        return null;
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
      //trace de Hung
      //System.out.println("DialogManagerBean - getNextPreKoDialogOutputId() ne marche pas.");
      //
    }

    return null;
  }

  public Long getNextPostKoDialogOutputId() {
    try {
      Long next = null;

      if (this.currentDialogInput == null) {
        next = this.currentDialogOutput.getPostKoNextDialogOutputId();
        //trace de Hung
        //System.out.println("DialogManagerBean - this.currentDialogInput == null.");
        //System.out.println("DialogManagerBean - Valeur de PostKoNextDialogOutputId: "+next.toString());
        //
      }
      else {
        next = this.currentDialogInput.getPostKoNextDialogOutputId();
      }

      //if (next != null) {
      //ajout de Hung
      if (next.intValue() != 0) {
        return next;
      //
        /* mettre  en complementaire
        if (next.intValue() == 0) {
          ejbLog.finest("--- next value is random - random counter value is " + this.maxRandomDialogOutputs + " ---");

          //if (this.currentDialog.getMaxRandom() != null) {
          //ajout de Hung
          if (this.currentDialog.getMaxRandom().longValue()>0) {
          //
            if (this.currentDialog.getMaxRandom().intValue() > this.maxRandomDialogOutputs) {
              return getRandomValue();
            }
          }
          else {
            if (this.DEFAULT_MAX_RANDOM_DIALOG_OUTPUTS > this.maxRandomDialogOutputs) {
              return getRandomValue();
            }
          }
        }
        //*/
      }

      return next;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
      //trace de Hung
      //System.out.println("DialogManagerBean - getNextPostKoDialogOutputId() ne marche pas.");
      //
    }

    return null;
  }

  private Long getRandomValue() {
    try {
      ejbLog.finest("--- generating random value for dialog_id " + this.currentDialog.getDialogId() + " ---");

      //DialogOutputsLocalHome dialogOutputsHome = getDialogOutputsLocalHome();
      //ajout de Hung
      DialogOutputs_pl dialogOutputsHome = new DialogOutputs_pl();
      //
      Collection coll = dialogOutputsHome.findAllRandomWithDialogId(this.currentDialog.getDialogId());
      Iterator iter = coll.iterator();
      Vector notRead = new Vector();

      while (iter.hasNext()) {
        //DialogOutputsLocal dout = (DialogOutputsLocal) iter.next();
        //ajout de Hung
        DialogOutputs_pl dout = (DialogOutputs_pl) iter.next();
        //
        Long current = dout.getDialogOutputId();

        if (!(alreadyRandom.contains(current))) {
          notRead.add(current);
        }
      }

      if (notRead.size() > 0) {
        Long[] list = new Long[notRead.size()];
        int j = 0;

        for (Enumeration e = notRead.elements(); e.hasMoreElements(); j++) {
          list[j] = ((Long) e.nextElement());
        }

        int i = rdm.nextInt(notRead.size());
        Long randomValue = list[i];
        ejbLog.finest("--- random value is : " + randomValue.intValue() + " ---");
        alreadyRandom.add(randomValue);
        maxRandomDialogOutputs++;

        return randomValue;
      }
      else {
        ejbLog.finest("--- there is no random value possible ---");

        return null;

        //return getNextDialog();
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  /* procedure part  DO*/
  public Long getDOPreProcedureId() {
    try {
      return this.currentDialogOutput.getPreProcedureId();
    }

    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getDOPreKoProcedureId() {
    try {
      return this.currentDialogOutput.getPreKoProcedureId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getDOPostProcedureId() {
    try {
      return this.currentDialogOutput.getPostProcedureId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getDOPostKoProcedureId() {
    try {
      return this.currentDialogOutput.getPostKoProcedureId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getDIPostProcedureId() {
    try {
      return this.currentDialogInput.getPostProcedureId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getDIPostKoProcedureId() {
    try {
      return this.currentDialogInput.getPostKoProcedureId();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getAffectParameterId() {
    try {
      if (this.currentDialogInput == null) {
        return null;
      }
      else {
        return this.currentDialogInput.getAffectParameterId();
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

  public Long getDINextDialogOutpuId() throws RemoteException {
    try {
      return currentDialogInput.getNextDialogOutputId();
    }
    catch (Exception ex) {
      //ejbLog.severe(ex);
      ejbLog.info("no next DO for DI : " + currentDialogInput.getDialogInputId());

      return null;
    }
  }

  public Long getDINextDialogOutputId(Long dialogInputId)
    throws RemoteException {
    try {
      //only for login in html
      //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
      //return dialogInputsHome.findByPrimaryKey(dialogInputId).getNextDialogOutputId();
      //ajout de Hung
      DialogInputs_pl dialogInputsHome= new DialogInputs_pl();
      //exist_data= dialogInputsHome.findByPrimaryKey(dialogInputId);
      dialogInputsHome.findByPrimaryKey(dialogInputId);
      return dialogInputsHome.getNextDialogOutputId();
      //
    }
    catch (Exception ex) {
      //ejbLog.severe(ex);
      ejbLog.info("no next DO for DI : " + currentDialogInput.getDialogInputId());

      return null;
    }
  }

  public String getLoginLabel(Long loginDialogOutputId) {
    try {
      //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
      //ajout de Hung
      DialogInputs_pl dialogInputsHome= new DialogInputs_pl();
      //
      Collection dis = dialogInputsHome.findByDialogOutputId(loginDialogOutputId);
      Iterator disIterator = dis.iterator();

      //DialogInputsLocal di = (DialogInputsLocal) disIterator.next();
      //ajout de Hung
      DialogInputs_pl di = (DialogInputs_pl) disIterator.next();
      //
      return di.getAnswerLabel();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
      ejbLog.info("error getting login label for : " + loginDialogOutputId);

      return null;
    }
  }

  public Long getDINextPostKoDialogOutputId() throws RemoteException {
    try {
      return currentDialogInput.getPostKoNextDialogOutputId();
    }
    catch (Exception ex) {
      //ejbLog.severe(ex);
      ejbLog.info("no next POST KO DO for DI : " + currentDialogInput.getDialogInputId());

      return null;
    }
  }

  public String getDONextDialogVariable() {
    try {
      ejbLog.fine("getDONextDialogVariable currentDO " + currentDialogOutput.getDialogOutputId());

      return this.currentDialogOutput.getNextDialogOutputVar();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

  public String getDefaultLanguage() throws RemoteException {
    return currentDialog.getDfltLanguage();
  }

  public String getDINextDialogVariable() {
    try {
      ejbLog.fine("DI getNextDialogVariable " + currentDialogInput.getDialogInputId() + " var = " +
        currentDialogInput.getNextDialogOutputVar());
      //System.out.println("DialogManagerBean - DI getNextDialogVariable " + currentDialogInput.getDialogInputId() + " var = " +
        //currentDialogInput.getNextDialogOutputVar());

      return currentDialogInput.getNextDialogOutputVar();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

  public Long getDONextDialog() {
    try {
      Long next = this.currentDialogOutput.getNextDialogOutputId();
      //trace de Hung
      //System.out.println("DialogManagerBean - getDONextDialog() a retire data.");
      //System.out.println("DialogManagerBean - Valeur de NextDialogOutputId: "+next.toString());
      //
      //if (next != null) {
      //ajout de Hung
      if (next.intValue() != 0) {
        return next;
      //
        /* mettre en complementaire
        if (next.intValue() == 0) {
          ejbLog.finest("--- next value is random - random counter value is " + this.maxRandomDialogOutputs + " ---");

          //if (this.currentDialog.getMaxRandom() != null) {
          //ajout de hung
          if (this.currentDialog.getMaxRandom().longValue()>0) {
          //
            if (this.currentDialog.getMaxRandom().intValue() > this.maxRandomDialogOutputs) {
              return getRandomValue();
            }
            else {
              return null;
            }
          }
          else {
            if (this.DEFAULT_MAX_RANDOM_DIALOG_OUTPUTS > this.maxRandomDialogOutputs) {
              return getRandomValue();
            }
            else {
              return null;
            }
          }
        }
        else {
          return next;
        }
        //*/
      }
      else {
        return null;
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
      //trace de Hung
      System.out.println("DialogManagerBean - getDONextDialog() ne marche pas.");
      //
    }

    return null;
  }

  public boolean isDialogOutputLocal() {
    try {
      String type = this.currentDialogOutput.getTypeDialogOutput();

      if ((type != null) && (type.contains("LOCAL"))) {
        return true;
      }
      else {
        return false;
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return false;
    }
  }

  public boolean isInputAllowed() {
    try {
      String allowInput = this.currentDialogOutput.getAllowInput();

      if ((allowInput != null) && allowInput.equals("YES")) {
        return true;
      }
      else {
        return false;
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return false;
    }
  }

  public String getOriginGroupCode() throws RemoteException {
    return currentDialog.getOriginGroupCode();
  }

  public List getDialogInputs() {
    try {
      //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
      //ajout de Hung
      DialogInputs_pl dialogInputsHome = new DialogInputs_pl();
      Collection dis = dialogInputsHome.findByDialogOutputId(currentDialogOutput.getDialogOutputId());
      Iterator disIterator = dis.iterator();

      DialogInputInfos[] returnDis = new DialogInputInfos[dis.size()];

      for (int i = 0; i < dis.size(); i++) {
        //DialogInputsLocal di = (DialogInputsLocal) disIterator.next();
        //ajout de Hung
        DialogInputs_pl di = (DialogInputs_pl) disIterator.next();

        Long diid = di.getDialogInputId();
        ejbLog.fine("\tdiid " + diid);

        Long doid = di.getDialogOutputId();

        ejbLog.fine("\tdoid " + doid);

        Long did = di.getDialogId();

        ejbLog.fine("\tdid  " + did);

        Long mn = di.getMessNumber();
        ejbLog.fine("\tmn   " + mn);

        String it = di.getDtmfMask();

        ejbLog.fine("\tit   " + it);

        long min = di.getValMin();

        ejbLog.fine("\tmin  " + min);

        long max = di.getValMax();

        ejbLog.fine("\tmax  " + max);

        String vf = di.getVariableFiltre();

        ejbLog.fine("\tvf   " + vf);

        boolean cf = false;

        if ((di.getConfirmRecording()).equals("Y")) {
          cf = true;
        }

        ejbLog.fine("\tcf   " + cf);

        long minDefault = 0;
        long maxDefault = 999999999;

        if ((min < 0)) {
          min = minDefault;
        }

        if ((max <= 0)) {
          max = maxDefault;
        }

        String fileWav = di.getFileWav();

        if (fileWav != null) {
          if (fileWav.lastIndexOf(".") >= 0) {
            fileWav = fileWav.substring(0, fileWav.lastIndexOf("."));
          }

          fileWav += ".wav";
        }

        String al = di.getAnswerLabel();

        ejbLog.fine("\tal   " + al);

        String mc = di.getMessageCode();

        ejbLog.fine("\tmc   " + mc);

        returnDis[i] = new DialogInputInfosImpl(diid, doid, did, mn, it, min, max, vf, cf, fileWav, al, mc);
      }

      //Arrays.sort(returnDis);
      return Arrays.asList(returnDis);
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

  public int getPushedKeysLogLevel() {
    try {
      return currentDialog.getPushedKeysLogLevel().intValue();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return 0;
    }
  }

  public int getProceduresLogLevel() {
    try {
      return currentDialog.getProceduresLogLevel().intValue();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return 0;
    }
  }

  public boolean isLogin(Long dialogOutputId) {
    try {
      if (dialogOutputId != null) {
        //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
        //ajout de Hung
        DialogInputs_pl dialogInputsHome = new DialogInputs_pl();
        //
        Collection dis = dialogInputsHome.findByDialogOutputId(dialogOutputId);
        Iterator disIterator = dis.iterator();
        //String label = ((DialogInputsLocal) disIterator.next()).getAnswerLabel();
        //ajout de Hung
        String label = ((DialogInputs_pl) disIterator.next()).getAnswerLabel();
        //

        return label.equals("VOICE_LOGIN");
      }
    }
    catch (Exception ex) {
      //ejbLog.severe(ex);
    }

    return false;
  }

  public boolean isPassword(Long dialogOutputId) {
    try {
      if (dialogOutputId != null) {
        //DialogInputsLocalHome dialogInputsHome = getDialogInputsLocalHome();
        //ajout de Hung
        DialogInputs_pl dialogInputsHome = new DialogInputs_pl();
        //
        Collection dis = dialogInputsHome.findByDialogOutputId(dialogOutputId);
        Iterator disIterator = dis.iterator();
        //String label = ((DialogInputsLocal) disIterator.next()).getAnswerLabel();
        //ajout de Hung
        String label = ((DialogInputs_pl) disIterator.next()).getAnswerLabel();
        //

        return label.equals("VOICE_PASSWORD");
      }
    }
    catch (Exception ex) {
      //ejbLog.severe(ex);
    }

    return false;
  } 

    @Override
    public void remove() {
        ejbLog.close();
    }

}