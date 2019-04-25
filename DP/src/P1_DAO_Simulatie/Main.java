package P1_DAO_Simulatie;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Main {
	public static void main(String[] args) throws ParseException {
		
		ReizigerOracleDaoImpl reizigerDao = new ReizigerOracleDaoImpl();
		
		Reiziger r1 = new Reiziger("Piet", "1/1/1999");
		System.out.println(r1.getNaam() + "  -  " + r1.getGBdatum());
		reizigerDao.save(r1);
		
		Reiziger r2 = new Reiziger("Jan", "2/2/1999");
		System.out.println(r2.getNaam() + "  -  " + r2.getGBdatum());
		reizigerDao.save(r2);
		
		Reiziger r3 = new Reiziger("Kees", "3/3/1999");
		System.out.println(r3.getNaam() + "  -  " + r3.getGBdatum());
		reizigerDao.save(r3);
		
		System.out.println(reizigerDao.findAll());
		
		reizigerDao.delete(r2);
		
		System.out.println(reizigerDao.findAll());
		
		System.out.println(reizigerDao.findByGBdatum("1/1/1999"));	
	}
}
