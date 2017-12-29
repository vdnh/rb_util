/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.caller;

import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.EJBLog;
import java.rmi.RemoteException;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;

import oracle.sql.STRUCT;

/**
 *
 * @author vdnh
 */
@Stateful (mappedName = "procedureCaller")
public class ProcedureCallerBean implements ProcedureCallerBeanRemote, SessionSynchronization {
    
  public static final int SEVERE = 10;
  public static final int WARNING = 7;
  public static final int INFO = 5;
  public static final int FINE = 3;
  public static final int FINEST = 0;
  private static int CURENT_LEVEL = FINEST;
  private SessionContext sessionContext;
  Connection connection;
  private final String CODE_RETOUR = "STATUS";
  private EJBLog ejbLog;

//  @Override
//    public ProcedureCallerBeanRemote create(String sessionId) throws CreateException, RemoteException {
////        initLogger(sessionId);
////        return this;
//        return null;
//    }

  @Override
    public void init(String sessionId) {
    initLogger(sessionId);

    try {
      ejbLog.info("ProcedureCaller created");
      
      connection = OracleJDBCConnection.getJDBCConnection();
      ejbLog.info("Connected !!");
      connection.setAutoCommit(false);
      setDebug(0);
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  public void ejbActivate() {
  }

  public void ejbPassivate() {
  }

  public void remove() {
    ejbLog.close();
  }

  private void initLogger(String sessionId) {
    ejbLog = new EJBLog(sessionId);
  }

  public void setSessionContext(SessionContext ctx) {
    this.sessionContext = ctx;
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

  @Override
  public Hashtable invokeActivitiesProcedure(String procName, String[] parameters)
    throws RemoteException {
    Hashtable retour = new Hashtable();

    try {
      String parametersCount = "";

      if (parameters.length > 0) {
        parametersCount += "?";

        for (int i = 1; i < parameters.length; i++) {
          parametersCount += ", ?";
        }
      }

      String function;
      function = new String("{? = call " + procName + "(" + parametersCount + ")  }");

      CallableStatement cstmt = (CallableStatement) connection.prepareCall(function);

      cstmt.registerOutParameter(1, Types.ARRAY, "SYS.PARAMETERS_VALUES_ARRAY");
      //cstmt.registerOutParameter(1, Types.ARRAY, "PARAMETERS_VALUES_ARRAY");

      //set parameters
      for (int i = 0; i < parameters.length; i++) {
        if (parameters[i] == null) {
          parameters[i] = "";
        }

        ejbLog.fine("\tparam " + (i + 2) + " " + parameters[i]);
        cstmt.setString(i + 2, parameters[i]);
      }

      ejbLog.fine("Try to EXECUTE " + function);
      //ajout de Hung
      boolean etat_exec_function= true;//cstmt.execute();
      // pour verifier si cstmt fonctionne ou pas?
      etat_exec_function= cstmt.execute();
      //ajout de Hung
      ejbLog.fine("etat_exec_function: "+ etat_exec_function);
      //

      Array result = cstmt.getArray(1);
      ejbLog.finest("result " + result.getArray());

      Object[] returnedValues = (Object[]) result.getArray();

      ejbLog.finest("result size " + returnedValues.length);

      STRUCT res;

      for (int i = 0; i < returnedValues.length; i++) {
        res = (STRUCT) returnedValues[i];

        Object label = res.getAttributes()[0];
        Object value = res.getAttributes()[1];

        ejbLog.finest("\t\tretour " + i + " : " + label + " = " + value);

        if (label != null) {
          if (value == null) {
            retour.put((String) label, "");
          }
          else {
            retour.put(label.toString(), value.toString());
          }
        }
      }

      cstmt.close();

      return retour;
    }
    catch (Exception ex) {
      if (procName.indexOf("doCommit") >= 0) {
        doCommit();
        retour.put(CODE_RETOUR, "0");
      }
      else {
        ejbLog.warning("ProcedureCallerBean.invokeActivitiesProcedure exception : " + ex.getMessage());
        retour.put(CODE_RETOUR, ex.getMessage());

        if (ejbLog.isDebugMode()) {
          ejbLog.severe(ex);
        }
      }

      return retour;
    }
  }

  @Override
  public void closeConnection() {
    try {
      ejbLog.fine("closing database connection");
      connection.close();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  @Override
  public void doCommit() {
    try {
      ejbLog.info("commit ......");
      connection.commit();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  @Override
  public void doRollback() {
    try {
      ejbLog.info("rollback ......");
      connection.rollback();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  private void setDebug(int debug) {
    try {
      //invokeActivitiesProcedure("ettp.setDebug", new String[] { Integer.toString(debug) });
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  @Override
  public void sendMail(String expediteur, String destinataire, String sujet, String message) {
    try {
      String procedure = new String("{call ettp.envoie_mail(?,?,?,?)}");

      CallableStatement cstmt = (CallableStatement) connection.prepareCall(procedure);
      cstmt.setString(1, expediteur);
      cstmt.setString(2, destinataire);
      cstmt.setString(3, sujet);
      cstmt.setString(4, message);
      ejbLog.fine(procedure + "\n\t" + expediteur + " " + destinataire + " " + sujet + " " + message);
      cstmt.execute();
      cstmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
    
}
