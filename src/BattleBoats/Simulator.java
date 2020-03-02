package BattleBoats;
// Potentially for fake player currently configured for the Game Of Life so it will need to be reprogrammed to randomly
// select a board cell and fire without using one it has already taken. To start with then you can enrich with an algorithm
// to check in logical vertical and horizontal placement of a boat

import java.util.ArrayList;

public class Simulator extends Thread {

    private Board gameBoard = null;
    private ArrayList<Cell> Cells = null;
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
        setCellsSimulator();
        startCellThreads();
        computeGenerations();
    }

    private void setCellsSimulator() {
        Cells = (ArrayList<Cell>) gameBoard.getCells();
        for (Cell Cell : Cells) {
            Cell.setSimulator(this);
        }
    }

    private void startCellThreads() {
        for (Cell Cell : Cells) {
            Cell.start();
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
        for (Cell Cell : Cells) {
            Cell.continueGame();
        }
    }

    private void printGeneration(int generationNumber) {
        System.out.println("**"+"Gen" + " " + generationNumber + "**");
        gameBoard.printBoard();
    }

    private void stopThreads() {
        for (Cell Cell : Cells) {
            Cell.stopGame();
        }
    }

    public final synchronized void threadFinishedNextValueCalculation() {
        threadsFinishedNextValueCalculation++;

        if (threadsFinishedNextValueCalculation == Cells.size()) {
            threadsFinishedNextValueCalculation = 0;
            notify();
        }
    }

    public final synchronized void threadFinishedNextValueSet() {
        threadsFinishedNextValueSet++;
        if (threadsFinishedNextValueSet == Cells.size()) {
            threadsFinishedNextValueSet = 0;
            notify();
        }
    }

}