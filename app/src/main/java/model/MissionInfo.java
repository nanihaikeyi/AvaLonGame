package model;

import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/1.
 */
public class MissionInfo {
    private int personNum;  //本次任务需要的人数
    private int needFailNum; //本轮失败需要的失败票数
    private int status; // 0: undo; 1:success; 2:fail
    private int voteFailNum; //投票失败的个数


    private TextView missionNumTextView; //任务TextView
    private TextView missionFailNumTextView; //任务失败数TextView

    public TextView getMissionNumTextView() {
        return missionNumTextView;
    }

    public void setMissionNumTextView(TextView missionNumTextView) {
        this.missionNumTextView = missionNumTextView;
    }

    public TextView getMissionFailNumTextView() {
        return missionFailNumTextView;
    }

    public void setMissionFailNumTextView(TextView missionFailNumTextView) {
        this.missionFailNumTextView = missionFailNumTextView;
    }

    public int getVoteFailNum() {
        return voteFailNum;
    }

    public void setVoteFailNum(int voteFailNum) {
        this.voteFailNum = voteFailNum;
    }

    public MissionInfo(int personNum) {
        this.personNum = personNum;
    }

    public MissionInfo(int personNum, int failNum) {
        this.personNum = personNum;
        this.needFailNum = failNum;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getNeedFailNum() {
        return needFailNum;
    }

    public void setNeedFailNum(int needFailNum) {
        this.needFailNum = needFailNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
