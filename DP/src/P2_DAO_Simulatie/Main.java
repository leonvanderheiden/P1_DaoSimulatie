package P2_DAO_Simulatie;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
	public static void main(String[] args) throws ParseException, SQLException {
		
		ReizigerOracleDaoImpl r = new ReizigerOracleDaoImpl();
		System.out.println("Reiziger find all:");
		System.out.println(r.findAll());
		
		System.out.println("\nNieuwe reiziger aanmaken (S. de Boor):");
		Reiziger r2 = new Reiziger("Boor", "22-01-99");
		r2.setID(10); r2.setVoorletters("S"); r2.setTussenvoegsel("De");
		if(r.save(r2) != null) {
			System.out.println("Reiziger opgeslagen");
		};
		
		System.out.println("\nWijzig de Boor naar de Boer:");
		
		String achternamen = "[ ";
		for (Reiziger rr : r.findAll()) {
			achternamen += rr.getAchternaam() + " ";
		}
		achternamen += "]";
		System.out.println("Alle achternamen: " + achternamen);
		r2.setAchternaam("Boer");
		if(r.update(r2) != null) {
			System.out.println("kaart gewijzigd");
		};
		achternamen = "[ ";
		for (Reiziger rr : r.findAll()) {
			achternamen += rr.getAchternaam() + " ";
		}
		achternamen += "]";
		System.out.println("Alle achternamen: " + achternamen);		

		System.out.println("\nKaart toevoegen:");
		OVChipkaartOracleDAOImpl o = new OVChipkaartOracleDAOImpl();
	    OVChipkaart ov = new OVChipkaart();
	    ov.setKaartNummer(44444);
	    ov.setGeldigTot(java.sql.Date.valueOf("1999-03-03"));
	    ov.setSaldo(44);
	    ov.setKlasse(2);
	    ov.setEigenaar(r2);
	    
	    System.out.println("Aantal kaarten: " + o.findAll().size());  	    
		if(o.save(ov, r2) != null) {
			System.out.println("kaart toegevoegt");
		};
		System.out.println("Aantal kaarten: " + o.findAll().size());
		
		System.out.println("\nFind by reiziger:");
		for (OVChipkaart rr : o.findByReiziger(r2)) {
			System.out.println(rr.getEigenaar().getAchternaam());
		}
		
		System.out.println("\nKaart updaten:");
		String kaarten = "[ ";
		for (OVChipkaart c : o.findAll()) {
			kaarten += c.getSaldo() + " ";
		}
		kaarten += "]";
		System.out.println("Alle saldo's: " + kaarten);
		
		ov.setSaldo(12);
		if(o.update(ov) != null) {
			System.out.println("kaart gewijzigd");
		};
		
	    kaarten = "[ ";
		for (OVChipkaart c : o.findAll()) {
			kaarten += c.getSaldo() + " ";
		}
		kaarten += "]";
		System.out.println("Alle saldo's: " + kaarten);
		
		System.out.println("\nDelete uitvoeren:");
		System.out.println("Aantal kaarten: " + o.findAll().size());
		System.out.println("Kaart verwijderd");
		o.delete(ov);    
		System.out.println("Aantal kaarten: " + o.findAll().size());
		
		System.out.println("\nReiziger verwijderen:");
		System.out.println("Aantal reizigers: " + r.findAll().size());
		r.delete(r2);
		System.out.println("Reiziger verwijderd.");
		System.out.println("Aantal reizigers: " + r.findAll().size());
	}
}
