package dimitarvashkov.tsarevets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static dimitarvashkov.tsarevets.R.id.info;

public class English extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int BARCODE_READER_REQUEST_CODE = 1;

    private String enLabel = "_en";

    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        videoView = (VideoView)findViewById(R.id.myvideoview);

        Button scanBarcodeButton = (Button) findViewById(R.id.scan_barcode_button);
        scanBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                boolean tmp = am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
                new IntentIntegrator(English.this).setBeepEnabled(tmp).setCaptureActivity(ScanScanner.class).setPrompt(getString(R.string.barcode_scanner_prompt)).initiateScan();
            }
        });

        Button back = (Button) findViewById(R.id.backButtonEN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                English.super.onBackPressed();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() != null) {
                    String scannerResult = result.getContents();
                    Log.d(LOG_TAG, scannerResult);
                    MediaController mediaController = new MediaController(this);
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    String path = "android.resource://" + getPackageName() + "/raw/" + scannerResult + enLabel;

                    switch(scannerResult){
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
                        default:
                            Toast.makeText(getApplicationContext(), R.string.barcode_scanner_unknown_code, Toast.LENGTH_LONG).show();
                            return;
                    }

                    videoView.setVideoPath(path);
                    videoView.start();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Unknown Qr-Code", Toast.LENGTH_SHORT).show();
        }

        //OLD CODE:
//        if (requestCode == BARCODE_READER_REQUEST_CODE) {
//            if (resultCode == CommonStatusCodes.SUCCESS) {
//                if (data != null) {
//                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
//                    Point[] p = barcode.cornerPoints;
//
//                    MediaController mediaController = new MediaController(this);
//                    mediaController.setAnchorView(videoView);
//                    videoView.setMediaController(mediaController);
//                    String path = "android.resource://" + getPackageName() + "/raw/" + barcode.displayValue + enLabel;
//
//                    switch(barcode.displayValue){
//                        case "first_song":
//                            videoView.setBackgroundResource(R.drawable.first_photo);
//                            break;
//                        case "second_song":
//                            videoView.setBackgroundResource(R.drawable.second_photo);
//                            break;
//                        case "third_song":
//                            videoView.setBackgroundResource(R.drawable.third_photo);
//                            break;
//                        case "fourth_song":
//                            videoView.setBackgroundResource(R.drawable.fourth_photo);
//                            break;
//                        case "fifth_song":
//                            videoView.setBackgroundResource(R.drawable.fifth_photo);
//                            break;
//                        case "sixth_song":
//                            videoView.setBackgroundResource(R.drawable.sixth_photo);
//                            break;
//                    }
//
//                    videoView.setVideoPath(path);
//                    videoView.start();
//
//
//                } else videoView.setBackgroundResource(R.drawable.tsarevets);
//            } else Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
//                    CommonStatusCodes.getStatusCodeString(resultCode)));
//        } else super.onActivityResult(requestCode, resultCode, data);
    }
}
