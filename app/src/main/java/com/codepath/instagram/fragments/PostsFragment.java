package com.codepath.instagram.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.instagram.R;
import com.codepath.instagram.adapters.InstagramPostsAdapter;
import com.codepath.instagram.core.MainApplication;
import com.codepath.instagram.helpers.SimpleVerticalSpacerItemDecoration;
import com.codepath.instagram.helpers.Utils;
import com.codepath.instagram.models.InstagramClient;
import com.codepath.instagram.models.InstagramPost;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lshi on 12/3/15.
 */
public class PostsFragment extends Fragment {
    private static final String TAG = "PostsFragment";
    List<InstagramPost> posts;
    InstagramPostsAdapter adapter;

    InstagramClient client;

//    @Bind(R.id.rvPosts) RecyclerView rvPosts;
    RecyclerView rvPosts;


    // newInstance constructor for creating fragment with arguments
    public static PostsFragment newInstance() { //int page, String title) {
        return new PostsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar.
        inflater.inflate(R.menu.menu_home, menu);

        // ...
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

//        ButterKnife.bind(this, view);
        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);

        posts = new ArrayList<>();
        adapter = new InstagramPostsAdapter(posts);

        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
        // Attach the adapter to the recyclerview to populate items
        rvPosts.setAdapter(adapter);
        // Set layout manager to position the items
        rvPosts.setLayoutManager(new LinearLayoutManager(view.getContext()));

        rvPosts.addItemDecoration(new SimpleVerticalSpacerItemDecoration(24));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        client = MainApplication.getRestClient();

        Log.wtf("PostsFragment", "CALLED: onActivityCreated.");
        if (isNetworkAvailable()) {
            getPosts();
        } else {
            Toast.makeText(
                    getContext(),
                    "Network Error\n\nPlease connect to the internet and try again.", 20).show();
            // TODO: this does not work any more?
            // ((HomeActivity) getContext()).finish();

        }
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                // (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void getPosts() {
        client.getPopularFeed(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    posts.clear();
                    posts.addAll(Utils.decodePostsFromJsonResponse(response));
                    Log.wtf("PostsFragment", "DONE: getPosts: " + posts.size());
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.wtf("Utils", "Error while trying to fetch posts.");
                    return;
                }
            }
        });
    }
}
