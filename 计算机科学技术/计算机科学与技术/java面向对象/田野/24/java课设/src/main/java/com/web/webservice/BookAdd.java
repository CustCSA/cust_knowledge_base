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

@WebServlet("/bookadd")
public class BookAdd extends HttpServlet {
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
        String searchName = req.getParameter("name");
        Map<String,Object> result = new HashMap<>();
        try (SqlSession sqlSession = Mybaits_Utils.getSqlSession()) {
            if (searchName != null && !searchName.trim().isEmpty()) {
                Book_api bookApi = sqlSession.getMapper(Book_api.class);
                int rows = bookApi.addBook(searchName);
                sqlSession.commit();
                result.put("success", true);
                result.put("rows", rows);
                result.put("message", "添加成功");
            } else {
                result.put("success", false);
                result.put("message", "缺少参数: name");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        PrintWriter out = resp.getWriter();
        out.print(new Gson().toJson(result));
        out.flush();
    }
}
