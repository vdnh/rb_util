/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.pldialog;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Dialogs_pl
{
  // les parametres
  private Long DIALOG_ID= null;
  private Long FIRST_DIALOG_OUTPUT_ID= null;
  private String DIALOG_AUTHOR= null;
  private String DIALOG_NAME= null;
  private String DIALOG_DESCRIPTION= null;
  private Timestamp CREATED_DATE= null;
  private Long STATUS_CODE_VALUE_ID= null;
  private Long MAX_RANDOM= null;
  private Long DFLT_INPUT_ERROR_MESSAGE_ID= null;
  private Long DFLT_TIMEOUT_ERROR_MESSAGE_ID= null;
  private Long DFLT_TOOEARLY_ERROR_MESSAGE_ID= null;
  private Long DFLT_TIMEOUT_VALUE= null;
  private String DFLT_LANGUAGE= null;
  private String ORIGIN_GROUP_CODE= null;
  private Long PUSHED_KEYS_LOG_LEVEL= null;
  private Long PROCEDURES_LOG_LEVEL= null;
  private Long VERSION_NUMBER= null;
  //
  public Dialogs_pl()
  {
    DIALOG_ID= null;
    FIRST_DIALOG_OUTPUT_ID= null;
    DIALOG_AUTHOR= null;
    DIALOG_NAME= null;
    DIALOG_DESCRIPTION= null;
    CREATED_DATE= null;
    STATUS_CODE_VALUE_ID= null;
    MAX_RANDOM= null;
    DFLT_INPUT_ERROR_MESSAGE_ID= null;
    DFLT_TIMEOUT_ERROR_MESSAGE_ID= null;
    DFLT_TOOEARLY_ERROR_MESSAGE_ID= null;
    DFLT_TIMEOUT_VALUE= null;
    DFLT_LANGUAGE= null;
    ORIGIN_GROUP_CODE= null;
    PUSHED_KEYS_LOG_LEVEL= null;
    PROCEDURES_LOG_LEVEL= null;
    VERSION_NUMBER= null;

    //System.out.println("Dialogs_pl - Fini initialisation Dialogs_pl");
  }

  //**** partie qui remplace DialogsLocalHome ****//

  //public boolean findByPrimaryKey(Long primaryKey)
  public Dialogs_pl findByPrimaryKey(Long primaryKey)
  {
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.DIALOGS where DIALOG_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.DIALOG_ID= resultat.getLong("DIALOG_ID");
        this.FIRST_DIALOG_OUTPUT_ID=resultat.getLong("FIRST_DIALOG_OUTPUT_ID");
        this.DIALOG_AUTHOR=resultat.getString("DIALOG_AUTHOR");
        this.DIALOG_NAME= resultat.getString("DIALOG_NAME");
        this.DIALOG_DESCRIPTION= resultat.getString("DIALOG_DESCRIPTION");
        this.CREATED_DATE= resultat.getTimestamp("CREATED_DATE");
        this.STATUS_CODE_VALUE_ID= resultat.getLong("STATUS_CODE_VALUE_ID");
        this.MAX_RANDOM= resultat.getLong("MAX_RANDOM");
        this.DFLT_INPUT_ERROR_MESSAGE_ID= resultat.getLong("DFLT_INPUT_ERROR_MESSAGE_ID");
        this.DFLT_TIMEOUT_ERROR_MESSAGE_ID= resultat.getLong("DFLT_TIMEOUT_ERROR_MESSAGE_ID");
        this.DFLT_TOOEARLY_ERROR_MESSAGE_ID= resultat.getLong("DFLT_TOOEARLY_ERROR_MESSAGE_ID");
        this.DFLT_TIMEOUT_VALUE= resultat.getLong("DFLT_TIMEOUT_VALUE");
        this.DFLT_LANGUAGE= resultat.getString("DFLT_LANGUAGE");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.PUSHED_KEYS_LOG_LEVEL= resultat.getLong("PUSHED_KEYS_LOG_LEVEL");
        this.PROCEDURES_LOG_LEVEL= resultat.getLong("PROCEDURES_LOG_LEVEL");
        this.VERSION_NUMBER= resultat.getLong("VERSION_NUMBER");

        statement.close();
        con.close();
        //System.out.println("Dialogs_pl - avoir trouve dialog");
        //return true;
        return this;
      }
      else{
        statement.close();
        con.close();
        //System.out.println("Dialogs_pl - ne pas avoir trouve dialog");
        //return false;
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("Dialogs_pl - il y a faute de connexion DB");
      //return false;
      return null;
    }
  }
  /*
  Collection findAll();
  */


  //**** partie qui remplace DialogsLocal ****//
  public Long getDialogId(){return this.DIALOG_ID;}

  public Long getFirstDialogOutputId(){return this.FIRST_DIALOG_OUTPUT_ID;}

  //void setFirstDialogOutputId(Long firstDialogOutputId);

  public String getDialogAuthor(){return this.DIALOG_AUTHOR;}

  //void setDialogAuthor(String dialogAuthor);

  public String getDialogName(){return this.DIALOG_NAME;}

  //void setDialogName(String dialogName);

  public String getDialogDescription(){return this.DIALOG_DESCRIPTION;}

  //void setDialogDescription(String dialogDescription);

  public Timestamp getCreatedDate(){return this.CREATED_DATE;}

  //void setCreatedDate(Timestamp createdDate);

  public Long getStatusCodeValueId(){return this.STATUS_CODE_VALUE_ID;}

  //void setStatusCodeValueId(Long statusCodeValueId);

  public Long getMaxRandom(){return this.MAX_RANDOM;}

  //void setMaxRandom(Long maxRandom);

  public Long getDfltInputErrorMessageId(){return this.DFLT_INPUT_ERROR_MESSAGE_ID;}

  //void setDfltInputErrorMessageId(Long dfltInputErrorMessageId);

  public Long getDfltTimeoutErrorMessageId(){return this.DFLT_TIMEOUT_ERROR_MESSAGE_ID;}

  //void setDfltTimeoutErrorMessageId(Long dfltTimeoutErrorMessageId);

  public Long getDfltTooearlyErrorMessageId(){return this.DFLT_TOOEARLY_ERROR_MESSAGE_ID;}

  //void setDfltTooearlyErrorMessageId(Long dfltTooearlyErrorMessageId);

  public Long getDfltTimeoutValue(){return this.DFLT_TIMEOUT_VALUE;}

  //void setDfltTimeoutValue(Long dfltTimeoutValue);

  public String getDfltLanguage(){return this.DFLT_LANGUAGE;}

  //void setDfltLanguage(String dfltLanguage);

  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}

  //void setOriginGroupCode(String originGroupCode);

  public Long getPushedKeysLogLevel(){return this.PUSHED_KEYS_LOG_LEVEL;}

  //void setPushedKeysLogLevel(Long pushedKeysLogLevel);

  public Long getProceduresLogLevel(){return this.PROCEDURES_LOG_LEVEL;}

  //void setProceduresLogLevel(Long proceduresLogLevel);
}