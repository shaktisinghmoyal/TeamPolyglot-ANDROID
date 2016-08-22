package com.talentica.presentation.leadCapturePage.addmybook.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import com.talentica.R;
import com.talentica.domain.usecases.BaseUseCase;
import com.talentica.domain.usecases.DefaultSubscriber;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddBookDetailView;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.mapper.DataMapper;
import com.talentica.presentation.utils.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class AddBookDetailPresenter implements IAddBookDetailPresenter {
    private final String Tag = "BookManuallyPresenter";
    private final BaseUseCase submitBookUseCase;
    private final DataMapper bookModelDataMapper;
    private AddBookDetailView addBookDetailView;

    @Inject
    public AddBookDetailPresenter(@Named("submitBookUseCase") BaseUseCase submitBookUseCase, DataMapper bookModelDataMapper) {
        this.submitBookUseCase = submitBookUseCase;
        this.bookModelDataMapper = bookModelDataMapper;
    }


    public void setView(@NonNull AddBookDetailView view) {
        Log.e(Tag, "" + "setView  " + view);
        addBookDetailView = view;
    }

    private void setActionBar() {
        addBookDetailView.setActionBarItems();
    }

    private void setFragmentPageDiscriptions(int typeOfFillPage) {
        addBookDetailView.setDiscriptionBarText(typeOfFillPage);
    }

    public void initialize(int typeOfFillPage) {
        setActionBar();
        setFragmentPageDiscriptions(typeOfFillPage);
        renderDetailFillPage(typeOfFillPage);


    }


    @Override
    public void renderSnackBar(int messageId, Boolean isError) {
        addBookDetailView.displaySnackBar(messageId, isError);
    }

    @Override
    public void addBookToServer(BookModel bookModel) {
        Log.e(Tag, "addBookToServer ");
        submitBookUseCase.execute(bookModelDataMapper.transform(bookModel), new SubmitBookToServerSubscriber());
    }

    @Override
    public void onGalleryChoiceResult(Context context, Intent data) {
        Bitmap thumbnail = null;
        if (data != null) {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addBookDetailView.addBookImage(thumbnail);
    }

    @Override
    public void onCameraChoiceResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                "Book_Image" + System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addBookDetailView.addBookImage(thumbnail);
    }

    @Override
    public void onCancelButtonClicked() {
        addBookDetailView.cancelThisFragment();
    }

    @Override
    public void onBookImageClicked() {
        addBookDetailView.displayAddImageDialog();
    }

    @Override
    public void renderDetailFillPage(int typeOfFillPage) {
        if (typeOfFillPage == Util.AUTO_FILL_DETAIL) {
            addBookDetailView.enableAutoFilledPart();
        } else if (typeOfFillPage == Util.MANUALLY_FILL_DETAIL) {
            addBookDetailView.enableManualFilledPart();
        }

    }


    private final class SubmitBookToServerSubscriber extends DefaultSubscriber<Boolean> {


        @Override
        public void onCompleted() {
            Log.e(Tag, "onCompleted ");
            //   hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(Tag, "onError ");
            //showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(Boolean submitted) {
            Log.e(Tag, "onNext " + submitted);
            if (submitted) {
                //  showSearchBookResults(books);
                renderSnackBar(R.string.submitted, false);
                onCancelButtonClicked();
            } else {
                renderSnackBar(R.string.book_submition_failed, true);
            }


        }
    }
}
