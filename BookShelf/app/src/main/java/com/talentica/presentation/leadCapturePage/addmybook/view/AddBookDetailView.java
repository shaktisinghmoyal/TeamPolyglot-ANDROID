package com.talentica.presentation.leadCapturePage.addmybook.view;

import android.graphics.Bitmap;

public interface AddBookDetailView {


    public void enableAutoFilledPart();

    public void enableManualFilledPart();


    public void displaySnackBar(int messageID, Boolean isError);


    public void setActionBarItems();


    public void setDiscriptionBarText(int typeOfPage);

    void displayAddImageDialog();

    void addBookImage(Bitmap bm);

    void cancelThisFragment();


}
