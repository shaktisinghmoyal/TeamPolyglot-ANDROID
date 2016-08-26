package com.talentica.presentation.leadCapturePage.base.view;

import android.os.Bundle;

public interface LeadCapturePageView {

    // to set the title for each fragment/Activity

    void displaySearchResultForDrawerItem();

    void initializeBottomMenuItemIds();

    void showBottomMenu();

    void hideBottomMenu();

    void displayNotificationCount();

    void setActionBar(String title);

    void setFirstFragment(Bundle savedInstanceState);


}
