package com.ettp.plprocedure;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Parameters_pl
{
  //les parameters
  private Long PARAMETER_ID= null;
  private String PARAMETER_TYPE= null;
  private String PARAMETER_LABEL= null;
  private String PARAMETER_VALUE= null;
  private String PARAMETER_CYCLOPE= null;
  //private Long DIALOG_ID= null;

  public Parameters_pl()
  {
    this.PARAMETER_ID= null;
    this.PARAMETER_TYPE= null;
    this.PARAMETER_LABEL= null;
    this.PARAMETER_VALUE= null;
    this.PARAMETER_CYCLOPE= null;
    //DIALOG_ID= null;

    //System.out.println("Parameters_pl - Fini initialisation Parameters_pl");
  }

  //**** partie qui remplace ParametersLocalhome ****//
  //public boolean findByPrimaryKey(Long primaryKey){
  public Parameters_pl findByPrimaryKey(Long primaryKey){
    try{      
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.PARAMETERS where PARAMETER_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.PARAMETER_ID= new Long(resultat.getLong("PARAMETER_ID"));
        this.PARAMETER_TYPE=resultat.getString("PARAMETER_TYPE");
        this.PARAMETER_LABEL=resultat.getString("PARAMETER_LABEL");
        this.PARAMETER_VALUE= resultat.getString("PARAMETER_VALUE");
        this.PARAMETER_CYCLOPE= resultat.getString("PARAMETER_CYCLOPE");

        statement.close();
        con.close();
        //System.out.println("Parameters_pl - avoir trouve Parameters_pl");
        //return true;
        return this;
      }
      else{
        statement.close();
        con.close();
        System.out.println("Parameters_pl - ne pas avoir trouve Parameters_pl");
        //return false;
        return null;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
      System.out.println("Parameters_pl - il y a faute de connexion DB de Parameters_pl");
      //return false;
      return null;
    }
  }

  //**** partie qui remplace ParameterLocal ****//
  public Long getParameterId(){return this.PARAMETER_ID;}

  public String getParameterType(){return this.PARAMETER_TYPE;}

  //public void setParameterType(String parameterType){;}

  public String getParameterLabel(){return this.PARAMETER_LABEL;}

  //public void setParameterLabel(String parameterLabel){;}

  public String getParameterValue(){return this.PARAMETER_VALUE;}

  //public void setParameterValue(String parameterValue){;}
}