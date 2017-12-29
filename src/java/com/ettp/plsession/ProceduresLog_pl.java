package com.ettp.plsession;
//import com.ettp.ejb.util.sequence.Sequence;
import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.sequence.SequenceRemote;
//import com.ettp.plsession.ProceduresLog_pl;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.ejb.CreateException;
//import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.rmi.PortableRemoteObject;
//import javax.sql.DataSource;

public class ProceduresLog_pl 
{
  //les parametres 
  private Long PROCEDURE_LOG_ID= null;
  private Timestamp CREATED_DATE= null;
  private Long CALL_LOG_ID= null;
  private String LOG_CALL;
  private String LOG_RETURN= null;
  //private Long TEMPO= null;
  
  public ProceduresLog_pl()
  {
    this.PROCEDURE_LOG_ID= null;
    this.CREATED_DATE= null;
    this.CALL_LOG_ID= null;
    this.LOG_CALL= null;
    this.LOG_RETURN= null;
    //this.TEMPO= null;
    //System.out.println("ProceduresLog_pl - Fini initialiation ProceduresLog_pl");
  }
  
  //partie qui remplace ProceduresLogsLocalHome
  public ProceduresLog_pl create(Long callLogId, String logCall, String logReturn, Timestamp ts)
    throws CreateException, RemoteException, NamingException {
    Long procedureLogId = null;
//    SequenceHome seqHome = getSequenceHome();
//    Sequence s = seqHome.create();
    InitialContext ic = new InitialContext();
    SequenceRemote sr = (SequenceRemote) ic.lookup("squence");
    procedureLogId = new Long(sr.getNextValue("robot_LOG_SEQ"));    
    //Creer en memme temps tous les champs
    try{      
      Connection con = OracleJDBCConnection.getJDBCConnection();
      //String sql = "insert into robot.PROCEDURES_LOGS (PROCEDURE_LOG_ID, CREATE_DATE, CALL_LOG_ID, LOG_CALL, LOG_RETURN) values (?, ?, ?, ?, ?)";
      String sql = "insert into robot.PROCEDURES_LOGS (PROCEDURE_LOG_ID, CALL_LOG_ID, LOG_CALL, LOG_RETURN, CREATED_DATE) values (?, ?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, procedureLogId.intValue());
      statement.setLong(2, callLogId.longValue());
      statement.setString(3, logCall);
      statement.setString(4, logReturn);
      statement.setTimestamp(5, ts);
      
      statement.executeUpdate();
      con.commit();
      statement.close();
      con.close();
      //System.out.println("ProceduresLog_pl - avoir cree Procedure Log");      
    }
    catch(Exception ex) {
      System.out.println("ProceduresLog_pl - "+ex.getMessage());
      //System.out.println("ProceduresLog_pl - "+ex.toString());
      //System.out.println("ProceduresLog_pl - avoir problem de connection DB de ProceduresLog_pl");
    }    
    this.PROCEDURE_LOG_ID= procedureLogId;
    this.CALL_LOG_ID= callLogId;
    this.LOG_CALL= logCall;
    this.LOG_RETURN= logReturn;
    this.CREATED_DATE= ts;
    
    return this;
  }
  //partie qui remplace ProceduresLogsLocal
  public Long getProcedureLogId(){return this.PROCEDURE_LOG_ID;}

  public Timestamp getCreatedDate(){return this.CREATED_DATE;}

  public void setCreatedDate(Timestamp createdDate)
  {
    
  }

  public Long getCallLogId(){return this.CALL_LOG_ID;}

  public void setCallLogId(Long callLogId)
  {
    
  }

  public String getLogCall(){return this.LOG_CALL;}

  public void setLogCall(String logCall)
  {
    
  }

  public String getLogReturn(){return this.LOG_RETURN;}

  public void setLogReturn(String logReturn)
  {
    
  }
  
}