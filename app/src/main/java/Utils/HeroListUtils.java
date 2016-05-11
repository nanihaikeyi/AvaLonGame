package Utils;

import java.util.ArrayList;

import model.HeroInfo;
import avalon.king.com.activity.R;

/**
 * Created by Administrator on 2016/2/5.
 */
public class HeroListUtils {
    public static ArrayList<HeroInfo> mHeroDataList = new ArrayList<HeroInfo>();

    public static HeroInfo creatHero(int heroId) {
        HeroInfo heroInfo = new HeroInfo();
        switch (heroId) {
            case 1:
                heroInfo.setName(R.string.meilin);
                heroInfo.setPositive(1);
                heroInfo.setImgId(R.mipmap.meilin);
                break;
            case 2:
                heroInfo.setName(R.string.percival);
                heroInfo.setPositive(1);
                heroInfo.setImgId(R.mipmap.percival);
                break;
            case 3:
                heroInfo.setName(R.string.defender);
                heroInfo.setPositive(1);
                heroInfo.setImgId(R.mipmap.defender);
                break;
            case 4:
                heroInfo.setName(R.string.assassin);
                heroInfo.setPositive(-1);
                heroInfo.setImgId(R.mipmap.assassin);
                break;
            case 5:
                heroInfo.setName(R.string.moganna);
                heroInfo.setPositive(-1);
                heroInfo.setImgId(R.mipmap.moganna);
                break;
            case 6:
                heroInfo.setName(R.string.oberon);
                heroInfo.setPositive(-1);
                heroInfo.setImgId(R.mipmap.obern);
                break;
            case 7:
                heroInfo.setName(R.string.mordred);
                heroInfo.setPositive(-1);
                heroInfo.setImgId(R.mipmap.moder);
                break;
            case 8:
                heroInfo.setName(R.string.pawn);
                heroInfo.setImgId(R.mipmap.pawn);
                heroInfo.setPositive(-1);
                break;
        }
        return heroInfo;
    }

    public static ArrayList<HeroInfo> getHeroIdList(int heroNum, boolean hasModer) {
        ArrayList<HeroInfo> heroInfoList = new ArrayList<>();
        ArrayList<Integer> heroIdList = new ArrayList<>();
        switch (heroNum) {
            case 5:
                heroIdList.add(1);
                heroIdList.add(2);

                heroIdList.add(3);

                heroIdList.add(4);
                heroIdList.add(5);
                heroInfoList = creatHeroInfoList(heroIdList);
                break;
            case 6:
                heroIdList.add(1);
                heroIdList.add(2);

                heroIdList.add(3);
                heroIdList.add(3);

                heroIdList.add(4);
                heroIdList.add(5);
                heroInfoList = creatHeroInfoList(heroIdList);
                break;
            case 7:
                heroIdList.add(1);
                heroIdList.add(2);

                heroIdList.add(3);
                heroIdList.add(3);

                heroIdList.add(4);
                heroIdList.add(5);

                heroIdList.add(6);
                heroInfoList = creatHeroInfoList(heroIdList);
                break;
            case 8:
                heroIdList.add(1);
                heroIdList.add(2);

                heroIdList.add(3);
                heroIdList.add(3);
                heroIdList.add(3);

                heroIdList.add(4);
                heroIdList.add(5);

                heroIdList.add(8);
                heroInfoList = creatHeroInfoList(heroIdList);
                break;
            case 9:
                heroIdList.add(1);
                heroIdList.add(2);

                heroIdList.add(3);
                heroIdList.add(3);
                heroIdList.add(3);
                heroIdList.add(3);

                heroIdList.add(4);
                heroIdList.add(5);
                if (hasModer) {
                    heroIdList.add(7);
                } else {
                    heroIdList.add(8);
                }
                heroInfoList = creatHeroInfoList(heroIdList);
                break;
            case 10:
                heroIdList.add(1);
                heroIdList.add(2);

                heroIdList.add(3);
                heroIdList.add(3);
                heroIdList.add(3);
                heroIdList.add(3);

                heroIdList.add(4);
                heroIdList.add(5);

                heroIdList.add(6);

                if (hasModer) {
                    heroIdList.add(7);
                } else {
                    heroIdList.add(8);
                }
                heroInfoList = creatHeroInfoList(heroIdList);
                break;
        }
        return heroInfoList;
    }

    private static ArrayList<HeroInfo> creatHeroInfoList(ArrayList<Integer> heroIdList) {
        ArrayList<HeroInfo> heroInfoList = new ArrayList<>();
        for (int i = 0; i < heroIdList.size(); i++) {
            heroInfoList.add(creatHero(heroIdList.get(i)));
        }
        return heroInfoList;
    }

}
