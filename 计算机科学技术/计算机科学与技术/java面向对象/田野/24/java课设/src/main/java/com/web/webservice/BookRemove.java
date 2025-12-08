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

@WebServlet("/bookremove")
public class BookRemove extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应类型和字符编码
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 获取搜索参数
        String temp = req.getParameter("searchID");
        if (temp == null) temp = req.getParameter("id");
        Map<String,Object> result = new HashMap<>();

        if (temp == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            result.put("success", false);
            result.put("message", "缺少参数: searchID 或 id");
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(result));
            out.flush();
            return;
        }

        try (SqlSession sqlSession = Mybaits_Utils.getSqlSession()) {
            int searchID = Integer.parseInt(temp);
            Book_api bookApi = sqlSession.getMapper(Book_api.class);
            bookApi.removeBook(searchID);
            sqlSession.commit();
            result.put("success", true);
            result.put("message", "删除成功");
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
