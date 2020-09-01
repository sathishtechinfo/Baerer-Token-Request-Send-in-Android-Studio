package com.example.mvpdemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;


import com.example.mvpdemo.di.component.ActivityComponent;
import com.example.mvpdemo.utils.CommonUtils;

import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity activity;
    private ProgressDialog progressDialog;
    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BaseActivity) {
            activity = (BaseActivity) context;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.cancel();
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(getContext());
    }

    @Override
    public void showMessage(String message) {
        if (activity != null)
            activity.showMessage(message);
    }

    @Override
    public void showMessage(@StringRes int resID) {
        if (activity != null)
            activity.showMessage(resID);
    }

    @Override
    public void onError(String message) {
        if (activity != null)
            activity.onError(message);
    }

    @Override
    public void onError(@StringRes int resID) {
        if (activity != null)
            activity.onError(resID);
    }

    @Override
    public void hideKeyboard() {
        if (activity != null)
            activity.hideKeyboard();
    }

    @Override
    public boolean isNetworkConnected() {
        return activity != null && activity.isNetworkConnected();
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unBinder = unBinder;
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null)
            return activity.getActivityComponent();
        return null;
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroy() {
        if (unBinder != null)
            unBinder.unbind();
        super.onDestroy();
    }

    protected abstract void setUp(View view);

    interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
