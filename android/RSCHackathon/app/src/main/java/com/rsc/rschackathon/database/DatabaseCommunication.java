package com.rsc.rschackathon.database;

public class DatabaseCommunication {

    //https://github.com/satyan/sugar

    //http://satyan.github.io/sugar/getting-started.html


    //http://satyan.github.io/sugar/query.html
    public static void addBook(Book book){
            book.save();
    }

    public static Book findByIdBook(int id){
        Book book = Book.findById(Book.class, id);
        return  book;
    }

    public static void updateBook(int id){
        Book book = Book.findById(Book.class, id);
        book.title = "updated title heresad"; // modify the values
        book.edition = "3rd edition";
        book.save();

    }

    public static void deleteBook(int id){
        Book book = Book.findById(Book.class, id);
        book.delete();
    }
}
