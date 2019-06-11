package P3_DAO_Simulatie;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDao {
	public List<Product> findByKaartnummer(int nr);
	public List<Integer> findByProduct(int nr);
	public List<OVChipkaart> findAll();
	public OVChipkaart findByKaart(int kn);
	public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
	public OVChipkaart update(OVChipkaart ov) throws SQLException;
	public boolean delete(OVChipkaart ov) throws SQLException;
	public OVChipkaart save(OVChipkaart ov, Reiziger reiziger) throws SQLException;
}
