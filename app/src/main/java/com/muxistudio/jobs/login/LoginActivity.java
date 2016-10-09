package com.muxistudio.jobs.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.muxistudio.jobs.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 16/6/6.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.edit_name)
    EditText mEditName;
    @BindView(R.id.layout_name)
    TextInputLayout mLayoutName;
    @BindView(R.id.edit_pwd)
    EditText mEditPwd;
    @BindView(R.id.layout_pwd)
    TextInputLayout mLayoutPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
        mBtnLogin.setOnClickListener((v) -> {
            mPresenter.login(mEditName.getText().toString(),mEditPwd.getText().toString());
        });
    }

    @Override
    public void showLoading() {
        if (!isProgressShown()) {
            mProgressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (isProgressShown()) {
            mProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showUserNameError(String err) {
        mEditName.setText(err);
    }

    @Override
    public void showUserPwdError(String err) {
        mEditPwd.setText(err);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this,"login success",Toast.LENGTH_LONG).show();
        Log.d("tag","login success");
    }

    public boolean isProgressShown() {
        return mProgressbar.isShown();
    }

}
