package org.crud.wxw.util;

import org.hibernate.Session;

public class SessionUtil {
    private Session session;

    public Session openSession(){
        return HibernateSessionFactory.getSessionFactory().openSession();
    }

    public Session openTransactionSession(){
        session = openSession();
        session.beginTransaction();
        return session;
    }
}
