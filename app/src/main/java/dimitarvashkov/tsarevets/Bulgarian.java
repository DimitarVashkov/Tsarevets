package dimitarvashkov.tsarevets;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class Bulgarian extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int BARCODE_READER_REQUEST_CODE = 1;
    //CHANGE TO BG
    //TODO Change to BG once you have the music
    private String bgLabel = "DE.mp3";

    private TextView mResultTextView;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulgarian);

        videoView = (VideoView)findViewById(R.id.myvideoview);

         //mResultTextView = (TextView) findViewById(R.id.result_textview);


        Button scanBarcodeButton = (Button) findViewById(R.id.scan_barcode_button);
        scanBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
            }
        });

        Button back = (Button) findViewById(R.id.backButtonBG);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Bulgarian.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Point[] p = barcode.cornerPoints;

//                    //mResultTextView.append("android.resource://"+getPackageName()+"/raw/" + barcode.displayValue);
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    //Uri.parse("android.resource://com.my.package/drawable/icon");
//                    //Uri.parse(barcode.displayValue + bgLabel)
//                    String location = "android.resource://"+getPackageName()+"/raw/";
//                    intent.setDataAndType(Uri.parse(location + barcode.displayValue), "audio/mp3");
//                    startActivity(intent);


                    //Start audio and switch background photos of VideoView

                    MediaController mediaController = new MediaController(this);
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    String path = "android.resource://" + getPackageName() + "/raw/" + barcode.displayValue;

                    switch(barcode.displayValue){
                        case "first_song":
                            videoView.setBackgroundResource(R.drawable.first_photo);
                            break;
                        case "second_song":
                            videoView.setBackgroundResource(R.drawable.second_photo);
                            break;
                        case "third_song":
                            videoView.setBackgroundResource(R.drawable.third_photo);
                            break;
                        case "fourth_song":
                            videoView.setBackgroundResource(R.drawable.fourth_photo);
                            break;
                        case "fifth_song":
                            videoView.setBackgroundResource(R.drawable.fifth_photo);
                            break;
                        case "sixth_song":
                            videoView.setBackgroundResource(R.drawable.sixth_photo);
                            break;
                    }

                    videoView.setVideoPath(path);
                    videoView.start();




                } else mResultTextView.setText("Capture code");
            } else Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                    CommonStatusCodes.getStatusCodeString(resultCode)));
        } else super.onActivityResult(requestCode, resultCode, data);
    }

}
