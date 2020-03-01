package BattleBoats;


import java.util.ArrayList;

public class Simulator extends Thread {

    private Board gameBoard = null;
    private ArrayList<Soul> souls = null;
    private int maxGenerations = 2;
    private int threadsFinishedNextValueCalculation = 0;
    private int threadsFinishedNextValueSet = 0;


    public Simulator(final Board board) {
        this.gameBoard = board;
    }

    public final void setMaxGenerations(final int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    public final void runSimulation() {
        setSoulsSimulator();
        startCellThreads();
        computeGenerations();
    }

    private void setSoulsSimulator() {
        souls = (ArrayList<Soul>) gameBoard.getSouls();
        for (Soul soul : souls) {
            soul.setSimulator(this);
        }
    }

    private void startCellThreads() {
        for (Soul soul : souls) {
            soul.start();
        }
    }

    private synchronized void computeGenerations() {
        for (int i = 1; i <= maxGenerations; i++) {
            waitNextGenerationValueCalculations();
            wakeUpCellThreads();
            waitNextGenerationValueSets();

            printGeneration(i);

            if (i < maxGenerations) {
                wakeUpCellThreads();
            }
        }

        stopThreads();
        wakeUpCellThreads();
    }

    private void waitNextGenerationValueCalculations() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitNextGenerationValueSets() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void wakeUpCellThreads() {
        for (Soul soul : souls) {
            soul.continueGame();
        }
    }

    private void printGeneration(int generationNumber) {
        System.out.println("**"+"Gen" + " " + generationNumber + "**");
        gameBoard.printBoard();
    }

    private void stopThreads() {
        for (Soul soul : souls) {
            soul.stopGame();
        }
    }

    public final synchronized void threadFinishedNextValueCalculation() {
        threadsFinishedNextValueCalculation++;

        if (threadsFinishedNextValueCalculation == souls.size()) {
            threadsFinishedNextValueCalculation = 0;
            notify();
        }
    }

    public final synchronized void threadFinishedNextValueSet() {
        threadsFinishedNextValueSet++;
        if (threadsFinishedNextValueSet == souls.size()) {
            threadsFinishedNextValueSet = 0;
            notify();
        }
    }

}