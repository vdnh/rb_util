package com.ettp.plmessage;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MESSAGES_RECORDING_BLOBS
{
  //les parametres
  private String ORIGIN_GROUP_CODE= null;
  private String LANGUAGE_CODE= null;
  private String MESSAGE_CODE= null;
  private Long RECORDING_BLOB_ID= null;
  //
//  Context context;
//  DataSource ds;
  Connection con;
  //PreparedStatement statement;
  ResultSet resultat;
  Statement statement;

  public MESSAGES_RECORDING_BLOBS()
  {
    ORIGIN_GROUP_CODE= null;
    LANGUAGE_CODE= null;
    MESSAGE_CODE= null;
    RECORDING_BLOB_ID= null;
    System.out.println("MESSAGES_RECORDING_BLOBS - initialisation");
  }
  protected void open_con()
  {
    try
    {
      con = OracleJDBCConnection.getJDBCConnection();
    }
    catch(Exception ex)
    {
      System.out.println("MESSAGES_RECORDING_BLOBS: "+ex.getMessage());
    }
  }
  protected void close_con()
  {
    try{
      //con.commit();
      statement.close();
      con.close();
    }
    catch(SQLException ex) {
      System.out.println("MESSAGES_RECORDING_BLOBS - "+ex.getMessage());
    }
  }

  //les fonctions
  public MESSAGES_RECORDING_BLOBS findByPrimarykey(String originGroup, String languageCode, String messageCode)
    //throws SQLException
  {
    try{
    this.open_con();

    statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    String sql= "select * from robot.MESSAGES_RECORDING_BLOBS where ORIGIN_GROUP_CODE = '"+
    originGroup+"' AND LANGUAGE_CODE = '"+languageCode+"' AND MESSAGE_CODE= '"+messageCode+"'";
    System.out.println("MESSAGES_RECORDING_BLOBS - "+sql);
    resultat = statement.executeQuery(sql);
    if(resultat.next())
    {
      this.ORIGIN_GROUP_CODE=resultat.getString("ORIGIN_GROUP_CODE");
      this.LANGUAGE_CODE=resultat.getString("LANGUAGE_CODE");
      this.MESSAGE_CODE=resultat.getString("MESSAGE_CODE");
      this.RECORDING_BLOB_ID=new Long(resultat.getLong("RECORDING_BLOB_ID"));
    }

    this.close_con();
    return this;
    }
    catch(SQLException es)
    {
      return null;
    }
  }
  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}
  public String getLanguageCode(){return this.LANGUAGE_CODE;}
  public String getMessageCode(){return this.MESSAGE_CODE;}
  public Long getRecordingBlobId(){return this.RECORDING_BLOB_ID;}
}