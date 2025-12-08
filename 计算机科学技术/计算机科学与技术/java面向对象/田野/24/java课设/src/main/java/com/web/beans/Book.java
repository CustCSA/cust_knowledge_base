package com.web.beans;


public class Book {
    String bookname;
    int bookid;

    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Book(String bookname, int bookid) {
        this.bookname = bookname;
        this.bookid = bookid;
    }
    public Book() {
    }

    @Override
    public String toString() {
        return "("+bookname+")";
    }
}
