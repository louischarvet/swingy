package swingy.database.dao;

import java.lang.Exception;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import swingy.database.HibernateUtil;

import swingy.model.character.Hero;

public class HeroDAO {
	public void	insert(Hero hero) throws Exception {
		Transaction	transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(hero);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		}
	}

	public List< Hero >	getAll() throws Exception {
		List< Hero >	heroes = null;
		Transaction	transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query< Hero >	query = session.createQuery("SELECT h FROM Hero h", Hero.class);
			heroes = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		}

		return heroes;
	}
}