package com.books.sample.shared.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected abstract int getLayoutResourceId();

    protected abstract Fragment getThis();

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @Override
    public final void onAttach(Context context) {
        AndroidInjection.inject(getThis());
        super.onAttach(context);
        onAfterAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(getThis(), view);
        ofAfterViewCreated(view, savedInstanceState);
    }

    protected void onAfterAttach(Context context) {

    }

    protected void ofAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public final void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        onAfterDestroyView();
    }

    protected void onAfterDestroyView() {

    }
}
