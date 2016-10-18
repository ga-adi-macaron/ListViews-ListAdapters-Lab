package com.scottlindley.listview_listadapters_lab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private BaseAdapter mBaseAdapter;
    private ArrayList<Book> mBooks;
    private Button mSortButton;
    private Spinner mSpinner;
    private ArrayList<String> mSortingOptions;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSortButton = (Button)findViewById(R.id.sort_button);

        initializeBooksArray();

        initializeSpinner();

        initializeListView();

        setListViewClick();

        setSortButtonClick();


    }

    public void initializeBooksArray(){
        mBooks = new ArrayList<>();
        mBooks.add(new Book("The Stand", "Stephen King", 1234));
        mBooks.add(new Book("The Hobbit", "JRR Tolkein", 321));
        mBooks.add(new Book("Gone Girl", "Gillian Flynn", 432));
        mBooks.add(new Book("The Lorax", "Dr. Seuss", 45));
    }

    public void initializeSpinner(){
        mSpinner = (Spinner)findViewById(R.id.drop_down);
        mSortingOptions = new ArrayList<>();
        mSortingOptions.add("Title");
        mSortingOptions.add("Author");
        mSortingOptions.add("Pages");
        mArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, mSortingOptions);
        mSpinner.setAdapter(mArrayAdapter);
        mArrayAdapter.notifyDataSetChanged();
    }

    public void initializeListView(){
        mListView = (ListView)findViewById(R.id.book_list);
        mBaseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mBooks.size();
            }

            @Override
            public Object getItem(int i) {
                return mBooks.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view == null){
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    view = inflater.inflate(R.layout.list_view_element, null);
                }
                TextView title = (TextView)view.findViewById(R.id.title);
                title.setText("Title: "+mBooks.get(i).getTitle());
                TextView author = (TextView)view.findViewById(R.id.author);
                author.setText("Author: "+mBooks.get(i).getAuthor());
                TextView pages = (TextView)view.findViewById(R.id.numPages);
                pages.setText("Pages: "+Integer.toString(mBooks.get(i).getNumberPages()));
                return view;
            }
        };
        mListView.setAdapter(mBaseAdapter);
        mBaseAdapter.notifyDataSetChanged();
    }

    public void setListViewClick(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView title = (TextView)view.findViewById(R.id.title);
                TextView author = (TextView)view.findViewById(R.id.author);
                TextView pages = (TextView)view.findViewById(R.id.numPages);
                if(!mBooks.get(i).isRead()) {
                    title.setTextColor(Color.rgb(187, 13, 15));
                    author.setTextColor(Color.rgb(187, 13, 15));
                    pages.setTextColor(Color.rgb(187, 13, 15));
                    mBooks.get(i).setIsRead(true);
                }else{
                    title.setTextColor(Color.rgb(254, 254, 254));
                    author.setTextColor(Color.rgb(254, 254, 254));
                    pages.setTextColor(Color.rgb(254, 254, 254));
                    mBooks.get(i).setIsRead(false);
                }
            }
        });
    }

    public void setSortButtonClick(){
        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(mBooks, new Comparator<Book>() {
                    @Override
                    public int compare(Book book1, Book book2) {
                        switch (mSpinner.getSelectedItem().toString().toLowerCase()) {
                            case "title":
                                return book1.getTitle().toLowerCase().compareTo(book2.getTitle().toLowerCase());
                            case "author":
                                return book1.getAuthor().toLowerCase().compareTo(book2.getAuthor().toLowerCase());
                            case "pages":
                                return book2.getNumberPages()-book1.getNumberPages();
                            default:
                                return -1;
                        }
                    }
                });

                mBaseAdapter.notifyDataSetChanged();
            }
        });
    }

}
