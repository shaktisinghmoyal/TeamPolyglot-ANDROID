package com.talentica.presentation.leadCapturePage.base.view;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
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
import android.widget.ListView;

import com.talentica.R;
import com.talentica.databinding.MainActivityBinding;
import com.talentica.presentation.internal.di.HasComponent;
import com.talentica.presentation.internal.di.components.DaggerLeadCaptureComponent;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.presenter.LeadCapturePagePresenter;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BookDetailFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.BooksGridViewFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.HomeFragment;
import com.talentica.presentation.leadCapturePage.home.view.fragment.SearchFragment;
import com.talentica.presentation.utils.ClickListenerInterface;
import com.talentica.presentation.utils.Enums;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements LeadCapturePageView, HasComponent<LeadCaptureComponent>, ClickListenerInterface {

    public static final String fragmentTitle = "Fragment_Title";
    public static final String bookDetail = "book_detail";
    public static Activity activity;
    private final String Tag = "MainActivity";
    SearchView.SearchAutoComplete searchAutoComplete;
    @Inject
    LeadCapturePagePresenter leadCapturePagePresenter;
    private LeadCaptureComponent leadCaptureComponent;
    private String[] drawerStrings;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private Toolbar toolbar;
    private ImageView previousBottomItemPressed;
    private MainActivityBinding mainActivityBinding;
    private boolean isSearchBarExpanded;
    private boolean bookDetailActionBar;
    private boolean navigationBackFromBookDetail;
    private int searchBarTextLength = 0;
    private String searchBarText = "";
    private String searchBarTextToSave = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(
                this, R.layout.main_activity);
        initializeInjector();
        activity = leadCaptureComponent.activity();
        leadCaptureComponent.inject(this);
        if (savedInstanceState == null) {
            navigator.addFragment(this, R.id.main_fragment_container, new HomeFragment(), "first");
        }
        initializeVariables();
        initializeActivityComponents();
        leadCapturePagePresenter.setView(this);
        leadCapturePagePresenter.initialize();

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

    public void setActionViewSearchBar() {
//        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_category);
        // mainActivityBinding.toolbar.setNavigationIcon(null);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_category);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mainActivityBinding.toolbar.setPadding(0, 0, 10, 0);
        getSupportActionBar().setTitle("Home");

    }

    public void setNavigationDrawer() {
        setSupportActionBar(mainActivityBinding.toolbar);
        drawerStrings = getResources().getStringArray(R.array.nav_drawer_items);

        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_listview_item, R.id.rowListTextView, drawerStrings));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                if (getSupportFragmentManager().getBackStackEntryCount() == 2) {
                    getSupportFragmentManager().popBackStack();
                    Log.e(Tag, "" + "onItemClick getBackStackEntryCount == 2 ");
            }
                searchBarTextToSave = searchBarText = mDrawerList.getItemAtPosition(position).toString();
                searchBarTextLength = searchBarTextToSave.length();
                invalidateOptionsMenu();

//                Bundle fragmentTitleBundle = new Bundle();
//                fragmentTitleBundle.putInt(MainActivity.fragmentTitle, R.string.results_string);
//                BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
//                booksGridViewFragment.setArguments(fragmentTitleBundle);
//                navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, "home");


            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {

            /** Called when a drawer has settled in a completely closed state. */
            @Override
            public void onDrawerClosed(View view) {
                Log.e(Tag, "onDrawerClosed");

                super.onDrawerClosed(view);
                // getActionBar().setTitle(mTitle);

                //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                Log.e(Tag, "onDrawerOpened");


                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Shakti");

                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()


            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.setDrawerIndicatorEnabled(false); //disable "hamburger to arrow" drawable
        //  mDrawerToggle.setHomeAsUpIndicator(R.drawable.icon_category);
        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_category);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Tag, "navigation onClick");
                if (bookDetailActionBar == true) {
                    navigationBackFromBookDetail = true;
                    onBackPressed();
                } else {
                    mainActivityBinding.drawerLayout.openDrawer(Gravity.LEFT);
                }


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
//
//        if(bookDetailActionBar){
//
//           // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//          //  getSupportActionBar().setHomeButtonEnabled(true);
//            mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_back);
//            menu.removeItem(R.id.search);
//            getSupportActionBar().setTitle(getResources().getString(R.string.book_detail_title));
//
//        }
//      else {

        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_category);
        getSupportActionBar().setTitle(getResources().getString(R.string.home_title));

        if (searchBarTextToSave.length() != 0) {
            Log.e(Tag, "" + "inside searchBarText length ");
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.search).getActionView();
            MenuItem searchMenuItem = menu.findItem(R.id.search); // get my MenuItem with placeholder submenu

            searchMenuItem.expandActionView();
            searchView.setQuery(searchBarTextToSave, false);
