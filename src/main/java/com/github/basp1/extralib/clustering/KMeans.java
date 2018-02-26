package com.github.basp1.extralib.clustering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMeans {
    private int numClusters;
    private int maxSteps;

    public KMeans(int numClusters, int maxSteps) {
        this.numClusters = numClusters;
        this.maxSteps = maxSteps;
    }

    public Summary Cluster(List<double[]> data) {
        int arity = data.get(0).length;
        Integer[] clusters = new Integer[data.size()];
        ArrayList<double[]> centroids = new ArrayList<>();

        int[] group = new int[numClusters];
        int step = 0;

        for (int i = 0; i < data.size(); i++) {
            clusters[i] = 0;
        }

        boolean uniqueCentroid;
        for (int c = 0; c < numClusters; c++) {
            centroids.add(new double[arity]);

            int r = c;
            do {
                /// loop to ensure new centroid is unique
                System.arraycopy(data.get(r), 0, centroids.get(c), 0, arity);

                r = r + 1;
                uniqueCentroid = true;

                for (int c2 = 0; c2 < c; c2++) {
                    /// loop Through Record Dimensions and check if all are the same
                    double x = 0;
                    double y = 0;
                    for (int d2 = 0; d2 < arity; d2++) {
                        x += Math.pow(centroids.get(c)[d2], 2);
                        y += Math.pow(centroids.get(c2)[d2], 2);
                    }

                    uniqueCentroid = !(Math.sqrt(x) == Math.sqrt(y));
                    if (!uniqueCentroid) {
                        break;
                    }
                }
            }
            while (!uniqueCentroid && r < numClusters);
        }

        /// calculate distances from centroids
        double lowestDistance;
        int lastCluster;
        boolean clustersStable = false;
        while (!clustersStable && step < maxSteps) {
            clustersStable = true; // until Proved otherwise

            for (int r = 0; r < data.size(); r++) {

                lastCluster = clusters[r];
                lowestDistance = 0; // Reset lowest distance

                /// loop through record distances to centroids
                for (int c = 0; c < centroids.size(); c++) {
                    /// calculate elucidean distance
                    double x = 0;
                    for (int d = 0; d < arity; d++) {
                        double y = data.get(r)[d] - centroids.get(c)[d];
                        x += y * y;
                    }

                    x = Math.sqrt(x);

                    if (c == 0 || x < lowestDistance) {
                        lowestDistance = x;
                        clusters[r] = c;
                    }
                }

                /// only change if true
                if (clustersStable) {
                    clustersStable = clusters[r] == lastCluster;
                }

            }

            /// move centroids to calculated cluster average
            for (int c = 0; c < centroids.size(); c++) {
                for (int d = 0; d < arity; d++) {
                    group[c] = 0; // reset number of records in cluster
                    centroids.get(c)[d] = 0; // reset centroid dimensions

                    for (int r = 0; r < data.size(); r++) {
                        if (clusters[r] == c) {
                            group[c] += 1;
                            centroids.get(c)[d] += data.get(r)[d];
                        }
                    }

                    // assign average dimension distance
                    centroids.get(c)[d] /= (group[c]);
                }
            }

            step += 1;
        }

        return new Summary(new ArrayList<Integer>(Arrays.asList(clusters)), centroids);
    }
}