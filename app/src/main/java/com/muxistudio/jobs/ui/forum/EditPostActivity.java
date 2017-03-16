package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.RxBus;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.PostContent;
import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.event.PostDetailUpdateEvent;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 17/1/3.
 */

public class EditPostActivity extends ToolbarActivity {

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

    private PostContent mPostContent = new PostContent();
    private int pid;

    private boolean isNewPost = false;

    public static void start(Context context, boolean isNewPost, PostData postData, int pid) {
        Intent starter = new Intent(context, EditPostActivity.class);
        starter.putExtra("is_new_post", isNewPost);
        if (postData != null) {
            starter.putExtra("post_data", postData);
            starter.putExtra("pid", pid);
        }
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
        ButterKnife.bind(this);
        isNewPost = getIntent().getBooleanExtra("is_new_post", false);
        setTitle(isNewPost ? "发布帖子" : "修改帖子");
        if (!isNewPost) {
            PostData postData = getIntent().getParcelableExtra("post_data");
            pid = getIntent().getIntExtra("pid", 0);
            mPostContent.title = postData.title;
            mPostContent.content = postData.content;
            mEtTitle.setText(mPostContent.title);
            mEtContent.setText(mPostContent.content);
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
            mPostContent.title = mEtTitle.getText().toString();
            mPostContent.content = mEtContent.getText().toString();
            if (isNewPost) {
                mUserApi.getUserService().newPost(mPostContent)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                ToastUtil.showShort("发布成功");
                                this.finish();
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort(R.string.err_request);
                        });
            } else {
                mUserApi.getUserService().changePost(pid, mPostContent)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                ToastUtil.showShort("更改成功");
                                this.finish();
                                RxBus.getDefault().send(new PostDetailUpdateEvent());
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort(R.string.err_request);
                        });
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initInjector() {
        DaggerForumComponent.builder()
                .applicationComponent(getApplicationComponent()).build().inject(this);
    }

}
