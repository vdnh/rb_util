package com.ettp.plsession;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;

public class Services_pl
{
  //les parametres
  //service_id, module_id, service_code, dialog_id
  private Long SERVICE_ID= null;
  private Long MODULE_ID= null;
  private String SERVICE_CODE= null;
  private Long DIALOG_ID= null;
  private String SERVICE_PHONE_NUMBER= null;

  public Services_pl()
  {
    this.SERVICE_ID= null;
    this.MODULE_ID= null;
    this.SERVICE_CODE= null;
    this.DIALOG_ID= null;
    this.SERVICE_PHONE_NUMBER= null;
    //System.out.println("Services_pl - Fini initialisation Service_pl");
  }

  //**** partie qui remplace ServicesLocalHome ****//
  public Services_pl findByServicePhoneNumber(String phoneNumber)
  {
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from ettp.ALL_TOP_SERVICES where SERVICE_PHONE_NUMBER = '"+phoneNumber+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.SERVICE_ID= new Long(resultat.getLong("SERVICE_ID"));
        this.MODULE_ID= new Long(resultat.getLong("MODULE_ID"));
        this.SERVICE_CODE= resultat.getString("SERVICE_CODE");
        this.DIALOG_ID= new Long(resultat.getLong("DIALOG_ID"));
        this.SERVICE_PHONE_NUMBER= resultat.getString("SERVICE_PHONE_NUMBER");

        statement.close();
        con.close();
        //System.out.println("Services_pl - avoir trouve service a partir de CalledNumber");
        return this;
      }
      else{
        statement.close();
        con.close();
        //System.out.println("Services_pl - ne pas avoir trouve service a partir de CalledNumber");
        return null;
      }
    }
    catch(Exception ex) {
      System.out.println("Services_pl - Il y a probleme de connection DB avec Services_pl.");
      ex.printStackTrace();
      return null;
    }
  }
  //**** partie qui remplace ServicesLocal ****//
  public Long getServiceId(){return this.SERVICE_ID;}

  public Long getModuleId(){return this.MODULE_ID;}

  public String getServiceCode(){return this.SERVICE_CODE;}

  public Long getDialogId(){return this.DIALOG_ID;}

}