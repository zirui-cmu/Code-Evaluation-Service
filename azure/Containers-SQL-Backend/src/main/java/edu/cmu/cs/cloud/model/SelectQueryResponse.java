package edu.cmu.cs.cloud.model;

import java.util.List;
import java.util.Map;

public class SelectQueryResponse {
    private List<Map<String, Object>> results;
    private String error;

    public SelectQueryResponse(List<Map<String, Object>> results) {
        this.results = results;
    }

    public SelectQueryResponse(String error) {
        this.error = error;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public String getError() {
        return error;
    }
}
