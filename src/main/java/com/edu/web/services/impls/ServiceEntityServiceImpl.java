package com.edu.web.services.impls;

import com.edu.web.entities.Service;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.ServiceEntityService;
import com.edu.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Service
public class ServiceEntityServiceImpl implements ServiceEntityService {

    @Override
    public void create(Service service) throws DBConnectionException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(service);
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
    public List<Service> getAll() throws DBConnectionException {
        Session session = null;
        List<Service> services = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Service ");
            services = query.list();
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
        return services;
    }

    @Override
    public Service getById(Integer id) throws DBConnectionException {
        Session session = null;
        Service service = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            service = session.get(Service.class, id);
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
        return service;
    }

    @Override
    public boolean update(Service service) throws DBConnectionException {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(service);
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
            Object persistentInstance = session.load(Service.class, id);
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
