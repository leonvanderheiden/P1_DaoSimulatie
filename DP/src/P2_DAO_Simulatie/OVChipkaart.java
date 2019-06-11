package P2_DAO_Simulatie;

import java.sql.Date;

public class OVChipkaart {
	private int kaartNummer;
	private Date geldigTot;
	private int klasse;
	private float saldo;
	private Reiziger eigenaar;
	
	public OVChipkaart() { }
	public OVChipkaart(int kn, Date g, int k, float s) {
		kaartNummer = kn;
		g = geldigTot;
		klasse = k;
		saldo = s;
	}
	public int getKaartNummer() {
		return kaartNummer;
	}
	public void setKaartNummer(int kn) {
		kaartNummer = kn;
	}
	public Date getGeldigTot() {
		return geldigTot;
	}
	public void setGeldigTot(Date dat) {
		geldigTot = dat;
	}
	public int getKlasse() {
		return klasse;
	}
	public void setKlasse(int kl) {
		klasse = kl;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float s) {
		saldo = s;
	}
	public void setEigenaar(Reiziger r) {
		eigenaar = r;
	}
	public Reiziger getEigenaar() {
		return eigenaar;
	}
	public String toString() {
		return "test";
	}
}

