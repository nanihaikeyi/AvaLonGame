package avalon.king.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import Utils.HeroListUtils;
import Utils.SoundUtils;
import model.HeroInfo;


/**
 * Created by Administrator on 2016/2/5.
 */
public class HeroChooseActivity extends AppCompatActivity {
    private static final String SUB_TAG = "HeroChooseActivity";
    private int player = 1;
    private Button mBtnConfirm;
    private ImageView mHeroImg;
    private Button mBtnFinish;
    private TextView mTvPlayer;
    private Button mBtnStart;
    private TextView mTvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_hero);
        initData();
        initView();
    }

    private void initView() {

        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mBtnFinish = (Button) findViewById(R.id.btn_finish);
        mBtnStart = (Button) findViewById(R.id.btn_start_mission);

        mTvPlayer = (TextView) findViewById(R.id.tv_player);
        mTvTip = (TextView) findViewById(R.id.tv_tip);
        mHeroImg = (ImageView) findViewById(R.id.img_hero);
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player >= 1 && player <= HeroListUtils.mHeroDataList.size()) {
                    mHeroImg.setImageResource(HeroListUtils.mHeroDataList.get(player - 1).getImgId());
                    mBtnConfirm.setVisibility(View.GONE);
                    mBtnFinish.setVisibility(View.VISIBLE);
                }
            }
        });
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HeroChooseActivity.this, MissionActivity.class);
                intent.putExtra("heroNum", HeroListUtils.mHeroDataList.size());
                startActivity(intent);
            }
        });

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player == HeroListUtils.mHeroDataList.size()) {
                    mTvTip.setText("全部确认完毕，将手机交给第一个队长");
                    mHeroImg.setImageResource(R.mipmap.avalon);
                    mBtnConfirm.setVisibility(View.GONE);
                    mBtnFinish.setVisibility(View.GONE);
                    mBtnStart.setVisibility(View.VISIBLE);
                    mTvPlayer.setVisibility(View.GONE);
                }
                player++;
                mTvPlayer.setText(player + "");
                mHeroImg.setImageResource(R.mipmap.avalon);
                mBtnConfirm.setVisibility(View.VISIBLE);
                mBtnFinish.setVisibility(View.GONE);
                SoundUtils.playSound(SoundUtils.mSoundPool, 3);
            }
        });
    }

    private void initData() {
        HeroListUtils.mHeroDataList = randomList((ArrayList<HeroInfo>) getIntent().getSerializableExtra("heroList"));
        printHeroList();
    }

    private void printHeroList() {
        for (int i = 0; i < HeroListUtils.mHeroDataList.size(); i++) {
            Log.d(SUB_TAG, "  " + HeroListUtils.mHeroDataList.get(i));
        }
    }

    public static <V> boolean isEmpty(ArrayList<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * 打乱ArrayList
     */
    public static <V> ArrayList<V> randomList(ArrayList<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        ArrayList<V> randomList = new ArrayList<V>(sourceList.size());
        do {
            int randomIndex = Math.abs(new Random().nextInt(sourceList.size()));
            randomList.add(sourceList.remove(randomIndex));
        } while (sourceList.size() > 0);

        return randomList;
    }
}
