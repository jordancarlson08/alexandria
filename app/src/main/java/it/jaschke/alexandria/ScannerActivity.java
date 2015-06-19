package it.jaschke.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.google.zxing.Result;

import it.jaschke.alexandria.utils.Constants;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * This Activity was created to scan barcodes. It uses a library found here: https://github.com/dm77/barcodescanner
 */
public class ScannerActivity extends ActionBarActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = ScannerActivity.class.getSimpleName();
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        Log.v(TAG, result.getText()); // Prints scan results
        Log.v(TAG, result.getBarcodeFormat().toString());

        Intent intent = new Intent();
        intent.putExtra(Constants.BARCODE, result.getText());
        setResult(Constants.RESULT_OK, intent);
        finish();
    }
}
