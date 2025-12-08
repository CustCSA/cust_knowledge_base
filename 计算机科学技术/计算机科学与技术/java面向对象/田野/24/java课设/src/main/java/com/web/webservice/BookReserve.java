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

@WebServlet("/bookreserve")
public class BookReserve extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String bookIdStr = req.getParameter("bookid");
        String username = req.getParameter("username");
        Map<String,Object> result = new HashMap<>();

        if (bookIdStr == null || username == null || username.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            result.put("success", false);
            result.put("message", "missing parameters: bookid and username are required");
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(result));
            out.flush();
            return;
        }

        try (SqlSession sqlSession = Mybaits_Utils.getSqlSession()) {
            int bookid = Integer.parseInt(bookIdStr);
            Book_api bookApi = sqlSession.getMapper(Book_api.class);
            int rows = bookApi.reserveBook(bookid, username);
            sqlSession.commit();
            result.put("success", rows > 0);
            result.put("rows", rows);
            result.put("message", rows>0?"预约成功":"预约失败");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.put("success", false);
            result.put("message", e.getMessage());
        }

        PrintWriter out = resp.getWriter();
        out.print(new Gson().toJson(result));
        out.flush();
    }
}
