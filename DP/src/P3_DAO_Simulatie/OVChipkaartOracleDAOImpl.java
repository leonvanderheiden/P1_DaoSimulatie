package P3_DAO_Simulatie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class OVChipkaartOracleDAOImpl extends OracleBaseDao implements OVChipkaartDao {
	public List<Product> findByKaartnummer(int nr) {
		List<Product> lijst = new ArrayList<Product>();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM product p, ov_chipkaart ov, ov_chipkaart_product ovp "
		    + "WHERE p.productnummer = ovp.productnummer AND ov.kaartnummer = ovp.kaartnummer AND ov.kaartnummer = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, nr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setNummer(rs.getInt("productnummer"));
				p.setNaam(rs.getString("productnaam"));
				p.setBeschrijving(rs.getString("beschrijving"));
				p.setPrijs(rs.getDouble("prijs"));
				lijst.add(p);		
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lijst;
	}
	public List<Integer> findByProduct(int nr) {
		List<Integer> lijst = new ArrayList<Integer>();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM product p, ov_chipkaart ov, ov_chipkaart_product ovp "
		    + "WHERE p.productnummer = ovp.productnummer AND ov.kaartnummer = ovp.kaartnummer AND p.productnummer = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, nr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lijst.add(rs.getInt("kaartnummer"));			
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lijst;
	}
	
	public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
		getConnection();
        String query = "SELECT * FROM OV_chipkaart WHERE reizigerid = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, reiziger.getID());
        ResultSet rs = ps.executeQuery();
        int kaartnr;
        Date geldigtot;
        int klasse;
        float saldo;
        ArrayList<OVChipkaart> result = new ArrayList<OVChipkaart>();
        while (rs.next()) {
            kaartnr = rs.getInt("kaartnummer");
            geldigtot = rs.getDate("geldigtot");
            klasse = rs.getInt("klasse");
            saldo = rs.getFloat("saldo");
            OVChipkaart ov = new OVChipkaart(kaartnr, geldigtot, klasse, saldo);
            reiziger.getKaarten().add(ov);
            ov.setEigenaar(reiziger);
            ov.setProducten(this.findByKaartnummer(rs.getInt("kaartnummer")));
            result.add(ov);
        }
        return result;
	}

	@Override
	public List<OVChipkaart> findAll() {
		List<OVChipkaart> lijst = new ArrayList<OVChipkaart>();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM ov_chipkaart";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OVChipkaart kaart = new OVChipkaart();
				kaart.setKaartNummer(rs.getInt("kaartnummer"));
				kaart.setGeldigTot(rs.getDate("geldigtot"));
				kaart.setKlasse(rs.getInt("klasse"));
				kaart.setSaldo(rs.getFloat("saldo"));
				kaart.setEigenaar(findByID(rs.getInt("reizigerid")));
				kaart.setProducten(this.findByKaartnummer(rs.getInt("kaartnummer")));
				lijst.add(kaart);		
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lijst;
	}
	public OVChipkaart findByKaart(int kn) {
		OVChipkaart kaart = new OVChipkaart();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM ov_chipkaart WHERE kaartnummer = '" + kn + "'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				kaart.setKaartNummer(rs.getInt("kaartnummer"));
				kaart.setGeldigTot(rs.getDate("geldigtot"));
				kaart.setKlasse(rs.getInt("klasse"));
				kaart.setSaldo(rs.getFloat("saldo"));
				kaart.setEigenaar(findByID(rs.getInt("reizigerid")));
				kaart.setProducten(this.findByKaartnummer(rs.getInt("kaartnummer")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return kaart;
	}
	public Reiziger findByID(int ID) {
		Reiziger r = new Reiziger();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM reiziger WHERE reizigerid = '" + ID + "'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r.setID(rs.getInt("reizigerid"));
				r.setAchternaam(rs.getString("achternaam"));
				r.setVoorletters(rs.getString("voorletters"));
				r.setTussenvoegsel(rs.getString("tussenvoegsel"));
				r.setGBdatum(rs.getString("gebortedatum"));	
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return r;
	}

	@Override
	public OVChipkaart update(OVChipkaart ov) throws SQLException {
		try {
	        getConnection();
	        String query = "UPDATE OV_chipkaart SET geldigtot = ?, klasse = ?, saldo = ?, reizigerID = ? WHERE kaartnummer = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setDate(1, ov.getGeldigTot());
	        ps.setInt(2, ov.getKlasse());
	        ps.setFloat(3, ov.getSaldo());
	        ps.setInt(4, ov.getEigenaar().getID());
	        ps.setInt(5, ov.getKaartNummer());
	        ps.executeUpdate();
	        closeConnection();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return ov;
	}

	@Override
	public boolean delete(OVChipkaart ov) throws SQLException {
        boolean canDelete = true;
        try {
            getConnection();
            String query = "DELETE FROM OV_Chipkaart WHERE kaartnummer = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ov.getKaartNummer());
            ps.executeQuery();
            closeConnection();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            canDelete = false;
        }
        return canDelete;
	}

	@Override
	public OVChipkaart save(OVChipkaart ov, Reiziger reiziger) throws SQLException {
		try {
			   getConnection();
		        String query = "INSERT INTO ov_chipkaart (kaartnummer, geldigtot, klasse, saldo, reizigerid) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement ps = conn.prepareStatement(query);		        
		        ps.setDate(2, ov.getGeldigTot());
		        ps.setInt(3, ov.getKlasse());
		        ps.setDouble(4, ov.getSaldo());
		        ps.setInt(5, ov.getEigenaar().getID());
		        ps.setInt(1, ov.getKaartNummer());
		        ps.executeQuery();
		        closeConnection();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
     
        return ov;
	}
}
