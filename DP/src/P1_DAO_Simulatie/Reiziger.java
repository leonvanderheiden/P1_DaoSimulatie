package P1_DAO_Simulatie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reiziger {
	private String naam;
	private Date gbdatum;
	
	public Reiziger(String nm, String dat) throws ParseException {
		naam = nm;
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dat);
		gbdatum = date1;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String nm) {
		naam = nm;
	}
	public Date getGBdatum() {
		return gbdatum;
	}
	public void setGBdatum(Date dat) {
		gbdatum = dat;
	}
	public String toString() {
		return naam;
	}
}
