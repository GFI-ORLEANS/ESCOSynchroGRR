package fr.recia.grr.utils;

public class DateBatch {
    private String lastBatch;
    private String currentBatch;

    public DateBatch(String lastBatch, String currentBatch) {
        this.lastBatch = lastBatch;
        this.currentBatch = currentBatch;
    }

    public String getLastBatch() {
        return lastBatch;
    }

    public String getCurrentBatch() {
        return currentBatch;
    }
}
