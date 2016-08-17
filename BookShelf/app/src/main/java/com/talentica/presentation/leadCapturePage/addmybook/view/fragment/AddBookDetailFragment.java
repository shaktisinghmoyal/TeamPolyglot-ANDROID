package com.talentica.presentation.leadCapturePage.addmybook.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.talentica.R;
import com.talentica.databinding.AddBookDetailBinding;
import com.talentica.presentation.internal.di.components.AddBookComponent;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookActivityPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookDetailPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddBookDetailView;
import com.talentica.presentation.leadCapturePage.addmybook.view.activity.AddMyBookActivity;
import com.talentica.presentation.leadCapturePage.addmybook.view.adapter.NothingSpinAdapForBookDetailPage;
import com.talentica.presentation.leadCapturePage.addmybook.view.adapter.SpinnerAdapterForBookDetailPage;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.CameraPermissionHandling;
import com.talentica.presentation.utils.CustomSnackbar;
import com.talentica.presentation.utils.GalleryPermissionHandling;
import com.talentica.presentation.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

public class AddBookDetailFragment extends BaseFragment implements AddBookDetailView, View.OnClickListener, Spinner.OnItemSelectedListener, DialogInterface.OnClickListener {


    private final String Tag = "AddBookDetailFragment";
    @Inject
    AddBookDetailPresenter addBookDetailPresenter;
    AlertDialog.Builder addImageBuilder;
    private AddBookActivityPresenter addBookActivityPresenter;
    private AddBookDetailBinding binding;
    private int typeOfFillPage;
    private FragmentButtonListner buttonListner;
    private ArrayList<String> bindingTypes = new ArrayList<String>();
    private ArrayList<String> genreTypes = new ArrayList<String>();
    private String TAKE_PHOTO;
    private String CHOOSE_FROM_LIBRARY;
    private String CANCEL;
    private String[] addImageDialogOptions;
    private BookModel bookModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(AddBookComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.add_book_detail, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e(Tag, " onViewCreated" + "  ");
        super.onViewCreated(view, savedInstanceState);
        addBookActivityPresenter = ((AddMyBookActivity) getActivity()).addBookActivityPresenter;
        addBookDetailPresenter.setView(this);
        bookModel = getArguments().getParcelable(Util.bookDetailBundle);
        Log.e(Tag, " onViewCreated bookModel " + bookModel);
        if (getArguments() != null) {
            typeOfFillPage = getArguments().getInt(Util.fillDetailType);
            Log.e(Tag, " bundle" + " not null ");

        }
        if (savedInstanceState == null) {
            initializeBook();
        }


        //  initializeSnackBar();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentButtonListner) {
            this.buttonListner = (FragmentButtonListner) context;
        }
        TAKE_PHOTO = getResources().getString(R.string.take_photo);
        CHOOSE_FROM_LIBRARY = getResources().getString(R.string.load_from_library);
        CANCEL = getResources().getString(R.string.cancel);
        addImageDialogOptions = new String[]{TAKE_PHOTO, CHOOSE_FROM_LIBRARY, CANCEL};
    }

    private void initializeBook() {
        binding.back.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        binding.bookImage.setOnClickListener(this);
        setBindingTypeSpinner();
        setGenreTypeSpinner();
        makeDialogBuilder();
        // binding.bindingSpinner.setSelection(1);
        // binding.bindingSpinner.setSelected(true);
        addBookDetailPresenter.initialize(typeOfFillPage);
    }

    @Override
    public void displayAddImageDialog() {
        addImageBuilder.show();
    }

    @Override
    public void addBookImage(Bitmap bm) {
        binding.bookImage.setImageBitmap(bm);
    }

    private void makeDialogBuilder() {
        addImageBuilder = new AlertDialog.Builder(getActivity());
        addImageBuilder.setItems(addImageDialogOptions, this);
    }

    private void setGenreTypeSpinner() {
        genreTypes.addAll(Arrays.asList(getResources().getStringArray(R.array.genre_types)));
        SpinnerAdapterForBookDetailPage bindingTypesAdapter = new SpinnerAdapterForBookDetailPage(getActivity(), android.R.layout.simple_spinner_item, genreTypes);
        bindingTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genreSpinner.setAdapter(
                new NothingSpinAdapForBookDetailPage(
                        bindingTypesAdapter,
                        R.layout.nothing_selected_spinner_row,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        getActivity()));
        binding.genreSpinner.setOnItemSelectedListener(this);
    }

    private void setBindingTypeSpinner() {
        bindingTypes.addAll(Arrays.asList(getResources().getStringArray(R.array.binding_types)));
        SpinnerAdapterForBookDetailPage bindingTypesAdapter = new SpinnerAdapterForBookDetailPage(getActivity(), android.R.layout.simple_spinner_item, bindingTypes);
        bindingTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.bindingSpinner.setAdapter(
                new NothingSpinAdapForBookDetailPage(
                        bindingTypesAdapter,
                        R.layout.nothing_selected_spinner_row,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        getActivity()));
        binding.bindingSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void enableAutoFilledPart() {
        binding.setBookModel(bookModel);
        binding.autoAddUpperPortion.setVisibility(View.VISIBLE);

    }

    @Override
    public void enableManualFilledPart() {
        binding.manuallyAddUpperPortion.setVisibility(View.VISIBLE);
    }

    @Override
    public void displaySnackBar(int messageId, Boolean isError) {
        Snackbar snackbar = Snackbar.make(binding.submitButtons, messageId, Snackbar.LENGTH_SHORT);
        CustomSnackbar.show(snackbar, isError);
    }

    @Override
    public void setActionBarItems() {

    }

    @Override
    public void setDiscriptionBarText(int TypeOfPage) {
        if (typeOfFillPage == Util.MANUALLY_FILL_DETAIL)
        addBookActivityPresenter.setDiscriptionBar(R.string.add_manually, R.string.step2);
        else if (typeOfFillPage == Util.AUTO_FILL_DETAIL) {
            addBookActivityPresenter.setDiscriptionBar(R.string.add_detail, R.string.step2);
        }
    }

    private Boolean checkImportantFields() {


        if ((binding.authorNameEditText.getText().length() != 0) && (binding.bookNameEditText.getText().length() != 0)) {
            Log.e(Tag, " authorNameEditText" + " not null ");
            if ((binding.isbn13.getText().length() != 0) && (binding.isbn10.getText().length() != 0) && (binding.bookCondition.getCheckedRadioButtonId() != -1)) {
                Log.e(Tag, " isbn13" + " not null ");
                if ((binding.genreSpinner.getSelectedItemPosition() != 0) && binding.tagsEditText.getText().length() != 0) {
                    Log.e(Tag, " genreSpinner" + " not null ");
                    if (binding.manuallyAddUpperPortion.getVisibility() == View.VISIBLE) {
                        Log.e(Tag, " manuallyAddUpperPortion" + " not null ");
                        if (binding.bindingSpinner.getSelectedItemPosition() != 0) {
                            Log.e(Tag, " bindingSpinner" + " not null ");
                            return true;
                        }

                    } else {
                        Log.e(Tag, " manuallyAddUpperPortion " + " not VISIBLE ");
                        return true;
                    }

                }
            }
        }
        return false;

    }

    @Override
    public void cancelThisFragment() {
        buttonListner.onBackButtonClicked();
    }

    @Override
    public void onClick(View v) {
        Log.e(Tag, "onClick ");
        switch (v.getId()) {
            case R.id.submit:
                if (checkImportantFields()) {
                    BookModel bookModel = binding.getBookModel();
                    addBookDetailPresenter.addBookToServer(bookModel);
                } else {
                    displaySnackBar(R.string.field_empty, true);

                }

                break;


            case R.id.back:
                addBookDetailPresenter.onCancelButtonClicked();
                break;

            case R.id.book_image:
                addBookDetailPresenter.onBookImageClicked();
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (binding.bindingSpinner.getId() == id) {
            binding.bindingSpinner.setSelection(position + 1);
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        if (addImageDialogOptions[which].equals(TAKE_PHOTO)) {
            CameraPermissionHandling.handleCameraPermission((AddMyBookActivity) getActivity(), binding.submit);


        } else if (addImageDialogOptions[which].equals(CHOOSE_FROM_LIBRARY)) {

            GalleryPermissionHandling.handleGalleryPermission((AddMyBookActivity) getActivity(), binding.submit);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == Util.SELECT_FILE) {
                Log.e(Tag, " bundle" + " SELECT_FILE ");
                addBookDetailPresenter.onGalleryChoiceResult(getContext(), data);
            } else if (requestCode == Util.REQUEST_CAMERA) {
                Log.e(Tag, " bundle" + " REQUEST_CAMERA ");
                addBookDetailPresenter.onCameraChoiceResult(data);

            }

        }
    }

    public interface FragmentButtonListner {
        void onBackButtonClicked();
    }


}
