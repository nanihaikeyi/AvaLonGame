package Utils;

import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/4.
 */
public class SoundUtils {
    public static HashMap<Integer, Integer> musicId = new HashMap<Integer, Integer>();
    public static SoundPool mSoundPool = new SoundPool(12, 0, 5);
    public static void playSound(SoundPool soundPool, int id) {
        soundPool.play(id, 1, 1, 0, 0, 1);
    }
}
