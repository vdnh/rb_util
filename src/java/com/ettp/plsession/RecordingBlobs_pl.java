package com.ettp.plsession;

//import com.ettp.ejb.util.sequence.Sequence;
import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.sequence.SequenceRemote;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.CreateException;
//import javax.ejb.FinderException;
//import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.rmi.PortableRemoteObject;
//import javax.sql.DataSource;

public class RecordingBlobs_pl 
{
  //les parametres
  private Long RECORDING_BLOB_ID= null;
  private byte[] RECORDING= null;
  private String TRANSCRIPTION= null;
  private Double RECORDING_SIZE= null;
  
  public RecordingBlobs_pl()
  {
    this.RECORDING_BLOB_ID= null;
    this.RECORDING= null;
    this.TRANSCRIPTION= null;
    this.RECORDING_SIZE= null;
    //System.out.println("RecordingBlobs_pl - Fini initialisation RecordingBlobs_pl");
  }
  //**** partie qui remplace RecordingBlobsLocal ***//
  public Long getRecordingBlobId(){return this.RECORDING_BLOB_ID;}
  
  public void setRecordingBlobId(Long RECORDING_BLOB_ID) throws CreateException, RemoteException, NamingException
  {
    this.RECORDING_BLOB_ID= RECORDING_BLOB_ID;
    //this.RECORDING_BLOB_ID= new Long (RECORDING_BLOB_ID.longValue()+1);
    /*
    Long recordingBlobId = null;
    SequenceHome seqHome = getSequenceHome();
    Sequence s = seqHome.create();
    recordingBlobId = new Long(s.getNextValue("RECORDING_BLOBS_SEQ"));     
    this.RECORDING_BLOB_ID=recordingBlobId;
     //*/    
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      //Statement statement = con.createStatement();
      //String sql= "insert into Robot.RECORDING_BLOBS (RECORDING_BLOB_ID) values ("+
        //          RECORDING_BLOB_ID.toString()+")";
      String sql= "insert into robot.RECORDING_BLOBS (RECORDING_BLOB_ID) values (?)";
      PreparedStatement statement = con.prepareStatement(sql);
      //statement.setLong(1, 0);
      statement.setLong(1, RECORDING_BLOB_ID.longValue());
      /*
      String sql = "insert into ettp.BUGS (ID, DATE_BUG, DESCRIPTION, AUTEUR, ETAT, ORIGIN_GROUP_CODE) values (?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setLong(1, 0);
      */            
      //System.out.println("RecordingBlobs_pl - insert dans Recording_Blobs: "+sql);      
      //ResultSet resultat = 
      //if(statement.execute(sql))
      try {
        statement.executeUpdate();//)
      //{
        con.commit();
        statement.close();
        con.close();
        System.out.println("RecordingBlobs_pl - avoir cree RecordingBlob_Id");      
      }
      //*
      catch(Exception e)
      {
        statement.close();
        con.close();
        System.out.println("RecordingBlob_Id - existe deja");              
      }//*/
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("RecordingBlobs_pl - ne pas arriver creer RecordingBlob_Id - catch");
      //return false;
    }    
  }
  
  public byte[] getRecording(){return this.RECORDING;}

  public void setRecording(byte[] recording){
    this.RECORDING= recording;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql = "update robot.RECORDING_BLOBS set RECORDING=? WHERE RECORDING_BLOB_ID=?";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setBytes(1, recording);
      statement.setString(2,this.RECORDING_BLOB_ID.toString());
      statement.executeUpdate();
      //ResultSet resultat = statement.executeQuery(sql);  
      con.commit();
      statement.close();
      con.close();
      //System.out.println("RecordingBlobs_pl - avoir ecrit recording");      
    }
    catch(Exception ex) {
      System.out.println("RecordingBlobs_pl - "+ex.getMessage());
      //System.out.println("RecordingBlobs_pl - "+ex.toString());
      //System.out.println("RecordingBlobs_pl - avoir problem de connection DB de RecordingBlobs_pl");
    }
    //*/
    
  }

  public String getTranscription(){return this.TRANSCRIPTION;}

  public void setTranscription(String transcription){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "update robot.RECORDING_BLOBS set TRANSCRIPTION ='"+transcription+
                  "' where RECORDING_BLOB_ID= "+ this.RECORDING_BLOB_ID.toString();
      //ResultSet resultat = 
      try{
        statement.executeUpdate(sql);
        con.commit();
        statement.close();
        con.close();
        //System.out.println("RecordingBlobs_pl - avoir ecrit Transcription");      
      }
      catch(SQLException se)
      {
        //statement.close();
        //con.close();
        se.printStackTrace();
        System.out.println("RecordingBlobs_pl - ne pas arriver ecrire Transcription");              
      }
    }
    catch(Exception ex) {
      System.out.println("Hung - "+ex.toString());
      System.out.println("Hung - "+ex.getMessage());
      System.out.println("Hung - problem de connection DB de RecordingBlobs_pl");
      //return false;
    }  
  }

  public Double getRecordingSize(){return this.RECORDING_SIZE;}

  public void setRecordingSize(Double recordingSize){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "update robot.RECORDING_BLOBS set RECORDING_SIZE ="+recordingSize.toString()+
                  " where RECORDING_BLOB_ID='"+ this.RECORDING_BLOB_ID.toString()+"'";
      System.out.println("Hung - SQL"+sql);
      //ResultSet resultat = 
      try{
        statement.executeUpdate(sql);
        con.commit();
        statement.close();
        con.close();
        //System.out.println("RecordingBlobs_pl - avoir ecrit recordingSize");      
      }
      catch(SQLException se)
      {
        //statement.close();
        //con.close();
        se.printStackTrace();
        System.out.println("RecordingBlobs_pl - ne pas arriver ecrire recordingSize");              
      }
    }
    catch(Exception ex) {
      //System.out.println("RecordingBlobs_pl - "+ex.toString());
      System.out.println("RecordingBlobs_pl - "+ex.getMessage());
      //System.out.println("RecordingBlobs_pl - problem de connection DB de RecordingBlobs_pl");
      //return false;
    }  
  }
  
  //**** partie qui remplace RecordingBlobsLocalHome ****//
  /*
  RecordingBlobsLocal create() throws CreateException, RemoteException, NamingException;
  */
  //public boolean findByPrimaryKey(Long primaryKey){
  public RecordingBlobs_pl findByPrimaryKey(Long primaryKey){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.RECORDING_BLOBS where RECORDING_BLOB_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.RECORDING_BLOB_ID= new Long(resultat.getLong("RECORDING_BLOB_ID"));
//*
        try{
          Blob blob = resultat.getBlob("RECORDING");
            int len=(int)blob.length();
            this.RECORDING= blob.getBytes(1,len);
            //System.out.println("RecordingBlobs_pl - Longeur de recording: "+ this.RECORDING.length);
        }
        catch(Exception e)
        {
          //this.RECORDING= null;
          System.out.println("RecordingBlobs_pl - Recording est null actuellement ");
        }
 //*/
        //this.RECORDING=resultat.getBytes("RECORDING");
        //System.out.println("RecordingBlobs_pl - avoir retire data de BLOB de RecordingBlobs_pl");
        this.TRANSCRIPTION=resultat.getString("TRANSCRIPTION");
        this.RECORDING_SIZE= new Double(resultat.getDouble("RECORDING_SIZE"));
        
        statement.close();
        con.close();
        //System.out.println("RecordingBlobs_pl - avoir trouve RecordingBlobs_pl");
        return this;
      }
      else{
        statement.close();
        con.close();
        //System.out.println("RecordingBlobs_pl - ne pas avoir trouve RecordingBlobs_pl");
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("RecordingBlobs_pl - il y a faute de connexion DB de RecordingBlobs_pl");
      return null;
    }    
  }

