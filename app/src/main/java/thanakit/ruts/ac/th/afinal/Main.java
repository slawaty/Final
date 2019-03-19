package thanakit.ruts.ac.th.afinal;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Main extends AppCompatActivity {
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference LED1, REfer;

    public Button Switch1;
    public Integer Valuel, Valuel01;
    public String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseDatabase = FirebaseDatabase.getInstance();
        LED1 = firebaseDatabase.getReference("LED1");


        REfer = firebaseDatabase.getReference();


        REfer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                date = String.valueOf(map.get("LED1"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Switch1 = (Button) findViewById(R.id.btn1);

        LED1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Valuel = dataSnapshot.getValue(Integer.class);
                if (Valuel==1){
                    Switch1.setText("LED1_NO");
                    Valuel01=0;
                }else {
                    Switch1.setText("LED1_OFF");
                    Valuel01 = 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LED1.setValue(Valuel01);
            }
        });

    }

}