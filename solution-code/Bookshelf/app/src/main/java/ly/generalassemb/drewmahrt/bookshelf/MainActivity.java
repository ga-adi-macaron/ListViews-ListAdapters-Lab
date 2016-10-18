package ly.generalassemb.drewmahrt.bookshelf;

import android.content.Context;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BaseAdapter mBookAdapter;
    ListView mBookListView;
    List<Book> mBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate List
        mBookList = generateBooks();

        //Instantiate BaseAdapter
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
                View v = convertView;

                if (convertView == null) {
                    LayoutInflater li = (LayoutInflater) MainActivity.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = li.inflate(android.R.layout.simple_list_item_2, null);
                }

                TextView textView1 = (TextView)v.findViewById(android.R.id.text1);
                TextView textView2 = (TextView)v.findViewById(android.R.id.text2);

                textView1.setText("Title: "+mBookList.get(position).getTitle());
                textView2.setText("Author: "+mBookList.get(position).getAuthor());
                return v;
            }
        };


        mBookListView = (ListView)findViewById(R.id.book_list_view);
        mBookListView.setAdapter(mBookAdapter);

        mBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
                TextView textView2 = (TextView)view.findViewById(android.R.id.text2);

                textView1.setTextColor(Color.RED);
                textView2.setTextColor(Color.RED);
            }
        });

        // Set up sorting buttons
        Button sortByTitleButton = (Button) findViewById(R.id.sort_by_title_button);
        Button sortByAuthorButton = (Button) findViewById(R.id.sort_by_author_button);

        sortByTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort using the appropriate comparator
                Collections.sort(mBookList, new ComparatorTitle());

                // Notify the adapter that it needs to re-assign data to views
                mBookAdapter.notifyDataSetChanged();
            }
        });

        sortByAuthorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort using the appropriate comparator
                Collections.sort(mBookList, new ComparatorAuthor());

                // Notify the adapter that it needs to re-assign data to views
                mBookAdapter.notifyDataSetChanged();
            }
        });
    }

    private List<Book> generateBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("Apples Book","Brad","1950"));
        books.add(new Book("Cats Book","Ryan","1920"));
        books.add(new Book("Eagles Book","Kate","1987"));
        books.add(new Book("Strawberries Cathy","Brad","1982"));
        books.add(new Book("Dogs Book","Tom","2005"));
        books.add(new Book("Ants Book","Zane","2001"));

        return books;
    }
}
