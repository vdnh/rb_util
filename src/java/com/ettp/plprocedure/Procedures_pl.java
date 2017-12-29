package com.ettp.plprocedure;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Procedures_pl
{
  //les parametres
  private Long PROCEDURE_ID= null;
  private String PROCEDURE_NAME= null;
  private String PROCEDURE_RESULT_NAME= null;
  private String PROCEDURE_TYPE= null;
  //private Long DIALOG_ID= null;

  public Procedures_pl()
  {
    this.PROCEDURE_ID= null;
    this.PROCEDURE_NAME= null;
    this.PROCEDURE_RESULT_NAME= null;
    this.PROCEDURE_TYPE= null;
    //DIALOG_ID= null;

    //System.out.println("Procedures_pl - Fini initialisation Procedures_pl");
  }

  //**** partie qui remplace ProcedureLocalHome ****//
  //public boolean findByPrimaryKey(Long primaryKey){
  public Procedures_pl findByPrimaryKey(Long primaryKey){
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.PROCEDURES where PROCEDURE_ID = '"+primaryKey.toString()+"'";
      ResultSet resultat = statement.executeQuery(sql);
      if(resultat.next()){
        this.PROCEDURE_ID= new Long(resultat.getLong("PROCEDURE_ID"));
        this.PROCEDURE_NAME=resultat.getString("PROCEDURE_NAME");
        this.PROCEDURE_RESULT_NAME=resultat.getString("PROCEDURE_RESULT_NAME");
        this.PROCEDURE_TYPE= resultat.getString("PROCEDURE_TYPE");

        statement.close();
        con.close();
        //System.out.println("Procedures_pl - avoir trouve Procedures_pl");
        //return true;
        return this;
      }
      else{
        statement.close();
        con.close();
        System.out.println("Procedures_pl - ne pas avoir trouve Procedures_pl");
        //return false;
        return null;
      }
    }
    catch(SQLException ex) {
      ex.printStackTrace();
      System.out.println("Procedures_pl - il y a faute de connexion DB de Procedures_pl");
      //return false;
      return null;
    }
  }
  //**** partie qui remplace ProceduresLocal ****//
  public Long getProcedureId(){return this.PROCEDURE_ID;}

  public String getProcedureName(){return this.PROCEDURE_NAME;}

  //public void setProcedureName(String procedureName);

  public String getProcedureResultName(){return this.PROCEDURE_RESULT_NAME;}

  //public void setProcedureResultName(String procedureResultName);

  public String getProcedureType(){return this.PROCEDURE_TYPE;}

  //public void setProcedureType(String procedureType);
}