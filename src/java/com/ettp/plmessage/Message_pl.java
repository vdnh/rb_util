package com.ettp.plmessage;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class Message_pl
{
  // les varibles de table Message
  private Long MESSAGE_NUMBER= null;
  private String LANGUAGE_CODE=null;
  private String MESSAGE_AUTHOR=null;
  private String MESSAGE_TEXT=null;
  private Timestamp CREATED_DATE= null;
  private String CREATED_BY=null;
  private Long HYPER_LINK_MESSAGE_NUMBER=null;
  private Long NEXT_SUB_MESSAGE_NUMBER=null;
  private String ORIGIN_TABLE=null;
  private Integer MESSAGE_LEVEL=null;
  private String FORMAT=null;
  private String AUDIO_USED=null;
  private String ORIGIN_GROUP_CODE=null;
  private String FILE_WAV=null;
  private String MESSAGE_CODE=null;
  //private byte[] MESSAGE_BLOB=null;

  //constructor
  public Message_pl()
  {
    this.MESSAGE_NUMBER= null;
    this.LANGUAGE_CODE=null;
    this.MESSAGE_AUTHOR=null;
    this.MESSAGE_TEXT=null;
    this.CREATED_DATE= null;
    this.CREATED_BY=null;
    this.HYPER_LINK_MESSAGE_NUMBER=null;
    this.NEXT_SUB_MESSAGE_NUMBER=null;
    this.ORIGIN_TABLE=null;
    this.MESSAGE_LEVEL=null;
    this.FORMAT=null;
    this.AUDIO_USED=null;
    this.ORIGIN_GROUP_CODE=null;
    this.FILE_WAV=null;
    this.MESSAGE_CODE=null;
//    this.MESSAGE_BLOB=null;

    // trace
    //System.out.println("this is Message_pl");
  }


  // ******************* Partie remplace MessagesLocalHome ********************

  //public boolean findByPrimaryKey(MessagesPK primaryKey){
  public Message_pl findByPrimaryKey(MessagesPK primaryKey){
    //boolean existe_data = false;
    //EJB_BDWrapper bdwrapper= new EJB_BDWrapper();
    String[] cle = {primaryKey.languageCode, primaryKey.originGroupCode, primaryKey.messageCode};
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.messages where LANGUAGE_CODE = '"+cle[0]+
                  "' AND ORIGIN_GROUP_CODE = '"+cle[1]+"' AND MESSAGE_CODE= '"+cle[2]+"'";
      ResultSet resultat = statement.executeQuery(sql);
      //resultat.first();
      if(resultat.next()){
        //s'il y a de data
        //existe_data= true;

        //long messageNumber=resultat.getLong("MESSAGE_NUMBER");
        this.MESSAGE_NUMBER= new Long(resultat.getLong("MESSAGE_NUMBER"));
        this.LANGUAGE_CODE=resultat.getString("LANGUAGE_CODE");
        this.MESSAGE_AUTHOR = resultat.getString("MESSAGE_AUTHOR");
        this.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        this.CREATED_DATE=(Timestamp)resultat.getTimestamp("CREATED_DATE");
        System.out.println("Hung - voir TimeStamp: "+this.CREATED_DATE.toString());
        this.CREATED_BY= resultat.getString("CREATED_BY");
        //long hyperLinkMessageNumber=resultat.getLong("HYPER_LINK_MESSAGE_NUMBER");
        this.HYPER_LINK_MESSAGE_NUMBER= new Long(resultat.getLong("HYPER_LINK_MESSAGE_NUMBER"));
        //long nextSubMessageNumber= resultat.getLong("NEXT_SUB_MESSAGE_NUMBER");
        this.NEXT_SUB_MESSAGE_NUMBER = new Long(resultat.getLong("NEXT_SUB_MESSAGE_NUMBER"));
        this.ORIGIN_TABLE= resultat.getString("ORIGIN_TABLE");
        //int messageLevel= resultat.getInt("MESSAGE_LEVEL");
        this.MESSAGE_LEVEL= new Integer(resultat.getInt("MESSAGE_LEVEL"));
        this.FORMAT= resultat.getString("FORMAT");
        this.AUDIO_USED= resultat.getString("AUDIO_USED");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.FILE_WAV= resultat.getString("FILE_WAV");
        this.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");
      }
      else{
        statement.close();
        con.close();
        return null;
      }
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
    //bdwrapper.closeConnection();
    //return existe_data;
    return this;
    //*** fini travaille ave ResultSet
  }

///*
   public Collection findAll(){
      //EJB_BDWrapper bdwrapper= new EJB_BDWrapper();
      List ls = new LinkedList();
      Collection collection= (Collection) ls;
      //Connection connection = bdwrapper.getConnection();
      //Message_pl message_pl=new Message_pl();
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM robot.MESSAGES";
      ResultSet resultat = statement.executeQuery(sql);
      while(resultat.next()){
        Message_pl message_pl=new Message_pl();
        message_pl.MESSAGE_NUMBER=new Long(resultat.getLong("MESSAGE_NUMBER"));
        message_pl.LANGUAGE_CODE=resultat.getString("LANGUAGE_CODE");
        message_pl.MESSAGE_AUTHOR = resultat.getString("MESSAGE_AUTHOR");
        message_pl.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        message_pl.CREATED_DATE=(Timestamp)resultat.getTimestamp("CREATED_DATE");
        System.out.println("Hung - voir TimeStamp: "+message_pl.CREATED_DATE.toString());
        message_pl.CREATED_BY= resultat.getString("CREATED_BY");
        message_pl.HYPER_LINK_MESSAGE_NUMBER=new Long(resultat.getLong("HYPER_LINK_MESSAGE_NUMBER"));
        message_pl.NEXT_SUB_MESSAGE_NUMBER= new Long(resultat.getLong("NEXT_SUB_MESSAGE_NUMBER"));
        message_pl.ORIGIN_TABLE= resultat.getString("ORIGIN_TABLE");
        message_pl.MESSAGE_LEVEL= new Integer(resultat.getInt("MESSAGE_LEVEL"));
        message_pl.FORMAT= resultat.getString("FORMAT");
        message_pl.AUDIO_USED= resultat.getString("AUDIO_USED");
        message_pl.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        message_pl.FILE_WAV= resultat.getString("FILE_WAV");
        message_pl.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");
        collection.add(message_pl);
      }

      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    //bdwrapper.closeConnection();
    return collection;
  }
//*/
  //public boolean findByMessageNumber(Long message_number, String language_code, Integer message_level){
  public Message_pl findByMessageNumber(Long message_number, String language_code, Integer message_level){
      String messN= message_number.toString();
      String messL= message_level.toString();
      String[] paras = {messN, language_code, messL};
    //boolean exist_data= false;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.messages where MESSAGE_NUMBER = '"+paras[0]+
                  "' AND LANGUAGE_CODE = '"+paras[1]+"' AND MESSAGE_LEVEL= '"+paras[2]+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        // s'il y a data
        //exist_data= true;
        this.MESSAGE_NUMBER= new Long(resultat.getLong("MESSAGE_NUMBER"));
        this.LANGUAGE_CODE=resultat.getString("LANGUAGE_CODE");
        this.MESSAGE_AUTHOR = resultat.getString("MESSAGE_AUTHOR");
        this.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        this.CREATED_DATE=(Timestamp)resultat.getTimestamp("CREATED_DATE");
        //System.out.println("Hung - voir TimeStamp: "+this.CREATED_DATE.toString());
        this.CREATED_BY= resultat.getString("CREATED_BY");
        this.HYPER_LINK_MESSAGE_NUMBER= new Long(resultat.getLong("HYPER_LINK_MESSAGE_NUMBER"));
        this.NEXT_SUB_MESSAGE_NUMBER = new Long(resultat.getLong("NEXT_SUB_MESSAGE_NUMBER"));
        this.ORIGIN_TABLE= resultat.getString("ORIGIN_TABLE");
        this.MESSAGE_LEVEL= new Integer(resultat.getInt("MESSAGE_LEVEL"));
        this.FORMAT= resultat.getString("FORMAT");
        this.AUDIO_USED= resultat.getString("AUDIO_USED");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.FILE_WAV= resultat.getString("FILE_WAV");
        this.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");

        //this.messageBlob= bytes;
        //fini extraction BLOB
      }
      else{
        statement.close();
        con.close();
        return null;
      }
      //s'il n'y a pas data
      //else exist_data= false;
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
    //bdwrapper.closeConnection();
    //return exist_data;
    return this;
    //*** fini travaille ave ResultSet
  }

