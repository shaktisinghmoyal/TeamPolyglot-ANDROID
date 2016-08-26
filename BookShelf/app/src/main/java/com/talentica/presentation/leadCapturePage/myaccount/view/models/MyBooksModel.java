package com.talentica.presentation.leadCapturePage.myaccount.view.models;

public class MyBooksModel {

    private String bookName;
    private String auther;
    private String requestedBy;
    private String borrowedBy;
    private String requestAcceptedDate;
    private String returnDate;
    private int totalRequest;


    public MyBooksModel() {

    }

    public MyBooksModel(String bookName, String auther, String requestedBy, String borrowedBy, String requestAcceptedDate, String returnDate, int totalRequest) {
        this.bookName = bookName;
        this.auther = auther;
        this.requestedBy = requestedBy;
        this.borrowedBy = borrowedBy;
        this.requestAcceptedDate = requestAcceptedDate;
        this.returnDate = returnDate;
        this.totalRequest = totalRequest;


    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public String getAutherNameName() {

        return auther;
    }

    public void setAutherNameName(String auther) {

        this.auther = auther;
    }

    public String getRequestedBy() {

        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {

        this.requestedBy = requestedBy;
    }

    public String geBorrowedBy() {

        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {

        this.borrowedBy = borrowedBy;
    }

    public String getRequestAcceptedDate() {

        return requestAcceptedDate;
    }

    public void setRequestAcceptedDate(String requestAcceptedDate) {

        this.requestAcceptedDate = requestAcceptedDate;
    }

    public String getReturnDate() {

        return returnDate;
    }

    public void setReturnDate(String returnDate) {

        this.returnDate = returnDate;
    }

    public int getTotalRequest() {

        return totalRequest;
    }

    public void setTotalRequest(int totalRequest) {

        this.totalRequest = totalRequest;
    }

}
