package com.example.bakeryshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView rev;
    myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Recyler and card view demo");
        rev=(RecyclerView)findViewById(R.id.recview);
    //rev.setLayoutManager(new LinearLayoutManager(this));
    adapter=new myAdapter(dataqueue(),getApplicationContext());
    rev.setAdapter(adapter);
    //LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    //rev.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        rev.setLayoutManager(gridLayoutManager);
    }
    public ArrayList<Model>dataqueue(){
       ArrayList<Model>holder=new ArrayList<>();
       Model ob1=new Model();
       ob1.setHeader("Cookies");
       ob1.setDesc("price:125");
       ob1.setImagename(R.drawable.biskut1);
       holder.add(ob1);

        Model ob2=new Model();
        ob2.setHeader("Biscotti");
        ob2.setDesc("price:120");
        ob2.setImagename(R.drawable.biskut2);
        holder.add(ob2);

        Model ob3=new Model();
        ob3.setHeader("Crostoli");
        ob3.setDesc("price:115");
        ob3.setImagename(R.drawable.biskut3);
        holder.add(ob3);

        Model ob4=new Model();
        ob4.setHeader("Scones");
        ob4.setDesc("price:105");
        ob4.setImagename(R.drawable.biskut12);
        holder.add(ob4);

        Model ob5=new Model();
        ob5.setHeader("Dumplings");
        ob5.setDesc("price:95");
        ob5.setImagename(R.drawable.biskut5);
        holder.add(ob5);

        Model ob6=new Model();
        ob6.setHeader("Shortcake");
        ob6.setDesc("price:135");
        ob6.setImagename(R.drawable.biskut6);
        holder.add(ob6);

        Model ob7=new Model();
        ob7.setHeader("Angel Biscuits");
        ob7.setDesc("price:125");
        ob7.setImagename(R.drawable.biskut7);
        holder.add(ob7);

        Model ob8=new Model();
        ob8.setHeader("Buttermilk Biscuits");
        ob8.setDesc("price:165");
        ob8.setImagename(R.drawable.biskut8);
        holder.add(ob8);

        Model ob9=new Model();
        ob9.setHeader("Drop Biscuits");
        ob9.setDesc("price:155");
        ob9.setImagename(R.drawable.biskut9);
        holder.add(ob9);

        Model ob10=new Model();
        ob10.setHeader("Rolled Biscuits");
        ob10.setDesc("price:175");
        ob10.setImagename(R.drawable.biskut10);
        holder.add(ob10);
       return holder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        MenuItem item=menu.findItem(R.id.search_menu);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);




    }
}