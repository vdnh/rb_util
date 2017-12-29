package com.ettp.plsession;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;

public class UsersPersons_pl
{
  // les parametres
  //user_person_id, country_code, language_code, mobilephone
  private Long USER_PERSON_ID= null;
  private String COUNTRY_CODE= null;
  private String LANGUAGE_CODE= null;
  private String MOBILE_PHONE= null;
  public UsersPersons_pl()
  {
    this.USER_PERSON_ID= null;
    this.COUNTRY_CODE= null;
    this.LANGUAGE_CODE= null;
    this.MOBILE_PHONE= null;
    //System.out.println("UsersPersons_pl - Fini initialisation UsersPersons_pl");
  }

  //**** partie qui remplace UsersPersonsLocalHome ****//
  public UsersPersons_pl findByMobilePhone(String mobilePhone)
  {
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from ettp.ALL_USERS_PERSONS where MOBILE_PHONE = '"+mobilePhone.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.USER_PERSON_ID= new Long(resultat.getLong("USER_PERSON_ID"));
        this.COUNTRY_CODE= resultat.getString("COUNTRY_CODE");
        this.LANGUAGE_CODE= resultat.getString("LANGUAGE_CODE");
        this.MOBILE_PHONE= resultat.getString("MOBILE_PHONE");

        statement.close();
        con.close();
        //System.out.println("UsersPersons_pl - avoir trouve person a partir de son mobilephone");
        return this;
      }
      else{
        statement.close();
        con.close();
        //System.out.println("UsersPersons_pl - ne pas avoir trouve person a partir de ce mobilephone");
        return null;
      }
    }
    catch(Exception ex) {
      System.out.println("UsersPersons_pl - Il y a probleme de connection DB avec UsersPersonS_pl.");
      ex.printStackTrace();
      return null;
    }
    //return this;
  }

  //**** partie qui remplace UsersPersonsLocal ****//
  public Long getUserPersonId(){return this.USER_PERSON_ID;}
  public String getCountryCode(){return this.COUNTRY_CODE;}
  public String getMobilePhone(){return this.MOBILE_PHONE;}
  public String getLanguageCode(){return this.LANGUAGE_CODE;}
}