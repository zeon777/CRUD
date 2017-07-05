package vaginD.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * Created by zeon on 29.06.2017.
 */

@Component
@Service
public class Factory{


    private static SessionFactory sessionFactory=Factory.getSessionFactory();
private static Session session;





    public static Session getSession() {
        if(session ==null||!session.isOpen())
            session=sessionFactory.openSession();
        return session;
    }

    public static void setSession(Session session) {
        Factory.session = session;
    }
    @Bean
    public static SessionFactory getSessionFactory() {

//            Configuration configuration = new Configuration();
//            configuration.configure();
//            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            if(Factory.sessionFactory == null)
                sessionFactory = new Configuration().configure().buildSessionFactory();



            return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Factory.sessionFactory = sessionFactory;
    }
}
