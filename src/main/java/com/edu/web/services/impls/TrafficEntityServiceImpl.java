package com.edu.web.services.impls;

import com.edu.web.entities.Traffic;
import com.edu.web.entities.TrafficId;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.TrafficEntityService;
import com.edu.web.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrafficEntityServiceImpl implements TrafficEntityService {

    private static final Map<TrafficId, Traffic> TRAFFIC_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger RATE_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger SERVICE_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Traffic traffic) throws DBConnectionException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(traffic);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error(ex.getCause().toString());
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new DBConnectionException("Traffic add Error!!!");
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
            }
            log.error("Traffic finding Error!");
            throw new DBConnectionException("Traffics get Error!!!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return traffics;
    }

    @Override
    public List<Traffic> getByRateId(int rateId) {
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
            log.error("Traffic finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return traffics;
    }

    @Override
    public List<Traffic> getByServiceId(int serviceId) {
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
            log.error("Traffic finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return traffics;
    }

    @Override
    public Traffic getByTrafficId(TrafficId trafficId) {
        Session session = null;
        Traffic traffic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            traffic = session.get(Traffic.class, trafficId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Traffic finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return traffic;
    }

    @Override
    public boolean update(Traffic traffic) {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(traffic);
            session.getTransaction().commit();
            isUpdated = true;
        } catch (Exception ex) {
            log.error("Traffic updating Error!");
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
    public boolean delete(Traffic traffic) {
        Session session = null;
        boolean isDeleted = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            Object persistentInstance = session.get(Traffic.class, id);
            session.delete(traffic);
//            session.delete(service);
            session.getTransaction().commit();
            isDeleted = true;
        } catch (Exception ex) {
            log.error("Traffic deleting Error!");
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
