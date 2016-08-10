package com.talentica.data.entity;

public class BookEntity {

    private final String Tag = "BookEntity";
    private String bookName;
    private String authersName;
    private String lenderName;
    private String isbn13;
    private String isbn10;
    private String publisherName;
    private String publishDate;
    private String[] publisherURLs;
    private String binding;
    private String bookPrice;
    private String bookCondition;
    private String[] tags;
    private String genre;
    private String edition;
    private String commentByOwner;

    public BookEntity() {
    }

    public BookEntity(String name, String author, String lender, String binding, String publishDate, String publisher, String isbn13, String isbn10, String edition, String price) {
        this.bookName = name;
//        this.authersName=new ArrayList<Auther>();
//        Auther a=new Auther();
//        a.setAutherName(author);
        authersName = author;
        this.lenderName = lender;
        this.publishDate = publishDate;
        this.publisherName = publisher;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
        this.binding = binding;
        this.bookPrice = price;
        this.edition = edition;
    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public String getLenderName() {

        return lenderName;
    }

    public void setLenderName(String lenderName) {

        this.lenderName = lenderName;
    }

    public String getAuthersName() {

        return authersName;
    }

    public void setAuthersName(String authersName) {

        this.authersName = authersName;
    }

    public String getEdition() {

        return edition;
    }

    public void setEdition(String edition) {

        this.edition = edition;
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

        public void serAutherName(String name) {

            this.name = name;
        }


        public String getBookGenre() {

            return mailId;
        }

        public void setBookGenre(String mailId) {

            this.mailId = mailId;
        }
    }


    public class PublisherAdress {
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
