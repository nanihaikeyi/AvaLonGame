package avalon.king.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Utils.HeroListUtils;
import model.HeroInfo;

/**
 * Created by Administrator on 2016/2/9.
 */
public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";
    private ListView resultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ResultActivity onCreate");
        setContentView(R.layout.result_activity);
        initView();
    }

    private void initView() {
        resultListView = (ListView) findViewById(R.id.ll_result);
        resultListView.setAdapter(new resultAdapter(this, HeroListUtils.mHeroDataList));
    }

    private class resultAdapter extends BaseAdapter {
        private ArrayList<HeroInfo> mHeroInfo = new ArrayList<HeroInfo>();
        private LayoutInflater mInflater;

        public resultAdapter(Context context, ArrayList<HeroInfo> heroInfo) {
            mHeroInfo = heroInfo;
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            if (mHeroInfo == null) {
                return 0;
            }
            return mHeroInfo.size();
        }

        public Object getItem(int position) {
            return mHeroInfo.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ItemViewTag viewTag;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.result_item, null);

                // construct an item tag
                viewTag = new ItemViewTag((TextView) convertView.findViewById(R.id.hero_id),
                        (ImageView) convertView.findViewById(R.id.hero_icon));
                convertView.setTag(viewTag);
            } else {
                viewTag = (ItemViewTag) convertView.getTag();
            }
            viewTag.mNum.setText(String.valueOf(position + 1));
            viewTag.mIcon.setImageResource(mHeroInfo.get(position).getImgId());
            return convertView;
        }

        class ItemViewTag {
            protected TextView mNum;
            protected ImageView mIcon;

            public ItemViewTag(TextView num, ImageView icon) {
                this.mIcon = icon;
                this.mNum = num;
            }
        }

        public void changeData(ArrayList<HeroInfo> heroInfos) {
            mHeroInfo = heroInfos;
            notifyDataSetChanged();
        }
    }
}