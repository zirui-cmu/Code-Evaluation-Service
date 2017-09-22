package edu.cmu.cs.cloud.model;

public class WriteQueryResponse {
    private boolean success;
    private int rowCount;
    private String error;

    public WriteQueryResponse(boolean success, int rowCount) {
        this.success = success;
        this.rowCount = rowCount;
    }

    public WriteQueryResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean getSuccess() {
        return success;
    }

    public int getRowCount() {
        return rowCount;
    }

    public String getError() {
        return error;
    }
}
