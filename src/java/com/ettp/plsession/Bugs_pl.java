package com.ettp.plsession;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Bugs_pl
{
    //les parametres
    private Long ID= null;
    private Timestamp DATE_BUG= null;
    private String DESCRIPTION= null;
    private String AUTEUR= null;
    private String ETAT= null;
    private String ORIGIN_GROUP_CODE= null;

  public Bugs_pl()
  {
    this.ID= null;
    this.DATE_BUG= null;
    this.DESCRIPTION= null;
    this.AUTEUR= null;
    this.ETAT= null;
    this.ORIGIN_GROUP_CODE= null;
    //System.out.println("Bugs_pl - Fini initialisation Bugs_pl");
  }
  //**** partie quiremplace BugsLocalHome ****//
  public Bugs_pl create(Timestamp date, String description, String auteur, String etat, String originGroupCode)
  {
    setId(new Long(0));
    setDateBug(date);
    setDescription(description);
    setAuteur(auteur);
    setEtat(etat);
    setOriginGroupCode(originGroupCode);
    //Creer en memme temps tous les champs
    try{     
      Connection con = OracleJDBCConnection.getJDBCConnection();
      String sql = "insert into ettp.BUGS (ID, DATE_BUG, DESCRIPTION, AUTEUR, ETAT, ORIGIN_GROUP_CODE) values (?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setLong(1, 0);
      statement.setTimestamp(2, date);
      statement.setString(3, description);
      statement.setString(4, auteur);
      statement.setString(5, etat);
      statement.setString(6, originGroupCode);
      statement.executeUpdate();
      con.commit();
      statement.close();
      con.close();
      //System.out.println("Bugs_pl - avoir cree BUG");
    }
    catch(Exception ex) {
      //System.out.println("Hung - "+ex.getMessage());
      System.out.println("Bugs_pl - "+ex.toString());
      //System.out.println("Hung - avoir problem de connection DB de Bugs_pl");
    }
    return this;
  }
  //**** partie qui remplace BugsLocal ****//
    public Long getId(){return this.ID;}
    public void setId(Long ID){
      this.ID= ID;

    }

    public Timestamp getDateBug(){return this.DATE_BUG;}
    public void setDateBug(Timestamp DATE_BUG){
      this.DATE_BUG= DATE_BUG;

    }

    public String getDescription(){return this.DESCRIPTION;}
    public void setDescription(String DESCRIPTION){
      this.DESCRIPTION= DESCRIPTION;

    }

    public String getAuteur(){return this.AUTEUR;}
    public void setAuteur(String AUTEUR){
      this.AUTEUR= AUTEUR;

    }

    public String getEtat(){return this.ETAT;}
    public void setEtat(String ETAT){
      this.ETAT= ETAT;

    }

    public String getOriginGroupCode(){return this.ORIGIN_GROUP_CODE;}
    public void setOriginGroupCode(String ORIGIN_GROUP_CODE){
      this.ORIGIN_GROUP_CODE= ORIGIN_GROUP_CODE;

    }
}