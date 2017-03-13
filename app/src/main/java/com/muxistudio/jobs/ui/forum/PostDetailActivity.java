package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.RxBus;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.PostDetailResult;
import com.muxistudio.jobs.bean.ReplyBean;
import com.muxistudio.jobs.event.PostDetailUpdateEvent;
import com.muxistudio.jobs.injector.PerActivity;
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

@PerActivity
public class PostDetailActivity extends ToolbarActivity {

    @Inject
    UserApi mUserApi;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_reply)
    EditText mEtReply;
    @BindView(R.id.iv_send)
    ImageView mIvSend;
    @BindView(R.id.layout_reply)
    LinearLayout mLayoutReply;

    private PostDetailResult.DataBean mPostDetailData;
    private PostDetailAdapter mPostDetailAdapter;
    private int pid;

    public static void start(Context context, int pid) {
        Intent starter = new Intent(context, PostDetailActivity.class);
        starter.putExtra("pid", pid);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);
        mToolbar.setTitle("详情");
        this.pid = getIntent().getIntExtra("pid", 0);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPostDetailData = new PostDetailResult.DataBean();
        mPostDetailAdapter = new PostDetailAdapter(mPostDetailData);
        mRecyclerView.setAdapter(mPostDetailAdapter);

        loadPostDetail();

        RxBus.getDefault().toObservable(PostDetailUpdateEvent.class)
                .subscribe(postDetailUpdateEvent -> {
                    loadPostDetail();
                }, throwable -> throwable.printStackTrace());
        mIvSend.setOnClickListener(v -> {
            sendReply();
        });
    }

    public void sendReply() {
        mUserApi.getUserService().replyPost(new ReplyBean(mEtReply.getText().toString()), pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseData -> {
                    if (baseData.code < 300) {
                        loadPostDetail();
                        mEtReply.setText("");
                        hideKeyBoard();
                    } else {
                        throw new Error();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    ToastUtil.showShort(R.string.err_request);
                });
    }

    public void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mEtReply.getWindowToken(), 0);
    }

    @Override
    protected void initInjector() {
        DaggerForumComponent.builder().applicationComponent(
                getApplicationComponent()).build().inject(this);
    }

    public void loadPostDetail() {
        mUserApi.getUserService().getPostDetail(pid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postDetailResult -> {
                    if (postDetailResult.code == 0) {
                        mPostDetailData.topic = postDetailResult.data.topic;
                        mPostDetailData.replys = postDetailResult.data.replys;
                        mPostDetailAdapter.notifyDataSetChanged();
                    } else {
                        throw new IllegalArgumentException();
                    }
                });
    }

    public void deletePost() {
        mUserApi.getUserService().deletePost(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseData -> {
                    if (baseData.code == 0) {
                        finish();
                        ToastUtil.showShort(R.string.tip_delete_success);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    ToastUtil.showShort(R.string.err_request);
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                EditPostActivity.start(App.sContext, false, mPostDetailData.topic, pid);
                break;
            case R.id.action_delete:
                deletePost();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
