package Utils;

import java.util.ArrayList;

import model.MissionInfo;
import model.RoundInfo;

/**
 * Created by Administrator on 2016/2/9.
 */
public class MissionUtils {

    public static ArrayList<MissionInfo> creatMission(int heroNum, RoundInfo roundInfo) {
        ArrayList<MissionInfo> missionList = roundInfo.mMissionList;
        switch (heroNum) {
            case 5:
                missionList.add(new MissionInfo(2, 1));
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(2, 1));
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(3, 1));
                break;
            case 6:
                missionList.add(new MissionInfo(2, 1));
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(4, 1));
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(4, 1));
                break;
            case 7:

                missionList.add(new MissionInfo(2, 1));
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(4, 2));
                missionList.add(new MissionInfo(4, 1));

                break;
            case 8:
            case 9:
            case 10:
                missionList.add(new MissionInfo(3, 1));
                missionList.add(new MissionInfo(4, 1));
                missionList.add(new MissionInfo(4, 1));
                missionList.add(new MissionInfo(5, 2));
                missionList.add(new MissionInfo(5, 1));
                break;
        }
        return missionList;
    }
}
