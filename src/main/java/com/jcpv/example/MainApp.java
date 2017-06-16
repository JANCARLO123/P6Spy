package com.jcpv.example;

import com.jcpv.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by JanCarlo on 14/06/2017.
 */
public class MainApp {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        System.out.println("hola");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            Person person = new Person();
            person.setName("Jan Carlo");
            session.save(person);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }
}
