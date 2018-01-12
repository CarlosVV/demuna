package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.LocationDAO;
import pe.gob.dmn.model.Location;
@Repository
public class LocationDAOImpl extends HibernateDaoSupport implements LocationDAO {


	@Autowired
	public LocationDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> listarDepartamentos() {
		return getHibernateTemplate().find(" from Location where codprov='00' and coddist='00' order by coddpto ");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> listarProvincias(String Departamento) {
		return getHibernateTemplate().find(" from Location where coddpto='"+ Departamento +"' and codprov<>'00' and coddist='00' order by codprov ");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> listarDistritos(String Departamento, String Provincia) {
		return getHibernateTemplate().find(" from Location where coddpto='"+ Departamento +"' and codprov='"+ Provincia +"' and coddist<>'00' order by coddist ");
	}

}
