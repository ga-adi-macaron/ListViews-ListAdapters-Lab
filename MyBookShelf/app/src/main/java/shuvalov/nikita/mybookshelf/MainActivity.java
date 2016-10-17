package shuvalov.nikita.mybookshelf;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BaseAdapter mBookAdapter;

    List<Book> mBookList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //Use helper method to add books to the list
        mBookList = generateBooks();
        
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
                if(convertView == null){
                    LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                    convertView = layoutInflater.inflate(R.layout.book_form, null);
                }
                TextView textView1 = (TextView) convertView.findViewById(R.id.title);
                TextView textView2 = (TextView) convertView.findViewById(R.id.author);
                TextView textView3 =(TextView) convertView.findViewById(R.id.date);
                ImageView imgV = (ImageView) convertView.findViewById(R.id.bookImg);
                textView1.setText(mBookList.get(position).getTitle());
                textView2.setText(mBookList.get(position).getAuthor());
                textView3.setText(mBookList.get(position).getDate());
                imgV.setImageResource(mBookList.get(position).getImgPath());
                return convertView;
            }

        };
        listView.setAdapter(mBookAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView1 = (TextView) view.findViewById(R.id.title);
                TextView textView2 = (TextView) view.findViewById(R.id.author);
                TextView textView3 = (TextView) view.findViewById(R.id.date);
                textView1.setTextColor(Color.RED);
                textView2.setTextColor(Color.RED);
                textView3.setTextColor(Color.RED);
                mBookAdapter.notifyDataSetChanged();
            }
        });

    }

    //Method to generate a list of Books
    private List<Book> generateBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("The Ocean at the end of the Lane", "Neil Gaiman", 2013, R.drawable.ocean));
        books.add(new Book("Cat in the Hat", "Dr.Seuss",1957, R.drawable.cat));
        books.add(new Book("Bible", "G-d", 0, R.drawable.bible));
        books.add(new Book ("1984", "George Well", 1949, R.drawable.a1984));
        return books;
    }
}

