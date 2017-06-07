package com.books.sample.shared.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutResourceId();

    protected abstract Fragment getThis();

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @Override
    public final void onAttach(Context context) {
        AndroidSupportInjection.inject(getThis());
        super.onAttach(context);
        onAfterAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getThis(), view);
        onAfterViewCreated(view, savedInstanceState);
    }

    protected void onAfterAttach(Context context) {

    }

    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
