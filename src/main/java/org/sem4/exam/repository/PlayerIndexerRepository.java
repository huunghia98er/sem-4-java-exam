package org.sem4.exam.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sem4.exam.entity.Indexer;
import org.sem4.exam.entity.Player;
import org.sem4.exam.entity.PlayerIndexer;
import org.sem4.exam.utils.HibernateUtils;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
public class PlayerIndexerRepository implements Repository<PlayerIndexer, Integer> {
    @Override
    public List<PlayerIndexer> all() {
        return List.of();
    }

    @Override
    public PlayerIndexer findById(Integer id) {
        return null;
    }

    public PlayerIndexer findByPlayerIdAndIndexerId(Integer playerId, Integer indexerId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from PlayerIndexer where PlayerIndexer.player.playerId = :playerId and PlayerIndexer.indexer.indexerId = :indexerId", PlayerIndexer.class)
                    .setParameter("playerId", playerId)
                    .setParameter("indexerId", indexerId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(PlayerIndexer s) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(s);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(PlayerIndexer s) {
        return false;
    }

    @Override
    public boolean delete(PlayerIndexer s) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (s != null) {
                session.remove(s);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <K> PlayerIndexer find(K id) {
        return null;
    }
}
