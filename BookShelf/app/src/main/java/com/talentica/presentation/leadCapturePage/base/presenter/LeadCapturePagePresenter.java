package main.java.presentation.leadCapturePage.base.presenter;

public interface LeadCapturePagePresenter {

    // this function wil be used to navigate between fragments for bottom menu item click
    void navigatePage();

    void getCurrentPage();

    void onBottomMenuItemClick();
}
