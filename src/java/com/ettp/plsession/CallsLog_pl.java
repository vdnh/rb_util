package com.ettp.plsession;

//import com.ettp.ejb.util.sequence.Sequence;
import com.ettp.ejb.util.sequence.SequenceRemote;
//import com.ettp.ejb.util.sequence.Sequence;
import com.ettp.OracleJDBCConnection;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class CallsLog_pl 
{
  //les paramtres
  private Long CALL_LOG_ID= null;
  private String CALLED_NUMBER= null;
  private Long USER_PERSON_ID= null;
  private String CALL_TYPE= null;
  private Timestamp DATE_BEGIN_CALL= null;
  private Timestamp DATE_END_CALL= null;
  private Long SERVICE_ID= null;
  private String CALLER_NUMBER= null;
  private String ORIGIN_GROUP_CODE= null;
  //
  Connection con;
  PreparedStatement statement;
    
    
  public CallsLog_pl()
  {
    this.CALL_LOG_ID= null;
    this.CALLED_NUMBER=null;
    this.USER_PERSON_ID= null;
    this.CALL_TYPE= null;
    this.DATE_BEGIN_CALL= null;
    this.DATE_END_CALL= null;
    this.SERVICE_ID= null;
    this.CALLER_NUMBER= null;
    this.ORIGIN_GROUP_CODE= null;
    //System.out.println("CallsLog_pl - Fini initialisation CallsLog_pl");
  }
  
  protected void open_con()
  {
    try
    {
      
      con = OracleJDBCConnection.getJDBCConnection();
      //System.out.println("Hung - CallsLog_pl est pret a connecter DB");
    }
    catch(Exception ex)
    {
      System.out.println("CallsLog_pl: "+ex.getMessage());
      //System.out.println("CallsLog_pl n'est pas pret a connecter DB");
    }        
  }
  //destructure
  //protected void finalize() throws Throwable
  protected void close_con()
  {
    try{      
      //con.commit();
      statement.close();
      con.close();
      //System.out.println("CallsLog_pl a bien fini connexion");      
    }
    catch(Exception ex) {
      System.out.println("CallsLog_pl - "+ex.getMessage());
      //System.out.println("Hung - CallsLog_pl n'a pas bien fini connexion a cause de DB");
    }        
  }
  
  //**** partie qui remplace CallsLogLocalHome ****//
  
  public CallsLog_pl create(String calledNumber, Long userPersonId, Timestamp beginDate, String callType, 
    Long serviceId) throws CreateException, RemoteException, NamingException
  {
    Long id = null;

//    InitialContext ctx = new InitialContext();
//    SequenceHome sHome = getSequenceHome();
//    Sequence s = sHome.create();
    InitialContext ic = new InitialContext();
    SequenceRemote sr = (SequenceRemote) ic.lookup("squence");
    id = new Long(sr.getNextValue("CALLS_LOG_SEQ"));
    
    setCallLogId(id);
    setCalledNumber(calledNumber);
    setUserPersonId(userPersonId);
    setCallType(callType);
    setDateBeginCall(beginDate);
    setServiceId(serviceId);

    return this;        
  }
  
  
  //**** partie qui remplace CallsLogLocal ****//
  
  public Long getCallLogId(){return this.CALL_LOG_ID;}
  public void setCallLogId(Long callLogId)
  {
    this.CALL_LOG_ID= callLogId;
    
    String sql="insert into robot.CALLS_LOG (CALL_LOG_ID, USER_PERSON_ID, DATE_BEGIN_CALL, CALL_TYPE) "+
      "values (?, ?, ?, ?)";
    open_con();    
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setLong(1, callLogId);
      statement.setLong(2, 0);
      statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
      statement.setString(4, "I");
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a insere une nouvelle ligne.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("Hung - CallsLog_pl n'arrive pas d'inserer nouvelle ligne.");
    }
    close_con();
  }
    
  public String getCalledNumber(){return this.CALLED_NUMBER;}
  public void setCalledNumber(String calledNumber)
  {
    this.CALLED_NUMBER= calledNumber;
    
    String sql="update robot.CALLS_LOG set CALLED_NUMBER = '"+calledNumber+"' where CALL_LOG_ID= "+
    this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre called_number.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer called_number.");
    }  
    close_con();
  }
    
  public Long getUserPersonId(){return this.USER_PERSON_ID;}
  public void setUserPersonId(Long userPersonId)
  {
    this.USER_PERSON_ID= userPersonId;
    
    String sql="update robot.CALLS_LOG set USER_PERSON_ID = "+userPersonId.toString()+" where CALL_LOG_ID= "+
    this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre USER_PERSON_ID.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer USER_PERSON_ID.");
    }    
    close_con();
  }
    
  public String getCallType(){return this.CALL_TYPE;}
  public void setCallType(String callType)
  {
    this.CALL_TYPE= callType;
    
    String sql="update robot.CALLS_LOG set CALL_TYPE = '"+callType+"' where CALL_LOG_ID= "+
    this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre CALL_TYPE.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer CALL_TYPE.");
    }  
    close_con();
  }
    
  public Timestamp getDateBeginCall(){return this.DATE_BEGIN_CALL;}
  public void setDateBeginCall(Timestamp beginDate)
  {
    this.DATE_BEGIN_CALL= beginDate;
    
    String sql="update robot.CALLS_LOG set DATE_BEGIN_CALL = ?  where CALL_LOG_ID= "+
        this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setTimestamp(1, beginDate);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre DATE_BEGIN_CALL.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer DATE_BEGIN_CALL.");
    }
    close_con();
  }
    
  public Timestamp getDateEndCall(){return this.DATE_END_CALL;}
  public void setDateEndCall(Timestamp endDate)
  {
    this.DATE_END_CALL= endDate;
    
    String sql="update robot.CALLS_LOG set DATE_END_CALL = ?  where CALL_LOG_ID= "+
      this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setTimestamp(1, endDate);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre DATE_END_CALL.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer DATE_END_CALL.");
    }  
    close_con();
  }
    
  public Long getServiceId(){return this.SERVICE_ID;}
  public void setServiceId(Long serviceId)
  {
    this.SERVICE_ID= serviceId;
    
    String sql="update robot.CALLS_LOG set SERVICE_ID = ?  where CALL_LOG_ID= "+
    this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setLong(1, serviceId.longValue());
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre SERVICE_ID.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer SERVICE_ID.");
    }  
    close_con();
  }
    
  public String getCallerNumber(){return this.CALLER_NUMBER;}
  public void setCallerNumber(String callerNumber)
  {
    this.CALLER_NUMBER= callerNumber;
    
    String sql="update robot.CALLS_LOG set CALLER_NUMBER = ? where CALL_LOG_ID= "+
    this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setString(1, callerNumber);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre CALLER_NUMBER.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer CALLER_NUMBER.");
    }  
    close_con();
  }
    
  public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}
  public void setOriginGroupCode(String originGroupCode)
  {
    this.ORIGIN_GROUP_CODE= originGroupCode;
    
    String sql="update robot.CALLS_LOG set ORIGIN_GROUP_CODE = ? where CALL_LOG_ID= "+
    this.CALL_LOG_ID.toString();
    open_con();
    try{
      this.statement= this.con.prepareStatement(sql);
      statement.setString(1, originGroupCode);
      this.statement.executeUpdate();
      //System.out.println("CallsLog_pl a enregistre ORIGIN_GROUP_CODE.");
    }
    catch(Exception e)
    {
      System.out.println("CallsLog_pl - "+e.getMessage());
      //System.out.println("CallsLog_pl n'arrive pas d'enregistrer ORIGIN_GROUP_CODE.");
    }
    close_con();
  }  
}