package com.talentica.presentation.leadCapturePage.addmybook.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.talentica.R;
import com.talentica.databinding.AddBookManuallyOrSearchBinding;
import com.talentica.presentation.internal.di.components.AddBookComponent;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookActivityPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.presenter.AddBookMainPresenter;
import com.talentica.presentation.leadCapturePage.addmybook.view.AddBookMainView;
import com.talentica.presentation.leadCapturePage.addmybook.view.activity.AddMyBookActivity;
import com.talentica.presentation.leadCapturePage.addmybook.view.adapter.AddBookGridViewAdapter;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.utils.GridViewItemClickListnerInterface;

import java.util.Collection;

import javax.inject.Inject;

public class AddBookMainFragment extends BaseFragment implements AddBookMainView, View.OnClickListener {
    private final String Tag = "AddBookMainFragment";
    private final String SAVE_STATE1 = "MOVED_TO_AUTO_FILL_PAGE";
    @Inject
    AddBookMainPresenter addBookMainPresenter;
    private AddBookActivityPresenter addBookActivityPresenter;
    private AddBookManuallyOrSearchBinding binding;
    private GridView gridView;
    private AddBookGridViewAdapter gridViewAdapter;
    private boolean isMovingToAutoFillPage;
    private ImageView previousSelectImage;
    private ImageView previousSelectedImage;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private GridViewItemClickListnerInterface gridViewItemClickListnerInterface;
    private GridView.OnScrollListener onGridViewScrollListener;
    private String resultText;
    private FragmentButtonListner buttonListner;
    private BookModel bookDetail;
    private AddBookGridViewAdapter.OnLongItemClickListener onLongItemClickListener = new AddBookGridViewAdapter.OnLongItemClickListener() {
        @Override
        public void onBookItemLongClicked(ImageView bookImage, ImageView selectImage, int position, BookModel bookModel) {
            if (addBookMainPresenter != null && bookModel != null) {
                addBookMainPresenter.onGridViewBookClick(bookModel);
                bookDetail = bookModel;
                if (previousSelectImage != null) {
                    previousSelectImage.setVisibility(View.INVISIBLE);
                }
                if (previousSelectedImage != null) {
                    previousSelectedImage.setAlpha(1.0f);
                }
                previousSelectImage = selectImage;
                previousSelectedImage = bookImage;


                bookImage.setAlpha(0.3f);
                selectImage.setVisibility(View.VISIBLE);

            }
        }
    };
    private AddBookGridViewAdapter.OnItemClickListener onItemClickListener =
            new AddBookGridViewAdapter.OnItemClickListener() {
                @Override
                public void onBookItemClicked(ImageView bookImage, ImageView selectImage, int position, BookModel bookModel) {
                    if (addBookMainPresenter != null && bookModel != null) {
                        addBookMainPresenter.onGridViewBookClick(bookModel);
                        bookDetail = bookModel;
                        if (previousSelectImage != null) {
                            previousSelectImage.setVisibility(View.INVISIBLE);
                        }
                        if (previousSelectedImage != null) {
                            previousSelectedImage.setAlpha(1.0f);
                        }
                        previousSelectImage = selectImage;
                        previousSelectedImage = bookImage;

                        bookImage.setAlpha(0.3f);
                        selectImage.setVisibility(View.VISIBLE);

                    }
                }
            };

