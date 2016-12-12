package com.muxistudio.jobs.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.util.DimenUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybao on 16/12/8.
 */

public class SelectSearchView extends RelativeLayout {

  @BindView(R.id.iv_back) ImageView mIvBack;
  @BindView(R.id.et_search) EditText mEtSearch;
  @BindView(R.id.iv_clear) ImageView mIvClear;
  @BindView(R.id.divider) View mDivider;
  @BindView(R.id.tv_tag) TextView mTvTag;
  @BindView(R.id.tv_history) TextView mTvHistory;
  @BindView(R.id.fl_tag) FlowLayout mFlTag;
  @BindView(R.id.fl_history) FlowLayout mFlHistory;

  private List<String> queryList = new ArrayList<>();

  //标签 adapter
  private FlowAdapter mTagAdapter;
  //历史记录 adapter
  private FlowAdapter mHistoryAdapter;

  private Context mContext;

  public SelectSearchView(Context context) {
    this(context, null);
  }

  public SelectSearchView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SelectSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mContext = context;
    initView();
  }

  private void initView() {
    LayoutInflater.from(mContext).inflate(R.layout.view_search, this, true);
    ButterKnife.bind(this);
    mIvBack.setOnClickListener(v -> {
      closeSearchView();
      clearSearchText();
    });
    mIvClear.setOnClickListener(v -> {
      clearSearchText();
    });
    this.setOnClickListener(v -> {
      closeSearchView();
    });

    initFlowLayout();
  }

  private void initFlowLayout() {
    mFlTag.setHorizontalSpacing(DimenUtil.dp2px(4));
    mFlTag.setVerticalSpacing(DimenUtil.dp2px(4));
    mFlHistory.setHorizontalSpacing(DimenUtil.dp2px(4));
    mFlHistory.setVerticalSpacing(DimenUtil.dp2px(4));
  }

  public void closeSearchView() {
    this.setVisibility(GONE);
  }

  public void showSearchView() {
    this.setVisibility(VISIBLE);
  }

  public void clearSearchText() {
    mEtSearch.setText("");
    queryList.clear();
  }

  public void setSearchTag(List<String> tagList) {
    if (!tagList.isEmpty()) {
      mTvTag.setVisibility(VISIBLE);
      mFlTag.setVisibility(VISIBLE);
    } else {
      mTvTag.setVisibility(GONE);
      mFlTag.setVisibility(GONE);
    }
    mTagAdapter = new FlowAdapter(tagList);
    //mTagAdapter.setOnItemClickListener(query -> {
      //因为标签类 query 冒号标记
      //queryList.add(query.substring(query.indexOf(":") + 1));
    //});
    mFlTag.setAdapter(mTagAdapter);
  }

  public void setSearchHistory(List<String> historyList) {
    if (!historyList.isEmpty()) {
      mTvHistory.setVisibility(VISIBLE);
      mFlHistory.setVisibility(VISIBLE);
    } else {
      mTvHistory.setVisibility(GONE);
      mFlHistory.setVisibility(GONE);
    }
    mHistoryAdapter = new FlowAdapter(historyList);
    //mHistoryAdapter.setOnItemClickListener(query -> {
    //  queryList.add(query);
    //});
    mFlHistory.setAdapter(mHistoryAdapter);
  }

  public String getQueryText() {
    return TextUtils.join("+", queryList);
  }

  public interface OnSearchViewListener {

    void onQuerySubmit(String query);
  }
}
