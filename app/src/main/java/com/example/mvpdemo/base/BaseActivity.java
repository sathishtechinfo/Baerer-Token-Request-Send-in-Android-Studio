package com.example.mvpdemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mvpdemo.MVPapp;
import com.example.mvpdemo.R;
import com.example.mvpdemo.di.component.ActivityComponent;
import com.example.mvpdemo.di.component.DaggerActivityComponent;
import com.example.mvpdemo.di.module.ActivityModule;
import com.example.mvpdemo.utils.CommonUtils;
import com.example.mvpdemo.utils.NetworkUtils;

import butterknife.Unbinder;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback {

    private ProgressDialog progressDialog;
    private Unbinder unBinder;
    private ActivityComponent activityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instantiate ActivityComponent
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule (this))
                .applicationComponent(((MVPapp) getApplication()).getComponent())
                .build();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        //super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.cancel();
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void showMessage(String message) {
        if (message != null)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, R.string.some_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resID) {
        showMessage(getString(resID));
    }

    @Override
    public void onError(String message) {
        if (message != null)
            showSnackbar(message);
        else showSnackbar(getString(R.string.some_error));
    }

    @Override
    public void onError(int resID) {
        onError(getString(resID));
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (NetworkUtils.isNetworkConnected(getApplicationContext()))
            return true;
        else {
            onError(R.string.connection_error);
            return false;
        }
    }

    private void showSnackbar(String message) {
       /* Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_SHORT);

        View view = snackbar.getView();

        TextView snackTV = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        snackTV.setTextColor(ContextCompat.getColor(this, R.color.white));

        snackbar.show();*/
       Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (unBinder != null)
            unBinder.unbind();
        super.onDestroy();
    }

    protected abstract void setUp();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
