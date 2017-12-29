package com.ettp.pldialog;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DialogInputs_pl
{
  //les parametres
  private Long DIALOG_INPUT_ID= null;
  private Long DIALOG_ID= null;
  private Long DIALOG_OUTPUT_ID= null;
  private String ANSWER_LABEL= null;
  private String RECORDING_TIMEOUT= null;
  private Long RECORDING_ERROR_MESSAGE_ID= null;
  private Long RECORDING_TIMEOUT_ERROR_MESSAG= null;
  private String RECORDING_DESTINATION= null;
  private Long POST_PROCEDURE_ID= null;
  private Long POST_KO_PROCEDURE_ID= null;
  private Long NEXT_DIALOG_OUTPUT_ID= null;
  private Long POST_KO_NEXT_DIALOG_OUTPUT_ID= null;
  private String DTMF_MASK= null;
  private Long VAL_MIN= null;
  private Long VAL_MAX= null;
  private Long MESS_NUMBER= null;
  private String VARIABLE_FILTRE= null;
  private Long AFFECT_PARAMETER_ID= null;
  private String NEXT_DIALOG_OUTPUT_VAR= null;
  private String POST_KO_NEXT_DIALOG_OUTPUT_VAR= null;
  private String CONFIRM_RECORDING= null;
  private String TOFILE= null;
  private String FILE_WAV= null;
  private String MESSAGE_CODE= null;
  private String DIALOG_OUTPUT_CODE= null;

  public DialogInputs_pl()
  {
    DIALOG_INPUT_ID= null;
    DIALOG_ID= null;
    DIALOG_OUTPUT_ID= null;
    ANSWER_LABEL= null;
    RECORDING_TIMEOUT= null;
    RECORDING_ERROR_MESSAGE_ID= null;
    RECORDING_TIMEOUT_ERROR_MESSAG= null;
    RECORDING_DESTINATION= null;
    POST_PROCEDURE_ID= null;
    POST_KO_PROCEDURE_ID= null;
    NEXT_DIALOG_OUTPUT_ID= null;
    POST_KO_NEXT_DIALOG_OUTPUT_ID= null;
    DTMF_MASK= null;
    VAL_MIN= null;
    VAL_MAX= null;
    MESS_NUMBER= null;
    VARIABLE_FILTRE= null;
    AFFECT_PARAMETER_ID= null;
    NEXT_DIALOG_OUTPUT_VAR= null;
    POST_KO_NEXT_DIALOG_OUTPUT_VAR= null;
    CONFIRM_RECORDING= null;
    TOFILE= null;
    FILE_WAV= null;
    MESSAGE_CODE= null;
    DIALOG_OUTPUT_CODE= null;

    //System.out.println("Hung - Fini initialisation DialogInputs_pl");
  }

  //**** partie qui remplace DialogInputsLocalHome ****//

  // public DialogInputsLocal findByPrimaryKey(Long primaryKey)
  //public boolean findByPrimaryKey(Long primaryKey){
  public DialogInputs_pl findByPrimaryKey(Long primaryKey){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      //String sql= "select * from robot.DIALOG_INPUTS where DIALOG_INPUT_ID = '"+primaryKey.toString()+"'";
      String sql= "select * from robot.DIALOG_INPUTS where DIALOG_INPUT_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.DIALOG_INPUT_ID= resultat.getLong("DIALOG_INPUT_ID");
        this.DIALOG_ID=resultat.getLong("DIALOG_ID");
        this.DIALOG_OUTPUT_ID=resultat.getLong("DIALOG_OUTPUT_ID");
        this.ANSWER_LABEL= resultat.getString("ANSWER_LABEL");
        this.RECORDING_TIMEOUT= resultat.getString("RECORDING_TIMEOUT");
        this.RECORDING_ERROR_MESSAGE_ID= resultat.getLong("RECORDING_ERROR_MESSAGE_ID");
        this.RECORDING_TIMEOUT_ERROR_MESSAG= resultat.getLong("RECORDING_TIMEOUT_ERROR_MESSAG");
        this.RECORDING_DESTINATION= resultat.getString("RECORDING_DESTINATION");
        this.POST_PROCEDURE_ID= resultat.getLong("POST_PROCEDURE_ID");
        this.POST_KO_PROCEDURE_ID= resultat.getLong("POST_KO_PROCEDURE_ID");
        this.NEXT_DIALOG_OUTPUT_ID= resultat.getLong("NEXT_DIALOG_OUTPUT_ID");
        this.POST_KO_NEXT_DIALOG_OUTPUT_ID= resultat.getLong("POST_KO_NEXT_DIALOG_OUTPUT_ID");
        this.DTMF_MASK= resultat.getString("DTMF_MASK");
        this.VAL_MIN= resultat.getLong("VAL_MIN");
        this.VAL_MAX= resultat.getLong("VAL_MAX");
        this.MESS_NUMBER= resultat.getLong("MESS_NUMBER");
        this.VARIABLE_FILTRE= resultat.getString("VARIABLE_FILTRE");
        this.AFFECT_PARAMETER_ID= resultat.getLong("AFFECT_PARAMETER_ID");
        this.NEXT_DIALOG_OUTPUT_VAR= resultat.getString("NEXT_DIALOG_OUTPUT_VAR");
        this.POST_KO_NEXT_DIALOG_OUTPUT_VAR= resultat.getString("POST_KO_NEXT_DIALOG_OUTPUT_VAR");
        this.CONFIRM_RECORDING= resultat.getString("CONFIRM_RECORDING");
        this.TOFILE= resultat.getString("TOFILE");
        this.FILE_WAV= resultat.getString("FILE_WAV");
        this.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");
        this.DIALOG_OUTPUT_CODE= resultat.getString("DIALOG_OUTPUT_CODE");

        statement.close();
        con.close();
        //System.out.println("DialogInputs_pl - avoir trouve dialog_input");
        //return true;
        return this;
      }
      else{
        statement.close();
        con.close();
        //System.out.println("DialogInputs_pl - ne pas avoir trouve dialog_input");
        //return false;
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("DialogInputs_pl - il y a faute de connexion DB");
      //return false;
      return null;
    }
  }
  /*
  public Collection findAll() throws FinderException;

  DialogInputsLocal create(Long dialogInputId, Long dialogId, Long dialogOutputId, String confirmRecording)
  {}
  */

  public Collection findByDialogOutputId(Long dialogOutputId)
  {
      List ls = new LinkedList();
      Collection collection= (Collection) ls;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      //String sql= "select * from robot.DIALOG_INPUTS where DIALOG_OUTPUT_ID = '"+dialogOutputId.toString()+"'";
      String sql= "select * from robot.DIALOG_INPUTS where DIALOG_OUTPUT_ID = '"+dialogOutputId.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      //DialogInputs_pl di_pl=new DialogInputs_pl();
      while(resultat.next()){
        DialogInputs_pl di_pl=new DialogInputs_pl();

        di_pl.DIALOG_INPUT_ID= resultat.getLong("DIALOG_INPUT_ID");
        di_pl.DIALOG_ID=resultat.getLong("DIALOG_ID");
        di_pl.DIALOG_OUTPUT_ID=resultat.getLong("DIALOG_OUTPUT_ID");
        di_pl.ANSWER_LABEL= resultat.getString("ANSWER_LABEL");
        di_pl.RECORDING_TIMEOUT= resultat.getString("RECORDING_TIMEOUT");
        di_pl.RECORDING_ERROR_MESSAGE_ID= resultat.getLong("RECORDING_ERROR_MESSAGE_ID");
        di_pl.RECORDING_TIMEOUT_ERROR_MESSAG= resultat.getLong("RECORDING_TIMEOUT_ERROR_MESSAG");
        di_pl.RECORDING_DESTINATION= resultat.getString("RECORDING_DESTINATION");
        di_pl.POST_PROCEDURE_ID= resultat.getLong("POST_PROCEDURE_ID");
        di_pl.POST_KO_PROCEDURE_ID= resultat.getLong("POST_KO_PROCEDURE_ID");
        di_pl.NEXT_DIALOG_OUTPUT_ID= resultat.getLong("NEXT_DIALOG_OUTPUT_ID");
        di_pl.POST_KO_NEXT_DIALOG_OUTPUT_ID= resultat.getLong("POST_KO_NEXT_DIALOG_OUTPUT_ID");
        di_pl.DTMF_MASK= resultat.getString("DTMF_MASK");
        di_pl.VAL_MIN= resultat.getLong("VAL_MIN");
        di_pl.VAL_MAX= resultat.getLong("VAL_MAX");
        di_pl.MESS_NUMBER= resultat.getLong("MESS_NUMBER");
        di_pl.VARIABLE_FILTRE= resultat.getString("VARIABLE_FILTRE");
        di_pl.AFFECT_PARAMETER_ID= resultat.getLong("AFFECT_PARAMETER_ID");
        di_pl.NEXT_DIALOG_OUTPUT_VAR= resultat.getString("NEXT_DIALOG_OUTPUT_VAR");
        di_pl.POST_KO_NEXT_DIALOG_OUTPUT_VAR= resultat.getString("POST_KO_NEXT_DIALOG_OUTPUT_VAR");
        di_pl.CONFIRM_RECORDING= resultat.getString("CONFIRM_RECORDING");
        di_pl.TOFILE= resultat.getString("TOFILE");
        di_pl.FILE_WAV= resultat.getString("FILE_WAV");
        di_pl.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");
        di_pl.DIALOG_OUTPUT_CODE= resultat.getString("DIALOG_OUTPUT_CODE");

        //ajouter dans un Collection
        collection.add(di_pl);
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


  //**** partie qui remplace DialogInputsLocal ****//
  public Long getDialogInputId(){return this.DIALOG_INPUT_ID;}
  public Long getDialogId(){return this.DIALOG_ID;}
  //void setDialogId(Long dialogId);
  public Long getDialogOutputId(){return this.DIALOG_OUTPUT_ID;}
  //void setDialogOutputId(Long dialogOutputId);
  public String getAnswerLabel(){return this.ANSWER_LABEL;}
  //void setAnswerLabel(String answerLabel);
  public String getRecordingTimeout(){return this.RECORDING_TIMEOUT;}
  //void setRecordingTimeout(String recordingTimeout);
  public Long getRecordingErrorMessageId(){return this.RECORDING_ERROR_MESSAGE_ID;}
  //void setRecordingErrorMessageId(Long recordingErrorMessageId);
  public Long getRecordingTimeoutErrorMessag(){return this.RECORDING_TIMEOUT_ERROR_MESSAG;}
  //void setRecordingTimeoutErrorMessag(Long recordingTimeoutErrorMessag);
  public String getRecordingDestination(){return this.RECORDING_DESTINATION;}
  //void setRecordingDestination(String recordingDestination);
  public Long getPostProcedureId(){return this.POST_PROCEDURE_ID;}
  //void setPostProcedureId(Long postProcedureId);
  public Long getPostKoProcedureId(){return this.POST_KO_PROCEDURE_ID;}
  //void setPostKoProcedureId(Long postKoProcedureId);
  public Long getNextDialogOutputId(){return this.NEXT_DIALOG_OUTPUT_ID;}
  //void setNextDialogOutputId(Long nextDialogOutputId);
  public Long getPostKoNextDialogOutputId(){return this.POST_KO_NEXT_DIALOG_OUTPUT_ID;}
  //void setPostKoNextDialogOutputId(Long postKoNextDialogOutputId);
  public String getDtmfMask(){return this.DTMF_MASK;}
  //void setDtmfMask(String dtmfMask);
  public Long getValMin(){return this.VAL_MIN;}
  //void setValMin(Long valMin);
  public Long getValMax(){return this.VAL_MAX;}
  //void setValMax(Long valMax);
  public Long getMessNumber(){return this.MESS_NUMBER;}
  //void setMessNumber(Long messNumber);
  public String getVariableFiltre(){return this.VARIABLE_FILTRE;}
  //void setVariableFiltre(String variableFiltre);
  public Long getAffectParameterId(){return this.AFFECT_PARAMETER_ID;}
  //void setAffectParameterId(Long affectParameterId);
  public String getNextDialogOutputVar(){return this.NEXT_DIALOG_OUTPUT_VAR;}
  //void setNextDialogOutputVar(String nextDialogOutputVar);
  public String getPostKoNextDialogOutputVar(){return this.POST_KO_NEXT_DIALOG_OUTPUT_VAR;}
  //void setPostKoNextDialogOutputVar(String postKoNextDialogOutputVar);
  public String getConfirmRecording(){return this.CONFIRM_RECORDING;}
  //void setConfirmRecording(String confirmRecording);
  public String getTofile(){return this.TOFILE;}
  //void setTofile(String tofile);
  public String getFileWav(){return this.FILE_WAV;}
  //void setFileWav(String fileWav);
  public String getMessageCode(){return this.MESSAGE_CODE;}
  //void setMessageCode(String messageCode);
}