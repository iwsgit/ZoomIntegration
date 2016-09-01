package us.zoom.sdkexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * Created by iwsuser6 on 7/20/2016.
 */
public class Navigation_Fragment extends Fragment implements Navigation_Adapter.ClickListner {

    public static final String sharedprefrence = "Prefrence";
    public static final String Key_user_learned_Drawer = "user_learned_drawer";
    private boolean draweropend;
    private boolean savedinstance = false;
    RecyclerView recyclview;
    View view;
    Navigation_Adapter recycleAdapter;
    private String[] data = {"Chat"};
    ActionBarDrawerToggle actionBarDrawerToggle;
    private float lastTranslate = 0.0f;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        draweropend = readFromPrefrence(getActivity(), Key_user_learned_Drawer, "");
        if (savedInstanceState != null) {
            savedinstance = true;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        recyclview = (RecyclerView) view.findViewById(R.id.recyclview);
        recycleAdapter = new Navigation_Adapter(getActivity(), data);
        recycleAdapter.setClickListner(this);
        // recyclview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        // recyclview.setHasFixedSize(true);
        recyclview.setAdapter(recycleAdapter);
        recyclview.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclview.setLayoutManager(mLayoutManager);

        return view;
    }


    public static boolean readFromPrefrence(Context context, String PrefrenceName, String DefaultName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedprefrence, Context.MODE_PRIVATE);
        return sharedPreferences.contains(PrefrenceName);

    }

    @Override
    public void itemClicked(View view, int position) {

        if (position == 0)
            startActivity(new Intent(getActivity(), MainActivity.class));
        else
            getActivity().finish();

    }

    @Override
    public void itemLongClick(View view, int position) {

    }


    public void setup(final Toolbar appbar, DrawerLayout navigation_drawer, int fragment, final int container) {

        View fragment_veiw = getActivity().findViewById(fragment);
        final View container1 = getActivity().findViewById(container);


        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), navigation_drawer, appbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);


                float moveFactor = (recyclview.getWidth() * slideOffset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    container1.setTranslationX(moveFactor);
                    appbar.setTranslationX(moveFactor);
                } else {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    container1.startAnimation(anim);
                    appbar.startAnimation(anim);
                    lastTranslate = moveFactor;


                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);


                getActivity().invalidateOptionsMenu();

            }
        };
        navigation_drawer.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });

        if (!draweropend && !savedinstance) {
            draweropend = true;

            navigation_drawer.openDrawer(fragment_veiw);
            saveToPrefrence(getActivity(), Key_user_learned_Drawer, draweropend + "");
        }
        navigation_drawer.setDrawerListener(actionBarDrawerToggle);

    }

    public static void saveToPrefrence(Context context, String PrefrenceName, String PrefrenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedprefrence, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PrefrenceName, PrefrenceValue);
        editor.apply();
    }
}
