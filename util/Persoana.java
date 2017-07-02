/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import util.*;
import java.util.*;
import java.sql.*;
import com.mysql.jdbc.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andu
 */
public class Persoana {
    static Connection c;

    String nume, prenume, telmobil, telfix, email, adresa, judet, oras, cod;
    int id;


    public Persoana() {
        this.initDb();
    }

    public Persoana(String nume, String prenume, String telmobil, String telfix, String email, String adresa, String judet, String oras, String cod) {
        this.initDb();

        this.nume = nume;
        this.prenume = prenume;
        this.telmobil = telmobil;
        this.telfix = telfix;
        this.email = email;
        this.adresa = adresa;
        this.judet = judet;
        this.oras = oras;
        this.cod = cod;
    }

    public void load(int id) {
        try {
            this.id = id;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM agenda where id='"+this.id+"'");
            while (rs.next()) {
            this.nume=rs.getString("nume");
            this.prenume=rs.getString("prenume");
            this.telmobil=rs.getString("telmobil");
            this.telfix=rs.getString("telfix");
            this.email=rs.getString("email");
            this.adresa=rs.getString("adresa");
            this.judet=rs.getString("judet");
            this.oras=rs.getString("oras");
            this.cod=rs.getString("cod");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persoana.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList cauta(String query) {
        ArrayList rezultate = new ArrayList();
        
        return rezultate;
    }

    public ArrayList lista() {
        ArrayList rezultate = new ArrayList();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM agenda ORDER BY id ASC");
            while (rs.next()) {
                Persoana p = new Persoana(
                rs.getString("nume"),
                rs.getString("prenume"),
                rs.getString("telmobil"),
                rs.getString("telfix"),
                rs.getString("email"),
                rs.getString("adresa"),
                rs.getString("judet"),
                rs.getString("oras"),
                rs.getString("cod"));

                p.id = rs.getInt("id");

                rezultate.add(p);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rezultate;
    }
     public ArrayList searchlist(String nume , String prenume, String adresa) {
         //nume=""; prenume=""; adresa="";

        ArrayList rezultate = new ArrayList();
        

        try {
            String sql="SELECT * FROM agenda where 1<>1 ";
            if (!nume.equals("")) sql=sql+"or nume like '"+nume+"' ";
            if (!prenume.equals("")) sql=sql+"or prenume like '"+prenume+"' ";
            if (!adresa.equals("")) sql=sql+"or email like '"+adresa+"' ";
            sql=sql+"order by id asc";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
             /*String sqlp="SELECT * FROM agenda ";
             if (!prenume.equals("")) sql=sql+"where prenume like '"+prenume+"'";
            sqlp=sqlp+"order by id asc";
            Statement sp = c.createStatement();
            ResultSet rs = sp.executeQuery(sql);*/

            while (rs.next()) {
                Persoana p = new Persoana(
                rs.getString("nume"),
                rs.getString("prenume"),
                rs.getString("telmobil"),
                rs.getString("telfix"),
                rs.getString("email"),
                rs.getString("adresa"),
                rs.getString("judet"),
                rs.getString("oras"),
                rs.getString("cod"));

                p.id = rs.getInt("id");

                rezultate.add(p);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rezultate;
    }

    public void save() {
        try {
            // daca p.id e setat atunci ar trebui facut un update si nu un insert
            PreparedStatement p = c.prepareStatement("INSERT INTO agenda (nume, prenume, telmobil, telfix, email, adresa, judet, oras, cod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            p.setString(1, nume);
            p.setString(2, prenume);
            p.setString(3, telmobil);
            p.setString(4, telfix);
            p.setString(5, email);
            p.setString(6, adresa);
            p.setString(7, judet);
            p.setString(8, oras);
            p.setString(9, cod);
            p.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void modifica(){
        try{
    PreparedStatement p = c.prepareStatement("update agenda set nume='"+this.nume+"', " +
            "prenume='"+this.prenume+"', telmobil='"+this.telmobil+"', telfix='"+this.telfix+"'," +
            " email='"+this.email+"', adresa='"+this.adresa+"', judet='"+this.judet+"', oras='"+this.oras+"'," +
            " cod='"+this.cod+"' where id='"+this.id+"'");
          
            p.executeUpdate();
           

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }



public void sterge(){
        try {
            PreparedStatement p = c.prepareStatement("DELETE FROM agenda WHERE id='"+this.id+"'");
            p.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Persoana.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    void initDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/laborator", "root", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
