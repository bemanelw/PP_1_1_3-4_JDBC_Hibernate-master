package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session.beginTransaction();
            String sql ="CREATE TABLE IF NOT EXISTS Users (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age TINYINT NOT NULL)";
            Query query=session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try {
            session.beginTransaction();
            String sql="DROP TABLE IF EXISTS Users";
            Query query=session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session.beginTransaction();
            User user=session.get(User.class,id);
            if(user!=null){
                session.delete(user);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
//        List<User> users = null;
//        try {
//            session.beginTransaction();
//            Query<User> query=session.createSQLQuery("SELECT * FROM User", User.class);
//            users=query.list();
//            session.getTransaction().commit();
//        } catch (HibernateException e) {
//            if(session!=null){
//                session.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        }
//        return users;
         return session.createSQLQuery("SELECT * FROM users").addEntity(User.class).getResultList();
    }

    @Override
    public void cleanUsersTable() {
        try {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
