package androidcourse.venturus.org.br.androidcourse_firstexample_likecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String NUM_LIKES_PARAM = "num_likes";

    private int mLikeCounter = 0;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = this.getSharedPreferences("arquivo_preferencias", Context.MODE_PRIVATE);

        mLikeCounter = mPrefs.getInt("num_likes", 0);

        TextView likesText = (TextView) findViewById(R.id.likes_text);
        String zeroLikes = getResources().getString(R.string.like_counter, mLikeCounter);
        likesText.setText(zeroLikes);

        ImageView robotImageView = (ImageView) findViewById(R.id.marshmallow_image);

        robotImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(MainActivity.this, PictureActivity.class);
                pictureIntent.putExtra(NUM_LIKES_PARAM, mLikeCounter);
                MainActivity.this.startActivityForResult(pictureIntent, 999);
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Log.d("debug", "onActivityResult: " + requestCode);
        if (requestCode == 999) {
            Toast.makeText(this, "Você voltou da PictureActivity!!!", Toast.LENGTH_LONG).show();
        }
    }


    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt("num_likes", mLikeCounter);
        editor.apply();
    }

    public void likeClicked(View view) {
        mLikeCounter++;

        String someLikes = getResources().getString(R.string.like_counter, mLikeCounter);

        TextView likesText = (TextView) findViewById(R.id.likes_text);

        likesText.setText(someLikes);
    }
}
