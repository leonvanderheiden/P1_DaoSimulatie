package P2_DAO_Simulatie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reiziger {
	private String naam;
	private String voorletters;
	private String tussenvoegsel;
	private String gbdatum;
	private int id;
	private List<OVChipkaart> kaarten = new ArrayList<OVChipkaart>();
	
	public Reiziger() {	}
	public Reiziger(String nm, int id)  {
		naam = nm;
		this.id = id;
	}
	public Reiziger(String nm, String dat) throws ParseException {
		naam = nm;
		gbdatum = dat;
	}

	public String getGBdatum() {
		return gbdatum;
	}
	public void setGBdatum(String dat) {
		gbdatum = dat;
	}
	public String toString() {
		return "naam: "+ naam + " id: " + id;
	}
	public void setID(int ID) {
		id = ID;
	}
	public String getAchternaam() {
		return naam;
	}
	public void setAchternaam(String an) {
		naam = an;
	}
	public void setVoorletters(String vl) {
		voorletters = vl;
	}
	public void setTussenvoegsel(String tv) {
		tussenvoegsel = tv;
	}
	public String getVoorletters() {
		return voorletters;
	}
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	public int getID() {
		return id;
	}
	public void setKaarten(List<OVChipkaart> k) {
		kaarten = k;
	}
	public List<OVChipkaart> getKaarten() {
		return kaarten;
	}
}
