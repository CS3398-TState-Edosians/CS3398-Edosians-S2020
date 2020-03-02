package BattleBoats;

public class Cell extends Thread {
    private int value = 0;
    private int row = 0;
    private int column = 0;
    private int newOutcome = -1;
    private Board board = null;
    private Simulator simulator = null;

    public Cell(final Board board, final int row, final int column, final int value) {
        super("Cell Thread");
        this.board = board;
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public final void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public final void run() {
        while (!isInterrupted()) {
            playGame();
        }
    }

    private final synchronized void playGame() {
        calculateOutcome();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setOutcomeValue();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final synchronized void continueGame() {
        notify();
    }

    public final synchronized void stopGame() {
        notify();
        interrupt();
    }

    private void calculateOutcome() {
        newOutcome = getNewOutcome();
        simulator.threadFinishedNextValueCalculation();
    }

    public final int getNewOutcome() {
        if (value == 1) {
            return getOutcomeForOne();
        } else {

            return getOutcomeForZero();
        }
    }

    private int getOutcomeForOne() {
        // 1 becomes 0 if less than 2 or greater than 3 neighbours are 1 valued
        int neighborsAmount = getNumberOfNeighborsWithValue(1);
        if (neighborsAmount < 2 || neighborsAmount > 3) {
            return 0;
        }

        return 1;
    }

    private int getOutcomeForZero() {
        // 0 becomes 1 if exactly 3 neighbours are valued 1.
        int neighborsAmount = getNumberOfNeighborsWithValue(1);
        if (neighborsAmount == 3) {
            return 1;
        }

        return 0;
    }

    /** Returns the number of neighbors that have the given neighbourValue. */
    private int getNumberOfNeighborsWithValue(int neighborValue) {
        int amount = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                Cell neighbor = board.getCellAtPosition(i, j);

                if (neighbor == this) {
                    continue; // Do not count this.
                }

                if (neighbor != null) {
                    if (neighbor.getValue() == neighborValue) {
                        amount++;
                    }
                }
            }
        }

        return amount;
    }

    private final void setOutcomeValue() {
        value = newOutcome;
        newOutcome = -1;
        simulator.threadFinishedNextValueSet();
    }

    public final int getValue() {
        return value;
    }

}
