package com.codepath.instagram.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.instagram.R;
import com.codepath.instagram.helpers.SimpleVerticalSpacerItemDecoration;
import com.codepath.instagram.helpers.Utils;
import com.codepath.instagram.models.InstagramPost;
import com.codepath.instagram.models.InstagramPostsAdapter;

import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<InstagramPost> posts = Utils.fetchPosts(this);

        setContentView(R.layout.activity_home);






        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        // Create adapter passing in the sample user data
        InstagramPostsAdapter adapter = new InstagramPostsAdapter(posts); //.createContactsList(20));
        // Attach the adapter to the recyclerview to populate items
        rvPosts.setAdapter(adapter);
        // Set layout manager to position the items
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        rvPosts.addItemDecoration(new SimpleVerticalSpacerItemDecoration(24));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
