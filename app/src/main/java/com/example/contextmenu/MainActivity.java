package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    String[] name={"Sanika","Sakshi","Nikita","Payal","Pooja","Shital","Snehal","Shruti","Shivani","Janavi"};
    String[] phone={"1221231434","4564685721","5765457452","4545517778","4787878778","7878787844","2112454545","4545787878","147878545","4545445212"};
    int nameIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,name);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameIndex=position;
                Log.d("Index is",String.valueOf(position));
            }
        });
        registerForContextMenu(listview);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        menu.setHeaderTitle("Select Action");
        menuInflater.inflate(R.menu.namelist,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(this, "Clicked on Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(this, "Clicked on Remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.view:
                Toast.makeText(this, "Clicked on View", Toast.LENGTH_SHORT).show();
                break;
            case R.id.call:
                Log.d("Phone",phone[nameIndex]);
                calltoperson(phone[nameIndex]);
                Toast.makeText(this, "Clicked on Call", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
    public void calltoperson(String number){
        Intent calling=new Intent(Intent.ACTION_CALL);
        calling.setData(Uri.parse("tel:"+number));
        startActivity(calling);
    }
}