package com.codepath.instagram.models;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by lshi on 12/2/15.
 */
public class InstagramClient {
    private static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private static final String BASE_URL = "https://api.instagram.com";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getPopularFeed(JsonHttpResponseHandler responseHandler) {
        client.get(BASE_URL + "/v1/media/popular?client_id=" + CLIENT_ID, responseHandler);
    }

    public static void getComments(String mediaId, JsonHttpResponseHandler responseHandler) {
        client.get(BASE_URL + "/v1/media/" + mediaId + "/comments?client_id=" + CLIENT_ID,
                   responseHandler);
    }
}
