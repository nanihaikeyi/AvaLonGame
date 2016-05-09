package avalon.king.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import Utils.MissionUtils;
import Utils.SoundUtils;
import model.MissionInfo;
import model.RoundInfo;

/**
 * Created by Administrator on 2016/2/9.
 */

public class MissionActivity extends AppCompatActivity {
    private static final String TAG = "MissionActivity";
    private int mHeroNum;
    private ArrayList<Boolean> mMissionResultList = new ArrayList<>();//保存投票结果
    private RoundInfo mRoundInfo;  //整场游戏的结果保存
    private int mRound = 1;  //记录当前第几轮任务
    private TextView mTvMission_1;
    private TextView mTvMission_2;
    private TextView mTvMission_3;
    private TextView mTvMission_4;
    private TextView mTvMission_5;
    private Button mMissionSuccess;
    private Button mMissionFail;
    private int mVoteRound = 1;   //第几个人投票
    private View mLlMission;
    private Button mBtnStartMission;

    private MissionInfo mCurrentMission;
    private TextView mTvTip;
    private TextView mTvFail_1;
    private TextView mTvFail_2;
    private TextView mTvFail_3;
    private TextView mTvFail_4;
    private TextView mTvFail_5;
    private SoundPool mSoundPool;

    private TextView mTvVoltNum;
    private SeekBar mSbVoltBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_mission);
        initView();
        initData();
        initMissionView();
    }


    private void initMissionView() {
        mTvMission_1.setText(mRoundInfo.mMissionList.get(0).getPersonNum() + "");
        mTvMission_2.setText(mRoundInfo.mMissionList.get(1).getPersonNum() + "");
        mTvMission_3.setText(mRoundInfo.mMissionList.get(2).getPersonNum() + "");
        mTvMission_4.setText(mRoundInfo.mMissionList.get(3).getPersonNum() + "");
        mTvMission_5.setText(mRoundInfo.mMissionList.get(4).getPersonNum() + "");
        mTvTip.setText("第" + mRound + "个任务开始");
    }

    private void initData() {
        mHeroNum = getIntent().getIntExtra("heroNum", 5);
        mRoundInfo = new RoundInfo();
        mRoundInfo.mMissionList = new ArrayList<MissionInfo>();
        mRoundInfo.setCurrentRound(0);
        mRoundInfo.setFailNum(0);
        mRoundInfo.setSuccessNum(0);
        MissionUtils.creatMission(mHeroNum, mRoundInfo);
    }


    private void initView() {
        mTvTip = (TextView) findViewById(R.id.tv_tip);
        mTvVoltNum = (TextView) findViewById(R.id.tv_volt_num);
        mSbVoltBar = (SeekBar) findViewById(R.id.sb_volt_bar);
        mSbVoltBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mTvVoltNum.setText(String.valueOf(i + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mTvMission_1 = (TextView) findViewById(R.id.mission_1);
        mTvMission_2 = (TextView) findViewById(R.id.mission_2);
        mTvMission_3 = (TextView) findViewById(R.id.mission_3);
        mTvMission_4 = (TextView) findViewById(R.id.mission_4);
        mTvMission_5 = (TextView) findViewById(R.id.mission_5);

        mTvFail_1 = (TextView) findViewById(R.id.fail_num_1);
        mTvFail_2 = (TextView) findViewById(R.id.fail_num_2);
        mTvFail_3 = (TextView) findViewById(R.id.fail_num_3);
        mTvFail_4 = (TextView) findViewById(R.id.fail_num_4);
        mTvFail_5 = (TextView) findViewById(R.id.fail_num_5);

        mMissionSuccess = (Button) findViewById(R.id.mission_success);
        mBtnStartMission = (Button) findViewById(R.id.start_mission);

        mBtnStartMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRoundInfo.getWiner() >= 1) {
                    showGameResult();
                    return;
                }
                if (mRound <= 5) {
                    setViewForMission();
                    changeMissionUi(true);
                }
            }
        });

        mLlMission = findViewById(R.id.ll_mission);
        mMissionFail = (Button) findViewById(R.id.mission_fail);
        mMissionSuccess.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   AlertDialog.Builder builder = new AlertDialog.Builder(MissionActivity.this);
                                                   builder.setTitle("确认任务成功，好人只能投任务成功");
                                                   builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           updateMission();
                                                           dialog.dismiss();
                                                       }
                                                   });
                                                   builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           dialog.dismiss();
                                                       }
                                                   });
                                                   builder.create().show();
                                               }
                                           }
        );
        mMissionFail.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(MissionActivity.this);
                                                builder.setTitle("确认任务失败");
                                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        mCurrentMission.setVoteFailNum(mCurrentMission.getVoteFailNum() + 1);
                                                        updateMission();
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.create().show();
                                            }
                                        }
        );
    }

    private void setViewForMission() {
        mCurrentMission = mRoundInfo.mMissionList.get(mRound - 1);
        switch (mRound) {
            case 1:
                mCurrentMission.setMissionNumTextView(mTvMission_1);
                mCurrentMission.setMissionFailNumTextView(mTvFail_1);
                break;
            case 2:
                mCurrentMission.setMissionNumTextView(mTvMission_2);
                mCurrentMission.setMissionFailNumTextView(mTvFail_2);
                break;
            case 3:
                mCurrentMission.setMissionNumTextView(mTvMission_3);
                mCurrentMission.setMissionFailNumTextView(mTvFail_3);
                break;
            case 4:
                mCurrentMission.setMissionNumTextView(mTvMission_4);
                mCurrentMission.setMissionFailNumTextView(mTvFail_4);
                break;
            case 5:
                mCurrentMission.setMissionNumTextView(mTvMission_5);
                mCurrentMission.setMissionFailNumTextView(mTvFail_5);
                break;
        }
        mRound++;
    }

    private void showGameResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MissionActivity.this);
        builder.setTitle("确认核查身份");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jumpToResult(mRoundInfo.getWiner());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void setGameResult() {
        if (mRoundInfo.getFailNum() == 3) {
            mRoundInfo.setWiner(2);
            mTvTip.setText("3个任务已经失败，坏人胜利");
            SoundUtils.playSound(SoundUtils.mSoundPool, 2);
            mBtnStartMission.setText("查看遊戲結果");
        } else if (mRoundInfo.getSuccessNum() == 3) {
            mRoundInfo.setWiner(1);
            mTvTip.setText("3个任务已经成功，壞人開始刺殺梅林");
            SoundUtils.playSound(SoundUtils.mSoundPool, 1);
            mBtnStartMission.setText("查看遊戲結果");
        }
    }

    private void jumpToResult(int winner) {
        Intent intent = new Intent(MissionActivity.this, ResultActivity.class);
        intent.putExtra("winner", winner);
        startActivity(intent);
    }

    private void changeMissionUi(boolean isStart) {
        if (isStart) {
            mTvTip.setText("第" + mVoteRound + "个参加任务成员开始投票");
            mBtnStartMission.setVisibility(View.GONE);
            mLlMission.setVisibility(View.VISIBLE);
        } else {
            mBtnStartMission.setVisibility(View.VISIBLE);
            mLlMission.setVisibility(View.GONE);
            if (mRoundInfo.getWiner() == 1) {
            } else if (mRoundInfo.getWiner() == 2) {
            } else {
                mTvTip.setText("第" + mRound + "个任务开始");
            }
        }
        updateMissionNumUI();
    }

    private void updateMissionNumUI() {
        TextView missionTextView = mCurrentMission.getMissionNumTextView();
        TextView missionFailNumTextView = mCurrentMission.getMissionFailNumTextView();
        Log.d(TAG, "getStatus =" + mCurrentMission.getStatus());
        if (mCurrentMission.getStatus() == 1) {
            missionTextView.setTextColor(Color.BLUE);
            missionFailNumTextView.setText("失败" + mCurrentMission.getVoteFailNum());
        } else if (mCurrentMission.getStatus() == 2) {
            missionTextView.setTextColor(Color.RED);
            missionFailNumTextView.setText("失败" + mCurrentMission.getVoteFailNum());
        } else {
            missionTextView.setTextColor(Color.BLACK);
        }
    }

    private void resetVote() {
        mVoteRound = 1;
    }

    private void setResult() {
        Log.d(TAG, "getVoteFailNum = " + mCurrentMission.getVoteFailNum() + "getNeedFailNum =" + mCurrentMission.getNeedFailNum());
        if (mCurrentMission.getVoteFailNum() >= mCurrentMission.getNeedFailNum()) {
            mCurrentMission.setStatus(2);
            mRoundInfo.setFailNum(mRoundInfo.getFailNum() + 1);
        } else {
            mCurrentMission.setStatus(1);
            mRoundInfo.setSuccessNum(mRoundInfo.getSuccessNum() + 1);
        }
    }

    private void updateMission() {
        Log.d(TAG, "getPersonNum = " + mCurrentMission.getPersonNum());
        SoundUtils.playSound(SoundUtils.mSoundPool, 3);
        if (mVoteRound == mCurrentMission.getPersonNum()) {
            setResult();
            resetVote();
            changeMissionUi(false);
            setGameResult();

            return;
        }
        mVoteRound++;
        mTvTip.setText("第" + mVoteRound + "个参加任务成员开始投票");

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MissionActivity.this);
        builder.setTitle("注意，退出后任务将重置，确认退出？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}