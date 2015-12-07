package com.codepath.instagram.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.instagram.R;
import com.codepath.instagram.core.MainApplication;
import com.codepath.instagram.helpers.Utils;
import com.codepath.instagram.models.InstagramClient;
import com.codepath.instagram.models.InstagramComment;
import com.codepath.instagram.models.InstagramCommentsAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lshi on 12/2/15.
 */
public class CommentsActivity extends AppCompatActivity {
//    private static final String TAG = "CommentsActivity";
    List<InstagramComment> comments;
    InstagramCommentsAdapter adapter;
    InstagramClient client;

    @Bind(R.id.rvComments) RecyclerView rvComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        client = MainApplication.getRestClient();

        // Create adapter
        comments = new ArrayList<>();
        adapter = new InstagramCommentsAdapter(comments);
        // Attach the adapter to the recyclerview to populate items
        rvComments.setAdapter(adapter);
        // Set layout manager to position the items
        rvComments.setLayoutManager(new LinearLayoutManager(this));

        String mediaId = getIntent().getStringExtra("mediaId");

        getComments(mediaId);
    }

    public void getComments(String mediaId) {
        comments.clear();
        adapter.notifyDataSetChanged();

        client.getComments(mediaId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    comments.addAll(Utils.decodeCommentsFromJsonResponse(response));
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.wtf("Utils", "Error while trying to fetch comments.");
                    return;
                }
            }
        });
        }
    }
