package P3_DAO_Simulatie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOracleDAOImpl extends OracleBaseDao implements ProductDao {
	
	public List<Integer> createKaarten(int productnummer) {
		List<Integer> lijst = new ArrayList<Integer>();
		OVChipkaartOracleDAOImpl ov = new OVChipkaartOracleDAOImpl();
		lijst = ov.findByProduct(productnummer);
		return lijst;
	}
	public Product findByProductnummer(int nr) {
		Product p = new Product();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM product WHERE productnummer = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, nr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				p.setNummer(rs.getInt("productnummer"));
				p.setNaam(rs.getString("productnaam"));
				p.setBeschrijving(rs.getString("beschrijving"));
				p.setPrijs(rs.getDouble("prijs"));	
				p.setKaarten(this.createKaarten(rs.getInt("productnummer")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return p;
	}
	public List<Product> findProducten() {
		List<Product> lijst = new ArrayList<Product>();
		try(Connection con = super.getConnection()) {
			String query = "SELECT * FROM product";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setNummer(rs.getInt("productnummer"));
				p.setNaam(rs.getString("productnaam"));
				p.setBeschrijving(rs.getString("beschrijving"));
				p.setPrijs(rs.getDouble("prijs"));
				p.setKaarten(this.createKaarten(rs.getInt("productnummer")));
				lijst.add(p);		
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lijst;
	}
	public Product save(Product pr) {
		try(Connection con = super.getConnection()) {
			String query = "INSERT INTO product (productnummer, productnaam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, pr.getNummer());
			ps.setString(2, pr.getNaam());
			ps.setString(3, pr.getBeschrijving());
			ps.setDouble(4, pr.getPrijs());
			ps.executeQuery();		
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return pr;
	}
	public Product update(Product pr) {
		try(Connection con = super.getConnection()) {
			String query = "UPDATE product SET productnaam = ?, beschrijving = ?, prijs = ? WHERE productnummer = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(4, pr.getNummer());
			ps.setString(1, pr.getNaam());
			ps.setString(2, pr.getBeschrijving());
			ps.setDouble(3, pr.getPrijs());
			ps.executeQuery();		
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return pr;
	}
	public boolean delete(Product pr) {
		boolean canDelete = false;
		try(Connection con = super.getConnection()) {
			String query = "DELETE FROM product WHERE productnummer = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, pr.getNummer());
			ps.executeQuery();
			canDelete = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return canDelete;
	}
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
				p.setKaarten(this.createKaarten(rs.getInt("productnummer")));
				lijst.add(p);		
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lijst;
	}
}
