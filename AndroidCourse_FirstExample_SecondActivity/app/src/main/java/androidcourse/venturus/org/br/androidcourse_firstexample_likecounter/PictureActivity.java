package androidcourse.venturus.org.br.androidcourse_firstexample_likecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        int numLikes = this.getIntent().getIntExtra(MainActivity.NUM_LIKES_PARAM, 0);

        TextView likesText = (TextView) findViewById(R.id.likes_text);
        String zeroLikes = getResources().getString(R.string.like_counter, numLikes);
        likesText.setText(zeroLikes);
    }
}
