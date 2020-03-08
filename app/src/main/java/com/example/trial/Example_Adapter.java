package com.example.trial;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/*
*recyclerView. Adapter
* recycler
*
 */
public class Example_Adapter extends RecyclerView.Adapter<Example_Adapter.ExampleViewHolder> {

    private Context mCtx;
    private List<ExampleItem> mExampleList1;
    private ArrayList<ExampleItem>mExampleList;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public  void setOnItemClickListener(onItemClickListener listener){
        mListener=listener;
    }

    public Example_Adapter(Context context, ArrayList<ExampleItem> exampleList){
        mCtx=context;
        mExampleList=exampleList;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.example_item,null);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem exampleItem=mExampleList.get(i);
        String image_Url=exampleItem.getmImageUrl();
        String creatorName=exampleItem.getmCreator();
        int likeCount=exampleItem.getmLikes();

        exampleViewHolder.text_view_creator.setText(creatorName);
        exampleViewHolder.text_view_downloads.setText("Likes:" + likeCount);
        /*Picasso.with(mCtx).load(imageUrl).fit().centerInside().into(exampleViewHolder.image_view);*/
        Picasso.with(mCtx)
                .load(exampleItem.getmImageUrl())
                .into(exampleViewHolder.image_view);
       exampleViewHolder.image_view.setImageDrawable(mCtx.getResources().getDrawable(Integer.parseInt(image_Url)));





    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder{
        ImageView image_view;
        TextView text_view_creator, text_view_downloads;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            image_view=itemView.findViewById(R.id.image_view);
            text_view_creator=itemView.findViewById(R.id.text_view_creator);
            text_view_downloads=itemView.findViewById(R.id.text_view_downloads);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                       int i=getAdapterPosition();
                       if (i!=RecyclerView.NO_POSITION){
                           mListener.onItemClick(i);
                       }
                    }
                }
            });
        }
    }
}
