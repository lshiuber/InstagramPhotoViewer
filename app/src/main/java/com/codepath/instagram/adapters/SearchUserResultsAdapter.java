package com.codepath.instagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codepath.instagram.R;
import com.codepath.instagram.activities.CommentsActivity;
import com.codepath.instagram.helpers.Utils;
import com.codepath.instagram.models.InstagramPost;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lshi on 12/3/15.
 */
public class SearchUserResultsAdapter extends
        RecyclerView.Adapter<SearchUserResultsAdapter.SearchUserItemViewHolder> {

    public int getItemCount() { return 0; }

    @Override
    public SearchUserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Context context = parent.getContext();
//            LayoutInflater inflater = LayoutInflater.from(context);
//
//            // Inflate the custom layout
//            View view = inflater.inflate(R.layout.layout_item_post, parent, false);
//
//            // Return a new holder instance
//            return new PostItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchUserItemViewHolder holder, int position) {
//            Context context = holder.itemView.getContext();
//
//            InstagramPost post = mPosts.get(position);
//
//            // 1st row: user name, profile picture and date.
//            holder.tvUserName.setText(post.user.userName);
//            Picasso.with(context)
//                    .load(post.user.profilePictureUrl)
//                    .fit()
//                    .transform(mTransformation)
//                    .placeholder(R.drawable.gray_oval)
//                    .into(holder.ivProfilePicture);
//            holder.tvPostDate.setText(
//                    DateUtils.getRelativeTimeSpanString(
//                            post.createdTime * 1000,
//                            System.currentTimeMillis(),
//                            DateUtils.HOUR_IN_MILLIS));
//
//            // 2nd row: Image
//            Picasso.with(context)
//                    .load(post.image.imageUrl)
//                    .placeholder(R.drawable.gray_rectangle)
//                    .into(holder.ivPost);
//            int display_width = context.getResources().getDisplayMetrics().widthPixels;
//            holder.ivPost.getLayoutParams().height =
//                    display_width * post.image.imageHeight / post.image.imageWidth;
//
//            // 2.5 buttons
//            holder.ibShare.setTag(holder);
//            holder.ibShare.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                            PostItemViewHolder holder = (PostItemViewHolder) (v.getTag());
//                            Uri bmpUri = getLocalBitmapUri(holder.ivPost);
//                            if (bmpUri != null) {
//                                    // Construct a ShareIntent with link to image
//                                    Intent shareIntent = new Intent();
//                                    shareIntent.setAction(Intent.ACTION_SEND);
//                                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
//                                    shareIntent.setType("image/*");
//                                    // Launch sharing dialog for image
//                                    v.getContext().startActivity(Intent.createChooser(shareIntent, "Share Image"));
//                            } else {
//                                    // Handle error
//                            }
//                    }
//
//                    // Returns the URI path to the Bitmap displayed in specified ImageView
//                    public Uri getLocalBitmapUri(ImageView imageView) {
//                            // Extract Bitmap from ImageView drawable
//                            Drawable drawable = imageView.getDrawable();
//                            Bitmap bmp = null;
//                            if (drawable instanceof BitmapDrawable){
//                                    bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//                            } else {
//                                    return null;
//                            }
//                            // Store image to default external storage directory
//                            Uri bmpUri = null;
//                            try {
//                                    File file =  new File(Environment.getExternalStoragePublicDirectory(
//                                            Environment.DIRECTORY_DOWNLOADS),
//                                            "share_image_" + System.currentTimeMillis() + ".png");
//                                    file.getParentFile().mkdirs();
//                                    FileOutputStream out = new FileOutputStream(file);
//                                    bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
//                                    out.close();
//                                    bmpUri = Uri.fromFile(file);
//                            } catch (IOException e) {
//                                    e.printStackTrace();
//                            }
//                            return bmpUri;
//                    }
//            });
//
//            // 3rd row: Like
//            holder.tvLike.setText(Utils.formatNumberForDisplay(post.likesCount) + " likes");
//
//            // 4th row: caption
//            if (post.caption == null) {
//                    holder.tvCaption.setVisibility(View.GONE);
//            } else {
//                    SpannableStringBuilder ssb = RenderCaptionOrComment(
//                            context, post.user.userName, post.caption);
//                    holder.tvCaption.setText(ssb);
//                    holder.tvCaption.setVisibility(View.VISIBLE);
//            }
//
//            // 5th row: add the comments
//            if (post.commentsCount > 2) {
//                    holder.mediaId = post.mediaId;
//                    holder.tvViewAllComments.setText("View all " + post.commentsCount + " comments");
//                    holder.tvViewAllComments.setVisibility(View.VISIBLE);
//                    holder.tvViewAllComments.setTag(holder);
//                    holder.tvViewAllComments.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                    PostItemViewHolder holder = (PostItemViewHolder) (v.getTag());
//                                    Intent i = new Intent(v.getContext(), CommentsActivity.class);
//                                    i.putExtra("mediaId", holder.mediaId);
//                                    v.getContext().startActivity(i);
//                            }
//                    });
//            } else {
//                    holder.tvViewAllComments.setVisibility(View.GONE);
//            }
//            LinearLayout llComments = (LinearLayout) holder.itemView.findViewById(R.id.llComments);
//            llComments.removeAllViews();
//
//            // Last but one comment
//            for (int index = post.comments.size()-2; index < post.comments.size(); index++) {
//                    if (index >= 0) {
//                            View view = LayoutInflater.from(context).inflate(
//                                    R.layout.layout_item_text_comment, llComments, false);
//                            TextView tvComment = (TextView) view.findViewById(R.id.tvComment);
//                            SpannableStringBuilder ssb = RenderCaptionOrComment(
//                                    context,
//                                    post.comments.get(index).user.userName,
//                                    post.comments.get(index).text);
//                            tvComment.setText(ssb);
//                            llComments.addView(view);
//                    }
//            }
    }

    public static class SearchUserItemViewHolder extends RecyclerView.ViewHolder {
//            String mediaId;
//
//            // Elements for the 1st row
//            @Bind(R.id.tvUserName) TextView tvUserName;
//            @Bind(R.id.ivProfilePicture) ImageView ivProfilePicture;
//            @Bind(R.id.tvPostDate) TextView tvPostDate;
//
//            // Elements for the 2nd row
//            @Bind(R.id.ivPost) ImageView ivPost;
//
//            @Bind(R.id.ibShare)
//            ImageButton ibShare;
//
//            // Elements for the 3rd row
//            @Bind(R.id.tvLike) TextView tvLike;
//
//            // Elements for the 4th row
//            @Bind(R.id.tvCaption) TextView tvCaption;
//
//            // Elements for the 5th row
//            @Bind(R.id.tvViewAllComments) TextView tvViewAllComments;

        public SearchUserItemViewHolder(View itemView) {
            super(itemView);
//                ButterKnife.bind(this, itemView);
        }
    }
}