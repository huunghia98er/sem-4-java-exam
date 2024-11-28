package org.sem4.exam.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sem4.exam.dto.PlayerResponse;
import org.sem4.exam.entity.Indexer;
import org.sem4.exam.entity.Player;
import org.sem4.exam.entity.PlayerIndexer;
import org.sem4.exam.repository.IndexerRepository;
import org.sem4.exam.repository.PlayerIndexerRepository;
import org.sem4.exam.repository.PlayerRepository;
import org.sem4.exam.service.PlayerService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
@WebServlet(value = "/players")
public class PlayerController extends HttpServlet {
    private PlayerRepository playerDAO = new PlayerRepository();
    private IndexerRepository indexerDAO = new IndexerRepository();
    private PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PlayerResponse> responses = playerDAO.allWithPaging();

        request.setAttribute("players", responses);

        request.getRequestDispatcher("players/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = playerService.createPlayer(request, response);

        if (!result) {
            System.out.println("Error");
            response.sendRedirect("/players");
        }

        System.out.println("Success");
        response.sendRedirect("/players");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = playerService.delete(request, response);

        if (!result) {
            System.out.println("Error");
            response.sendRedirect("/players");
        }

        System.out.println("Success");
        response.sendRedirect("/players");
    }
}
