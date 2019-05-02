package fr.wildcodeschool.atelierfirebaselist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 : Liste d'objet Sport à afficher
        /*
        ArrayList<Sport> sports = new ArrayList<>();
        Sport foot = new Sport("Football", true, true);
        Sport tennis = new Sport("Tennis", false, true);
        Sport natation = new Sport("Natation", false, false);
        Sport escalade = new Sport("Escalade", false, true);
        sports.add(foot);
        sports.add(tennis);
        sports.add(natation);
        sports.add(escalade)
        */

        // 2 : ListView
        ListView lvSports = findViewById(R.id.lvSports);

        // 3 : Layout d'un élément de la liste (item)
        // 4 : Adapter
        final List<Sport> sports = new ArrayList<>();
        final SportAdapter adapter = new SportAdapter(MainActivity.this, sports);
        // 5 : Associer l'adapter à la ListView
        lvSports.setAdapter(adapter);

        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSportActivity.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Query myRef = database.getReference("loisir").orderByChild("outdoor").equalTo(false);
        DatabaseReference sportRef = database.getReference("loisir");
        sportRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sportSnapshot : dataSnapshot.getChildren()) {
                    Sport sport = sportSnapshot.getValue(Sport.class);
                    sports.add(sport);
                }
                // Notifier l'adapter que les données ont été modifiées
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
