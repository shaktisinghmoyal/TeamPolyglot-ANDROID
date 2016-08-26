package com.talentica.presentation.leadCapturePage.base.view.activity;

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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.talentica.R;
import com.talentica.databinding.MainActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.DaggerLeadCaptureComponent;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.addmybook.view.activity.AddMyBookActivity;
import com.talentica.presentation.leadCapturePage.base.presenter.LeadCapturePagePresenter;
import com.talentica.presentation.leadCapturePage.base.view.LeadCapturePageView;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BooksGridViewFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.HomeFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.SearchFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.fragment.MyAccountFragment;
import com.talentica.presentation.leadCapturePage.notifications.view.fragment.NotificationFragment;
import com.talentica.presentation.leadCapturePage.tasks.view.fragment.MyTaskFragment;
import com.talentica.presentation.utils.ClickListenerInterface;
import com.talentica.presentation.utils.Enums;
import com.talentica.presentation.utils.GridViewItemClickListnerInterface;
import com.talentica.presentation.utils.Util;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements LeadCapturePageView, HasComponent<LeadCaptureComponent>, ClickListenerInterface, GridViewItemClickListnerInterface {


    public static Activity activity;
    private final String Tag = "MainActivity";
    @Inject
    public LeadCapturePagePresenter leadCapturePagePresenter;
    SearchView.SearchAutoComplete searchAutoComplete;
    private LeadCaptureComponent leadCaptureComponent;
    private String[] drawerStrings;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private Toolbar toolbar;
    private ImageView previousBottomItemPressed;
    private MainActivityBinding mainActivityBinding;
    private int searchBarTextLength = 0;
    private String searchBarText = "";
    private String searchBarTextToSave = "";
    private MenuItem menuItem;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.main_activity);
        initializeInjector();
        activity = leadCaptureComponent.activity();
        leadCaptureComponent.inject(this);
        initializeVariables();
        initializeActivityComponents();
        leadCapturePagePresenter.setView(this);
        leadCapturePagePresenter.initialize(savedInstanceState);

    }

    @Override
    public void setFirstFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            navigator.addFragment(this, R.id.main_fragment_container, new HomeFragment(), Util.ENTRY);
        }
    }




    private void initializeVariables() {
        mDrawerLayout = mainActivityBinding.drawerLayout;
        mDrawerList = mainActivityBinding.leftDrawer;
        toolbar = mainActivityBinding.toolbar;
    }

    private void initializeInjector() {
        this.leadCaptureComponent = DaggerLeadCaptureComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    private void initializeActivityComponents() {
        initializeBottomMenuItemIds();
        setNavigationDrawer();
    }

    public void initializeBottomMenuItemIds() {
        previousBottomItemPressed = mainActivityBinding.homeSelected;

    }

    @Override
    public void showBottomMenu() {
        mainActivityBinding.bottomBar.setVisibility(LinearLayout.VISIBLE);
    }

    @Override
    public void hideBottomMenu() {
        mainActivityBinding.bottomBar.setVisibility(LinearLayout.GONE);
    }


    public void setNavigationDrawer() {
        setSupportActionBar(mainActivityBinding.toolbar);
        drawerStrings = getResources().getStringArray(R.array.genre_types);

        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_listview_item, R.id.rowListTextView, drawerStrings));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                searchBarTextToSave = searchBarText = mDrawerList.getItemAtPosition(position).toString();
                leadCapturePagePresenter.onDrawerItemClicked();

            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerClosed(View view) {
                Log.e(Tag, "onDrawerClosed");

                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.e(Tag, "onDrawerOpened");


                super.onDrawerOpened(drawerView);

            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.setDrawerIndicatorEnabled(false); //disable "hamburger to arrow" drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Tag, "navigation onClick");
                mainActivityBinding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    @Override
    public void displaySearchResultForDrawerItem() {

        menuItem.expandActionView();
        Log.e(Tag, "displaySearchResultForDrawerItem " + searchBarTextToSave);
        searchAutoComplete.setText(searchBarTextToSave);
        searchBarTextLength = searchBarTextToSave.length();

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
    public LeadCaptureComponent getComponent() {
        return leadCaptureComponent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        Log.e(Tag, "onOptionsItemSelected " + item);
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            Log.e(Tag, "onOptionsItemSelected true");
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.e(Tag, "" + "onPrepareOptionsMenu");

        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_category);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // toolbar.inflateMenu(R.menu.main_activity_option_menu);
        Log.e(Tag, "" + "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_option_menu, menu);
        menuItem = menu.findItem(R.id.search);
        this.menu = menu;

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.e(Tag, "" + "onMenuItemActionExpand  ");

                Log.e(Tag, "" + "menu item visible  ");
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                navigator.addFragment(MainActivity.this, R.id.main_fragment_container, new SearchFragment(), Util.HOME);

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                Log.e(Tag, "" + "onMenuItemActionCollapse   ");
                if (menuItem.isVisible()) {
                    getSupportFragmentManager().popBackStack();
                }
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
                searchView.setSearchableInfo(
                        searchManager.getSearchableInfo(getComponentName()));

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.e(Tag, "" + "onQueryTextSubmit");
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        if (menuItem.isVisible()) {
                            int queryLength = query.length();
                            searchBarText = query;
                            Log.e(Tag, "" + "onQueryTextChange called");

                            if (queryLength == 0) {
                                Log.e(Tag, "" + "queryLength==0");


                                if (searchBarTextLength != 0) {

                                    Log.e(Tag, "" + "searchBarTextLength!=0");
                                    getSupportFragmentManager().popBackStack();
                                    searchBarTextLength = queryLength;

                                }


                            } else {
                                if (searchBarTextLength == 0) {
                                    Log.e(Tag, "" + "searchBarTextLength==0");

                                    Bundle fragmentTitleBundle = new Bundle();
                                    fragmentTitleBundle.putInt(Util.fragmentTitle, R.string.results_string);
                                    BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
                                    booksGridViewFragment.setArguments(fragmentTitleBundle);
                                    navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, Util.HOME);

                                } else {
                                    getSupportFragmentManager().popBackStack();
                                    Log.e(Tag, "" + "else searchBarTextLength!=0");
                                    Bundle fragmentTitleBundle = new Bundle();
                                    fragmentTitleBundle.putInt(Util.fragmentTitle, R.string.results_string);
                                    BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
                                    booksGridViewFragment.setArguments(fragmentTitleBundle);
                                    navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, Util.HOME);

                                }

                            }
                            searchBarTextLength = queryLength;
                        }
                        return true;

                    }

                });
                searchView.setIconifiedByDefault(true);
                searchView.setOnSearchClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(Tag, "" + "searchView onClick ");
                    }


                });
                searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchAutoComplete.setHintTextColor(Color.parseColor("#999999"));
                //   searchAutoComplete.setHint("rgfregfreg");
                searchAutoComplete.setTextColor(Color.BLACK);
                searchAutoComplete.setTextSize(12);

