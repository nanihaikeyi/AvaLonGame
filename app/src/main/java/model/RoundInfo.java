package model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/1.
 * 记录整场游戏的状态
 */
public class RoundInfo {
    private int currentRound;//当前进行第几轮，0,1，2，3，4；
    private int successNum; //成功次数
    private int failNum;//失败次数
    private int winer;// 1：代表好人胜利；2：代表坏人胜利
    public ArrayList<MissionInfo> mMissionList;  //任务列表

    public int getWiner() {
        return winer;
    }

    public void setWiner(int winer) {
        this.winer = winer;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }
}
