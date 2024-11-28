package org.sem4.exam.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sem4.exam.entity.Indexer;
import org.sem4.exam.entity.Player;
import org.sem4.exam.entity.PlayerIndexer;
import org.sem4.exam.repository.IndexerRepository;
import org.sem4.exam.repository.PlayerIndexerRepository;
import org.sem4.exam.repository.PlayerRepository;

import java.util.Objects;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
public class PlayerService {
    private PlayerRepository playerDAO = new PlayerRepository();
    private IndexerRepository indexerDAO = new IndexerRepository();
    private PlayerIndexerRepository playerIndexerRepository = new PlayerIndexerRepository();

    public boolean createPlayer(HttpServletRequest request, HttpServletResponse response) {
        int indexId = Integer.parseInt(request.getParameter("indexId"));
        Indexer indexer = indexerDAO.findById(indexId);

        if (Objects.isNull(indexer)) {
            return false;
        }

        String fullName = request.getParameter("name");
        String age = request.getParameter("age");

        Player player = new Player();
        player.setName(fullName.split(" ")[0]);
        player.setFullName(fullName);
        player.setAge(age);
        player.setIndexerId(indexId);

        playerDAO.create(player);

        Float value = Float.valueOf(request.getParameter("value"));
        PlayerIndexer playerIndexer = new PlayerIndexer();
        playerIndexer.setPlayer(player);
        playerIndexer.setIndexer(indexer);
        playerIndexer.setValue(value);
        playerIndexerRepository.create(playerIndexer);
        return true;
    }

    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String indexName = request.getParameter("indexName");
        Indexer indexer = indexerDAO.findByName(indexName);
        Player player = playerDAO.findById(id);

        PlayerIndexer playerIndexer = playerIndexerRepository.findByPlayerIdAndIndexerId(id, indexer.getIndexerId());

        if (Objects.isNull(player) || Objects.isNull(playerIndexer)) {
            return false;
        }
        playerDAO.delete(player);
        playerIndexerRepository.delete(playerIndexer);
        return true;
    }
}
