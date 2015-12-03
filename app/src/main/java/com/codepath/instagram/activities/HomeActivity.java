package com.codepath.instagram.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.codepath.instagram.R;
import com.codepath.instagram.helpers.SimpleVerticalSpacerItemDecoration;
import com.codepath.instagram.helpers.Utils;
import com.codepath.instagram.models.InstagramClient;
import com.codepath.instagram.models.InstagramPost;
import com.codepath.instagram.models.InstagramPostsAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    List<InstagramPost> posts;
    InstagramPostsAdapter adapter;

    @Bind(R.id.rvPosts) RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // Create adapter
        posts = new ArrayList<>();
        adapter = new InstagramPostsAdapter(this, posts);
        // Attach the adapter to the recyclerview to populate items
        rvPosts.setAdapter(adapter);
        // Set layout manager to position the items
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        rvPosts.addItemDecoration(new SimpleVerticalSpacerItemDecoration(24));
        if (isNetworkAvailable()) {
            getPosts();
        } else {
            Toast.makeText(
                    this,
                    "Network Error\n\nPlease connect to the internet and try again.", 20).show();
            finish();
        }
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void getPosts() {
        InstagramClient.getPopularFeed(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    posts.clear();
                    posts.addAll(Utils.decodePostsFromJsonResponse(response));
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.wtf("Utils", "Error while trying to fetch posts.");
                    return;
                }
            }
        });
    }
}
