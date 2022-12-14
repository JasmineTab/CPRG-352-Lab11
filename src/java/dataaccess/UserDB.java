package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.User;

public class UserDB {

    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }

    public User getByUUID(String uuid) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

//            User user = em.find(User.class, uuid);
            
            Query query = em.createQuery("Select u from User u where u.resetPasswordUuid = :resetPasswordUuid");
            query.setParameter("resetPasswordUuid", uuid);
            User user = (User)query.getSingleResult();

            
//            if (user != null) {
//
//                System.out.println("user was found ");
//
//            } else {
//                System.out.println("user not found ");
//
//            }

            return user;
        } finally {
            em.close();
        }
    }

    public void update(User user) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.merge(user);
            trans.commit();
            
        } catch (Exception e) {

            trans.rollback();
        } finally {
            em.close();
        }
    }
}
