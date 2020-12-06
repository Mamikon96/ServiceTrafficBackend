package com.edu.web.services.impls;

import com.edu.web.entities.Rate;
import com.edu.web.services.RateEntityService;
import com.edu.web.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RateEntityServiceImpl implements RateEntityService {

    private static final Map<Integer, Rate> RATE_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger RATE_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Rate rate) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(rate);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Rate adding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Rate> getAll() {
        Session session = null;
        List<Rate> rates = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Rate ");
            rates = query.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Rate finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return rates;
    }

    @Override
    public Rate getById(int id) {
        Session session = null;
        Rate rate = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            rate = session.get(Rate.class, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Rate finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return rate;
    }

    @Override
    public boolean update(int id, Rate rate) {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(rate);
            session.getTransaction().commit();
            isUpdated = true;
        } catch (Exception ex) {
            log.error("Rate updating Error!" + ex);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
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
            log.error("Rate deleting Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return isDeleted;
    }
}
