package com.talentica.domain.model;

public class BooksRequestedToUser {

    private String bookName;
    private String bookAuther;
    private String requestedBy;
    private String requestedDate;


    public BooksRequestedToUser() {

    }

    public BooksRequestedToUser(String name, String author, String requestedBy, String requestedDate) {
        this.bookName = name;
        this.bookAuther = author;
        this.requestedBy = requestedBy;
        this.requestedDate = requestedDate;


    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public String getBookAuther() {

        return bookAuther;
    }

    public void setBookAuther(String bookAuther) {

        this.bookAuther = bookAuther;
    }


    public String getRequestedBy() {

        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {

        this.requestedBy = requestedBy;
    }


    public String getRequestedDate() {

        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {

        this.requestedDate = requestedDate;
    }


}
