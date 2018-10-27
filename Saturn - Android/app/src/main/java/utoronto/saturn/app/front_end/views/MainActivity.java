package utoronto.saturn.app.front_end.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;

    private Fragment curFragment;
    private HomeFragment homeFragment;
    private DiscoverFragment discoverFragment;
    private FollowingFragment followingFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        homeFragment = HomeFragment.newInstance();
        discoverFragment = DiscoverFragment.newInstance();
        followingFragment = FollowingFragment.newInstance();
        mineFragment = MineFragment.newInstance();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, homeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        curFragment = homeFragment;
        setNavMenuListener();
    }

    private void replaceFragment(Fragment fragment) {
        if (curFragment != fragment) {
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.replace(R.id.container_fragment, fragment);
            trans.addToBackStack(null);
            trans.commit();
            curFragment = fragment;
        }
    }

    private void setNavMenuListener(){
        BottomNavigationView navMenu = findViewById(R.id.nav_menu);

        navMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_home:
                                replaceFragment(homeFragment);
                                break;
                            case R.id.menu_discover:
                                replaceFragment(discoverFragment);
                                break;
                            case R.id.menu_following:
                                replaceFragment(followingFragment);
                                break;
                            case R.id.menu_mine:
                                replaceFragment(mineFragment);
                                break;
                        }
                        return true;
                    }
                });
    }
}
