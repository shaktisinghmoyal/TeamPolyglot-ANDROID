package com.talentica.presentation.leadCapturePage.base.view;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.talentica.R;
import com.talentica.databinding.MainActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.BookComponent;
import com.talentica.presentation.internal.di.components.DaggerBookComponent;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.view.SearchFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.HomeFragment;

public class MainActivity extends BaseActivity implements LeadCapturePageView, HasComponent<BookComponent>, HomeFragment.BookListListener {

    public static Activity activity;
    private BookComponent bookComponent;
    private String[] drawerStrings;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private Toolbar toolbar;
    private ImageView previousBottomItemPressed;
    private MainActivityBinding mainActivityBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.main_activity);
        initializeInjector();
        activity = bookComponent.activity();
        if (savedInstanceState == null) {
            navigator.addFragment(this, R.id.main_fragment_container, new HomeFragment(), "home_fragment");
        }
        intializevariables();
        initializeActivityComponents();

    }

    private void intializevariables() {
        mDrawerLayout = mainActivityBinding.drawerLayout;
        mDrawerList = mainActivityBinding.leftDrawer;
        toolbar = mainActivityBinding.toolbar;
    }
    private void initializeInjector() {
        this.bookComponent = DaggerBookComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    private void initializeActivityComponents() {
        initializeBottomMenuItemIds();
        setActionBar();
        setNavigationDrawer();
    }

    public void initializeBottomMenuItemIds() {
        previousBottomItemPressed = mainActivityBinding.homeSelected;

    }

    public void setActionBar() {
        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_category);
        setSupportActionBar(mainActivityBinding.toolbar);
        mainActivityBinding.toolbar.setPadding(0, 0, 10, 0);
        getSupportActionBar().setTitle("Home");

    }

    public void setNavigationDrawer() {
        drawerStrings = getResources().getStringArray(R.array.nav_drawer_items);

        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_listview_item, R.id.rowListTextView, drawerStrings));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {

            /** Called when a drawer has settled in a completely closed state. */
            @Override
            public void onDrawerClosed(View view) {
                Log.e("onDrawerClosed", "onDrawerClosed");

                super.onDrawerClosed(view);
                // getActionBar().setTitle(mTitle);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                Log.e("onDrawerOpened", "onDrawerOpened");


                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Shakti");

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()


            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.setDrawerIndicatorEnabled(false); //disable "hamburger to arrow" drawable
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.icon_category);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("setNavigation", "" + v);
                mainActivityBinding.drawerLayout.openDrawer(Gravity.LEFT);

            }
        });

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void displayNotificationCount() {

    }


    @Override
    public void onBookClicked(BookModel book) {

    }

    @Override
    public BookComponent getComponent() {
        return bookComponent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        Log.e("onOptionsItemSelected", "" + item);
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // toolbar.inflateMenu(R.menu.main_activity_option_menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_option_menu, menu);
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                //DO SOMETHING WHEN THE SEARCHVIEW IS CLOSING
                onBackPressed();

                return true;
            }
        });
// Associate searchable configuration with the SearchView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            {
                SearchManager searchManager =
                        (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView =
                        (SearchView) menu.findItem(R.id.search).getActionView();
//                searchView.setBackgroundColor(Color.WHITE);
                searchView.setSearchableInfo(
                        searchManager.getSearchableInfo(getComponentName()));
                searchView.setIconifiedByDefault(false);

                searchView.setOnSearchClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("searchView", "" + "searchView");
                        navigator.addFragment(MainActivity.this, R.id.main_fragment_container, new SearchFragment(), "search_fragment");
                    }
                });
                SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchAutoComplete.setHintTextColor(Color.parseColor("#999999"));
                //   searchAutoComplete.setHint("rgfregfreg");
                searchAutoComplete.setTextColor(Color.BLACK);
                searchAutoComplete.setTextSize(12);

                ImageView searchIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
                searchIcon.setImageResource(R.drawable.icon_search);

                ImageView searchCloseIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
                searchCloseIcon.setImageResource(R.drawable.searchbar_close);// search close image ka background


                View editFrame = (View) searchView.findViewById(android.support.v7.appcompat.R.id.search_edit_frame);
                editFrame.setBackgroundColor(Color.WHITE);// kam ki cheeze


//                View searchplate = (View)searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
//                searchplate.setBackgroundColor(Color.WHITE);//poori patti white karta

//                View searchbar = (View)searchView.findViewById(android.support.v7.appcompat.R.id.search_bar);
//                searchbar.setBackgroundColor(Color.WHITE);//poori ki poori patti white karta

//               View searchutton = (View)searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
//               searchutton.setBackgroundColor(Color.BLACK);//  kuch na hora


//                View goButton= (View) searchView.findViewById(android.support.v7.appcompat.R.id.search_go_btn);
//                goButton.setBackgroundColor(Color.WHITE);// koi kam k ana h

//                View icon = (View)searchView.findViewById(android.support.v7.appcompat.R.id.search_badge);
//                icon.setBackgroundColor(Color.WHITE);// koi kam k ana h


            }

        }
        return true;

    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use The query to search your data somehow
        }
    }


    @Override
    public void onBackPressed() {
        Log.e("onBackPressed", "" + "onBackPressed");

        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }

    }

    public void bottomBarItemClick(View bottomBarItem) {
        switch (bottomBarItem.getId()) {
            case R.id.home_item:
                previousBottomItemPressed.setVisibility(View.INVISIBLE);
                mainActivityBinding.homeSelected.setVisibility(View.VISIBLE);
                previousBottomItemPressed = mainActivityBinding.homeSelected;
                break;

            case R.id.todo_item:
                previousBottomItemPressed.setVisibility(View.INVISIBLE);
                mainActivityBinding.todoSelected.setVisibility(View.VISIBLE);
                previousBottomItemPressed = mainActivityBinding.todoSelected;
                break;

            case R.id.add_book_item:
                previousBottomItemPressed.setVisibility(View.INVISIBLE);
                mainActivityBinding.addBookSelected.setVisibility(View.VISIBLE);
                previousBottomItemPressed = mainActivityBinding.addBookSelected;
                break;

            case R.id.notification_item:
                previousBottomItemPressed.setVisibility(View.INVISIBLE);
                mainActivityBinding.notificationSelected.setVisibility(View.VISIBLE);
                previousBottomItemPressed = mainActivityBinding.notificationSelected;
                break;

            case R.id.profile_item:
                previousBottomItemPressed.setVisibility(View.INVISIBLE);
                mainActivityBinding.profileSelected.setVisibility(View.VISIBLE);
                previousBottomItemPressed = mainActivityBinding.profileSelected;
                break;

        }

    }
}