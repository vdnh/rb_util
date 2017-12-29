package com.ettp.plsession;
import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.sequence.SequenceRemote;
//import com.ettp.ejb.util.sequence.SequenceHome;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.CreateException;
//import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.rmi.PortableRemoteObject;
//import javax.sql.DataSource;

public class RecordingLog_pl 
{
  // les parametres
  private Long RECORDING_LOG_ID= null;
  private Long RECORDING_BLOB_ID= null;
  private Long CALL_LOG_ID= null;
  private Timestamp RECORDING_DATE= null;
  private String RECORDING_FILE= null;
  private String KEY_WORDS= null;
  private String TRANSCRIPTION= null;
  private String ORIGIN_GROUP_CODE= null;
  private Double RECORDING_DURATION= null;
  //
  Connection con;
  PreparedStatement statement;
  
  public RecordingLog_pl()
  {
    this.RECORDING_LOG_ID= null;
    this.RECORDING_BLOB_ID= null;
    this.CALL_LOG_ID= null;
    this.RECORDING_DATE= null;
    this.RECORDING_FILE= null;
    this.KEY_WORDS= null;
    this.TRANSCRIPTION= null;
    this.ORIGIN_GROUP_CODE= null;
    this.RECORDING_DURATION= null;
    //System.out.println("RecordingLog_pl - Fini initialisation RecordingLog_pl");
  }
  
  protected void open_con()
  {
    try
    {
      con = OracleJDBCConnection.getJDBCConnection();
      System.out.println("RecordingLog_pl est pret a connecter DB");
    }
    catch(Exception ex)
    {
      System.out.println("RecordingLog_pl: "+ex.getMessage());
      //System.out.println("RecordingLog_pl n'est pas pret a connecter DB");
    }        
  }
  //destructure
  //protected void finalize() throws Throwable
  protected void close_con()
  {
    try{      
      //con.commit();
      statement.close();
      con.close();
      //System.out.println("RecordingLog_pl a bien fini connexion");      
    }
    catch(Exception ex) {
      System.out.println("RecordingLog_pl - "+ex.getMessage());
      //System.out.println("RecordingLog_pl n'a pas bien fini connexion a cause de DB");
    }        
  }  
  //**** partie qui remplace Recording LogLocalHome ****//

