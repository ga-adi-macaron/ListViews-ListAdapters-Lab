package ly.generalassemb.drewmahrt.bookshelf;

/**
 * Created by drewmahrt on 12/16/15.
 */
public class Book {
    private String mTitle;
    private String mAuthor;
    private String mDetails;

    public Book(String title, String author, String details){

        mTitle = title;
        mAuthor = author;
        mDetails = details;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDetails() {
        return mDetails;
    }

}
