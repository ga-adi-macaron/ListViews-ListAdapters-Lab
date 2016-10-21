package ly.generalassemb.drewmahrt.bookshelf;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    BaseAdapter mBookAdapter;
    Button mTittleButton;
    Button mAuthorButton;

    //TODO: Define your ListView
    ListView mListView;

    //TODO: Define your Book List
    List<Book> mBookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Button
        mTittleButton = (Button) findViewById(R.id.sortTitle);
        mAuthorButton = (Button) findViewById(R.id.sortAuthor);

        //Use helper method to add books to the list
        mBookList = generateBooks();

        //TODO: Instantiate BaseAdapter
        //Below is a partially complete example for one Adapter
        mBookAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mBookList.size();
            }

            @Override
            public Object getItem(int position) {
                return mBookList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //TODO: Update the view

                if (convertView==null){
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    convertView = inflater.inflate(R.layout.custom_adapter,null);
                }

                TextView textView = (TextView)convertView.findViewById(R.id.text1);
                textView.setText(mBookList.get(position).getTitle());
                TextView textView2 = (TextView)convertView.findViewById(R.id.text2);
                textView2.setText(mBookList.get(position).getAuthor());
                TextView textView3 = (TextView)convertView.findViewById(R.id.text3);
                textView3.setText(mBookList.get(position).getDetails());

                return convertView;
            }
        };

        //TODO: Set the ListView's adapter
        mListView = (ListView)findViewById(R.id.list_view);
        mListView.setAdapter(mBookAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)view.findViewById(R.id.text1);
                textView.setTextColor(Color.RED);
                TextView textView2 = (TextView)view.findViewById(R.id.text2);
                textView2.setTextColor(Color.RED);
                TextView textView3 = (TextView)view.findViewById(R.id.text3);
                textView3.setTextColor(Color.RED);
            }
        });
        mAuthorButton.setOnClickListener(this);
        mTittleButton.setOnClickListener(this);
    }

    //Method to generate a list of Books
    private List<Book> generateBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("Apples Book","Brad","This is a book about apple."));
        books.add(new Book("Cats Book","Ryan","This is a book about cat."));
        books.add(new Book("Eagles Book","Kate","An eagle is coming to town."));
        books.add(new Book("Strawberries Cathy","Brad","Strawberries taste sweet."));
        books.add(new Book("Dogs Book","Tom","Who lets the dogs out."));
        books.add(new Book("Ants Book","Zane","Books for ants."));

        return books;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sortAuthor:
                mBookList = sortBookByTitle(mBookList,false);
                mBookAdapter.notifyDataSetChanged();
                break;
            case R.id.sortTitle:
                mBookList = sortBookByTitle(mBookList,true);
                mBookAdapter.notifyDataSetChanged();
                break;
        }
    }

    //Sort by title if true else sort by Author
    private List<Book> sortBookByTitle(List<Book> bookList, boolean isTitle){
        //Sort by title
        if (isTitle){
            List<String> titleList = new ArrayList<>();
            HashMap<String, Book> bookMap = new HashMap<>();

            for (Book book : bookList) {
                titleList.add(book.getTitle());
                bookMap.put(book.getTitle(),book);
            }

            Collections.sort(titleList, String.CASE_INSENSITIVE_ORDER);
            bookList.clear();

            for (String title : titleList) {
                bookList.add(bookMap.get(title));
            }
        //Sort by Author
        }else {
            List<String> authorList = new ArrayList<>();
            HashMap<String, Book> bookMap = new HashMap<>();

            for (Book book : bookList) {
                authorList.add(book.getAuthor());
                bookMap.put(book.getAuthor(),book);
            }

            Collections.sort(authorList, String.CASE_INSENSITIVE_ORDER);
            bookList.clear();

            for (String author : authorList) {
                bookList.add(bookMap.get(author));
            }
        }
        return mBookList;
    }
}
