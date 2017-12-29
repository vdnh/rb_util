package com.ettp.plsession;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OnlineTelServices_pl
{
  //les parametres
  private String SERVICE_CODE= null;
  private Long DIALOG_ID= null;
  private String DIALOG_CODE= null;
  private String PHONE_NUMBER= null;

  public OnlineTelServices_pl()
  {
    this.SERVICE_CODE= null;
    this.DIALOG_ID= null;
    this.DIALOG_CODE=null;
    this.PHONE_NUMBER= null;
    System.out.println("OnlineTelServices_pl - Fini initialisation OnlineTelService_pl");
  }

  public OnlineTelServices_pl findByServicePhoneNumber(String phoneNumber)
  {
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from ettp.ONLINE_TEL_SERVICES where PHONE_NUMBER = '"+phoneNumber+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.SERVICE_CODE= resultat.getString("SERVICE_CODE");
        this.DIALOG_ID= new Long(resultat.getLong("DIALOG_ID"));
        this.DIALOG_CODE= resultat.getString("DIALOG_CODE");
        this.PHONE_NUMBER= resultat.getString("PHONE_NUMBER");

        statement.close();
        con.close();
        System.out.println("OnlineTelServices_pl - avoir trouve service a partir de CalledNumber");
        return this;
      }
      else{
        statement.close();
        con.close();
        System.out.println("OnlineTelServices_pl - ne pas avoir trouve service a partir de CalledNumber");
        return null;
      }
    }
    catch(Exception ex) {
      System.out.println("OnlineTelServices_pl - Il y a probleme de connection DB avec OnlineTelServices_pl.");
      ex.printStackTrace();
      return null;
    }
  }

  public String getPhoneNumber(){return this.PHONE_NUMBER;}

  public String getDialogCode(){return this.DIALOG_CODE;}

  public String getServiceCode(){return this.SERVICE_CODE;}

  public Long getDialogId(){return this.DIALOG_ID;}

}