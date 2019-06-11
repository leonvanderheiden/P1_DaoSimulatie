package P2_DAO_Simulatie;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {
	public List<Reiziger> findAll();
	public List<Reiziger> findByGBdatum(String GBdatum);
	public Reiziger save(Reiziger reiziger) throws SQLException;
	public Reiziger update(Reiziger reiziger) throws SQLException;
	public boolean delete(Reiziger reiziger) throws SQLException;
}
