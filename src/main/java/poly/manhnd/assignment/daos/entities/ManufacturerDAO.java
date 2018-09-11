package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.Manufacturer;

public interface ManufacturerDAO {
	
	public boolean createManufacturer(Manufacturer m) throws Exception;

	public boolean updateManufacturer(Manufacturer m) throws Exception;

	public boolean deleteManufacturer(Manufacturer m) throws Exception;

	public List<Manufacturer> getAllManufacturers() throws Exception;

	public Manufacturer getManufacturer(int id) throws Exception;

	public List<Manufacturer> getListManufacturers(int startPosition, int maxResult) throws Exception;

	public int getTotalManufacturer() throws Exception;

}
