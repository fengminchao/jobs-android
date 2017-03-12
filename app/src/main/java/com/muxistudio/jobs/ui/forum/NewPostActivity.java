package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.PostContent;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 17/1/3.
 */

public class NewPostActivity extends ToolbarActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.et_content)
    EditText mEtContent;

    @Inject
    UserStorge mUserStorge;
    @Inject
    UserApi mUserApi;

    private boolean isNewPost = false;

    public static void start(Context context, boolean isNewPost) {
        Intent starter = new Intent(context, NewPostActivity.class);
        starter.putExtra("isNewPost", isNewPost);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_post);
        isNewPost = getIntent().getBooleanExtra("isNewPost", false);
        setTitle(isNewPost ? "发布帖子" : "修改帖子");
        if (!isNewPost) {
            mEtTitle.setText(getIntent().getStringExtra("title"));
            mEtContent.setText(getIntent().getStringExtra("content"));
        }
        mToolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_ok) {
            if (TextUtils.isEmpty(mEtTitle.getText()) || TextUtils.isEmpty(mEtContent.getText())) {
                ToastUtil.showShort("请完善帖子信息");
                return super.onOptionsItemSelected(item);
            }
            PostContent content = new PostContent();
            content.title = mEtTitle.getText().toString();
            content.content = mEtContent.getText().toString();
            if (isNewPost) {
                mUserApi.getUserService().newPost(content)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                ToastUtil.showShort("发布成功");
                                this.finish();
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort("请检查网络连接");
                        });
            } else {
                mUserApi.getUserService().changePost(content)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                ToastUtil.showShort("更改成功");
                                this.finish();
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort("请检查网络连接");
                        });
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initInjector() {

    }

}
