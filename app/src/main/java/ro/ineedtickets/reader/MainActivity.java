package ro.ineedtickets.reader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements IVolleyCallback{

    private Button scanButton;
    private TextView successInfo;
    private TextView responseDetails;

    private String ticketId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = findViewById(R.id.scan_button);
        successInfo = findViewById(R.id.success_info);
        responseDetails = findViewById(R.id.response_details);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() != null){
                ticketId = result.getContents();

                TicketValidationRequestService requestService = new TicketValidationRequestService(this);
                requestService.SendRequest(ticketId);
            }
        }
        else{
            Toast.makeText(this, "There was an error in retrieving the data", Toast.LENGTH_LONG).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void VolleyResponse(ValidationResponseModel ticketValidation) {
        if(ticketValidation != null && !ticketValidation.hasError) {
            if(ticketValidation.isValid){
                successInfo.setTextColor(Color.parseColor("#99dd99"));
                successInfo.setText("Success!");
                responseDetails.setText(ticketValidation.eventName + " " + ticketValidation.areaName + " " + ticketValidation.locationName + " " + ticketValidation.eventDate);
            }
            else{
                successInfo.setTextColor(Color.parseColor("#dd9999"));
                successInfo.setText("This ticket was already used");
                responseDetails.setText("");
            }
        }
        else{
            Toast.makeText(this, "Null", Toast.LENGTH_LONG).show();
        }
    }
}
