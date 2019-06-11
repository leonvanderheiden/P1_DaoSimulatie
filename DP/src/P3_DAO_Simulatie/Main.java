package P3_DAO_Simulatie;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
	public static void main(String[] args) throws ParseException, SQLException {
		
		ProductOracleDAOImpl p = new ProductOracleDAOImpl();
		OVChipkaartOracleDAOImpl ov = new OVChipkaartOracleDAOImpl();
		for (Product pr : p.findProducten()) {
			System.out.println(pr);
			String test = "Eigenaren van de kaarten: ";
			for (Integer info : pr.getKaarten()) {
				test += ov.findByKaart(info).getEigenaar() + " ";
			}
			System.out.println(test);
		}
		
		System.out.println("\n");
		for (OVChipkaart o : ov.findAll()) {
			System.out.println(o);
		}
		
		System.out.println("\nSaven van het nieuwe product:");
		
		Product product = new Product(7, "Studenten OV", "Gratis in het weekend", 0);
		p.save(product);
		
		String lijst = "";
		for (Product pr : p.findProducten()) { lijst += "[" + pr.getNaam() + ": " + pr.getKaarten() + "] ";}
		System.out.println(lijst);
		
		product.setBeschrijving("Gratis doordeweeks");
		p.update(product);
		System.out.println("\nUpdaten van het product:"); lijst = "";
		for (Product pr : p.findProducten()) { lijst += "[" + pr.getNaam() + ": " + pr.getKaarten() + "] ";}
		System.out.println(lijst);
		
		p.delete(product);
		System.out.println("\nVerwijderen van het product:"); lijst = "";
		for (Product pr : p.findProducten()) { lijst += "[" + pr.getNaam() + ": " + pr.getKaarten() + "] ";}
		System.out.println(lijst);
		
	}
}
