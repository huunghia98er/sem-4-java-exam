package org.sem4.exam.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sem4.exam.entity.Indexer;
import org.sem4.exam.repository.IndexerRepository;

import java.io.IOException;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
@WebServlet(urlPatterns = "/getIndexValueRange")
public class IndexerServlet extends HttpServlet {
    IndexerRepository indexerRepository = new IndexerRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexerId = request.getParameter("indexerId");
        Indexer indexer = indexerRepository.findById(Integer.parseInt(indexerId));

        if (indexer != null) {
            response.setContentType("application/json");
            response.getWriter().write("{\"minValue\": " + indexer.getValueMin() + ", \"maxValue\": " + indexer.getValueMax() + "}");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
