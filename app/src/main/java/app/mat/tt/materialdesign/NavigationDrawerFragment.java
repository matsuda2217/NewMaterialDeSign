package app.mat.tt.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.mat.tt.materialdesign.adapter.MyRecyclerviewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PRE_FILE_NAME = "testPref";
    public static final String KEY_USER_LEARN_DRAWER = "user_learn_drawer";

    private RecyclerView recyclerView;
    ActionBarDrawerToggle mdDrawerToggle;
    private DrawerLayout mDrawerLayout;
    MyRecyclerviewAdapter myRecyclerviewAdapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSaveInstanceState;
    View container;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mUserLearnedDrawer = Boolean.valueOf(readToPreferences(getActivity(),KEY_USER_LEARN_DRAWER,"false"));
        if (savedInstanceState!=null){
            mFromSaveInstanceState = true;
        }

    }
    public List<Infomation> getData(){
        List<Infomation > data = new ArrayList<>();
        int[] icon = {R.drawable.ic_action_next, R.drawable.ic_search_action, R.drawable.ic_settings};
        String[] titles = {"Next", "Search" , "Settings"};

        for (int i = 0; i < titles.length; i++) {
            Infomation cur  = new Infomation();
            cur.id = icon[i];
            cur.title = titles[i];
            data.add(cur);

        }

        return data;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        myRecyclerviewAdapter =new MyRecyclerviewAdapter(getActivity(),getData());
        recyclerView.setAdapter(myRecyclerviewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return v;
    }

    public void setUp(int fragmentID, DrawerLayout drawerLayout, Toolbar toolbar) {
        container = getActivity().findViewById(fragmentID);
        mDrawerLayout = drawerLayout;
        mdDrawerToggle  = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer){

                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(),KEY_USER_LEARN_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }
        };
        if (!mUserLearnedDrawer && !mFromSaveInstanceState){
                mDrawerLayout.openDrawer(container);
        }
        mDrawerLayout.addDrawerListener(mdDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mdDrawerToggle.syncState();
            }
        });
    }
    public static void saveToPreferences(Context context, String preferencesName, String preferencesValues){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(PRE_FILE_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(preferencesName,preferencesValues).apply();
    }
    public static String readToPreferences(Context context, String preferencesName, String defaultValues){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(PRE_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferencesName,defaultValues);
    }
}
