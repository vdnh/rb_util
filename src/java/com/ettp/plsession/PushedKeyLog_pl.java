package com.ettp.plsession;
//import com.ettp.ejb.util.sequence.Sequence;
import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.sequence.SequenceRemote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.ejb.CreateException;
//import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.rmi.PortableRemoteObject;
//import javax.sql.DataSource;

public class PushedKeyLog_pl 
{
  // les paramtres
  private Long CALL_LOG_ID= null;
  private Timestamp PUSHING_DATE= null;
  private String PUSHED_KEY= null;
  private Long DIALOG_ID= null;
  private Long DIALOG_OUTPUT_ID= null;
  private String VALID= null;
  private Long RECORDING_LOG_ID= null;
  private Long PUSHED_KEY_LOG_ID= null;
  
  public PushedKeyLog_pl()
  {
    this.CALL_LOG_ID= null;
    this.PUSHING_DATE= null;
    this.PUSHED_KEY= null;
    this.DIALOG_ID= null;
    this.DIALOG_OUTPUT_ID= null;
    this.VALID= null;
    this.RECORDING_LOG_ID= null;
    this.PUSHED_KEY_LOG_ID=  null;
    
    //System.out.println("PushedKeyLog_pl - Fini initialisation PushedKeyLog_pl");
  }
  
  // partie qui remplace PushedKeyLogLocalHome
  public PushedKeyLog_pl create(Long call_log_id, Timestamp pushing_date, String pushedKeys, Long dialogId,
    Long dialogOutputId) throws CreateException, RemoteException, NamingException {
    Long pklId = null;
    InitialContext ic = new InitialContext();
    SequenceRemote s = (SequenceRemote)ic.lookup("sequence");
    pklId = new Long(s.getNextValue("robot_LOG_SEQ"));
    //Creer en memme temps tous les champs
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql = "insert into robot.PUSHED_KEY_LOG (PUSHED_KEY_LOG_ID, CALL_LOG_ID, PUSHING_DATE,"+
      " PUSHED_KEY, DIALOG_ID, DIALOG_OUTPUT_ID, VALID) values (?, ?, ?, ?, ?, ?, ?)";      
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, pklId.intValue());
      statement.setLong(2, call_log_id.longValue());
      statement.setTimestamp(3, pushing_date);
      statement.setString(4, pushedKeys);
      statement.setLong(5, dialogId.longValue());
      statement.setLong(6, dialogOutputId.longValue());
      statement.setString(7,"N");
      
      statement.executeUpdate();
      con.commit();
      statement.close();
      con.close();
      //System.out.println("PushedKeyLog_pl - avoir cree PushedKey Log sans recordingLogId");      
    }
    catch(Exception ex) {
      System.out.println("PushedKeyLog_pl - "+ex.getMessage());
      //System.out.println("PushedKeyLog_pl - avoir problem de connection DB de PushedKeyLog_pl");
    }    
    this.PUSHED_KEY_LOG_ID= pklId;
    this.CALL_LOG_ID= call_log_id;
    this.PUSHING_DATE= pushing_date;
    this.PUSHED_KEY= pushedKeys;
    this.DIALOG_ID= dialogId;
    this.DIALOG_OUTPUT_ID= dialogOutputId;
    this.VALID="N";
    this.RECORDING_LOG_ID= null;
    
    return this;
  }  
  
  public PushedKeyLog_pl create(Long call_log_id, Timestamp pushing_date, String pushedKeys, Long dialogId,
    Long dialogOutputId, Long recordingLogId) throws CreateException, RemoteException, NamingException {
    Long pklId = null;
    InitialContext ic = new InitialContext();
    SequenceRemote s = (SequenceRemote) ic.lookup("sequence");
    pklId = new Long(s.getNextValue("robot_LOG_SEQ"));
    //Creer en memme temps tous les champs
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql = "insert into robot.PUSHED_KEY_LOG (PUSHED_KEY_LOG_ID, CALL_LOG_ID, PUSHING_DATE,"+
      " PUSHED_KEY, DIALOG_ID, DIALOG_OUTPUT_ID, VALID, RECORDING_LOG_ID) values (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, pklId.intValue());
      statement.setLong(2, call_log_id.longValue());
      statement.setTimestamp(3, pushing_date);
      statement.setString(4, pushedKeys);
      statement.setLong(5, dialogId.longValue());
      statement.setLong(6, dialogOutputId.longValue());
      statement.setString(7,"N");
      statement.setLong(8, recordingLogId.longValue());
      
      statement.executeUpdate();
      con.commit();
      statement.close();
      con.close();
      //System.out.println("PushedKeyLog_pl - avoir cree PushedKey Log avec recordingLogId");      
    }
    catch(Exception ex) {
      System.out.println("PushedKeyLog_pl - "+ex.getMessage());
      //System.out.println("PushedKeyLog_pl - avoir problem de connection DB de PushedKeyLog_pl");
    }    
    this.PUSHED_KEY_LOG_ID= pklId;
    this.CALL_LOG_ID= call_log_id;
    this.PUSHING_DATE= pushing_date;
    this.PUSHED_KEY= pushedKeys;
    this.DIALOG_ID= dialogId;
    this.DIALOG_OUTPUT_ID= dialogOutputId;
    this.VALID="N";
    this.RECORDING_LOG_ID= recordingLogId;
    
    return this;
  }    
  // partie qui remplace PushedKeyLogLocal
  public Long getCallLogId(){return this.CALL_LOG_ID;}

  public Timestamp getPushingDate(){return this.PUSHING_DATE;}

  public String getPushedKey(){return this.PUSHED_KEY;}

  public void setPushedKey(String pushedKey){;}

  public Long getDialogId(){return this.DIALOG_ID;}

  public void setDialogId(Long dialogId){;}

  public Long getDialogOutputId(){return this.DIALOG_OUTPUT_ID;}

  public void setDialogOutputId(Long dialogOutputId){;}

  public String getValid(){return this.VALID;}

  public void setValid(String valid){    
    this.VALID= valid;    
    
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql = "update robot.PUSHED_KEY_LOG set VALID='"+valid+"' where PUSHED_KEY_LOG_ID= "+
      this.PUSHED_KEY_LOG_ID.toString();      
      PreparedStatement statement = con.prepareStatement(sql);
      statement.executeUpdate();
      con.commit();
      statement.close();
      con.close();
      //System.out.println("PushedKeyLog_pl - avoir ecrit champs VALID");      
    }
    catch(Exception ex) {
      System.out.println("PushedKeyLog_pl - "+ex.getMessage());
      //System.out.println("Hung - avoir problem d'ecrire Pushed_Key.Valid");
    }        
    
  }

  public Long getRecordingLogId(){return this.RECORDING_LOG_ID;}

  public void setRecordingLogId(Long recordingLogId){;}

  public Long getPushedKeyLogId(){return this.PUSHED_KEY_LOG_ID;}
  
  
}