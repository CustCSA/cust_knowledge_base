package com.web.webservice;

import com.google.gson.Gson;
import com.web.dao.Book_api;
import com.web.utils.Mybaits_Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bookstats")
public class BookStats extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (SqlSession sqlSession = Mybaits_Utils.getSqlSession()) {
            Book_api bookApi = sqlSession.getMapper(Book_api.class);
            int total = bookApi.getTotalBooks();
            int borrowed = bookApi.getBorrowedCount();
            int overdue = bookApi.getOverdueCount();
            Map<String,Integer> result = new HashMap<>();
            result.put("total", total);
            result.put("borrowed", borrowed);
            result.put("overdue", overdue);
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(result));
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(new Gson().toJson(e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

