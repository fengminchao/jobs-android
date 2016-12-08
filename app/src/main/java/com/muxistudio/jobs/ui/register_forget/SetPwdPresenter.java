package com.muxistudio.jobs.ui.register_forget;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.RegexUtil;
import com.muxistudio.jobs.util.ToastUtil;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/10/22.
 */
@PerActivity public class SetPwdPresenter extends SubscriptionPresenter
    implements SetPwdContract.Presenter {

  private SetPwdContract.View mView;
  @Inject UserApi mUserApi;

  @Inject public SetPwdPresenter(UserApi userApi) {
    Logger.d(mUserApi == null ? "user api is null" : "user api is not null");
    mUserApi = userApi;
  }

  @Override public void sendAuthCode(String mail) {
    if (!RegexUtil.checkMail(mail)) {
      mView.showMailError("邮箱格式错误");
      return;
    }
    User user = new User();
    user.setMail(mail);
    Subscription s = mUserApi.getUserService()
        .authNewMail(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseData -> {
          if (baseData.code == 0) {
            mView.showAuthSend();
          } else {
            mView.showError("验证码发送失败");
          }
        }, throwable -> {
          throwable.printStackTrace();
          mView.showError(App.sContext.getString(R.string.err_send_auth));
        });
    addSubscription(s);
  }

  @Override public void sendResetAuthCode(String mail) {
    if (!RegexUtil.checkMail(mail)) {
      mView.showMailError("邮箱格式错误");
      return;
    }
    User user = new User();
    user.setMail(mail);
    Subscription s = mUserApi.getUserService()
        .findAuth(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseData -> {
          if (baseData.code == 0) {
            mView.showAuthSend();
          } else {
            mView.showError("验证码发送失败");
          }
        }, throwable -> {
          throwable.printStackTrace();
          ToastUtil.showShort(App.sContext.getString(R.string.err_send_auth));
        });
    addSubscription(s);
  }

  @Override public void register(String mail, String pwd, String auth) {
    if (!RegexUtil.checkMail(mail)) {
      mView.showMailError("邮箱格式错误");
      return;
    }
    if (TextUtils.isEmpty(pwd)) {
      mView.showPwdError("密码不能为空");
      return;
    }
    mView.showLoading();
    User user = new User();
    user.setMail(mail);
    user.setPwd(pwd);
    user.setAuthCode(Integer.valueOf(auth));
    Subscription s = mUserApi.getUserService()
        .registerByAuth(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseData -> {
          if (baseData.code == 0) {
            mView.authSuccess();
          } else {
            mView.showError("验证失败");
          }
        }, throwable -> throwable.printStackTrace(), () -> {
          mView.hideLoading();
          ToastUtil.showShort(App.sContext.getString(R.string.err_register));
        });
    addSubscription(s);
  }

  @Override public void resetPwd(String mail, String pwd, String auth) {
    if (!RegexUtil.checkMail(mail)) {
      mView.showMailError("邮箱格式错误");
      return;
    }
    if (TextUtils.isEmpty(pwd)) {
      mView.showPwdError("密码不能为空");
      return;
    }
    mView.showLoading();
    User user = new User();
    user.setMail(mail);
    user.setPwd(pwd);
    user.setAuthCode(Integer.valueOf(auth));
    Subscription s = mUserApi.getUserService()
        .findByAuth(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseData -> {
          if (baseData.code == 0) {
            mView.authSuccess();
          } else {
            mView.showError("验证失败");
          }
        }, throwable -> throwable.printStackTrace(), () -> {
          mView.hideLoading();
          ToastUtil.showShort(App.sContext.getString(R.string.err_register));
        });
    addSubscription(s);
  }

  @Override public void attachView(@NonNull SetPwdContract.View view) {
    mView = view;
  }

  @Override public void detachView() {
    mView = null;
  }
}
