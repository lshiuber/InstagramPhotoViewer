package com.codepath.instagram.adapters;

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
import com.codepath.instagram.models.InstagramComment;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lshi on 12/2/15.
 */
public class InstagramCommentsAdapter extends
        RecyclerView.Adapter<InstagramCommentsAdapter.CommentItemViewHolder> {
    private List<InstagramComment> mComments;
    Transformation mTransformation;

    public InstagramCommentsAdapter(List<InstagramComment> comments) {
        mComments = comments;
        mTransformation = new RoundedTransformationBuilder()
                .borderWidthDp(0)
                .cornerRadiusDp(30)
                .oval(false)
                .build();
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    @Override
    public CommentItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.layout_item_comment, parent, false);

        // Return a new holder instance
        return new CommentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentItemViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        InstagramComment comment= mComments.get(position);

        // Picture
        Picasso.with(context)
                .load(comment.user.profilePictureUrl)
                .fit()
                .transform(mTransformation)
                .placeholder(R.drawable.gray_oval)
                .into(holder.ivCProfilePicture);

        // Comment
        SpannableStringBuilder ssb = RenderCaptionOrComment(
                context,
                comment.user.userName,
                comment.text);
        holder.tvCComment.setText(ssb);

        // Date
        holder.tvCPostDate.setText(
                DateUtils.getRelativeTimeSpanString(
                        comment.createdTime * 1000,
                        System.currentTimeMillis(),
                        DateUtils.HOUR_IN_MILLIS));
    }

    private SpannableStringBuilder RenderCaptionOrComment(
            Context context, String userName, String text) {
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        int prev_length = 0;

        {
            ssb.append(userName + " ");
            ForegroundColorSpan span_c = new ForegroundColorSpan(
                    context.getResources().getColor(R.color.blue_text));
            TypefaceSpan span_t = new TypefaceSpan("sans-serif-medium");
            ssb.setSpan(span_c, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(span_t, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            prev_length = ssb.length();
        }
        {
            ssb.append(text);
            ForegroundColorSpan span_c = new ForegroundColorSpan(
                    context.getResources().getColor(R.color.gray_text));
            TypefaceSpan span_t = new TypefaceSpan("sans-serif");
            ssb.setSpan(span_c, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(span_t, prev_length, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            prev_length = ssb.length();
        }
        return ssb;
    }

    public static class CommentItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivCProfilePicture) ImageView ivCProfilePicture;
        @Bind(R.id.tvCComment) TextView tvCComment;
        @Bind(R.id.tvCPostDate) TextView tvCPostDate;

        public CommentItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
