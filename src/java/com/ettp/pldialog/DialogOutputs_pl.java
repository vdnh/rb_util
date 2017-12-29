package com.ettp.pldialog;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DialogOutputs_pl
{
  // les parametres
  private Long DIALOG_OUTPUT_ID= null;
  private Long DIALOG_ID= null;
  private String ALLOW_RANDOM= null;
  private Long PRE_PROCEDURE_ID= null;
  private Long PRE_KO_PROCEDURE_ID= null;
  private Long MESS_NUMBER= null;
  private Long MESS_NUMBER_HELP= null;
  private Long POST_PROCEDURE_ID= null;
  private Long POST_KO_PROCEDURE_ID= null;
  private Long NEXT_DIALOG_OUTPUT_ID= null;
  private Long PRE_KO_NEXT_DIALOG_OUTPUT_ID= null;
  private Long POST_KO_NEXT_DIALOG_OUTPUT_ID= null;
  private String ALLOW_INPUT= null;
  private String GRAMMAR_FILE= null;
  private String GRAMMAR_POLICY= null;
  private Long TIMEOUT_VALUE= null;
  private Long TIMEOUT_ERROR_MESSAGE_ID= null;
  private Long INPUT_ERROR_MESSAGE_ID= null;
  private Long TOOEARLY_ERROR_MESSAGE_ID= null;
  private Long TOOMUCH_TIMEOUT_DO_ID= null;
  private Long TOOMUCH_INPUT_DO_ID= null;
  private Long TOOMUCH_TOOEARLY_DO_ID= null;
  private String LABEL= null;
  private String TYPE_DIALOG_OUTPUT= null;
  private String NEXT_DIALOG_OUTPUT_VAR= null;
  private String PRE_KO_NEXT_DIALOG_OUTPUT_VAR= null;
  private String POST_KO_NEXT_DIALOG_OUTPUT_VAR= null;
  private String DIALOG_OUTPUT_CODE= null;

  public DialogOutputs_pl()
  {
    DIALOG_OUTPUT_ID= null;
    DIALOG_ID= null;
    ALLOW_RANDOM= null;
    PRE_PROCEDURE_ID= null;
    PRE_KO_PROCEDURE_ID= null;
    MESS_NUMBER= null;
    MESS_NUMBER_HELP= null;
    POST_PROCEDURE_ID= null;
    POST_KO_PROCEDURE_ID= null;
    NEXT_DIALOG_OUTPUT_ID= null;
    PRE_KO_NEXT_DIALOG_OUTPUT_ID= null;
    POST_KO_NEXT_DIALOG_OUTPUT_ID= null;
    ALLOW_INPUT= null;
    GRAMMAR_FILE= null;
    GRAMMAR_POLICY= null;
    TIMEOUT_VALUE= null;
    TIMEOUT_ERROR_MESSAGE_ID= null;
    INPUT_ERROR_MESSAGE_ID= null;
    TOOEARLY_ERROR_MESSAGE_ID= null;
    TOOMUCH_TIMEOUT_DO_ID= null;
    TOOMUCH_INPUT_DO_ID= null;
    TOOMUCH_TOOEARLY_DO_ID= null;
    LABEL= null;
    TYPE_DIALOG_OUTPUT= null;
    NEXT_DIALOG_OUTPUT_VAR= null;
    PRE_KO_NEXT_DIALOG_OUTPUT_VAR= null;
    POST_KO_NEXT_DIALOG_OUTPUT_VAR= null;
    DIALOG_OUTPUT_CODE= null;

    //System.out.println("Hung - fini initialisation DialogOutputs_pl");
  }

 //**** Partie qui remplace DialogOutputsLocalHome ****//

  public DialogOutputs_pl findByPrimaryKey(Long primaryKey){
  //public boolean findByPrimaryKey(Long primaryKey){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.DIALOG_OUTPUTS where DIALOG_OUTPUT_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.DIALOG_OUTPUT_ID= resultat.getLong("DIALOG_OUTPUT_ID");
        this.MESS_NUMBER=resultat.getLong("MESS_NUMBER");
        this.ALLOW_RANDOM= resultat.getString("ALLOW_RANDOM");
        this.MESS_NUMBER_HELP=resultat.getLong("MESS_NUMBER_HELP");
        this.TIMEOUT_ERROR_MESSAGE_ID= resultat.getLong("TIMEOUT_ERROR_MESSAGE_ID");
        this.INPUT_ERROR_MESSAGE_ID= resultat.getLong("INPUT_ERROR_MESSAGE_ID");
        this.GRAMMAR_FILE= resultat.getString("GRAMMAR_FILE");
        this.GRAMMAR_POLICY= resultat.getString("GRAMMAR_POLICY");
        this.TIMEOUT_VALUE= resultat.getLong("TIMEOUT_VALUE");
        this.DIALOG_ID= resultat.getLong("DIALOG_ID");
        this.PRE_KO_NEXT_DIALOG_OUTPUT_ID= resultat.getLong("PRE_KO_NEXT_DIALOG_OUTPUT_ID");
        this.POST_KO_NEXT_DIALOG_OUTPUT_ID= resultat.getLong("POST_KO_NEXT_DIALOG_OUTPUT_ID");
        this.PRE_PROCEDURE_ID= resultat.getLong("PRE_PROCEDURE_ID");
        this.PRE_KO_PROCEDURE_ID= resultat.getLong("PRE_KO_PROCEDURE_ID");
        this.POST_PROCEDURE_ID= resultat.getLong("POST_PROCEDURE_ID");
        this.POST_KO_PROCEDURE_ID= resultat.getLong("POST_KO_PROCEDURE_ID");
        this.NEXT_DIALOG_OUTPUT_VAR= resultat.getString("NEXT_DIALOG_OUTPUT_VAR");
        this.NEXT_DIALOG_OUTPUT_ID= resultat.getLong("NEXT_DIALOG_OUTPUT_ID");
        this.TYPE_DIALOG_OUTPUT= resultat.getString("TYPE_DIALOG_OUTPUT");
        this.ALLOW_INPUT= resultat.getString("ALLOW_INPUT");

        statement.close();
        con.close();
        //System.out.println("DialogOutputs_pl - avoir trouve dialog_output");
        //return true;
        return this;
      }
      else{
        statement.close();
        con.close();
        //System.out.println("DialogOutputs_pl - ne pas avoir trouve dialog_output");
        //return false;
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("DialogOutputs_pl - il y a faute de connexion DB");
      //return false;
      return null;
    }
  }

  /*
  Collection findAll() throws FinderException;

  DialogOutputsLocal create(Long dialogOutputId) throws CreateException;
  */
  public Collection findAllRandomWithDialogId(Long dialogId)
  {
      List ls = new LinkedList();
      Collection collection= (Collection) ls;
      //DialogOutputs_pl do_pl=new DialogOutputs_pl();
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.DIALOG_OUTPUTS where DIALOG_ID = "+dialogId.toString()+" and ALLOW_RANDOM = 'YES'";
      ResultSet resultat = statement.executeQuery(sql);
      while(resultat.next()){
        DialogOutputs_pl do_pl=new DialogOutputs_pl();

        do_pl.DIALOG_OUTPUT_ID= resultat.getLong("DIALOG_OUTPUT_ID");
        do_pl.MESS_NUMBER=resultat.getLong("MESS_NUMBER");
        do_pl.ALLOW_RANDOM= resultat.getString("ALLOW_RANDOM");
        do_pl.MESS_NUMBER_HELP=resultat.getLong("MESS_NUMBER_HELP");
        do_pl.TIMEOUT_ERROR_MESSAGE_ID= resultat.getLong("TIMEOUT_ERROR_MESSAGE_ID");
        do_pl.INPUT_ERROR_MESSAGE_ID= resultat.getLong("INPUT_ERROR_MESSAGE_ID");
        do_pl.GRAMMAR_FILE= resultat.getString("GRAMMAR_FILE");
        do_pl.GRAMMAR_POLICY= resultat.getString("GRAMMAR_POLICY");
        do_pl.TIMEOUT_VALUE= resultat.getLong("TIMEOUT_VALUE");
        do_pl.DIALOG_ID= resultat.getLong("DIALOG_ID");
        do_pl.PRE_KO_NEXT_DIALOG_OUTPUT_ID= resultat.getLong("PRE_KO_NEXT_DIALOG_OUTPUT_ID");
        do_pl.POST_KO_NEXT_DIALOG_OUTPUT_ID= resultat.getLong("POST_KO_NEXT_DIALOG_OUTPUT_ID");
        do_pl.PRE_PROCEDURE_ID= resultat.getLong("PRE_PROCEDURE_ID");
        do_pl.PRE_KO_PROCEDURE_ID= resultat.getLong("PRE_KO_PROCEDURE_ID");
        do_pl.POST_PROCEDURE_ID= resultat.getLong("POST_PROCEDURE_ID");
        do_pl.POST_KO_PROCEDURE_ID= resultat.getLong("POST_KO_PROCEDURE_ID");
        do_pl.NEXT_DIALOG_OUTPUT_VAR= resultat.getString("NEXT_DIALOG_OUTPUT_VAR");
        do_pl.NEXT_DIALOG_OUTPUT_ID= resultat.getLong("NEXT_DIALOG_OUTPUT_ID");
        do_pl.TYPE_DIALOG_OUTPUT= resultat.getString("TYPE_DIALOG_OUTPUT");
        do_pl.ALLOW_INPUT= resultat.getString("ALLOW_INPUT");
        //ajouter dans un Collection
        collection.add(do_pl);
        //do_pl.finalize();
      }
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
    return collection;
  }


  //**** Partie qui remplace DialogOutputsLocal ****//
  public Long getDialogOutputId(){return this.DIALOG_OUTPUT_ID;}
  public Long getDialogId() {return this.DIALOG_ID;}
  //private String ALLOW_RANDOM= null;
  public Long getPreProcedureId() {return this.PRE_PROCEDURE_ID;}
  public Long getPreKoProcedureId() {return this.PRE_KO_PROCEDURE_ID;}
  public Long getMessNumber() {return this.MESS_NUMBER;}
  public Long getMessNumberHelp() {return this.MESS_NUMBER_HELP;}
  public Long getPostProcedureId() {return this.POST_PROCEDURE_ID;}
  public Long getPostKoProcedureId() {return this.POST_KO_PROCEDURE_ID;}
  public Long getNextDialogOutputId() {return this.NEXT_DIALOG_OUTPUT_ID;}
  public Long getPreKoNextDialogOutputId() {return this.PRE_KO_NEXT_DIALOG_OUTPUT_ID;}
  public Long getPostKoNextDialogOutputId() {return this.POST_KO_NEXT_DIALOG_OUTPUT_ID;}
  public String getAllowInput() {return this.ALLOW_INPUT;}
  public String getGrammarFile() {return this.GRAMMAR_FILE;}
  public String getGrammarPolicy() {return this.GRAMMAR_POLICY;}
  public Long getTimeoutValue(){return this.TIMEOUT_VALUE;}
  public Long getTimeoutErrorMessageId(){return this.TIMEOUT_ERROR_MESSAGE_ID;}
  public Long getInputErrorMessageId() {return this.INPUT_ERROR_MESSAGE_ID;}
  //private Long TOOEARLY_ERROR_MESSAGE_ID= null;
  //private Long TOOMUCH_TIMEOUT_DO_ID= null;
  //private Long TOOMUCH_INPUT_DO_ID= null;
  //private Long TOOMUCH_TOOEARLY_DO_ID= null;
  //private String LABEL= null;
  public String getTypeDialogOutput() {return this.TYPE_DIALOG_OUTPUT;}
  public String getNextDialogOutputVar() {return this.NEXT_DIALOG_OUTPUT_VAR;}
  //private String PRE_KO_NEXT_DIALOG_OUTPUT_VAR= null;
  //private String POST_KO_NEXT_DIALOG_OUTPUT_VAR= null;
  //private String DIALOG_OUTPUT_CODE= null;
}