//                searchAutoComplete.setAdapter(searchSuggAdapter);

                ImageView searchIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
                searchIcon.setImageResource(R.drawable.icon_search);

                ImageView searchCloseIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
                searchCloseIcon.setImageResource(R.drawable.searchbar_close);// search close image ka background


                View editFrame = (View) searchView.findViewById(android.support.v7.appcompat.R.id.search_edit_frame);
                editFrame.setBackgroundColor(Color.WHITE);// kam ki cheeze

            }

        }

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.e(Tag, "onKeyDown" + " " + event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            onBackPressed();

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Log.e(Tag, "" + "onBackPressed");


        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            Log.e(Tag, "" + "onBackPressed " + count);
            if (count == 0) {
                Log.e(Tag, "" + "super onBackPressed ");
                super.onBackPressed();
                //additional code
            } else {
                getSupportFragmentManager().popBackStackImmediate();

            }

        }


    }


    public void bottomBarItemClick(View bottomBarItem) {
        switch (bottomBarItem.getId()) {
            case R.id.home_item:
//                previousBottomItemPressed.setVisibility(View.INVISIBLE);
//                mainActivityBinding.homeSelected.setVisibility(View.VISIBLE);
//                previousBottomItemPressed = mainActivityBinding.homeSelected;
                navigator.openAsMainRoot(MainActivity.this);
                break;

            case R.id.todo_item:
//                previousBottomItemPressed.setVisibility(View.INVISIBLE);
//                mainActivityBinding.todoSelected.setVisibility(View.VISIBLE);
//                previousBottomItemPressed = mainActivityBinding.todoSelected;
                navigator.openAsRoot(MainActivity.this, R.id.main_fragment_container, new MyTaskFragment(), Util.TASKS);
                break;

            case R.id.add_book_item:
                previousBottomItemPressed.setVisibility(View.INVISIBLE);
                mainActivityBinding.addBookSelected.setVisibility(View.VISIBLE);
                previousBottomItemPressed = mainActivityBinding.addBookSelected;
                navigator.startAnotherActivity(this, new Intent(this, AddMyBookActivity.class));
                break;

            case R.id.notification_item:
//                previousBottomItemPressed.setVisibility(View.INVISIBLE);
//                mainActivityBinding.notificationSelected.setVisibility(View.VISIBLE);
//                previousBottomItemPressed = mainActivityBinding.notificationSelected;
                navigator.openAsRoot(MainActivity.this, R.id.main_fragment_container, new NotificationFragment(), Util.NOTIFICATION);
                break;

            case R.id.profile_item:
//                previousBottomItemPressed.setVisibility(View.INVISIBLE);
//                mainActivityBinding.profileSelected.setVisibility(View.VISIBLE);
//                previousBottomItemPressed = mainActivityBinding.profileSelected;
                navigator.openAsRoot(MainActivity.this, R.id.main_fragment_container, new MyAccountFragment(), Util.MY_ACCOUNT);
                break;

        }

    }

    public void setBottomBarIconForHome() {
        previousBottomItemPressed.setVisibility(View.INVISIBLE);
        mainActivityBinding.homeSelected.setVisibility(View.VISIBLE);
        previousBottomItemPressed = mainActivityBinding.homeSelected;
    }

    @Override
    public void onRecyclerViewBookClicked(BookModel bookModel) {
        onBookClicked(bookModel);
    }

    @Override
    public void onGridViewBookClicked(BookModel bookModel) {
        onBookClicked(bookModel);
    }

    @Override
    public void onSuggestionItemClicked(String suggestionString) {
        Log.e(Tag, "onSuggestionItemClicked " + "" + suggestionString);
        Bundle fragmentTitleBundle = new Bundle();
        fragmentTitleBundle.putInt(Util.fragmentTitle, R.string.results_string);
        BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
        booksGridViewFragment.setArguments(fragmentTitleBundle);

        searchAutoComplete.setText(suggestionString);


    }

    private void onBookClicked(BookModel bookModel) {

        searchBarTextToSave = searchBarText;

        Bundle bookDetailBundle = new Bundle();
        bookDetailBundle.putParcelable(Util.bookDetailBundle, bookModel);
        navigator.startBookDetailActivity(this, bookDetailBundle);

        if (searchBarTextToSave.length() != 0) {
            leadCapturePagePresenter.saveForRecentSearches(searchBarTextToSave);
        }

    }


    private void setSearchSuggestionActionBar() {


    }

    @Override
    public void setActionBar(String title) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);

        if (menuItem != null) {
            menuItem.setVisible(true);
        }
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_category);
        mainActivityBinding.toolbar.setPadding(0, 0, 10, 0);
    }

    private void setTaskActionBar(String text) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(text);
        menuItem.setVisible(false);
        menuItem.collapseActionView();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        previousBottomItemPressed.setVisibility(View.INVISIBLE);
        mainActivityBinding.todoSelected.setVisibility(View.VISIBLE);
        previousBottomItemPressed = mainActivityBinding.todoSelected;
    }

    private void setNotificationActionBar(String text) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(text);
        menuItem.setVisible(false);
        menuItem.collapseActionView();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        previousBottomItemPressed.setVisibility(View.INVISIBLE);
        mainActivityBinding.notificationSelected.setVisibility(View.VISIBLE);
        previousBottomItemPressed = mainActivityBinding.notificationSelected;
    }

    private void setMyAccountActionBar(String text) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(text);
        menuItem.setVisible(false);
        menuItem.collapseActionView();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        previousBottomItemPressed.setVisibility(View.INVISIBLE);
        mainActivityBinding.profileSelected.setVisibility(View.VISIBLE);
        previousBottomItemPressed = mainActivityBinding.profileSelected;
    }

    private void setGridViewActionBar(String text) {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        if(menuItem!=null)
//        {
//            menuItem.setVisible(true);
//        }
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void setActionViewBar(Enums.actionBarTypeEnum actionBarType, String text) {
        Log.e(Tag, "setActionViewBar" + " actionBarType");
        switch (actionBarType) {


            case HOME:
                Log.e(Tag, "HOME");
                setActionBar(text);

                break;

            case SEARCH_SUGGESTION:
                Log.e(Tag, "SEARCH_SUGGESTION  But not required");
                setSearchSuggestionActionBar();

                break;

            case TASK:
                Log.e(Tag, "TASK");
                setTaskActionBar(text);

                break;

            case NOTIFICATION:
                Log.e(Tag, "NOTIFICATION");
                setNotificationActionBar(text);

                break;

            case GRID_VIEW:
                Log.e(Tag, "GRID_VIEW");
                setGridViewActionBar(text);

                break;

            case MY_ACCOUNT:
                Log.e(Tag, "MY_ACCOUNT");
                setMyAccountActionBar(text);

                break;

        }


    }

    @Override
    public void onResume() {
        super.onResume();
        leadCapturePagePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        leadCapturePagePresenter.pause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        leadCapturePagePresenter.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == Util.REQUEST_CODE_ADD_BOOK_ACTIVITY) {
            Log.e(Tag, "onActivityResult");
            if (menuItem.isActionViewExpanded()) {
                menuItem.collapseActionView();
            }
            navigator.popEveryFragment(MainActivity.this);
            setBottomBarIconForHome();
        }
    }

}