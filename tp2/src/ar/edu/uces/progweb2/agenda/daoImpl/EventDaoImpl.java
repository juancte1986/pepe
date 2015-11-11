package ar.edu.uces.progweb2.agenda.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.User;

@Repository("eventDao")
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents(Date date, User user) {
		Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Event.class,"e");
		Criterion resultDate = Restrictions.eq("e.date", date);
		Criterion resultOwner = Restrictions.eq("e.owner.id", user.getId());
		criteria.createAlias("e.guests", "guest"); 
		Criterion resultGuest = Restrictions.eq("guest.id", user.getId());
		return (List<Event>) criteria.add(Restrictions.or(resultDate, resultOwner, resultGuest)).list();
	}

}
