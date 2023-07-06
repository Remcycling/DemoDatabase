package sg.edu.rp.c346.id22045554.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText etTask;
    EditText etDate;

    ArrayList<Task> al;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);

        lv = findViewById(R.id.lv);

        al = new ArrayList<Task>();
        ArrayAdapter aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String chore = etTask.getText().toString();
                String date = etDate.getText().toString();

                // Insert a task
                db.insertTask(chore, date);

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();

                // Insert a task
                ArrayList<Task> localArrayList  = db.getTasks();
                db.close();

                String txt = "";

                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";

                }
                al.clear();
                for (int i = 0; i < localArrayList.size(); i++) {
                    Log.d("Database Content", i +". "+ localArrayList.get(i));
                    al.add(localArrayList.get(i));

                }

                tvResults.setText(txt);

                aa.notifyDataSetChanged();







            }
        });

    }



}