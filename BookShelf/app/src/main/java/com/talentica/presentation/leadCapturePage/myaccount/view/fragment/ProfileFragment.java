package com.talentica.presentation.leadCapturePage.myaccount.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentica.R;
import com.talentica.databinding.ProfileLayoutBinding;
import com.talentica.presentation.internal.di.components.LeadCaptureComponent;
import com.talentica.presentation.leadCapturePage.base.view.fragment.BaseFragment;
import com.talentica.presentation.leadCapturePage.myaccount.view.ProfileView;

public class ProfileFragment extends BaseFragment implements ProfileView {
    private final String Tag = "ProfileFragment";
    private ProfileLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.profile_layout, container, false);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(Tag, "onCreate ");
        super.onCreate(savedInstanceState);
        getComponent(LeadCaptureComponent.class).inject(this);
    }

    @Override
    public void renderProfile() {

    }
}
