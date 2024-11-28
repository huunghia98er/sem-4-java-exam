package org.sem4.exam.repository;

import org.hibernate.Session;
import org.sem4.exam.entity.Indexer;
import org.sem4.exam.entity.Player;
import org.sem4.exam.utils.HibernateUtils;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
public class IndexerRepository implements Repository<Indexer, Integer> {
    @Override
    public List<Indexer> all() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Indexer", Indexer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Indexer findById(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Indexer where id = :id", Indexer.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Indexer findByName(String name) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Indexer where name = :name", Indexer.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Indexer s) {
        return false;
    }

    @Override
    public boolean update(Indexer s) {
        return false;
    }

    @Override
    public boolean delete(Indexer s) {
        return false;
    }

    @Override
    public <K> Indexer find(K id) {
        return null;
    }
}
