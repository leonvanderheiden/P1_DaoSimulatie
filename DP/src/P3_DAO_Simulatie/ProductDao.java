package P3_DAO_Simulatie;

import java.util.List;

public interface ProductDao {
	public List<Integer> createKaarten(int productnummer);
	public List<Product> findProducten();
	public List<Product> findByKaartnummer(int nr);
	public Product findByProductnummer(int nr);
	public Product save(Product pr);
	public Product update(Product pr);
	public boolean delete(Product pr);
}
