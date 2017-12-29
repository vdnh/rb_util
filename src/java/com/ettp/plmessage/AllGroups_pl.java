package com.ettp.plmessage;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class AllGroups_pl
{
  //les parametres
  private Long GROUP_ID= null;
  //private String COUNTRY_CODE= null;
  private Long GROUP_TYPE_ID= null;
  private String GROUP_CODE= null;
  /*
  private String LDAP_GROUP_ID= null;
  private String PHONE_NUMBER= null;
  private String WEB_SITE= null;
  private String EMAIL= null;
  private String ADDRESS1= null;
  private String ADDRESS2= null;
  private String ADDRESS3= null;
  private String POSTAL_CODE= null;
  private String CITY= null;
  private String STATE_REGION= null;
  private Long TOP_WORKING_TIME= null;
  private Long TOP_MEAN_TIME= null;
  private String FONT= null;
  private String FONT_SIZE= null;
  private Timestamp VALID_FROM= null;
  private Timestamp VALID_UNTIL= null;
  private String VALIDATED_BY= null;
  private Long MESS_NUMBER_GROUP_CODE= null;
  private Long MESS_NUMBER_GROUP_DESC= null;
  private String COLOR= null;
  private String ORIGIN_GROUP_CODE= null;
  private Long PASSWORD_LIFE= null;
  private Long PASSWORD_MAX_LENGTH= null;
  private Long PASSWORD_MIN_LENGTH= null;
  */

  public AllGroups_pl()
  {
    this.GROUP_ID= null;
    //this.COUNTRY_CODE= null;
    this.GROUP_TYPE_ID= null;
    this.GROUP_CODE= null;
    /*
    this.LDAP_GROUP_ID= null;
    this.PHONE_NUMBER= null;
    this.WEB_SITE= null;
    this.EMAIL= null;
    this.ADDRESS1= null;
    this.ADDRESS2= null;
    this.ADDRESS3= null;
    this.POSTAL_CODE= null;
    this.CITY= null;
    this.STATE_REGION= null;
    this.TOP_WORKING_TIME= null;
    this.TOP_MEAN_TIME= null;
    this.FONT= null;
    this.FONT_SIZE= null;
    this.VALID_FROM= null;
    this.VALID_UNTIL= null;
    this.VALIDATED_BY= null;
    this.MESS_NUMBER_GROUP_CODE= null;
    this.MESS_NUMBER_GROUP_DESC= null;
    this.COLOR= null;
    this.ORIGIN_GROUP_CODE= null;
    this.PASSWORD_LIFE= null;
    this.PASSWORD_MAX_LENGTH= null;
    this.PASSWORD_MIN_LENGTH= null;
    /*/
    //System.out.println("AllGroups_pl - fini initialisation AllGroups_pl");
  }

  //**** Partie remplace AllGroupLocalHome ****//
  ///*
  public AllGroups_pl findByPrimaryKey(Long primaryKey)
  {
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from ettp.ALL_GROUPS where GROUP_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.GROUP_ID= new Long(resultat.getLong("GROUP_ID"));
        this.GROUP_CODE= resultat.getString("GROUP_CODE");
        this.GROUP_TYPE_ID=new Long(resultat.getLong("GROUP_TYPE_ID"));

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

  AllGroupsLocal create(Long groupId, String countryCode, Long groupTypeId)
    throws CreateException;

  AllGroupsLocal findByCode(String groupCode) throws FinderException;
  */



  //**** Partie remplace AllGroupsLocal ****//

  public Long getGroupId(){return this.GROUP_ID;}

  //public String getCountryCode(){ return this.COUNTRY_CODE;}


  public Long getGroupTypeId(){return this.GROUP_TYPE_ID;}

  public String getGroupCode(){return this.GROUP_CODE;}


  /*
  public String getLdapGroupId(){return this.LDAP_GROUP_ID;}

  public String getPhoneNumber(){return this.PHONE_NUMBER;}

  public String getWebSite(){return this.WEB_SITE;}

  public String getEmail(){return this.EMAIL;}

  public String getAddress1(){return this.ADDRESS1;}

  public String getAddress2(){return this.ADDRESS2;}

  public String getAddress3(){return this.ADDRESS3;}

  public String getPostalCode(){return this.POSTAL_CODE;}

  public String getCity(){return this.CITY;}

  public String getStateRegion(){return this.STATE_REGION;}

  public Long getTopWorkingTime(){return this.TOP_WORKING_TIME;}

  public Long getTopMeanTime(){return this.TOP_MEAN_TIME;}

  public String getFont(){return this.FONT;}

  public String getFontSize(){return this.FONT_SIZE;}

  public Timestamp getValidFrom(){return this.VALID_FROM;}

  public Timestamp getValidUntil(){return this.VALID_UNTIL;}

  public String getValidatedBy(){return this.VALIDATED_BY;}

  public Long getMessNumberGroupCode(){return this.MESS_NUMBER_GROUP_CODE;}

  public Long getMessNumberGroupDesc(){return this.MESS_NUMBER_GROUP_DESC;}

  public String getColor(){return this.COLOR;}

  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}

  public Long getPasswordLife(){return this.PASSWORD_LIFE;}

  public Long getPasswordMaxLength(){return this.PASSWORD_MAX_LENGTH;}

  public Long getPasswordMinLength(){return this.PASSWORD_MIN_LENGTH;}

  */
}