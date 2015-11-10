package ar.edu.uces.progweb2.agenda.daoImpl;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.agenda.dao.GuestDao;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.User;


@Repository("guestDao")
public class GuestDaoImpl extends GenericDaoImpl<Guest> implements GuestDao {
	
}
