package P3_DAO_Simulatie;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private int productNummer;
	private String productNaam;
	private String beschrijving;
	private double prijs;
	private List<Integer> kaartnummers = new ArrayList<Integer>();
	
	public Product() { }
	public Product(int nr, String nm, String bes, double pr) {
		productNummer = nr;
		productNaam = nm;
		beschrijving = bes;
		prijs = pr;
	}
	public int getNummer() {
		return productNummer;
	}
	public String getNaam() {
		return productNaam;
	}
	public String getBeschrijving() {
		return beschrijving;
	}	
	public double getPrijs() {
		return prijs;
	}
	public List<Integer> getKaarten() {
		return kaartnummers;
	}
	public void setNummer(int nr) {
		productNummer = nr;
	}
	public void setNaam(String nm) {
		productNaam = nm;
	}
	public void setBeschrijving(String bes) {
		beschrijving = bes;
	}
	public void setPrijs(double pr) {
		prijs = pr;
	}
	public void setKaarten(List<Integer> ov) {
		kaartnummers = ov;
	}
	public void addKaart(Integer ov) {
		kaartnummers.add(ov);
	}
	public String toString() {
		return productNummer + ", " + productNaam + ", " + beschrijving + ", " + prijs + " (Kaarten: " + this.getKaarten() + ")";
	}
}
