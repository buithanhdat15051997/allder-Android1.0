package company.com.allder1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

import company.com.allder1.R;
import company.com.allder1.httpRequester.VollyRequester;

public class FeedbackActivity extends AppCompatActivity {
    Button btnSubmitFeedback;
    EditText edtConmentFeedback, edtemailFeedback;
    TextView txtRatingView;
    RatingBar ratingBar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        inti();
        getdataFeedback();
        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String ratingView = String.valueOf(rating);
                txtRatingView.setText(ratingView + "/5");
            }
        });
    }

    private void getdataFeedback() {
        HashMap<String, String> params = new HashMap<>();
    }

    private void inti() {
        edtConmentFeedback= findViewById(R.id.edtComments);
        edtemailFeedback = findViewById(R.id.edtEmailfeedback);
        btnSubmitFeedback = findViewById(R.id.btnFeedback);
        ratingBar1 = findViewById(R.id.ratingBar1);
        txtRatingView = findViewById(R.id.txtViewrating);
    }
}
