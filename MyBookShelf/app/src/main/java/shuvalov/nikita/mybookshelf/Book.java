package shuvalov.nikita.mybookshelf;


/**
 * Created by drewmahrt on 12/16/15.
 */
public class Book {
    private String mTitle;
    private String mAuthor;
    private int mDate;
    private int mImg;

    public Book(String title,String author, int date, int img){
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mImg = img;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate(){return String.valueOf(mDate);}

    public int getImg(){return mImg;}


}
