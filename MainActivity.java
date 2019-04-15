package com.stepwisedesigns.workingwithdatasnapshots;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    EditText mEditTextview;
    Button stringCheckButton;

    DatabaseReference databaseRef;
    private ValueEventListener  valueEventListener;
    private static final String TAG = "Working With Snapshots";
    private String catName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextview = findViewById(R.id.edit_view_cat_name);
        stringCheckButton = findViewById(R.id.check_string_button);

        stringCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catName = mEditTextview.getText().toString();
                if(!catName.isEmpty()){
                    checkForDupes(catName);
                }
                else {
                    Toast.makeText(MainActivity.this,"no text provided",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private boolean isTrue;
    private void checkForDupes(final String catValue){

        //reset isTrue base value to false
        isTrue = false;

        //get instance of FirebaseDatabase database object reference
        databaseRef = FirebaseDatabase.getInstance().getReference().child("Category");
        valueEventListener = databaseRef.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Category mCategory = postSnapshot.getValue(Category.class);

                    Log.d(TAG, "onDataChange: " + mEditTextview.getText() + "==" + mCategory.getName());

                    if(catValue.equals(mCategory.getName())){
                        isTrue = true;
                        Log.d(TAG, "withinIf onDataChange: " + mEditTextview.getText() + "==" + mCategory.getName());
                        Log.d(TAG, "catValue.equals(mCategory.getName(): " + catValue.equals(mCategory.getName()));

                        Toast.makeText(MainActivity.this,"Match Result is " + isTrue,Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));
    }
}