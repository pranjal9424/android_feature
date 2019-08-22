package com.example.ahbottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    AHBottomNavigation bottomNavigation;
    TextView headerText;


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
                switch (position) {
                    case  0:
                        headerText.setText("Place");
                        break;
                    case 1:
                        headerText.setText("Bar");
                        break;
                    case 2:
                        headerText.setText("Galaxies");
                        break;
                    case 3:
                        headerText.setText("Order");
                    default:
                        break;
                }
                return  true;
            }
        });
    }
}