//                searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//                searchAutoComplete.setText(searchBarTextToSave);


        } else {
            Log.e(Tag, "" + " searchBarText length =0 ");
            bookDetailActionBar = false;
        }


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // toolbar.inflateMenu(R.menu.main_activity_option_menu);
        Log.e(Tag, "" + "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_option_menu, menu);

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                isSearchBarExpanded = true;

                Log.e(Tag, "" + "onMenuItemActionExpand bookDetailActionBar " + bookDetailActionBar);
                if (!bookDetailActionBar) {

                    navigator.addFragment(MainActivity.this, R.id.main_fragment_container, new SearchFragment(), "home");

                }


                if (navigationBackFromBookDetail) {
                    Log.e(Tag, "" + "navigationBackFromBookDetail == true   ");
                    bookDetailActionBar = false;
                    navigationBackFromBookDetail = false;
                }

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                Log.e(Tag, "" + "onMenuItemActionCollapse   ");

                if (bookDetailActionBar) {
                    Log.e(Tag, "" + "bookDetailActionBar == true   ");
                    //getSupportFragmentManager().popBackStack();
                    isSearchBarExpanded = true;
                    bookDetailActionBar = false;
                    return false;
                } else {

                    getSupportFragmentManager().popBackStack();

                    Log.e(Tag, "" + "bookDetailActionBar == false   ");
                    isSearchBarExpanded = false;
                    return true;
                }


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


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.e(Tag, "" + "onQueryTextSubmit");
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        int queryLength = query.length();
                        searchBarText = query;
                        Log.e(Tag, "" + "onQueryTextChange called");

                        if (queryLength == 0) {
                            Log.e(Tag, "" + "queryLength==0");


                            if (searchBarTextLength != 0) {

                                Log.e(Tag, "" + "searchBarTextLength!=0");
                                if (!bookDetailActionBar) {
                                    Log.e(Tag, "" + "bookDetailActionBar =false ");
                                    searchBarTextLength = queryLength;

                                    getSupportFragmentManager().popBackStack();

                                }


                            }


                        } else {
                            if (searchBarTextLength == 0) {
                                Log.e(Tag, "" + "searchBarTextLength==0");

                                Bundle fragmentTitleBundle = new Bundle();
                                fragmentTitleBundle.putInt(MainActivity.fragmentTitle, R.string.results_string);
                                BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
                                booksGridViewFragment.setArguments(fragmentTitleBundle);
                                navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, "home");

                            } else {
                                getSupportFragmentManager().popBackStack();
                                Log.e(Tag, "" + "else searchBarTextLength!=0");
                                Bundle fragmentTitleBundle = new Bundle();
                                fragmentTitleBundle.putInt(MainActivity.fragmentTitle, R.string.results_string);
                                BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
                                booksGridViewFragment.setArguments(fragmentTitleBundle);
                                navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, "home");

                            }
                            searchBarTextLength = queryLength;
                        }

