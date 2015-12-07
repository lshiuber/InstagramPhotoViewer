package com.codepath.instagram.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.instagram.R;
import com.codepath.instagram.adapters.SearchUserResultsAdapter;
import com.codepath.instagram.core.MainApplication;
import com.codepath.instagram.models.InstagramClient;

/**
 * Created by lshi on 12/3/15.
 */
public class SearchUsersResultFragment extends Fragment {


    private static final String TAG = "SearchUsersResultFragment";
//    List<InstagramPost> posts;
    SearchUserResultsAdapter adapter;

    InstagramClient client;

    //    @Bind(R.id.rvPosts) RecyclerView rvPosts;
    RecyclerView rvSearchUsersResult;

    // newInstance constructor for creating fragment with arguments
    public static SearchUsersResultFragment newInstance() {
        return new SearchUsersResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar.
        inflater.inflate(R.menu.menu_home, menu);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_users_result, container, false);

//        ButterKnife.bind(this, view);
//        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
//
//        posts = new ArrayList<>();
//        adapter = new InstagramPostsAdapter(posts);
//
//        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
//        // Attach the adapter to the recyclerview to populate items
//        rvPosts.setAdapter(adapter);
//        // Set layout manager to position the items
//        rvPosts.setLayoutManager(new LinearLayoutManager(view.getContext()));
//
//        rvPosts.addItemDecoration(new SimpleVerticalSpacerItemDecoration(24));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        client = MainApplication.getRestClient();

//        Log.wtf("PostsFragment", "CALLED: onActivityCreated.");
//        if (isNetworkAvailable()) {
//            getPosts();
//        } else {
//            Toast.makeText(
//                    getContext(),
//                    "Network Error\n\nPlease connect to the internet and try again.", 20).show();
//            // TODO: this does not work any more?
//            // ((HomeActivity) getContext()).finish();
//
//        }
    }

//    private Boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager =
//                // (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
//    }
//
//    public void getPosts() {
//        client.getPopularFeed(new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    posts.clear();
//                    posts.addAll(Utils.decodePostsFromJsonResponse(response));
//                    Log.wtf("PostsFragment", "DONE: getPosts: " + posts.size());
//                    adapter.notifyDataSetChanged();
//                } catch (Exception e) {
//                    Log.wtf("Utils", "Error while trying to fetch posts.");
//                    return;
//                }
//            }
//        });
//    }
}
