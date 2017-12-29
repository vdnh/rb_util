package com.ettp.plmessage;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Audio_pl
{
// les parametres
  private String MESSAGE_TEXT=null;
  private String LANGUAGE_CODE=null;
  private Timestamp DATE_CREATION_WAV=null;
  private String FILE_WAV=null;
  private String TO_DELETE=null;
  private String FILE_WAV_TTS=null;
  private Timestamp DATE_LAST_USE_TTS=null;
  private String ORIGIN_GROUP_CODE=null;
  private String LOCUTOR=null;


  public Audio_pl()
  {
    this.MESSAGE_TEXT=null;
    this.LANGUAGE_CODE=null;
    this.DATE_CREATION_WAV=null;
    this.FILE_WAV=null;
    this.TO_DELETE=null;
    this.FILE_WAV_TTS=null;
    this.DATE_LAST_USE_TTS=null;
    this.ORIGIN_GROUP_CODE=null;
    this.LOCUTOR=null;
    //
    //System.out.println("Audio_pl - Fini initialisation Audio_pl");
  }
  //****Partie de remplacement de AudioLocalHome***//

  public Audio_pl findByPrimaryKey(AudioPK primaryKey)
  {
    String[] cle = {primaryKey.messageText, primaryKey.languageCode, primaryKey.originGroupCode};
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.AUDIO where MESSAGE_TEXT = '"+cle[0]+
                  "' AND LANGUAGE_CODE = '"+cle[1]+"' AND ORIGIN_GROUP_CODE= '"+cle[2]+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        this.LANGUAGE_CODE= resultat.getString("LANGUAGE_CODE");
        this.DATE_CREATION_WAV=(Timestamp)resultat.getTimestamp("DATE_CREATION_WAV");
        this.FILE_WAV= resultat.getString("FILE_WAV");
        this.TO_DELETE= resultat.getString("TO_DELETE");
        this.FILE_WAV_TTS= resultat.getString("FILE_WAV_TTS");
        this.DATE_LAST_USE_TTS= (Timestamp)resultat.getTimestamp("DATE_LAST_USE_TTS");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.LOCUTOR= resultat.getString("LOCUTOR");

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
      return null;
    }

  }
/*
  Collection findAll() throws FinderException;

  AudioLocal create(String messageText, String languageCode, String originGroupId)
    throws CreateException;
*/
  public Audio_pl findByWavName(String originGroupCode, String language, String fileWav)
  {
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.AUDIO where FILE_WAV = '"+fileWav+
                  "' AND LANGUAGE_CODE = '"+language+"' AND ORIGIN_GROUP_CODE= '"+originGroupCode+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        this.LANGUAGE_CODE= resultat.getString("LANGUAGE_CODE");
        this.DATE_CREATION_WAV=(Timestamp)resultat.getTimestamp("DATE_CREATION_WAV");
        this.FILE_WAV= resultat.getString("FILE_WAV");
        this.TO_DELETE= resultat.getString("TO_DELETE");
        this.FILE_WAV_TTS= resultat.getString("FILE_WAV_TTS");
        this.DATE_LAST_USE_TTS= (Timestamp)resultat.getTimestamp("DATE_LAST_USE_TTS");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.LOCUTOR= resultat.getString("LOCUTOR");

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
      return null;
    }
  }


  //****Partie de remplacement de AudioLocal***//
  public String getMessageText(){return this.MESSAGE_TEXT;}

  public String getLanguageCode(){ return this.LANGUAGE_CODE;}

  public Timestamp getDateCreationWav(){return this.DATE_CREATION_WAV;}

  public void setDateCreationWav(Timestamp dateCreationWav)
  {
    //this.DATE_CREATION_WAV= dateCreationWav;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "select * from robot.AUDIO where where MESSAGE_TEXT = '"+
                  this.MESSAGE_TEXT+"' and LANGUAGE_CODE = '"+this.LANGUAGE_CODE+
                  "' and ORIGIN_GROUP_CODE = "+this.ORIGIN_GROUP_CODE+"'";
      ResultSet resultat = statement.executeQuery(sql);
      //resultat = statement.getResultSet();
      if(resultat.next()){
        resultat.updateTimestamp("DATE_CREATION_WAV",dateCreationWav);
        //System.out.println("Audio_pl - avoir ecrit dateCreationWav");
        con.commit();
        statement.close();
        con.close();
      }
      else{
        System.out.println("Audio_pl - ne pas arriver ecrire dateCreationWav");
        statement.close();
        con.close();
      }
    }
    catch(Exception ex) {
      System.out.println("Audio_pl - avoir problem de connection DB de Audio_pl");
      ex.printStackTrace();
    }
  }

  public String getFileWav(){return this.FILE_WAV;}

  //public void setFileWav(String fileWav){this.fileWav=fileWav;}

  public String getToDelete(){return this.TO_DELETE;}

  //public void setToDelete(String toDelete){this.toDelete= toDelete;}

  public String getFileWavTts(){return this.FILE_WAV_TTS;}

  //public void setFileWavTts(String fileWavTts){this.fileWavTts= fileWavTts;}

  public Timestamp getDateLastUseTts(){return this.DATE_LAST_USE_TTS;}

  //public void setDateLastUseTts(Timestamp dateLastUseTts){this.dateLastUseTts= dateLastUseTts;}

  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}

  public String getLocutor(){return this.LOCUTOR;}

  public void setLocutor(String locutor)
  {
    //this.LOCUTOR= locutor;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement();
      String sql= "update robot.AUDIO set LOCUTOR = '"+locutor+"' where MESSAGE_TEXT = '"+
                  this.MESSAGE_TEXT+"' and LANGUAGE_CODE = '"+this.LANGUAGE_CODE+
                  "' and ORIGIN_GROUP_CODE = "+this.ORIGIN_GROUP_CODE+"'";
      //ResultSet resultat =
      if (statement.execute(sql))
      {
        //System.out.println("Audio_pl - avoir ecrit Locutor");
        con.commit();
        statement.close();
        con.close();
      }
      else{
        System.out.println("Audio_pl - ne pas arriver ecrit Locutor");
        statement.close();
        con.close();
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}