package com.talentica.data.storage;

import com.talentica.domain.model.Notification;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DataBase {

    @Inject
    public DataBase() {
    }

    public List<Notification> makeRequestedBookList() {
        List<Notification> notificationList = new ArrayList<Notification>();

        Notification book1 = new Notification();
        book1.setBookName("The Lal Killa ");
        book1.setReturnDueToOrFrom("Shakti  Moyal on 20th May");
        book1.setDueToOrFrom(true);
        book1.setReturnDate("09 mar");

        Notification book2 = new Notification();
        book2.setBookName("The Lal Killa ");
        book2.setReturnDueToOrFrom(" Singh Moyal on 18th may");
        book2.setDueToOrFrom(true);
        book2.setReturnDate("09 mar");

        Notification book3 = new Notification();
        book3.setBookName("The Lal Killa");
        book3.setReturnDueToOrFrom("Shakti Singh Moyal on 7th April");
        book3.setDueToOrFrom(true);
        book3.setReturnDate("09 mar");

        Notification book4 = new Notification();
        book4.setBookName("The Lal Killa");
        book4.setReturnDueToOrFrom("Shakti Singh on 1 December");
        book4.setDueToOrFrom(false);
        book4.setReturnDate("09 mar");

        Notification book5 = new Notification();
        book5.setBookName("The Lal Killa");
        book5.setReturnDueToOrFrom("Bhakti Singh Moyal on 1 December");
        book5.setDueToOrFrom(false);
        book5.setReturnDate("09 mar");

        Notification book6 = new Notification();
        book6.setBookName("The Lal Killa");
        book6.setReturnDueToOrFrom("Bhakti Singh on 1 December");
        book6.setDueToOrFrom(false);
        book6.setReturnDate("09 mar");

        Notification book7 = new Notification();
        book7.setBookName("The Lal Killa");
        book7.setReturnDueToOrFrom("Bhakti  Moyal on 1 December");
        book7.setDueToOrFrom(false);
        book7.setReturnDate("09 mar");
        notificationList.add(book1);
        notificationList.add(book2);
        notificationList.add(book3);
        notificationList.add(book4);
        notificationList.add(book5);
        notificationList.add(book6);
        notificationList.add(book7);

        return notificationList;
    }
}
