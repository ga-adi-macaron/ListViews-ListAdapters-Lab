package ly.generalassemb.drewmahrt.bookshelf;

/**
 * Created by drewmahrt on 12/16/15.
 */
public class Book {
    private String mTitle;
    private String mAuthor;

    public Book(String title,String author){
        mTitle = title;
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

}