  public RecordingLog_pl findByPrimaryKey(Long primaryKey)
  {
    String sql= "select * from robot.RECORDING_LOG where RECORDING_LOG_ID = "+primaryKey.toString()+"";
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.RECORDING_LOG_ID= new Long(resultat.getLong("RECORDING_LOG_ID"));
        this.CALL_LOG_ID= new Long(resultat.getLong("CALL_LOG_ID"));
        this.KEY_WORDS= resultat.getString("KEY_WORDS");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.RECORDING_BLOB_ID= new Long(resultat.getLong("RECORDING_BLOB_ID"));
        this.RECORDING_DATE= resultat.getTimestamp("RECORDING_DATE");
        this.RECORDING_DURATION= new Double(resultat.getDouble("RECORDING_DURATION"));
        this.RECORDING_FILE= resultat.getString("RECORDING_FILE");
        this.TRANSCRIPTION= resultat.getString("TRANSCRIPTION");

        //System.out.println("RecordingLog_pl - avoir trouve RecordingLog_pl a partir de primarykey");
        //return this;
      }
      else{
        System.out.println("RecordingLog_pl - ne pas avoir trouve RecordingLog_pl a partir de primarykey");
        //return null;
      }
    }
    catch(Exception ex) {
      System.out.println("RecordingLog_pl - Il y a probleme de connection DB avec RecordingLog_pl.");
      ex.printStackTrace();
      return null;
    }
    close_con();
    return this;  
  }

  public RecordingLog_pl create(Long call_log_id, Timestamp recording_date, String groupCode)
    throws CreateException, RemoteException, NamingException
  {
    Long id = null;
    InitialContext ic = new InitialContext();
    SequenceRemote s = (SequenceRemote) ic.lookup("sequence");
    id = new Long(s.getNextValue("RECORDING_LOG_SEQ"));
    this.RECORDING_LOG_ID= id;
    this.CALL_LOG_ID= call_log_id;
    this.RECORDING_DATE= recording_date;
    this.ORIGIN_GROUP_CODE= groupCode;
    this.RECORDING_DURATION= new Double(0);
    String sql= "insert into robot.RECORDING_LOG (RECORDING_LOG_ID, CALL_LOG_ID, RECORDING_DATE, "+
      "ORIGIN_GROUP_CODE, RECORDING_DURATION) values (?,?,?,?,?)";
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setLong(1, id.longValue());
      statement.setLong(2, call_log_id.longValue());
      statement.setTimestamp(3, recording_date);
      statement.setString(4, groupCode);
      statement.setDouble(5, 0);
      this.statement.executeUpdate();
      //System.out.println("RecordingLog_pl a insere une nouvelle ligne.");
    }
    catch(Exception e)
    {
      System.out.println("RecordingLog_pl - "+e.getMessage());
      //System.out.println("RecordingLog_pl n'arrive pas d'inserer nouvelle ligne.");
    }
    close_con();
    return this;  
  }

  public Collection findIfFileNotNull()
  {
    List ls = new LinkedList();
    Collection collection= (Collection) ls;
    open_con();
    try{
      String sql = "select * from robot.RECORDING_LOG where RECORDING_FILE is not null ";
      this.statement= this.con.prepareStatement(sql);
      ResultSet resultat = statement.executeQuery(sql);
      while(resultat.next()){
        RecordingLog_pl rl_pl=new RecordingLog_pl();
        rl_pl.RECORDING_LOG_ID= new Long(resultat.getLong("RECORDING_LOG_ID"));
        rl_pl.CALL_LOG_ID= new Long(resultat.getLong("CALL_LOG_ID"));
        rl_pl.KEY_WORDS= resultat.getString("KEY_WORDS");
        rl_pl.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        rl_pl.RECORDING_BLOB_ID= new Long(resultat.getLong("RECORDING_BLOB_ID"));
        rl_pl.RECORDING_DATE= resultat.getTimestamp("RECORDING_DATE");
        rl_pl.RECORDING_DURATION= new Double(resultat.getDouble("RECORDING_DURATION"));
        rl_pl.RECORDING_FILE= resultat.getString("RECORDING_FILE");
        rl_pl.TRANSCRIPTION= resultat.getString("TRANSCRIPTION");
        collection.add(rl_pl);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    close_con();
    return collection;  
  }
  
  //**** partie qui remplace RecordingLogLocal ****//
  public Long getRecordingLogId(){return this.RECORDING_LOG_ID;}

  public Long getRecordingBlobId(){return this.RECORDING_BLOB_ID;}

  public void setRecordingBlobId(Long recordingBlobId)
  {
    this.RECORDING_BLOB_ID= recordingBlobId;
    
    String sql="update robot.RECORDING_LOG set RECORDING_BLOB_ID = "+recordingBlobId.toString()+
      " where RECORDING_LOG_ID= "+ this.RECORDING_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      this.statement.executeUpdate();
      //System.out.println("RecordingLog_pl a enregistre RECORDING_BLOB_ID.");
    }
    catch(Exception e)
    {
      System.out.println("RecordingLog_pl - "+e.getMessage());
      //System.out.println("RecordingLog_pl n'arrive pas d'enregistrer RECORDING_BLOB_ID.");
    }    
    close_con();
  }

  public Long getCallLogId(){return this.CALL_LOG_ID;}

  //public void setCallLogId(Long callLogId){}

  public Timestamp getRecordingDate(){return this.RECORDING_DATE;}

  //public void setRecordingDate(Timestamp recordingDate){}

  public String getRecordingFile(){return this.RECORDING_FILE;}

  //public void setRecordingFile(String recordingFile){}

  public String getKeyWords(){return this.KEY_WORDS;}

  //public void setKeyWords(String keyWords){}

  public String getTranscription(){return this.TRANSCRIPTION;}

  //public void setTranscription(String transcription){}

  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}

  //public void setOriginGroupCode(String originGroupCode){}

  public Double getRecordingDuration(){return this.RECORDING_DURATION;}

  public void setRecordingDuration(Double recordingDuration)
  {
    this.RECORDING_DURATION= recordingDuration;
    
    String sql="update robot.RECORDING_LOG set RECORDING_DURATION = "+recordingDuration.toString()+
      " where RECORDING_LOG_ID= "+ this.RECORDING_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      this.statement.executeUpdate();
      //System.out.println("RecordingLog_pl a enregistre RECORDING_DURATION.");
    }
    catch(Exception e)
    {
      System.out.println("RecordingLog_pl - "+e.getMessage());
      //System.out.println("RecordingLog_pl n'arrive pas d'enregistrer RECORDING_DURATION.");
    }  
    close_con();
  }
    

}