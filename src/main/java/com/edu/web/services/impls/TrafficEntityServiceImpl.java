package com.edu.web.services.impls;

import com.edu.web.entities.Traffic;
import com.edu.web.entities.TrafficId;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.TrafficEntityService;
import com.edu.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrafficEntityServiceImpl implements TrafficEntityService {

    @Override
    public void create(Traffic traffic) throws DBConnectionException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(traffic);
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
    public List<Traffic> getAll() throws DBConnectionException {
        Session session = null;
        List<Traffic> traffics = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Traffic ");
            traffics = query.list();
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
        return traffics;
    }

    @Override
    public List<Traffic> getByRateId(int rateId) throws DBConnectionException {
        Session session = null;
        List<Traffic> traffics = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Traffic where trafficId.rateId = :rateId");
            query.setParameter("rateId", rateId);
            traffics = query.list();
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
        return traffics;
    }

    @Override
    public List<Traffic> getByServiceId(int serviceId) throws DBConnectionException {
        Session session = null;
        List<Traffic> traffics = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Traffic where trafficId.serviceId = :serviceId");
            query.setParameter("serviceId", serviceId);
            traffics = query.list();
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
        return traffics;
    }

    @Override
    public Traffic getByTrafficId(TrafficId trafficId) throws DBConnectionException {
        Session session = null;
        Traffic traffic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            traffic = session.get(Traffic.class, trafficId);
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
        return traffic;
    }

    @Override
    public boolean update(Traffic traffic) throws DBConnectionException {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(traffic);
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
    public boolean delete(Traffic traffic) throws DBConnectionException {
        Session session = null;
        boolean isDeleted = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(traffic);
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
