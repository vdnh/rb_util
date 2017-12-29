package com.ettp.plsession;

import com.ettp.OracleJDBCConnection;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.CreateException;
import javax.naming.NamingException;

public class CommonBlobs_pl
{
  //les parametres
  private String BLOB_CODE= null;
  private String BLOB_NAME= null;
  private byte[] RECORDING= null;
  private Double RECORDING_SIZE= null;
  private String LANGUAGE_CODE= null;

  public CommonBlobs_pl()
  {
    this.BLOB_CODE= null;
    this.BLOB_NAME= null;
    this.RECORDING= null;
    this.RECORDING_SIZE= null;
    this.LANGUAGE_CODE= null;
  }
  public String getBlobCode(){return this.BLOB_CODE;}

  public void setBlobCode(String BLOB_CODE) throws CreateException, RemoteException, NamingException
  {
    this.BLOB_CODE= BLOB_CODE;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql= "insert into robot.COMMON_BLOBS (BLOB_CODE) values (?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, BLOB_CODE);
      try {
        statement.executeUpdate();
        con.commit();
        statement.close();
        con.close();
        System.out.println("CommonBlobs_pl - avoir cree Blob_Code");
      }
      catch(Exception e)
      {
        statement.close();
        con.close();
        System.out.println("CommonBlobs_pl - existe deja");
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("CommonBlobs_pl - ne pas arriver creer Blob_Code - catch");
    }
  }

  public String getBlobName(){return this.BLOB_NAME;}

  public void setBlobName(String blobName){
    this.BLOB_NAME= blobName;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "update robot.COMMON_BLOBS set BLOB_NAME = '"+blobName+
                  "' where BLOB_CODE='"+ this.BLOB_CODE+"'";
      System.out.println("Hung - SQL"+sql);
      try{
        statement.executeUpdate(sql);
        con.commit();
        statement.close();
        con.close();
      }
      catch(SQLException se)
      {
        se.printStackTrace();
        System.out.println("CommonBlobs_pl - ne pas arriver ecrire blobName");
      }
    }
    catch(Exception ex) {
      System.out.println("CommonBlobs_pl - "+ex.getMessage());
    }
  }

  public byte[] getRecording(){return this.RECORDING;}

  public void setRecording(byte[] recording){
    this.RECORDING= recording;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql = "update robot.COMMON_BLOBS set RECORDING=? WHERE BLOB_CODE=?";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setBytes(1, recording);
      statement.setString(2,this.BLOB_CODE);
      statement.executeUpdate();
      con.commit();
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      System.out.println("CommonBlobs_pl - "+ex.getMessage());
    }
  }


  public Double getRecordingSize(){return this.RECORDING_SIZE;}

  public void setRecordingSize(Double recordingSize){
    this.RECORDING_SIZE= recordingSize;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "update robot.COMMON_BLOBS set RECORDING_SIZE ="+recordingSize.toString()+
                  " where BLOB_CODE='"+ this.BLOB_CODE+"'";
      System.out.println("Hung - SQL"+sql);
      try{
        statement.executeUpdate(sql);
        con.commit();
        statement.close();
        con.close();
      }
      catch(SQLException se)
      {
        se.printStackTrace();
        System.out.println("CommonBlobs_pl - ne pas arriver ecrire recordingSize");
      }
    }
    catch(Exception ex) {
      System.out.println("CommonBlobs_pl - "+ex.getMessage());
    }
  }

  public String getLanguageCode(){return this.LANGUAGE_CODE;}

  public void setLanguageCode(String languageCode){
    this.LANGUAGE_CODE= languageCode;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "update robot.COMMON_BLOBS set LANGUAGE_CODE = '"+languageCode+
                  "' where BLOB_CODE='"+ this.BLOB_CODE+"'";
      System.out.println("Hung - SQL"+sql);
      try{
        statement.executeUpdate(sql);
        con.commit();
        statement.close();
        con.close();
      }
      catch(SQLException se)
      {
        se.printStackTrace();
        System.out.println("CommonBlobs_pl - ne pas arriver ecrire languageCode");
      }
    }
    catch(Exception ex) {
      System.out.println("CommonBlobs_pl - "+ex.getMessage());
    }
  }

  public CommonBlobs_pl findByPrimaryKey(String blobCode){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.COMMON_BLOBS where BLOB_CODE = '"+blobCode+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.BLOB_CODE= resultat.getString("BLOB_CODE");

        try{
          Blob blob = resultat.getBlob("RECORDING");
            int len=(int)blob.length();
            this.RECORDING= blob.getBytes(1,len);
        }
        catch(Exception e)
        {
          System.out.println("CommonBlobs_pl - Recording est null actuellement ");
        }
        this.RECORDING_SIZE= new Double(resultat.getDouble("RECORDING_SIZE"));
        this.BLOB_NAME= resultat.getString("BLOB_NAME");
        this.LANGUAGE_CODE= resultat.getString("LANGUAGE_CODE");
        statement.close();
        con.close();
        return this;
      }
      else{
        statement.close();
        con.close();
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("CommonBlobs_pl - il y a faute de connexion DB de CommonBlobs_pl");
      return null;
    }
  }

  public CommonBlobs_pl findByPrimaryKey(String blobName, String languageCode){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.COMMON_BLOBS where BLOB_NAME = '"+blobName+
          "' and LANGUAGE_CODE = '"+ languageCode+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.BLOB_CODE= resultat.getString("BLOB_CODE");

        try{
          Blob blob = resultat.getBlob("RECORDING");
            int len=(int)blob.length();
            this.RECORDING= blob.getBytes(1,len);
        }
        catch(Exception e)
        {
          System.out.println("CommonBlobs_pl - Recording est null actuellement ");
        }
        this.RECORDING_SIZE= new Double(resultat.getDouble("RECORDING_SIZE"));
        this.BLOB_NAME= resultat.getString("BLOB_NAME");
        this.LANGUAGE_CODE= resultat.getString("LANGUAGE_CODE");
        statement.close();
        con.close();
        return this;
      }
      else{
        statement.close();
        con.close();
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("CommonBlobs_pl - il y a faute de connexion DB de CommonBlobs_pl");
      return null;
    }
  }
  public CommonBlobs_pl create(String BlobCode)throws CreateException, RemoteException, NamingException
  {
    setBlobCode(BlobCode);
    return findByPrimaryKey(BlobCode);
  }

}