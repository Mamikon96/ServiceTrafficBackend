package com.edu.web.services.impls;

import com.edu.web.entities.Rate;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.RateEntityService;
import com.edu.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RateEntityServiceImpl implements RateEntityService {

    @Override
    public void create(Rate rate) throws DBConnectionException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(rate);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                if (session.isOpen()) {
                    session.close();
                }
            }
            throw new DBConnectionException(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Rate> getAll() throws DBConnectionException {
        Session session = null;
        List<Rate> rates = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Rate ");
            rates = query.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                if (session.isOpen()) {
                    session.close();
                }
            }
            throw new DBConnectionException(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return rates;
    }

    @Override
    public Rate getById(int id) throws DBConnectionException {
        Session session = null;
        Rate rate = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            rate = session.get(Rate.class, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                if (session.isOpen()) {
                    session.close();
                }
            }
            throw new DBConnectionException(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return rate;
    }

    @Override
    public boolean update(Rate rate) throws DBConnectionException {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(rate);
            session.getTransaction().commit();
            isUpdated = true;
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                if (session.isOpen()) {
                    session.close();
                }
            }
            throw new DBConnectionException(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) throws DBConnectionException {
        Session session = null;
        boolean isDeleted = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Object persistentInstance = session.load(Rate.class, id);
            session.delete(persistentInstance);
//            session.delete(service);
            session.getTransaction().commit();
            isDeleted = true;
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                if (session.isOpen()) {
                    session.close();
                }
            }
            throw new DBConnectionException(ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return isDeleted;
    }
}
