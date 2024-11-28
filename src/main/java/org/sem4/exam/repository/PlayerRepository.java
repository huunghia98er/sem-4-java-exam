package org.sem4.exam.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sem4.exam.dto.PlayerResponse;
import org.sem4.exam.entity.Indexer;
import org.sem4.exam.entity.Player;
import org.sem4.exam.entity.PlayerIndexer;
import org.sem4.exam.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
public class PlayerRepository implements Repository<Player, Integer>, Paging<PlayerResponse> {
    @Override
    public List<PlayerResponse> allWithPaging() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<PlayerIndexer> playerIndexers = session.createQuery("from PlayerIndexer", PlayerIndexer.class).list();
            List<Integer> integers = playerIndexers.stream().map(p -> p.getPlayer().getPlayerId()).collect(Collectors.toList());
            List<Player> players = session.createQuery("from Player where playerId in :id", Player.class).setParameter("id", integers).list();

            List<PlayerResponse> list = new ArrayList<>();

            for (int i = 0; i < players.size(); i++) {
                Indexer indexer = session.createQuery("from Indexer where indexerId = :id", Indexer.class).setParameter("id", players.get(i).getIndexerId()).uniqueResult();
                list.add(PlayerResponse.builder()
                        .id(players.get(i).getPlayerId())
                        .name(players.get(i).getName())
                        .age(players.get(i).getAge())
                        .indexName(indexer.getName())
                        .value(playerIndexers.get(i).getValue())
                        .build());
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Player> all() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Player", Player.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Player findById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Player s) {
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
    public boolean update(Player s) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(s);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Player s) {
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
    public <K> Player find(K id) {
        return null;
    }
}
