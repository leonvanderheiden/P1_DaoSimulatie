package P1_DAO_Simulatie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReizigerOracleDaoImpl implements ReizigerDAO {
	private List<Reiziger> reizigers = new ArrayList<Reiziger>();
	
	@Override
	public List<Reiziger> findAll() {
		return reizigers;
	}
	public List<Reiziger> findByGBdatum(String GBdatum) {
		List<Reiziger> newList = new ArrayList<Reiziger>();
		for (Reiziger r : reizigers) {
			try 
			{
				Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(GBdatum);
				if (date1.equals(r.getGBdatum())) {
					newList.add(r);
				}
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}  
		}
		return newList;
	}
	public Reiziger save(Reiziger reiziger) {
		if (!reizigers.contains(reiziger)) {
			reizigers.add(reiziger);
		}
		return reiziger;
	}
	public Reiziger update(Reiziger reiziger) {
		for (Reiziger r : reizigers) {
			if (r.getNaam() == reiziger.getNaam()) {
				reizigers.remove(r);
				reizigers.add(reiziger);
			}
		}
		return reiziger;
	}
	public boolean delete(Reiziger reiziger) {
		boolean canDelete = false;
		for (Reiziger r : reizigers) {
			if (r == reiziger) {
				reizigers.remove(r);
				canDelete = true;
			}
		}
		return canDelete;
	}
	public void closeConnection() {
		//voor nu nog leeg
	}
}
