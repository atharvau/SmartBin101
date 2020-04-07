package com.example.smartbin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseReference database=FirebaseDatabase.getInstance().getReference();

        final WaveLoadingView waveLoadingView=findViewById(R.id.waveLoadingView);



        database.child("Bin1").child("fil").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            try {
                TextView textView=findViewById(R.id.textView9);

                textView.setText(dataSnapshot.getValue().toString()+"%");
                waveLoadingView.setCenterTitle(dataSnapshot.getValue().toString()+"%");
                waveLoadingView.setAmplitudeRatio(Integer.parseInt(dataSnapshot.getValue().toString()));

                int cap= Integer.parseInt(dataSnapshot.getValue().toString());


                ImageView imageView=findViewById(R.id.imageView2);


                if(cap>79){

                    TextView textView1=findViewById(R.id.textView6);
                    textView1.setText("RECYCLE");
                }
                else {
                    TextView textView1=findViewById(R.id.textView6);
                    textView1.setText("PARTIAL");
                }



            }catch (Exception e){}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database.child("Bin1").child("cap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             try{
                 TextView textView=findViewById(R.id.editText);
                 textView.setText(dataSnapshot.getValue().toString());

             }catch (Exception e){}



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        database.child("Bin1").child("lastupdate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try{

                    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date(Long.parseLong(dataSnapshot.getValue().toString())));


                    TextView textView=findViewById(R.id.textView2);
                    textView.setText(timeStamp);


                }catch (Exception e){}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
