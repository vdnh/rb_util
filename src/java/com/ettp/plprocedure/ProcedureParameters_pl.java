package com.ettp.plprocedure;

import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProcedureParameters_pl
{
  //les parametres
  private Long PROCEDURE_ID= null;
  private Long PARAMETER_RANK= null;
  private Long PARAMETER_ID= null;

  public ProcedureParameters_pl()
  {
    this.PROCEDURE_ID= null;
    this.PARAMETER_RANK= null;
    this.PARAMETER_ID= null;

    //System.out.println("ProcedureParameters_pl - Fini initialisation ProcedureParameters_pl");
  }

  //**** partie qui remplace ProcedureParametersLocalHome ****//
  public Collection findByProcedureId(Long procedureId){
    List ls = new LinkedList();
    Collection collection= (Collection) ls;

    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql = "select * from robot.PROCEDURE_PARAMETERS where PROCEDURE_ID= '"+procedureId.toString()+"'";
      //System.out.println("Hung - ProcedureParameters_pl execute: "+sql);
      ResultSet resultat = statement.executeQuery(sql);
      //générer la faute pour tester Bugs_pl
      //ProcedureParameters_pl pp_pl=new ProcedureParameters_pl();
      //
      while(resultat.next()){
        ProcedureParameters_pl pp_pl=new ProcedureParameters_pl();
        pp_pl.PROCEDURE_ID=new Long(resultat.getLong("PROCEDURE_ID"));
        //System.out.println("Hung - ProcedureParameters_pl rend PROCEDURE_ID: "+pp_pl.PROCEDURE_ID);
        pp_pl.PARAMETER_RANK=new Long(resultat.getLong("PARAMETER_RANK"));
        //System.out.println("Hung - ProcedureParameters_pl rend PARAMETER_RANK: "+pp_pl.PARAMETER_RANK);
        pp_pl.PARAMETER_ID=new Long(resultat.getLong("PARAMETER_ID"));
        //System.out.println("Hung - ProcedureParameters_pl rend PARAMETER_ID: "+pp_pl.PARAMETER_ID);
        collection.add(pp_pl);
      }

      statement.close();
      con.close();
    }
    catch(SQLException ex) {
      ex.printStackTrace();
    }
    return collection;
  }


  //**** partie qui remplace ProcedureParameterslocal ****//
  public Long getProcedureId(){return this.PROCEDURE_ID;}

  public Long getParameterRank(){return this.PARAMETER_RANK;}

  public Long getParameterId(){return this.PARAMETER_ID;  }
}