//seulement avec Message_Number
  //public boolean findByMessageNumber(Long message_number){
  public Message_pl findByMessageNumber(Long message_number){
      String messN= message_number.toString();
    //boolean exist_data= false;
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.messages where MESSAGE_NUMBER = '"+messN+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        // s'il y a data
        //exist_data= true;
        this.MESSAGE_NUMBER= new Long(resultat.getLong("MESSAGE_NUMBER"));
        this.LANGUAGE_CODE=resultat.getString("LANGUAGE_CODE");
        this.MESSAGE_AUTHOR = resultat.getString("MESSAGE_AUTHOR");
        this.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        this.CREATED_DATE=(Timestamp)resultat.getTimestamp("CREATED_DATE");
        //System.out.println("Hung - voir TimeStamp: "+this.CREATED_DATE.toString());
        this.CREATED_BY= resultat.getString("CREATED_BY");
        this.HYPER_LINK_MESSAGE_NUMBER= new Long(resultat.getLong("HYPER_LINK_MESSAGE_NUMBER"));
        this.NEXT_SUB_MESSAGE_NUMBER = new Long(resultat.getLong("NEXT_SUB_MESSAGE_NUMBER"));
        this.ORIGIN_TABLE= resultat.getString("ORIGIN_TABLE");
        this.MESSAGE_LEVEL= new Integer(resultat.getInt("MESSAGE_LEVEL"));
        this.FORMAT= resultat.getString("FORMAT");
        this.AUDIO_USED= resultat.getString("AUDIO_USED");
        this.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        this.FILE_WAV= resultat.getString("FILE_WAV");
        this.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");

      }
      else{
        statement.close();
        con.close();
        return null;
      }
      //s'il n'y a pas data
      //else exist_data= false;
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
    //bdwrapper.closeConnection();
    //return exist_data;
    return this;
    //*** fini travaille ave ResultSet
  }
