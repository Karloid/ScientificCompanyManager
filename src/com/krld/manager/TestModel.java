package com.krld.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.krld.manager.model.Soldier;
import com.krld.manager.model.Message;
import com.krld.manager.model.Tag;

public class TestModel
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Soldier soldier = new Soldier("Dorofey");
        Message message = new Message("Код гиасс лучше всех");
        message.setSoldier(soldier);

        session.save(soldier);
        message.getTags().add(new Tag("fun"));
        message.getTags().add(new Tag("shit"));
        session.save(message);


        transaction.commit();

    }
}
