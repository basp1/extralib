package com.github.basp1.extralib.clustering;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    private final List<Integer> clusterMemberships;
    private final List<double[]> centroids;

    public Summary(List<Integer> clusterMemberships, List<double[]> centroids) {
        this.clusterMemberships = new ArrayList<>(clusterMemberships);
        this.centroids = new ArrayList<>(centroids);
    }

    public List<Integer> getClusterMemberships() {
        return clusterMemberships;
    }

    public List<double[]> getCentroids() {
        return centroids;
    }
}