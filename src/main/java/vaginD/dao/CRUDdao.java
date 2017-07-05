package vaginD.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import vaginD.model.UserEntity;


import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by zeon on 09.06.2017.
 */
@Repository
@Transactional
public class CRUDdao {

@Required
    public UserEntity saveUser(UserEntity user) {

        try (Session session=Factory.getSession()) {

            if(user.getId()==null)
            {
                session.save(user);
            }
            else {
                Transaction transaction = session.beginTransaction();

                session.saveOrUpdate(user);

                session.flush();
            }
        }
        return user;
    }
    @Required
    public String deleteUser(Integer id)
    {
        try(Session session = Factory.getSession()) {

            String temp = session.get(UserEntity.class,id).getName();
            Transaction tx = session.beginTransaction();
            Query query =  session.createQuery("delete UserEntity where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();


            return "user "+ temp +" id="+id+ " delete";
        }

    }
    @Required
    public UserEntity getUserById(Integer id)
    {

        UserEntity  userEntity;

       try (Session session = Factory.getSession())
       {
           userEntity= session.get(UserEntity.class,id);
       }

        return userEntity;
    }

    @Required
    public List<UserEntity> getAllUsers(String name)
    {
       try( Session session=Factory.getSession();) {
           List<UserEntity> userEntities;
if(name!="")
{

    String qstring ="from UserEntity u where u.name LIKE '%"+name+"%'";
    userEntities= (List<UserEntity>)  session.createQuery(qstring).list();
}
else
{
    userEntities= (List<UserEntity>)  session.createQuery("from UserEntity ").list();
}

           return userEntities;
       }

    }
}