/*
  //Collection findAll() throws FinderException;

  //RecordingBlobsLocal create(Long recordingBlobId) throws CreateException, RemoteException, NamingException;
*/
  public RecordingBlobs_pl create(String recordingBlobString)throws CreateException, RemoteException, NamingException
  {
    /*
    Long recordingBlobId= null;
    SequenceHome seqHome = getSequenceHome();
    Sequence s = seqHome.create();    
    recordingBlobId = new Long(s.getNextValueByHashval(recordingBlobString));
    setRecordingBlobId(recordingBlobId);
    //*/
    //*
    Long recordingBlobId = null;
    InitialContext ic = new InitialContext();
    SequenceRemote s = (SequenceRemote) ic.lookup("sequence");
    recordingBlobId = new Long(s.getNextValue("RECORDING_BLOBS_SEQ"));     
    setRecordingBlobId(recordingBlobId);
    //*/
    //boolean existe_data=findByPrimaryKey(recordingBlobId);
    
    //return this;
    //recordingBlobId= new Long (recordingBlobId.longValue()+1);
    return findByPrimaryKey(recordingBlobId);
  }
//
  public RecordingBlobs_pl create(Long recordingBlobId)throws CreateException, RemoteException, NamingException
  {
    /*
    Long recordingBlobId= null;
    SequenceHome seqHome = getSequenceHome();
    Sequence s = seqHome.create();    
    recordingBlobId = new Long(s.getNextValueByHashval(recordingBlobString));
    setRecordingBlobId(recordingBlobId);
    //*/
    //*
    //Long recordingBlobId = null;
    //SequenceHome seqHome = getSequenceHome();
    //Sequence s = seqHome.create();
    //recordingBlobId = new Long(s.getNextValue("RECORDING_BLOBS_SEQ"));     
    setRecordingBlobId(recordingBlobId);
    //*/
    //boolean existe_data=findByPrimaryKey(recordingBlobId);
    
    //return this;
    //recordingBlobId= new Long (recordingBlobId.longValue()+1);
    return findByPrimaryKey(recordingBlobId);
  }


  /*
  public Long ejbCreate(Long recordingBlobId) throws CreateException, RemoteException, NamingException {
    setRecordingBlobId(recordingBlobId);

    return recordingBlobId;
  }  
 */
}