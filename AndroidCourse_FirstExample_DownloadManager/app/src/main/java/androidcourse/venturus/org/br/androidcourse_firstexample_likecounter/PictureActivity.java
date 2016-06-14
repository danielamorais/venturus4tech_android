package androidcourse.venturus.org.br.androidcourse_firstexample_likecounter;

import android.Manifest;
import android.app.DownloadManager;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class PictureActivity extends AppCompatActivity {

    private long downloadId = -1;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 81;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ImageView image = (ImageView) findViewById(R.id.marshmallow_big_image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureActivity.this.checkPermissionAndDownload();
            }
        });
    }


    private void checkPermissionAndDownload() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, R.string.permission_explanation_text, Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            doDownload();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doDownload();
                } else {
                    Toast.makeText(this, R.string.permission_denied_text, Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    private void doDownload() {
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse("https://goo.gl/RnPVhk"));

        request.setTitle(getResources().getString(R.string.download_text));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                File.separator + "my_app_downloads" + File.separator + "marshmallow_big.jpg");

        downloadId = downloadManager.enqueue(request);

        registerReceiver(new DownloadCompleteReceiver(downloadId), new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}