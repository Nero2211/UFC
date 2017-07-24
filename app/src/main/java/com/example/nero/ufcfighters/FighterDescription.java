package com.example.nero.ufcfighters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.solver.Cache;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FighterDescription extends AppCompatActivity {

    TextView nName, fName, lName, wCount, lCount, dCount, wClass, fRank, fProfileLink;
    String nickName, firstName, lastName, profilePicURL,
            weightClass, fighterRank, fighterProfileLink;
    int winCount, loseCount, drawCount;
    ImageView profilePic;
    private Context context = this;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighter_description);

        Bundle extras = getIntent().getExtras();
        toolbar = (Toolbar)findViewById(R.id.fighter_description_toolbar);
        nName = (TextView)findViewById(R.id.fighter_description_nName);
        fName = (TextView)findViewById(R.id.fighter_description_fName);
        lName = (TextView)findViewById(R.id.fighter_description_lName);
        wCount = (TextView)findViewById(R.id.fighter_description_winCount);
        lCount = (TextView)findViewById(R.id.fighter_description_loseCount);
        dCount = (TextView)findViewById(R.id.fighter_description_drawCount);
        wClass = (TextView)findViewById(R.id.fighter_description_wClass);
        fRank = (TextView)findViewById(R.id.fighter_description_rank);
        fProfileLink = (TextView)findViewById(R.id.fighter_description_profileLink);
        profilePic = (ImageView)findViewById(R.id.fighter_description_profilePic);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nickName = extras.getString("nickname");
        firstName = extras.getString("firstname");
        lastName = extras.getString("lastname");
        winCount = extras.getInt("winCount");
        loseCount = extras.getInt("loseCount");
        drawCount = extras.getInt("drawCount");
        profilePicURL = extras.getString("picture");
        weightClass = extras.getString("weightclass");
        fighterRank = extras.getString("rank");
        fighterProfileLink = extras.getString("link");

        if(nickName == null){
            nName.setText("No Nickname");
        }else{
            nName.setText(nickName);
        }

        fName.setText(firstName + " ");
        lName.setText(lastName);
        wCount.setText(String.valueOf(winCount + ":"));
        lCount.setText(String.valueOf(loseCount + ":"));
        dCount.setText(String.valueOf(drawCount));

        if(weightClass.contains("_")){
            String newWeightClass = weightClass.replaceAll("_", " ");
            wClass.setText(newWeightClass);
        }else {
            wClass.setText(weightClass);
        }

        if(fighterRank == null){
            fRank.setText("N/A");
        }else {
            fRank.setText(fighterRank);
        }
        Picasso.with(context).load(profilePicURL).into(profilePic);

        fProfileLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(FighterDescription.this);
                alert.setTitle("VIEW FULL PROFILE");
                alert.setMessage("Are you sure you want to view the full profile on the Internet?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fighterProfileLink));
                        startActivity(browserIntent);
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;

    }
}
