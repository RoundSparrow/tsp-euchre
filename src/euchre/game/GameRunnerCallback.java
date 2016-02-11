package euchre.game;

import javax.swing.JFrame;

/**
 * Created by adminsag on 2/9/16.
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
}
