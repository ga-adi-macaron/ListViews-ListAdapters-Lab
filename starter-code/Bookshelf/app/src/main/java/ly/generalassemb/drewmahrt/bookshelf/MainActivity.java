package ly.generalassemb.drewmahrt.bookshelf;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BaseAdapter mBookAdapter;
    ListView mListView;
    List<Book> mBookList;

    //TODO: Define your ListView
    //TODO: Define your Book List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);
                }

                TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                textView.setText(mBookList.get(position).getTitle());
                TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);
                textView2.setText(mBookList.get(position).getAuthor());

                return convertView;
            }
        };

        //TODO: Set the ListView's adapter
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mBookAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.RED);
                TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
                textView2.setTextColor(Color.RED);
            }
        });

    }
    //Method to generate a list of Books
    private List<Book> generateBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("Apples Book","Brad"));
        books.add(new Book("Cats Book","Ryan"));
        books.add(new Book("Eagles Book","Kate"));
        books.add(new Book("Strawberries Cathy","Brad"));
        books.add(new Book("Dogs Book","Tom"));
        books.add(new Book("Ants Book","Zane"));

        return books;
    }
}
