package P1_DAO_Simulatie;

import java.util.List;

public interface ReizigerDAO {
	public List<Reiziger> findAll();
	public List<Reiziger> findByGBdatum(String GBdatum);
	public Reiziger save(Reiziger reiziger);
	public Reiziger update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
	public void closeConnection();
}
