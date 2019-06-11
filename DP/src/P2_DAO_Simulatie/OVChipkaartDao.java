package P2_DAO_Simulatie;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDao {
	public List<OVChipkaart> findAll();
	public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
	public OVChipkaart update(OVChipkaart ov) throws SQLException;
	public boolean delete(OVChipkaart ov) throws SQLException;
	public OVChipkaart save(OVChipkaart ov, Reiziger reiziger) throws SQLException;
}
