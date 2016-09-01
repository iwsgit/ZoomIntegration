package us.zoom.sdkexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by iwsuser6 on 7/20/2016.
 */
public class Home extends AppCompatActivity {

    @InjectView(R.id.appbar)
    Toolbar appbar;
    @InjectView(R.id.navigation_drawer)
    DrawerLayout navigation_drawer;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.home_screen);
        ButterKnife.inject(this);


        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Navigation_Fragment navigation_fragment = (Navigation_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        navigation_fragment.setup(appbar, navigation_drawer, R.id.fragment, R.id.container);


        Fragment fragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();


    }
}
