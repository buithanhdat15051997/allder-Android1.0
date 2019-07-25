package company.com.allder1.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import company.com.allder1.R;
import company.com.allder1.httpRequester.AsyncTaskCompleteListener;
import company.com.allder1.httpRequester.VollyRequester;
import company.com.allder1.model.Question;
import company.com.allder1.utils.Const;
import company.com.allder1.utils.PreferenceHelper;

public class MyFavoriteActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    private static final int REQUEST_CODE_QUIZ = 1;


    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String KEY_HIGHSCORE = "keyHighscore";
    private TextView textviewHighscore;
//    private int highscore;
    int i = 0;
    public  static  int  scoreHTQuiz;
    public  static  int  scoreHTBox;
    Button buttonStartQuiz,btnStartBox;
    public  static int TotalPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        textviewHighscore = findViewById(R.id.text_view_highscore);
        btnStartBox = findViewById(R.id.button_start_boxgame);
        btnStartBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBox = new Intent(MyFavoriteActivity.this,GameBox.class);
                startActivityForResult(intentBox,REQUEST_CODE_QUIZ);

            }
        });


        loadHighscore();
         buttonStartQuiz = findViewById(R.id.button_start_quiz);
        getData();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                 scoreHTQuiz = data.getIntExtra(Quiz.EXTRA_SCORE, 0);
                 scoreHTBox = data.getIntExtra(GameBox.EXTRA_SCOREBOX,0);
                Log.d("scoreBox",scoreHTBox+"");

                btnStartBox.setVisibility(View.INVISIBLE);
                buttonStartQuiz.setVisibility(View.INVISIBLE);

                updateHighscore(scoreHTQuiz,scoreHTBox);
                SendPoint();
            }
        }
    }
    private  void SendPoint(){
        if(scoreHTQuiz>0){
            HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put(Const.Params.URL, Const.ServiceType.PONINT_NIMIGAME);
            map1.put("user_id", new PreferenceHelper(this).getUserId());
            map1.put("type_person_order", "users");
            map1.put("token", new PreferenceHelper(this).getSessionToken());
            map1.put("point", String.valueOf(scoreHTQuiz));
            //EditText ed_Chopsticks,ed_spoon,ed_bowl,ed_folk;
            new VollyRequester(this,Const.POST, map1, 123456,
                    this);
            Log.d("ManhPointQuiz", map1.toString());


        }
        else {
            HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put(Const.Params.URL, Const.ServiceType.PONINT_NIMIGAME);
            map1.put("user_id", new PreferenceHelper(this).getUserId());
            map1.put("type_person_order", "users");
            map1.put("token", new PreferenceHelper(this).getSessionToken());
            map1.put("point", String.valueOf(scoreHTBox));
            //EditText ed_Chopsticks,ed_spoon,ed_bowl,ed_folk;
            new VollyRequester(this,Const.POST, map1, 123456,
                    this);
            Log.d("ManhPointBox", map1.toString());

        }

    }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
       TotalPoint= prefs.getInt("key_point", 0);
        Log.d("HighScoreLoad",TotalPoint+"");
        textviewHighscore.setText("Totalscore: " + TotalPoint);
    }
    private void updateHighscore(int highscoreNew, int highscoreBoxNew) {
        if(highscoreNew >0){
            TotalPoint += highscoreNew;
        }else {
            TotalPoint += highscoreBoxNew;
        }
        // high score bỏ dấu +
        textviewHighscore.setText("Totalscore: " + TotalPoint);
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(KEY_HIGHSCORE, highscore);
//        editor.apply();
    }
    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(MyFavoriteActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Const.GET, "http://allder.qooservices.cf/managetmentFoodApi/minigame/one", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d("Api111", "getData: "+response);
                buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String array = String.valueOf(response);
                        Intent intent = new Intent(getApplicationContext(),Quiz.class);
                        intent.putExtra("data", array);
                        startActivityForResult(intent,REQUEST_CODE_QUIZ);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
     requestQueue.add(jsonArrayRequest);
    }
    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case 123456:
                Log.d("ManhPointSuccess",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                 TotalPoint = Integer.parseInt(jsonObject.getString("point"));
                    Log.d("TotalPoint", String.valueOf(TotalPoint));
                    textviewHighscore.setText("Totalscore: " + TotalPoint);
                    SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("key_point", TotalPoint);
                    editor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
