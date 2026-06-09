package swingy.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.Session;
import org.hibernate.Transaction;

// import swingy.model.character.Hero;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Crée le registre de services
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

            // Crée la SessionFactory
            return new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .build()
                .getSessionFactoryBuilder()
                .build();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	// public static void	insert(Hero hero) throws Exception {
	// 	Transaction	transaction = null;

	// 	try (Session session = sessionFactory.openSession()) {
	// 		transaction = session.beginTransaction();
	// 		session.persist(hero);
	// 		transaction.commit();
	// 	} catch (Exception e) {
	// 		if (transaction != null)
	// 			transaction.rollback();
	// 		throw e;
	// 	}
	// }

	// public static List< HeroDAO >	getHeroes() throws Exception {
	// 	Transaction	transaction = null;

	// 	try (Session session = sessionFactory.openSession()) {
	// 		transaction = session.beginTransaction();
			
	// 	}
	// }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}