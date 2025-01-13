package org.example.hibernate;


import org.example.hibernate.entity.LapTob;
import org.example.hibernate.entity.Student;
import org.example.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateExample {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = new Configuration()
                    .configure() // This will load hibernate.cfg.xml
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(LapTob.class)
                    .buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

//            User user = new User("admin", "3");
//            session.save(user);

            User user = (User) session.get(User.class, 1);
            System.out.println(user);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
}