//
  public Collection findByOriginGroupLanguage(String origin_group_code, String language_code){
      List ls = new LinkedList();
      Collection collection= (Collection) ls;
      //EJB_BDWrapper bdwrapper= new EJB_BDWrapper();
      //Connection connection = bdwrapper.getConnection();
      //Message_pl message_pl=new Message_pl();
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.messages where ORIGIN_GROUP_CODE = '"+origin_group_code+
                  "' AND LANGUAGE_CODE = '"+language_code+"'";
      ResultSet resultat = statement.executeQuery(sql);
      while(resultat.next()){
        Message_pl message_pl=new Message_pl();
        message_pl.MESSAGE_NUMBER=new Long(resultat.getLong("MESSAGE_NUMBER"));
        message_pl.LANGUAGE_CODE=resultat.getString("LANGUAGE_CODE");
        message_pl.MESSAGE_AUTHOR = resultat.getString("MESSAGE_AUTHOR");
        message_pl.MESSAGE_TEXT= resultat.getString("MESSAGE_TEXT");
        message_pl.CREATED_DATE=(Timestamp)resultat.getTimestamp("CREATED_DATE");
        //System.out.println("Hung - voir TimeStamp: "+message_pl.CREATED_DATE.toString());
        message_pl.CREATED_BY= resultat.getString("CREATED_BY");
        message_pl.HYPER_LINK_MESSAGE_NUMBER=new Long(resultat.getLong("HYPER_LINK_MESSAGE_NUMBER"));
        message_pl.NEXT_SUB_MESSAGE_NUMBER= new Long(resultat.getLong("NEXT_SUB_MESSAGE_NUMBER"));
        message_pl.ORIGIN_TABLE= resultat.getString("ORIGIN_TABLE");
        message_pl.MESSAGE_LEVEL= new Integer(resultat.getInt("MESSAGE_LEVEL"));
        message_pl.FORMAT= resultat.getString("FORMAT");
        message_pl.AUDIO_USED= resultat.getString("AUDIO_USED");
        message_pl.ORIGIN_GROUP_CODE= resultat.getString("ORIGIN_GROUP_CODE");
        message_pl.FILE_WAV= resultat.getString("FILE_WAV");
        message_pl.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");

        //ajouter dans un Collection
        collection.add(message_pl);
      }
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    //bdwrapper.closeConnection();
    return collection;
  }

  // **************** partie remplace MessagesLocal *************

  public Long getMessageNumber(){return this.MESSAGE_NUMBER;}
  //public void setMessageNumber(Long messageNumber){ this.messageNumber= messageNumber;}

  public String getLanguageCode(){return this.LANGUAGE_CODE;}
  //public void setLanguageCode(String languageCode){ this.languageCode= languageCode;}

  public String getMessageAuthor() {return this.MESSAGE_AUTHOR;}
  //public void setMessageAuthor(String messageAuthor) {this.messageAuthor= messageAuthor;}

  public String getMessageText(){return this.MESSAGE_TEXT;}
  //public void setMessageText(String messageText){this.messageText= messageText;}

  public Timestamp getCreatedDate(){return this.CREATED_DATE;}
  //public void setCreatedDate(Timestamp createdDate) {this.createdDate= createdDate;}

  public String getCreatedBy(){return this.CREATED_BY;}
  //public void setCreatedBy(String createdBy){this.createdBy= createdBy;}

  public Long getHyperLinkMessageNumber() {return this.HYPER_LINK_MESSAGE_NUMBER;}
  //public void setHyperLinkMessageNumber(Long hyperLinkMessageNumber){this.hyperLinkMessageNumber= hyperLinkMessageNumber;}

  public Long getNextSubMessageNumber() {return this.NEXT_SUB_MESSAGE_NUMBER;}
  //public void setNextSubMessageNumber(Long nextSubMessageNumber){this.nextSubMessageNumber= nextSubMessageNumber;}

  public String getOriginTable() {return this.ORIGIN_TABLE;}
  //public void setOriginTable(String originTable){this.originTable= originTable;}

  public Integer getMessageLevel() {return this.MESSAGE_LEVEL;}
  //public void setMessageLevel(Integer messageLevel){this.messageLevel= messageLevel;}

  public String getFormat() {return this.FORMAT;}
  //public void setFormat(String format){this.format= format;}

  public String getAudioUsed() {return this.AUDIO_USED;}
  //public void setAudioUsed(String audioUsed){this.audioUsed= audioUsed;}

  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}
  //public void setOriginGroupCode(String originGroupCode){this.originGroupCode= originGroupCode;}

  public String getFileWav() {return this.FILE_WAV;}
  //public void setFileWav(String fileWav){this.fileWav= fileWav;}

  public String getMessageCode(){return this.MESSAGE_CODE;}
  //public void setMessageCode(String messageCode){this.messageCode= messageCode;}

}