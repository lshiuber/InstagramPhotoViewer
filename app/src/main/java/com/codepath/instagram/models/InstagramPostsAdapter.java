package com.codepath.instagram.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.instagram.R;
import com.codepath.instagram.helpers.Utils;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by lshi on 11/30/15.
 */
public class InstagramPostsAdapter extends
        RecyclerView.Adapter<InstagramPostsAdapter.PostItemViewHolder> {

    private List<InstagramPost> mPosts;
    Transformation mTransformation;

    public InstagramPostsAdapter(List<InstagramPost> posts) {
        mPosts = posts;
        mTransformation = new RoundedTransformationBuilder()
                .borderWidthDp(0)
                .cornerRadiusDp(30)
                .oval(false)
                .build();
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    @Override
    public PostItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.layout_item_post, parent, false);

        // Return a new holder instance
        return new PostItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostItemViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        InstagramPost post = mPosts.get(position);

        // 1st row: user name, profile picture and date.
        holder.tvUserName.setText(post.user.userName);
        Picasso.with(context)
                .load(post.user.profilePictureUrl)
                .fit()
                .transform(mTransformation)
                .placeholder(R.drawable.gray_oval)
                .into(holder.ivProfilePicture);
        holder.tvPostDate.setText(
                DateUtils.getRelativeTimeSpanString(
                        post.createdTime * 1000,
                        System.currentTimeMillis(),
                        DateUtils.DAY_IN_MILLIS));

        // 2nd row: Image
        Picasso.with(context)
                .load(post.image.imageUrl)
                .placeholder(R.drawable.gray_rectangle)
                .into(holder.ivPost);
        int display_width = context.getResources().getDisplayMetrics().widthPixels;
        holder.ivPost.getLayoutParams().height =
                display_width * post.image.imageHeight / post.image.imageWidth;

        // 3rd row: Like
        holder.tvLike.setText(Utils.formatNumberForDisplay(post.likesCount) + " likes");

        // 4th row: caption
        if (post.caption == null) {
            holder.tvCaption.setVisibility(View.GONE);
        } else {
            SpannableStringBuilder ssb = new SpannableStringBuilder();
            int prev_length = 0;

            {
                ssb.append(post.user.userName + " ");
                ForegroundColorSpan span_c = new ForegroundColorSpan(
                        context.getResources().getColor(R.color.blue_text));
                TypefaceSpan span_t = new TypefaceSpan("sans-serif-medium");
                ssb.setSpan(span_c, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.setSpan(span_t, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                prev_length = ssb.length();
            }
            {
                ssb.append(post.caption);
                ForegroundColorSpan span_c = new ForegroundColorSpan(
                        context.getResources().getColor(R.color.gray_text));
                TypefaceSpan span_t = new TypefaceSpan("sans-serif");
                ssb.setSpan(span_c, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.setSpan(span_t, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                prev_length = ssb.length();
            }

            holder.tvCaption.setText(ssb);
            holder.tvCaption.setVisibility(View.VISIBLE);
        }
    }

    public static class PostItemViewHolder extends RecyclerView.ViewHolder {
        // Elements for the 1st row
        public TextView tvUserName;
        public ImageView ivProfilePicture;
        public TextView tvPostDate;

        // Elements for the 2nd row
        public ImageView ivPost;

        // Elements for the 3rd row
        public TextView tvLike;

        // Elements for the 4th row
        public TextView tvCaption;

        public PostItemViewHolder(View itemView) {
            super(itemView);

            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            ivProfilePicture = (ImageView) itemView.findViewById(R.id.ivProfilePicture);
            tvPostDate = (TextView) itemView.findViewById(R.id.tvPostDate);

            ivPost = (ImageView) itemView.findViewById(R.id.ivPost);

            tvLike = (TextView) itemView.findViewById(R.id.tvLike);

            tvCaption = (TextView) itemView.findViewById(R.id.tvCaption);
        }
    }
}