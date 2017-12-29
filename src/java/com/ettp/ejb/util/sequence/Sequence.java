/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.util.sequence;

import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.EJBLog;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
//import javax.annotation.PostConstruct;
import javax.ejb.AfterBegin;
//import javax.ejb.BeforeCompletion;
//import javax.ejb.PostActivate;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

/**
 *
 * @author vdnh
 */
@Stateful (mappedName = "squence")
public class Sequence implements SequenceRemote {
    protected SessionContext sessionContext;
    private EJBLog ejbLog;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
    public void unsetSessionContext(){
        sessionContext = null;
    }
    
    @AfterBegin
     void initLogger(String sessionId) {
        ejbLog = new EJBLog(sessionId);
    }
      
    public int getNextValue(String keyName) throws RemoteException {
    int returnValue;

    String selectQuery = new StringBuffer("SELECT " + keyName + ".nextval ").append("FROM  DUAL").toString();

    //String selectQuery = keyName;
    /*            new StringBuffer("SELECT  *  FROM PERSON")
                         .append(" WHERE PERSONID=1234")
                         .toString();
    */
    System.out.println("SEQUENCE : Sequence query : " + selectQuery);

    try {      
      Connection con = OracleJDBCConnection.getJDBCConnection();

      PreparedStatement pstmt = con.prepareStatement(selectQuery);

      //pstmt.setString(1, keyName);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        returnValue = rs.getInt("NEXTVAL");
        System.out.println("SEQUENCE : New sequence created : " + keyName + " = " + returnValue);
      }
      else {
        throw new RemoteException("Could not obtain a key for : " + keyName);
      }

      pstmt.close();
      con.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new RemoteException(ex.getMessage());
    }

    return returnValue;
  }

//
  public int getNextValueByHashval(String keyName) throws RemoteException {
    int returnValue;

    String function= "{? = call ettp.hashval(?) }";

    System.out.println("SEQUENCE : Fonction Appel : " + function);

    try {      
      Connection con = OracleJDBCConnection.getJDBCConnection();

      CallableStatement cstmt = (CallableStatement) con.prepareCall(function);
      cstmt.setString(2, keyName);

      cstmt.registerOutParameter(1, Types.INTEGER);
      ResultSet rs = cstmt.executeQuery();

      returnValue = cstmt.getInt(1); //.getString(1);
      System.out.println("SEQUENCE : New ID created : " + keyName + " = " + returnValue);


      cstmt.close();
      con.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new RemoteException(ex.getMessage());
    }

    return returnValue;
  }
}
