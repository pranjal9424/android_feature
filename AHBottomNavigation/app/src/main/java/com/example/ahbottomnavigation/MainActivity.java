package com.example.ahbottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AHBottomNavigation bottomNavigation;
    TextView headerText;
    Spinner mySpinner;
    ArrayAdapter<String> adapter;
    private int cosmicCatagory = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();
        this.createnavigationitems();
    }


    //initialize views

    private void initialize()
    {
        bottomNavigation=findViewById(R.id.bottom_navigation);
        headerText=findViewById(R.id.headerLabel);
        mySpinner=findViewById(R.id.mySpinner);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,getCosmicBodies().get(i),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private  ArrayList<String > getCosmicBodies(){
ArrayList<String> data=new ArrayList<String >();
data.clear();
        switch (cosmicCatagory){
            case  0:
                headerText.setText("place");
                data.add("Mirzapur");
                data.add("varanasi");
                data.add("prayagraj");
                break;
            case  1:
                headerText.setText("Bar");
                data.add("old mong");
                data.add("I B");
                data.add("B P");
                data.add("R S");
                break;
            case  2:
                headerText.setText("Menu");
                data.add("dal fry");
                data.add("chapati");
                data.add("paneer");
                data.add("snacks");
                break;

            case  3:
                headerText.setText("Order");
                data.add("dal fry");
                data.add("chapati");

                break;
            default:
                break;


        }
        return data;
    }

    private void dataBind(){
adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getCosmicBodies());
mySpinner.setAdapter(adapter);
    }
    //create Ahbottomnavigation
    private void createnavigationitems()
    {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab1, R.drawable.ic_map_lacal_place, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab2, R.drawable.ic_map_lacal_bar, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab3, R.drawable.ic_map_lacal_restaurant, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab4, R.drawable.ic_map_lacal_order, R.color.color_tab_4);
// Add AHNavigationItems
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
// Set default background color for AHBottomNavigation
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
// Change colors for AHBottomNavigation
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
// Manage titles for AHBottomNavigation
//bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);
// Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                cosmicCatagory=position;
                switch (position) {
                    case  0:
                       dataBind();
                        break;
                    case 1:
                        dataBind();
                        break;
                    case 2:
                        dataBind();
                        break;
                    case 3:
                        dataBind();
                    default:
                        break;
                }
                return  true;
            }
        });
    }
}