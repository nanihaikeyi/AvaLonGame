package avalon.king.com.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import model.HeroInfo;

/**
 * Created by Administrator on 2016/2/6.
 */
public class HeroListAdapter extends BaseAdapter {
    private ArrayList<HeroInfo> mHeroInfo = new ArrayList<HeroInfo>();
    private LayoutInflater mInflater;

    public HeroListAdapter(Context context, ArrayList<HeroInfo> heroInfo) {
        mHeroInfo = heroInfo;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        if(mHeroInfo == null){
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
            convertView = mInflater.inflate(R.layout.gridview_item, null);

            // construct an item tag
            viewTag = new ItemViewTag((ImageView) convertView.findViewById(R.id.grid_icon));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemViewTag) convertView.getTag();
        }
        viewTag.mIcon.setImageResource(mHeroInfo.get(position).getImgId());
        return convertView;
    }

    class ItemViewTag {
        protected ImageView mIcon;

        public ItemViewTag(ImageView icon) {
            this.mIcon = icon;
        }
    }

    public void changeData(ArrayList<HeroInfo> heroInfos) {
        mHeroInfo = heroInfos;
        notifyDataSetChanged();
    }
}