//                        if(query.length()!=0) {
//
//
//
//                                Bundle fragmentTitleBundle = new Bundle();
//                                fragmentTitleBundle.putInt(MainActivity.fragmentTitle, R.string.results_string);
//                                BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
//                                booksGridViewFragment.setArguments(fragmentTitleBundle);
//                                int count = getSupportFragmentManager().getBackStackEntryCount();
//                                Log.e(Tag, "" + "onQueryTextChange getBackStackEntryCount " + count);
//                                if (count == 1) {
//                                    Log.e(Tag, "" + "onQueryTextChange  getBackStackEntryCount count = " + count);
//
//                                    navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, "home");
//                                } else if (count == 2) {
//                                    Log.e(Tag, "" + "onQueryTextChange getBackStackEntryCount count =" + count);
//                                    getSupportFragmentManager().popBackStack();
//                                    //  onBackPressed();
//                                    navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, "home");
//                                } else {
//                                    Log.e(Tag, "" + "onQueryTextChange inside if " + count);
//                                    Log.e(Tag, "" + "there is bug in the backstack implementation ");
//
//                            }
//                        }


                        return true;

                    }

                });
                searchView.setIconifiedByDefault(true);
                searchView.setOnSearchClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(Tag, "" + "searchView onClick");
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
            if (!isSearchBarExpanded) {
                onBackPressed();
            }

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
                getSupportFragmentManager().popBackStack();

            }

        }

        if (bookDetailActionBar == true) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            // bookDetailActionBar=false;
            invalidateOptionsMenu();
        }




    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//
//        handleIntent(intent);
//    }

//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            Log.e("onBackPressed", "" + "handleIntent  SearchManager "+query);
//            //use The query to search your data somehow
//        }
//    }




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
        fragmentTitleBundle.putInt(MainActivity.fragmentTitle, R.string.results_string);
        BooksGridViewFragment booksGridViewFragment = new BooksGridViewFragment();
        booksGridViewFragment.setArguments(fragmentTitleBundle);
        searchAutoComplete.setText(suggestionString);
        //navigator.addFragment(MainActivity.this, R.id.main_fragment_container, booksGridViewFragment, "home");

    }

    private void onBookClicked(BookModel bookModel) {
        Bundle bookDetailBundle = new Bundle();
        bookDetailBundle.putParcelable(MainActivity.bookDetail, bookModel);
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        bookDetailFragment.setArguments(bookDetailBundle);
        bookDetailActionBar = true;
        searchBarTextToSave = searchBarText;
        navigator.addFragment(MainActivity.this, R.id.main_fragment_container, bookDetailFragment, "home");
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if (searchBarTextToSave.length() != 0) {
            leadCapturePagePresenter.saveForRecentSearches(searchBarTextToSave);
        }
//        invalidateOptionsMenu();
        // setActionViewBar(Enums.actionBarTypeEnum.BOOK_DETAIL);

    }

    private void bookDetailActionBar() {
        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.icon_back);
        mainActivityBinding.toolbar.getMenu().removeItem(R.id.search);
        isSearchBarExpanded = false;
        getSupportActionBar().setTitle(getResources().getString(R.string.book_detail_title));
    }

    private void setSearchSuggestionActionBar() {


    }

    public void setActionViewBar(Enums.actionBarTypeEnum actionBarType) {
        Log.e(Tag, "setActionViewBar" + " actionBarType");
        switch (actionBarType) {

            case BOOK_DETAIL:
                Log.e(Tag, "BOOK_DETAIL");
                bookDetailActionBar();
                //  mainActivityBinding.toolbar.getMenu().removeItem(R.id.search);
                //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                //  getSupportActionBar().setTitle(getResources().getString(R.string.book_detail_title));

                break;

            case HOME:
                Log.e(Tag, "HOME");
                setActionViewSearchBar();

                break;

            case SEARCH_SUGGESTION:
                Log.e(Tag, "SEARCH_SUGGESTION  But not required");
                setSearchSuggestionActionBar();

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void disableError() {

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void setActionSearchBar() {

    }
}

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


//                searchView.setOnQueryTextFocusChangeListener(new SearchView.OnFocusChangeListener(){
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        Log.e(Tag, "" + "hasFocus "+hasFocus);
//                        if(!hasFocus){
//                            getSupportFragmentManager().popBackStack();
//                           // navigator.addFragment(MainActivity.this, R.id.main_fragment_container, new HomeFragment(), "home");
//
//                        }
//                        else{
//                            navigator.addFragment(MainActivity.this, R.id.main_fragment_container, new SearchFragment(), "home");
//
//                        }
//
//                    }
//                });