package me.nutchy.cine.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder> {

    private Context context;
    private DatabaseReference databaseReference;
    private List<Comment> mComments;

    public CommentsAdapter(Context context, Movie movie) {
        this.context = context;
        this.mComments = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("comments").child(String.valueOf(movie.getId()))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mComments.clear();
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            mComments.add(ds.getValue(Comment.class));
                        }
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }



    @Override
    public CommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.comment_item, parent, false);
        return new CommentsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsHolder holder, int position) {
        TextView tv_fullName = holder.tv_fullName;
        TextView tv_comment = holder.tv_comment;
        tv_fullName.setText(mComments.get(position).getFullName());
        tv_comment.setText(mComments.get(position).getComment());
        System.out.println(mComments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class CommentsHolder extends RecyclerView.ViewHolder {
        TextView tv_fullName, tv_comment;

        public CommentsHolder(View itemView) {
            super(itemView);
            tv_fullName = itemView.findViewById(R.id.tv_comment_fullName);
            tv_comment = itemView.findViewById(R.id.tv_comment);

        }
    }
}
