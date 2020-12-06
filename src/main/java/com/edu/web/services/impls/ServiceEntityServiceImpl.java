package com.edu.web.services.impls;

import com.edu.web.services.ServiceEntityService;
import com.edu.web.entities.Service;
import com.edu.web.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceEntityServiceImpl implements ServiceEntityService {

    private static final Map<Integer, Service> SERVICE_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger SERVICE_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Service service) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Service adding Error!");
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
    public List<Service> getAll() {
        Session session = null;
        List<Service> services = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Service ");
            services = query.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Service finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return services;
    }

    @Override
    public Service getById(Integer id) {
        Session session = null;
        Service service = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            service = session.get(Service.class, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Service finding Error!");
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return service;
    }

    @Override
    public boolean update(int id, Service service) {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(service);
            session.getTransaction().commit();
            isUpdated = true;
        } catch (Exception ex) {
            log.error("Service updating Error!");
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
            Object persistentInstance = session.load(Service.class, id);
            session.delete(persistentInstance);
//            session.delete(service);
            session.getTransaction().commit();
            isDeleted = true;
        } catch (Exception ex) {
            log.error("Service deleting Error!");
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
