package euchre.game;

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

/**
 * Created by adminsag on 2/9/16.
 * @author Stephen A. Gutknecht
 */
public class GameRunnerCallback {
    public static void createLocalGamePartTwo() {
        //GameRunner.getMasterGame().createLocalGamePartTwo();
    }

    public static void setDialogJFrame(JFrame jFrame, String callerNote)
    {
        //javax.swing.AndroidBridge.setDialogJFrame(jFrame);
    }

    public static void registerPlayerClient(int numConnectedClients, String registryInfo) {
        GameLog.outInformation("EP", "Register Player Client number " + numConnectedClients + " info: " + registryInfo);
    }

    public static void allPlayersConnectedToHost() {
        GameLog.outInformation("EP", "Callback, we have all players ready as server!");
    }


    public static AtomicInteger artificialIntelligencePlayerID = new AtomicInteger(12000);

    public static int getArtificialIntelligenceNextPlayerID()
    {
        return (int)(Math.random()*5000000);
        // ToDo: this is not working? Causes a loop; maybe the logic assumes that all 4 jar instances use random numbers (16 in total) pre-registration?
        // Yes, review of activity seems that each client independent creates it's id - so a means of ensuring no duplicates
        // return artificialIntelligencePlayerID.incrementAndGet();
    }

    public static boolean settingShowGameBoardForAI() {
        return true;
    }

    public static boolean applicationExitBehaviorForSituation(int inSituationCode) {
        switch (inSituationCode)
        {
            case 1000:   // Server socket drop
                return true;
            case 2000:   // AI game logic bug
                return true;
            default:
                return true;
        }
    }

    public static boolean applicationExitNow(int inSituationCode) {
        switch (inSituationCode)
        {
            case 1000:   // Server socket drop
            case 2000:   // AI game logic bug
                System.exit(0);
            default:
                return false;
        }
    }
}
