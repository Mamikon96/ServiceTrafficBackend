package com.edu.web.services.impls;

import com.edu.web.entities.Client;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.ClientEntityService;
import com.edu.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientEntityServiceImpl implements ClientEntityService {

    @Override
    public void create(Client client) throws DBConnectionException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(client);
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
    public List<Client> getAll() throws DBConnectionException {
        Session session = null;
        List<Client> clients = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Client ");
            clients = query.list();
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
        return clients;
    }

    @Override
    public Client getById(int id) throws DBConnectionException {
        Session session = null;
        Client client = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            client = session.get(Client.class, id);
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
        return client;
    }

    @Override
    public List<Client> getByRateId(int rateId) throws DBConnectionException {
        Session session = null;
        List<Client> clients = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Client where rate.rateId = :rateId");
            query.setParameter("rateId", rateId);
            clients = query.list();
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
        return clients;
    }

    @Override
    public boolean update(Client client) throws DBConnectionException {
        Session session = null;
        boolean isUpdated = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(client);
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
    public boolean delete(Client client) throws DBConnectionException {
        Session session = null;
        boolean isDeleted = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(client);
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
