package pe.gob.dmn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.dmn.dao.ReportesDAO;
import pe.gob.dmn.model.ReporteDemunasMeses;
import pe.gob.dmn.model.ReporteDemunasMesesMaterias;
import pe.gob.dmn.model.ReporteDemunasTodo;


@Repository
public class ReportesDAOImpl extends HibernateDaoSupport implements ReportesDAO {

	@Autowired
	public ReportesDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasTodo> getReporteDemunasTodo(){		
		
		Query query = getSession().createQuery(" select  count(e.demuna) "+
				" as casos,e.demuna.kydemuna as kydependencia," +
				" d.nombre as demuna " +
				" from Expediente as e " +
				" inner join e.demuna as d " +
				" group by e.demuna,d.nombre ");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasTodo.class));
		
		return (List<ReporteDemunasTodo>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasTodo> getReporteDemunasTodoPorFecha(String ini, String fin){		
		
		Query query = getSession().createQuery(" select  count(e.demuna) "+
				" as casos,e.demuna.kydemuna as kydependencia," +
				" d.nombre as demuna " +
				" from Expediente as e " +
				" inner join e.demuna as d " +
				" where  to_date(e.fecha,'dd-mm-yyyy') between to_date(:ini,'dd-mm-yyyy') and to_date(:fin,'dd-mm-yyyy')" +
				" group by e.demuna,d.nombre ");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasTodo.class));
		query.setString("ini", ini);
		query.setString("fin", fin);
		
		return (List<ReporteDemunasTodo>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasMeses> getReporteDemunasMeses(Integer kydemuna){		
		
		Query query = getSession().createQuery(" select  count(e.kyexpedientemateria) as casos,"+
				" d.nombre as demuna, " +
				" e.demuna.kydemuna as kydependencia, " +
				" substring(e.expediente.fecha,4,2) as Meses " +
				" from ExpedientesMateria as e " +
				" inner join e.demuna as d " +
				" where  e.demuna.kydemuna = :kydemuna" +
				" group by substring(e.expediente.fecha,4,2),e.demuna,d.nombre ");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasMeses.class));
		query.setInteger("kydemuna", kydemuna);
		
		return (List<ReporteDemunasMeses>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasMesesMaterias(Integer kydemuna){		
		
		Query query = getSession().createQuery(" select  count(e.kyexpedientemateria) as casos,"+
				" m.kymateria as kymateria, " +	
				" m.materia as materia, " +				
				" substring(e.expediente.fecha,4,2) as meses " +
				" from ExpedientesMateria as e " +
				" inner join e.materia as m " +
				" where  e.demuna.kydemuna = :kydemuna" +
				" group by substring(e.expediente.fecha,4,2),m.kymateria,m.materia ");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasMesesMaterias.class));
		query.setInteger("kydemuna", kydemuna);
		
		return (List<ReporteDemunasMesesMaterias>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasGrafico(Integer kydemuna){		

		Query query = getSession().createQuery(" select count(e.kyexpedientemateria) as casos,"+
				" m.kymateria as kymateria, " +	
				" m.materia as materia " +			
				" from ExpedientesMateria as e " +
				" inner join e.materia as m " +
				" where  e.demuna.kydemuna = :kydemuna" +
				" group by m.kymateria,m.materia ");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasMesesMaterias.class));
		query.setInteger("kydemuna", kydemuna);
		
		return (List<ReporteDemunasMesesMaterias>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasMesesMaterias> getReporteDemunasGraficoFecha(
			Integer kydemuna, String ini, String fin){		

		Query query = getSession().createQuery(" select count(e.kyexpedientemateria) as casos,"+
				" m.kymateria as kymateria, " +	
				" m.materia as materia " +			
				" from ExpedientesMateria as e " +
				" inner join e.materia as m " +
				" where  e.demuna.kydemuna = :kydemuna and " + 
				" to_date(e.expediente.fecha,'dd-mm-yyyy') between to_date(:ini,'dd-mm-yyyy') and to_date(:fin,'dd-mm-yyyy')" +
				" group by m.kymateria,m.materia ");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasMesesMaterias.class));
		query.setInteger("kydemuna", kydemuna);
		
		query.setString("ini", ini);
		query.setString("fin", fin);
		
		return (List<ReporteDemunasMesesMaterias>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDemunasMesesMaterias> 
			getReporteDemunasMesesMateriasPorMes(Integer kydemuna, String mes){		

		Query query = getSession().createQuery(" select  count(e.kyexpedientemateria) as casos,"+
				" m.materia as materia, " +		
				" m.kymateria as kymateria, " +							
				" substring(e.expediente.fecha,4,2) as meses " +
				" from ExpedientesMateria as e " +
				" inner join e.materia as m " +
				" where  e.demuna.kydemuna = :kydemuna " + 
				" and substring(e.expediente.fecha,4,2) = :mes " +
				" group by m.kymateria,m.materia,substring(e.expediente.fecha,4,2)");
		
		query.setResultTransformer(Transformers.aliasToBean(ReporteDemunasMesesMaterias.class));
		query.setInteger("kydemuna", kydemuna);
		query.setString("mes", mes);
		return (List<ReporteDemunasMesesMaterias>)query.list();
	}
}