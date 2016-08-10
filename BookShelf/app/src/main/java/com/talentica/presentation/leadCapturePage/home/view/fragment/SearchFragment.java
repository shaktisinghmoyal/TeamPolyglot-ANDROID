package com.talentica.presentation.leadCapturePage.home.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.talentica.R;
import com.talentica.databinding.SearchFragmentBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.presenter.LeadCapturePagePresenter;
import com.talentica.presentation.leadCapturePage.base.view.activity.MainActivity;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.home.presenter.SearchSuggestionPresenter;
import com.talentica.presentation.leadCapturePage.home.view.SearchSuggestionsView;
import com.talentica.presentation.utils.ClickListenerInterface;
import com.talentica.presentation.utils.Enums;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment implements SearchSuggestionsView {

    private final String Tag = "SearchFragment";
    @Inject
    SearchSuggestionPresenter searchSuggestionPresenter;
    LeadCapturePagePresenter leadCapturePagePresenter;
    ListView recentSearches;
    ListView topSearches;
    private ClickListenerInterface suggestionItemListener;
    private SearchFragmentBinding searchFragmentBinding;
    private ArrayAdapter recentSearchesAdapter;
    private ArrayAdapter topSearchesAdapter;
    private ListView.OnItemClickListener onListViewItemClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (searchSuggestionPresenter != null) {
                Log.e(Tag, "ListView " + "onItemClick " + parent.getItemAtPosition(position));

                searchSuggestionPresenter.onItemClicked(parent.getItemAtPosition(position).toString());
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(Tag, "onCreate " + "onCreate ");
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        leadCapturePagePresenter = ((MainActivity) getActivity()).leadCapturePagePresenter;
        leadCapturePagePresenter.disableBottomMenu();
        Log.e(Tag, "onCreateView " + "onCreateView ");
        searchFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false);

        return searchFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchSuggestionPresenter.setView(this);
        setupListViews();


        if (savedInstanceState == null) {
            initializeSearchSuggestions();
        }

    }

    private void setupListViews() {
        recentSearchesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.search_sugg_item);
        topSearchesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.search_sugg_item);

        recentSearches = searchFragmentBinding.recentSearch;
        recentSearches.setAdapter(recentSearchesAdapter);
        recentSearches.setOnItemClickListener(onListViewItemClickListener);

        topSearches = searchFragmentBinding.topSearch;
        topSearches.setAdapter(topSearchesAdapter);
        topSearches.setOnItemClickListener(onListViewItemClickListener);

        ViewGroup.LayoutParams params1 = topSearches.getLayoutParams();
        params1.height = (int) (280 * getContext().getResources().getDisplayMetrics().density + 0.5f);
        topSearches.setLayoutParams(params1);
        topSearches.requestLayout();

        ViewGroup.LayoutParams params2 = recentSearches.getLayoutParams();
        params2.height = (int) (280 * getContext().getResources().getDisplayMetrics().density + 0.5f);
        recentSearches.setLayoutParams(params2);
        recentSearches.requestLayout();

    }

    private void initializeSearchSuggestions() {
        searchSuggestionPresenter.initialize();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClickListenerInterface) {
            this.suggestionItemListener = (ClickListenerInterface) context;
        }
    }

    @Override
    public void showRecentSearches(String[] recentSearchesList) {
        Log.e(Tag, "showRecentSearches " + "showRecentSearches ");
        recentSearchesAdapter.clear();
        recentSearchesAdapter.addAll(recentSearchesList);
        recentSearchesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTopSearches(String[] topSearchsLiat) {
        Log.e(Tag, "showTopSearches " + "showTopSearches ");
        topSearchesAdapter.clear();
        topSearchesAdapter.addAll(topSearchsLiat);
        topSearchesAdapter.notifyDataSetChanged();


    }

    @Override
    public void showSuggestedBooks(String suggestedString) {
        if (this.suggestionItemListener != null) {
            this.suggestionItemListener.onSuggestionItemClicked(suggestedString);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        searchSuggestionPresenter.resume();
    }

    @Override
    public void onPause() {
        Log.e(Tag, "onPause " + " ");
        super.onPause();
        searchSuggestionPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        Log.e(Tag, "onDestroyView " + " ");
        super.onDestroyView();
        leadCapturePagePresenter.enableBottomMenu();
        recentSearches.setAdapter(null);
        topSearches.setAdapter(null);

    }

    @Override
    public void onDestroy() {
        Log.e(Tag, "onDestroy " + " ");
        super.onDestroy();
        searchSuggestionPresenter.destroy();
    }

    @Override
    public void onDetach() {
        Log.e(Tag, "onDetach " + " ");
        super.onDetach();
        searchSuggestionPresenter = null;
        suggestionItemListener = null;
    }

    @Override
    public void showLoading() {
        // getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        //this.getActivity().setProgressBarIndeterminateVisibility(false);
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
        return getActivity().getApplicationContext();
    }

    @Override
    public void setActionSearchBar() {
        ((MainActivity) getActivity()).setActionViewBar(Enums.actionBarTypeEnum.SEARCH_SUGGESTION);
    }


}
