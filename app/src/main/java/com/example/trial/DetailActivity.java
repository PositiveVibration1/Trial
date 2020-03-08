package com.example.trial;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.trial.MainActivity.EXTRA_CREATOR;
import static com.example.trial.MainActivity.EXTRA_LIKES;
import static com.example.trial.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {
    private Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        String image_Url=intent.getStringExtra(EXTRA_URL);
        String CreatorName=intent.getStringExtra(EXTRA_CREATOR);
        int likeCount=intent.getIntExtra(EXTRA_LIKES, 0);


        ImageView imageView=findViewById(R.id.new_image_view);
        TextView textViewCreator=findViewById(R.id.new_text_view);
        TextView textViewLikes=findViewById(R.id.new_text_description);
        textViewCreator.setText(CreatorName);
        textViewLikes.setText("Likes:" + likeCount);

        Picasso.with(this).load(image_Url).fit().centerInside().into(imageView);
        //imageView.setImageDrawable(mCtx.getResources().getDrawable(image_Url,image_Url));

    }
}
