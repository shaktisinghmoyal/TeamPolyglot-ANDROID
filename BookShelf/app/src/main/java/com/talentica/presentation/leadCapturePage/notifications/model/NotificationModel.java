package com.talentica.presentation.leadCapturePage.notifications.model;

public class NotificationModel {

    private String bookName;
    private String returnDueToOrFrom;
    private boolean dueToOrFrom;
    private String returnDate;


    public NotificationModel() {

    }

    public NotificationModel(String bookName, String returnDueToOrFrom, boolean dueToOrFrom, String returnDate) {
        this.bookName = bookName;
        this.returnDueToOrFrom = returnDueToOrFrom;
        this.dueToOrFrom = dueToOrFrom;
        this.returnDate = returnDate;


    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public String getReturnDueToOrFrom() {

        return returnDueToOrFrom;
    }

    public void setReturnDueToOrFrom(String returnDueToOrFrom) {

        this.returnDueToOrFrom = returnDueToOrFrom;
    }


    public boolean getDueToOrFrom() {

        return dueToOrFrom;
    }

    public void setDueToOrFrom(boolean dueToOrFrom) {

        this.dueToOrFrom = dueToOrFrom;
    }


    public String getReturnDate() {

        return returnDate;
    }

    public void setReturnDate(String returnDate) {

        this.returnDate = returnDate;
    }
}
