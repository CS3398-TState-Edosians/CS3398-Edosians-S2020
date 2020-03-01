package BattleBoats;

import java.io.IOException;

public class BattleBoats {

    private static BattleBoats instanceOfThis = null;
    private GameSession gameSession = null;

    public static final BattleBoats getInstance() {
        if (instanceOfThis == null) {
            instanceOfThis = new BattleBoats();
        }
        return instanceOfThis;
    }

    public void run() throws IOException {
        gameSession = new GameSession();
        gameSession.startSimulation();
    }

}
