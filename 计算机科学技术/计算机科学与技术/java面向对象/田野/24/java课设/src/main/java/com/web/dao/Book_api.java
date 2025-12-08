package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.beans.Book;

public interface Book_api {
    List<Book> getallBook();
    List<Book> getBookByName(String bookname);
    int addBook(String bookname);
    void removeBook(int searchID);

    // Borrow/return/reserve related methods
    int borrowBook(@Param("bookid") int bookid, @Param("username") String username);
    int returnBook(@Param("bookid") int bookid);
    int reserveBook(@Param("bookid") int bookid, @Param("username") String username);

    // Process overdue records (mark status)
    int markOverdue();

    // Query borrow records
    List<com.web.beans.BorrowRecord> getBorrowRecords();

    // Simple statistics
    int getTotalBooks();
    int getBorrowedCount();
    int getOverdueCount();
}