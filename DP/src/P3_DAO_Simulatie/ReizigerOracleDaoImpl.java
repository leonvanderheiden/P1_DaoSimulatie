package P3_DAO_Simulatie;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDAO {	
	@Override
	public List<Reiziger> findAll() {
		List<Reiziger> alleReizigers = new ArrayList<Reiziger>();
		try(Connection con = super.getConnection()) {
			String query = "Select * from reiziger";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String naam = rs.getString("achternaam");
				int id = rs.getInt("reizigerid");
				Reiziger r = new Reiziger(naam, id);
				alleReizigers.add(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return alleReizigers;
	}
	public List<Reiziger> findByGBdatum(String GBdatum) {
		List<Reiziger> alleReizigers = new ArrayList<Reiziger>();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM reiziger WHERE gebortedatum = '" + GBdatum + "'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String naam = rs.getString("achternaam");
				int id = rs.getInt("reizigerid");
				Reiziger r = new Reiziger(naam, id);
				alleReizigers.add(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return alleReizigers;
	}
	public Reiziger save(Reiziger reiziger) throws SQLException {
		try {
		      getConnection();
		        String query = "INSERT INTO reiziger (reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement ps = conn.prepareStatement(query);
		        ps.setInt(1, reiziger.getID());
		        ps.setString(2, reiziger.getVoorletters());
		        ps.setString(3, reiziger.getTussenvoegsel());
		        ps.setString(4, reiziger.getAchternaam());
		        ps.setString(5, reiziger.getGBdatum());
		        ps.executeUpdate();
		        closeConnection();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
  

        return reiziger;
	}
	public Reiziger update(Reiziger reiziger) throws SQLException {
        getConnection();
        String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, gebortedatum = ? WHERE reizigerid = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, reiziger.getVoorletters());
        ps.setString(2, reiziger.getTussenvoegsel());
        ps.setString(3, reiziger.getAchternaam());
        ps.setString(4, reiziger.getGBdatum());
        ps.setInt(5, reiziger.getID());
        ps.executeUpdate();
        closeConnection();

        return reiziger;
	}
	public boolean delete(Reiziger reiziger) throws SQLException {
        getConnection();
        boolean canDelete = true;
            String query = "DELETE FROM reiziger WHERE reizigerid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getID());
            ps.executeQuery();

        closeConnection();
        return canDelete;
	}

}