    private GridView.OnScrollListener getGridViewScrollListener() {
        return new GridView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    addBookMainPresenter.loadMoreBooks();
                    loading = true;
                }
            }
        };
    }

    @Override
    public void removeBookOpacity() {
        if (previousSelectImage != null) {
            previousSelectImage.setVisibility(View.INVISIBLE);
        }
        if (previousSelectedImage != null) {
            previousSelectedImage.setAlpha(1.0f);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(Tag, "onCreate " + this);
        super.onCreate(savedInstanceState);
        getComponent(AddBookComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.e(Tag, "onCreateView savedInstanceState " + savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.add_book_manually_or_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e(Tag, "onViewCreated savedInstanceState " + savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e(Tag, "onActivityCreated savedInstanceState " + savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        onGridViewScrollListener = getGridViewScrollListener();
        initializeViews();
        //Restore the fragment's state here
        if (isMovingToAutoFillPage) {
            Log.e(Tag, " isMovingToAutoFillPage " + isMovingToAutoFillPage);
            isMovingToAutoFillPage = false;
            enableBottomTextBar();
            enableSearchBokResults();
            setDiscriptionBarText();
            addBookMainPresenter.searchBooksForQueryString(binding.searchBox.getText().toString());

        } else {
            Log.e(Tag, " isMovingToAutoFillPage " + isMovingToAutoFillPage);
            addBookActivityPresenter = ((AddMyBookActivity) getActivity()).addBookActivityPresenter;
            resultText = getResources().getString(R.string.results_string);
            addBookMainPresenter.setView(this);
            addBookMainPresenter.initialize();
        }
    }

    private void initializeViews() {
        setupGridView();
        binding.enterDetailButton.setOnClickListener(this);
        binding.viewRetryToAddBook.setOnClickListener(this);
        binding.cancel.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.searchBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                String searchedText = textView.getText().toString();
                Log.e(Tag, "onEditorAction  " + searchedText);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.e(Tag, "EditorInfo.IME_ACTION_SEARCH  ");
                    if (!((searchedText.equals(null)) || searchedText.equals(""))) {
                        disableBottomTextBar();
                        disableButtonForManualDetail();
                        enableSearchBokResults();
                        gridViewAdapter.clearData();
                        addBookMainPresenter.searchBooksForQueryString(searchedText);

                    } else {
                        disableBottomTextBar();
                        disableSearchBookResults();
                        enableButtonForManualDetail();

                    }

                }
                return false;
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        Log.e(Tag, "onAttach  ");
        super.onAttach(context);
        if (context instanceof GridViewItemClickListnerInterface) {

            this.gridViewItemClickListnerInterface = (GridViewItemClickListnerInterface) context;
        }
        if (context instanceof FragmentButtonListner) {
            this.buttonListner = (FragmentButtonListner) context;
        }
    }

    private void setupGridView() {
        Log.e(Tag, "setupGridView  ");
        gridView = binding.booksResultToAdd;
        gridView.setOnScrollListener(onGridViewScrollListener);
        gridViewAdapter = new AddBookGridViewAdapter((AppCompatActivity) getActivity());
        gridViewAdapter.setOnItemClickListener(onItemClickListener);
        gridViewAdapter.setOnLongItemClickListener(onLongItemClickListener);
        gridView.setAdapter(gridViewAdapter);

    }

    @Override
    public void showSearchBookResults(Collection<BookModel> books) {
        Log.e(Tag, "showSearchBookResults  ");
        gridViewAdapter.addBookSearchResult(books);
        updateSearchBookResultQuantity();
    }

    @Override
    public void selectBook(BookModel bookModel) {
        Log.e(Tag, "selectBook  ");
        addBookMainPresenter.showNextBottomButtons();

    }

    @Override
    public void setActionBarItems() {
        Log.e(Tag, "setActionBarItems  ");
        ((AddMyBookActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.add_book_text));
    }

    @Override
    public void setDiscriptionBarText() {
        Log.e(Tag, "setDiscriptionBarText  ");
        addBookActivityPresenter.setDiscriptionBar(R.string.search_option, R.string.step1);
    }

    @Override
    public void enableButtonForManualDetail() {
        Log.e(Tag, "enableButtonForManualDetail  ");
        binding.manualSearchButtonLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableButtonForManualDetail() {
        Log.e(Tag, "disableButtonForManualDetail  ");
        binding.manualSearchButtonLayout.setVisibility(View.GONE);
    }

    @Override
    public void enableSearchBokResults() {
        Log.e(Tag, "enableSearchBokResults  ");
        binding.searchResultToAddBook.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableSearchBookResults() {
        Log.e(Tag, "disableSearchBookResults  ");
        binding.searchResultToAddBook.setVisibility(View.GONE);
    }

    @Override
    public void updateSearchBookResultQuantity() {
        Log.e(Tag, "updateSearchBookResultQuantity  ");
        String updateText = resultText + " (" + gridViewAdapter.getCount() + ")";
        binding.searchResultCount.setText(updateText);
    }

    @Override
    public void enableBottomTextBar() {
        Log.e(Tag, "enableBottomTextBar ");
        binding.selectBookText.setVisibility(View.VISIBLE);
        onBackClickEnabler();
    }

    @Override
    public void disableBottomTextBar() {
        Log.e(Tag, "disableBottomTextBar ");
        binding.selectBookText.setVisibility(View.GONE);
    }

    @Override
    public void enableBottomNextButtons() {
        binding.nextButtons.setVisibility(View.VISIBLE);

    }

    @Override
    public void disableBottomNextButtons() {
        binding.nextButtons.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        Log.e(Tag, "onResume ");
        super.onResume();
        addBookMainPresenter.resume();
        onBackClickEnabler();
    }

    private void onBackClickEnabler() {
        binding.getRoot().setFocusableInTouchMode(true);
        binding.getRoot().requestFocus();
        binding.getRoot().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e(Tag, "onKey " + event.getAction());
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.e(Tag, "KEYCODE_BACK " + event.getAction());

                    if (binding.selectBookText.getVisibility() == View.VISIBLE) {
                        disableBottomTextBar();
                        disableSearchBookResults();
                        enableButtonForManualDetail();
                        return true;
                    } else if (binding.nextButtons.getVisibility() == View.VISIBLE) {
                        disableBottomNextButtons();
                        enableBottomTextBar();
                        return true;
                    } else {
                        return false;
                    }

                }
                return false;
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        addBookMainPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        gridView.setAdapter(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addBookMainPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addBookMainPresenter = null;
        gridViewItemClickListnerInterface = null;
    }

    @Override
    public void showLoading() {
        binding.gridViewProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.gridViewProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        binding.viewRetryToAddBook.setVisibility(View.VISIBLE);
        binding.emptyViewText.setText(R.string.try_again);
    }

    @Override
    public void hideRetry() {
        binding.viewRetryToAddBook.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        binding.errorTextView.setText(R.string.not_connected);
    }

    @Override
    public void disableError() {

    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void moveToAutoFillDetailPage() {
        Log.e(Tag, "moveToAutoFillDetailPage bookDetail= " + bookDetail);
        isMovingToAutoFillPage = true;
        onGridViewScrollListener = null;
        buttonListner.onNextButtonClicked(bookDetail);
    }

    @Override
    public void moveToManuallyFillDetailPage() {
        Log.e(Tag, "moveToManuallyFillDetailPage ");
        buttonListner.onEnterManuallyButtonClicked();
    }

    @Override
    public void onClick(View v) {
        Log.e(Tag, "onClick ");
        switch (v.getId()) {
            case R.id.enter_detail_button:
                addBookMainPresenter.onManuallyAddDetailsButtonClicked();
                break;


            case R.id.view_retry_to_add_book:
                addBookMainPresenter.retry();
                break;

            case R.id.next:
                Log.e(Tag, "next clicked ");
                addBookMainPresenter.onNextButtonClicked();
                break;


            case R.id.cancel:
                addBookMainPresenter.onCancelButtonClicked();
                break;


        }
    }


    public interface FragmentButtonListner {
        void onEnterManuallyButtonClicked();

        void onNextButtonClicked(final BookModel bookModel);


    }


}
