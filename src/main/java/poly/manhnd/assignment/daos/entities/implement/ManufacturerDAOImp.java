package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.ManufacturerDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.Manufacturer;

public class ManufacturerDAOImp extends DAOImp implements ManufacturerDAO {

	@Override
	public boolean createManufacturer(Manufacturer m) throws Exception {
		return create(m);
	}

	@Override
	public boolean updateManufacturer(Manufacturer m) throws Exception {
		return update(m);
	}

	@Override
	public boolean deleteManufacturer(Manufacturer m) throws Exception {
		return delete(m);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getAllManufacturers() throws Exception {
		return (List<Manufacturer>) getAllObjects("FROM Manufacturer");
	}

	@Override
	public Manufacturer getManufacturer(int id) throws Exception {
		return (Manufacturer) getObject(Manufacturer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getListManufacturers(int startPosition, int maxResult) throws Exception {
		return (List<Manufacturer>) getObjects("FROM Manufacturer", startPosition, maxResult);
	}

	@Override
	public int getTotalManufacturer() throws Exception {
		return (int) getTotalRecords("Manufacturer");
	}

}
