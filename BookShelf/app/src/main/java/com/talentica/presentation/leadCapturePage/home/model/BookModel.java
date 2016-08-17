package com.talentica.presentation.leadCapturePage.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class BookModel implements Parcelable {
    public static final Parcelable.Creator<BookModel> CREATOR = new Parcelable.Creator<BookModel>() {

        @Override
        public BookModel createFromParcel(Parcel source) {
            return new BookModel(source);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };
    private final String Tag = "BookModel";
    private String bookName;
    private String bookURL;
    private ImageView bookImage;
    private String lender;
    private String authersName;
    private String isbn13;
    private String isbn10;
    private String publisherName;
    private String publishDate;
    private String[] publisherURLs;
    private String binding;
    private String bookPrice;
    private String edition;
    private String bookCondition;
    private String[] tags;
    private String genre;
    private String commentByOwner;

    public BookModel() {

    }

    public BookModel(String name, String author, String lender, String binding, String publishDate, String publisher, String isbn13, String isbn10, String edition, String price) {
        this.bookName = name;
        this.authersName = author;
        this.lender = lender;
        this.publishDate = publishDate;
        this.publisherName = publisher;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
        this.binding = binding;
        this.bookPrice = price;
        this.edition = edition;
    }

    public BookModel(Parcel in) {
        this.bookName = in.readString();
        this.authersName = in.readString();
        this.lender = in.readString();
        this.publishDate = in.readString();
        this.publisherName = in.readString();
        this.isbn13 = in.readString();
        this.isbn10 = in.readString();
        this.binding = in.readString();
        this.bookPrice = in.readString();
        this.edition = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(bookName);
        dest.writeString(authersName);
        dest.writeString(lender);
        dest.writeString(publishDate);
        dest.writeString(publisherName);
        dest.writeString(isbn13);
        dest.writeString(isbn10);
        dest.writeString(binding);
        dest.writeString(bookPrice);
        dest.writeString(edition);
    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public ImageView getBookImage() {

        return bookImage;
    }

    public void setBookImage(ImageView bookImage) {

        this.bookImage = bookImage;
    }

    public String getBookURL() {

        return bookURL;
    }

    public void setBookURL(String bookURL) {

        this.bookURL = bookURL;
    }

    public String getLender() {

        return lender;
    }

    public void setLender(String lender) {

        this.lender = lender;
    }

    public String getAuthersName() {

        return authersName;
    }

    public void setAuthersName(String autherName) {

        this.authersName = autherName;
    }


    public String getIsbn13Numbers() {

        return isbn13;

    }


    public void setIsbn13Numbers(String isbn13) {

        this.isbn13 = isbn13;
    }

    public String getIsbn10Numbers() {

        return isbn10;

    }


    public void setIsbn10Numbers(String isbn10) {

        this.isbn10 = isbn10;
    }


    public String[] getPublisherURLs() {

        return publisherURLs;
    }

    public void setPublisherURLs(String[] publisherURLs) {

        this.publisherURLs = publisherURLs;
    }

    public String getPublishDate() {

        return publishDate;
    }

    public void setPublishDate(String publishDate) {

        this.publishDate = publishDate;
    }

    public String getPublisher() {

        return publisherName;
    }


    public void setPublisher(String publisherName) {

        this.publisherName = publisherName;
    }


    public String getBinding() {

        return binding;
    }

    public void setBinding(String binding) {

        this.binding = binding;
    }

    public String getEdition() {

        return edition;
    }

    public void setEdition(String edition) {

        this.edition = edition;
    }

    public String getBookPrice() {

        return this.bookPrice;
    }

    public void setBookPrice(String bookPrice) {

        this.bookPrice = bookPrice;
    }

    public String getBookCondition() {

        return bookCondition;
    }

    public void setBookCondition(String bookCondition) {

        this.bookCondition = bookCondition;
    }

    public String[] getBookTags() {

        return tags;
    }

    public void setBookTags(String[] tags) {

        this.tags = tags;
    }


    public String getBookGenre() {

        return genre;
    }

    public void setBookGenre(String genre) {

        this.genre = genre;
    }


    public String getCommentByOwner() {

        return commentByOwner;
    }

    public void setCommentByOwner(String commentByOwner) {

        this.commentByOwner = commentByOwner;
    }


    public class Auther {
        private String name;
        private String mailId;

        public String getAutherName() {

            return name;
        }

        public void setAutherName(String name) {

            this.name = name;
        }


        public String getAutherMailId() {

            return mailId;
        }

        public void setAutherMailId(String mailId) {

            this.mailId = mailId;
        }
    }


    public class PublisherAddress {
        private String street1;
        private String street2;
        private String country;
        private String city;
        private String state;
        private String zip;

        public String getStreet1() {

            return street1;
        }

        public void setStreet1(String street1) {

            this.street1 = street1;
        }

        public String getStreet2() {

            return street2;
        }

        public void setStreet2(String street2) {

            this.street2 = street2;
        }

        public String getCountry() {

            return country;
        }

        public void setCountry(String country) {

            this.country = country;
        }

        public String getCity() {

            return city;
        }

        public void setCity(String city) {

            this.city = city;
        }

        public String getState() {

            return state;
        }

        public void setState(String state) {

            this.state = state;
        }

        public String getZip() {

            return zip;
        }

        public void setZip(String zip) {

            this.zip = zip;
        }

    }
}
