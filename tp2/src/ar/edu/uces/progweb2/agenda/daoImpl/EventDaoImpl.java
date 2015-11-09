package ar.edu.uces.progweb2.agenda.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;

@Repository("eventDao")
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {

	@Override
	public int getIndex(Date starTime) {
		// criteria
		Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Event.class,"e");
		Criterion result = Restrictions.eq("u.startTime", starTime);
		criteria.add(result).setProjection(Projections.max("u.index"));
		return (Integer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents(Date date) {
		Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Event.class,"e");
		Criterion result = Restrictions.eq("u.startTime", date);
		return (List<Event>) criteria.add(result).uniqueResult();
	}

}
