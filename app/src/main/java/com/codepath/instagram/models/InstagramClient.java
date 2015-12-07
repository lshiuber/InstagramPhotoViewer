package com.codepath.instagram.models;

import android.content.Context;

import com.codepath.instagram.helpers.Constants;
import com.codepath.instagram.networking.InstagramApi;
import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.scribe.builder.api.Api;

/**
 * Created by lshi on 12/2/15.
 */
public class InstagramClient extends OAuthBaseClient {
    private static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private static final String BASE_URL = "https://api.instagram.com";
    private static AsyncHttpClient client_old = new AsyncHttpClient();

    public static final Class<? extends Api> REST_API_CLASS = InstagramApi.class;
    public static final String REST_URL = "https://api.instagram.com/v1/";
    public static final String REST_CONSUMER_KEY = "e05c462ebd86446ea48a5af73769b602";
    public static final String REST_CONSUMER_SECRET = "7f18a14de6c241c2a9ccc9f4a3df4b35";

    public static final String REDIRECT_URI = Constants.REDIRECT_URI;
    public static final String SCOPE = Constants.SCOPE;

    public InstagramClient(Context context) {
        super(context,
              REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REDIRECT_URI, SCOPE);
    }

    public void getPopularFeed(JsonHttpResponseHandler responseHandler) {
        client.get(BASE_URL + "/v1/media/popular", responseHandler);
    }

    public void getComments(String mediaId, JsonHttpResponseHandler responseHandler) {
        client.get(BASE_URL + "/v1/media/" + mediaId + "/comments", responseHandler);
    }
}
