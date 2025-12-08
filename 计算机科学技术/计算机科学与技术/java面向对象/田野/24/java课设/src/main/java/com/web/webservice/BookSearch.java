package com.web.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.google.gson.Gson;
import com.web.dao.Book_api;
import com.web.beans.Book;
import com.web.utils.Mybaits_Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookservice")
public class BookSearch extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应类型和字符编码
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        // 获取搜索参数
        String searchName = req.getParameter("name");
        
        // 使用工具类获取SqlSession
        try (SqlSession sqlSession = Mybaits_Utils.getSqlSession()) {
            // 获取 Book_api 接口的代理实现
            Book_api bookApi = sqlSession.getMapper(Book_api.class);
            
            List<Book> books;
            
            // 根据是否有搜索参数决定调用哪个方法
            if (searchName != null && !searchName.trim().isEmpty()) {
                // 在搜索名称前后添加%以实现模糊搜索
                books = bookApi.getBookByName("%" + searchName + "%");
            } else {
                // 获取所有图书
                books = bookApi.getallBook();
            }
            sqlSession.close();
            // 将图书列表转换为JSON格式
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(books);
            
            // 发送响应
            PrintWriter out = resp.getWriter();
            out.print(jsonResponse);
            out.flush();
        } catch (Exception e) {
            // 处理异常
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter out = resp.getWriter();
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
