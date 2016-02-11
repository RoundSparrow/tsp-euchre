package euchre.game;

/**
 * Created by adminsag on 2/9/16.
 */
public class GameLog {
    public static void outInformation(String tag, String logMessage) {
        System.out.println(tag + " I: " + logMessage);
        //android.util.Log.i(tag, logMessage);
    }

    public static void outError(String tag, String logMessage) {
        System.out.println(tag + " ERR: " + logMessage);
        //android.util.Log.e(tag, logMessage);
    }

    public static void outWarning(String tag, String logMessage) {
        System.out.println(tag + " W: " + logMessage);
        //android.util.Log.w(tag, logMessage);
    }
}
