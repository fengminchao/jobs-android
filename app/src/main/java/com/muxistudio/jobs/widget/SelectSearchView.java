package com.muxistudio.jobs.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.util.DimenUtil;
import com.muxistudio.jobs.util.Logger;
import hugo.weaving.DebugLog;
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

  private OnSearchViewListener mOnSearchViewListener;
  //标签adapter
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
    mEtSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
      if (i == EditorInfo.IME_ACTION_SEARCH){
        if (mOnSearchViewListener != null) {
          mOnSearchViewListener.onQuerySubmit(getQueryText());
        }
        closeSearchView();
        clearSearchText();
        return true;
      }
      return false;
    });
    initFlowLayout();
    //mFlTag.setSelectedTags();
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
    mEtSearch.setFocusableInTouchMode(true);
    mEtSearch.requestFocus();
  }

  public void clearSearchText() {
    mEtSearch.setText("");
    queryList.clear();
    mEtSearch.clearFocus();
  }

  @DebugLog
  public void setSearchTag(List<String> tagList) {
    if (!tagList.isEmpty()) {
      mTvTag.setVisibility(VISIBLE);
      mFlTag.setVisibility(VISIBLE);
    } else {
      mTvTag.setVisibility(GONE);
      mFlTag.setVisibility(GONE);
   }
    mTagAdapter = new FlowAdapter(tagList);
    Logger.d(tagList.size() + "");
    //mTagAdapter.setOnItemClickListener(query -> {
      //因为标签类 query 冒号标记
      //queryList.add(query.substring(query.indexOf(":") + 1));
    //});
    mFlTag.setAdapter(mTagAdapter);
  }

  @DebugLog
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

  /**
   * 获取用户输入和选择的标签
   * @return
   */
  public String getQueryText() {
    Logger.d(mEtSearch.getText().toString());
    queryList.add(mEtSearch.getText().toString().replace(" ","+"));
    queryList.addAll(mFlHistory.getTags());
    queryList.addAll(mFlTag.getTags());
    Logger.d(TextUtils.join("+",queryList));
    return TextUtils.join("+", queryList);
  }

  //public void setSelectTags(List<String> tags){
  //  for (int i = 0;i < tags.size();i ++){
  //    for (int j = 0;j < mTagAdapter.getCount();j ++){
  //      if (tags.get(i).equals(mTagAdapter.getStr(j))){
  //
  //      }
  //    }
  //  }
  //}

  /**
   * 获取用户输入的搜索词
   * @return
   */
  @DebugLog
  public String getEditSearchText(){
    return mEtSearch.getText().toString();
  }

  public void setOnSeachViewListener(OnSearchViewListener onSeachViewListener){
    mOnSearchViewListener = onSeachViewListener;
  }

  public interface OnSearchViewListener {

    void onQuerySubmit(String query);
  }
}
