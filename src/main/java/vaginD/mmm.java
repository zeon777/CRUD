package vaginD;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vaginD.model.UserEntity;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zeon on 29.06.2017.
 */
@Transactional
public class mmm {
    public static void main(String[] args) {




            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        SessionFactory  factory =new Configuration().configure().buildSessionFactory();

//        System.out.println(entityManager.contains(UserEntity.class));
            Session session = factory.openSession();
       // List<UserEntity> userEntities= (List<UserEntity>)  session.createSQLQuery("select * from user").list();
//        UserEntity userEntity = new UserEntity();
//        userEntity.setAge(15);
//        userEntity.setName("Goodman");
//        userEntity.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
//session.beginTransaction();
//session.save(userEntity);
        List<UserEntity> userEntities= (List<UserEntity>)  session.createQuery("from UserEntity ").list();

for (UserEntity userEntity:userEntities)
{
    System.out.println(userEntity.getName()+" "+userEntity.getAge()+" "+userEntity.getCreatedDate());
}




            System.out.println("Test connection with the database created successfuly."+userEntities.size() );
        session.close();




    }
}
