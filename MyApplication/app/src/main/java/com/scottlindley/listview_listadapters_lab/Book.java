package com.scottlindley.listview_listadapters_lab;

/**
 * Created by Scott Lindley on 10/17/2016.
 */

public class Book {
    private String mTitle;
    private String mAuthor;
    private int mNumberPages;
    private boolean mIsRead;

    public Book(String title, String author, int numberPages) {
        mTitle = title;
        mAuthor = author;
        mNumberPages = numberPages;
        mIsRead = false;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getNumberPages() {
        return mNumberPages;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public boolean isRead(){
        return mIsRead;
    }

    public void setIsRead(boolean b){
        mIsRead = b;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setNumberPages(int numberPages) {
        mNumberPages = numberPages;
    }

    public void setRead(boolean read) {
        mIsRead = read;
    }
}
