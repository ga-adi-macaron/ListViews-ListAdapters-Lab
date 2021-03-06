package ly.generalassemb.drewmahrt.bookshelf;

import java.util.Comparator;

/**
 * Created by drewmahrt on 12/16/15.
 */
public class ComparatorTitle implements Comparator<Book> {

    // Provide an implementation to compare the "left hand side" to the "right hand side"
    @Override
    public int compare(Book lhs, Book rhs) {
        return lhs.getTitle().compareTo(rhs.getTitle());
    }
}
