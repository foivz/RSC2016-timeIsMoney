package com.rsc.rschackathon.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.RecyclerViewAdapter;
import com.rsc.rschackathon.database.Book;
import com.rsc.rschackathon.database.DatabaseCommunication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.Listener{

    RecyclerViewAdapter recyclerViewAdapter;

    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.list_of_items)
    RecyclerView recyclerView;
    
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;        

    List<String> testList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        recyclerViewAdapter = new RecyclerViewAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        testList.add("TEST PODATAKA 1");
        testList.add("TEST PODATAKA 2");
        testList.add("TEST PODATAKA 3");
        testList.add("TEST PODATAKA 4");
        testList.add("TEST PODATAKA 5");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");

        recyclerViewAdapter.setData(testList);
        recyclerViewAdapter.setListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecyclerViewActivity.this, "FAB", Toast.LENGTH_SHORT).show();
            }
        });
        
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0)
                    floatingActionButton.hide();
                else if (dy < 0)
                    floatingActionButton.show();
            }
        });

    }

    @Override
    public void getDeviceAtPosition(int position) {
        if(position == 1){
            Book book = new Book("51", "TINO", "4");
            book.save();

        }
        else if(position == 2){
            Book book = Book.findById(Book.class, 5);
            Toast.makeText(this, book.getTitle(), Toast.LENGTH_SHORT).show();
            //List<Note> notes = Note.findWithQuery(Note.class, "Select * from Note where name = ?", "satya");

        }
        else if(position == 3){
            Book book = Book.findById(Book.class, 5);
            book.setTitle("updated title heresad");
            book.setEdition("3rd edition");
            book.save();
        }
        else if(position == 4){
            Book book = Book.findById(Book.class, 5);
            book.delete();        }
        else if(position == 5){

        }
    }
}
