package company.com.allder1.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import company.com.allder1.R;


public class GameBox extends AppCompatActivity {
    TextView textViewBox,txtTotalBox;
    private ImageButton btn1, btn2, btn3;
    public static final String EXTRA_SCOREBOX = "extraScoreBox";


    public static int number;
    Integer colors[]= {R.color.color1,R.color.color2,R.color.color3,R.color.color4,R.color.color5,R.color.color6,R.color.color7};
    Integer shape[]={R.drawable.buttonoval,R.drawable.buttonrec,R.drawable.buttontamgiac};
    Random randomshape;
    Random randomcl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_box);
        randomcl = new Random();
        randomshape = new Random();
        anhxa();
        Randoncolor();


        //T?o s? ng?u nhi?n
//
//                String chuoi1 = Textview1.getText().toString();
//                String chuoi2 = Textview2.getText().toString();
        //Ep ki?u d? li?u t? chu?i sang s?
        //Ki?m tra n? r?ng c?ch 1: d?ng chuoi1.length()==0 ho?c chuoi1.isEmpty()
//                if(chuoi1.isEmpty() || chuoi2.isEmpty())
//                {
//                    Toast.makeText(MainActivity.this,"Nh?p ?? s?",Toast.LENGTH_LONG).show();
//                }
//                else
//
//                    {
//                    int so1 = Integer.parseInt(chuoi1);// vidu "12" --> 12
//                    int so2 = Integer.parseInt(chuoi2);
//                    if( so1 >= so2 ){// b?t l?i n?u s? th?u nh?t l?n h?n s? th? 2
//
//                        Toast.makeText(MainActivity.this,"Nh?p l?i",Toast.LENGTH_LONG).show();
//                    }
//                    else {
//
//                }}
    }
    public void ClickButton(View V){

        int Min = -5;
        int Max = 4;
        Random rd = new Random(); //H?m Random trong JAVA ??t t?n bi?n v? kh?i t?o
        number = rd.nextInt(Max - Min + 1) + Min;
        switch (V.getId()){
            case R.id.buttonbox1:
                textViewBox.setText(String.valueOf(number));//settext ch? nh?n v?o chu?i n?n c? th? D?ng String.valueOf(number) ?? ??a s? --> chu?i


                DialogBox();
                break;
            case R.id.buttonbox2:

                textViewBox.setText(String.valueOf(number));

                DialogBox();


                break;
            case R.id.buttonbox3:

                textViewBox.setText(String.valueOf(number));
                DialogBox();
                break;
        }
    }
    public void anhxa(){
        txtTotalBox = findViewById(R.id.txtTotalBox);
        textViewBox =  findViewById(R.id.textViewPointBox);
        btn1 =  findViewById(R.id.buttonbox1);
        btn2 = findViewById(R.id.buttonbox2);
        btn3 = findViewById(R.id.buttonbox3);


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void Randoncolor(){
        btn3.setBackgroundColor(ContextCompat.getColor(getApplicationContext()
                ,colors[randomcl.nextInt(colors.length)]));
        btn2.setBackgroundColor(ContextCompat.getColor(getApplicationContext()
                ,colors[randomcl.nextInt(colors.length)]));
        btn1.setBackgroundColor(ContextCompat.getColor(getApplicationContext()
                ,colors[randomcl.nextInt(colors.length)]));
        btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(),shape[randomshape.nextInt(shape.length)]));
        btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(),shape[randomshape.nextInt(shape.length)]));
        btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(),shape[randomshape.nextInt(shape.length)]));
    }
    private  void DialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("!!!");
        builder.setMessage("Point:"+number);
        builder.setCancelable(false);
        builder.setNegativeButton("Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finishQuiz();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void finishQuiz() {
        Intent intentresultBox = new Intent();
        intentresultBox.putExtra(EXTRA_SCOREBOX, number);
        Log.d("TotalBox",""+number);
        setResult(RESULT_OK, intentresultBox);
        finish();
    }

}
