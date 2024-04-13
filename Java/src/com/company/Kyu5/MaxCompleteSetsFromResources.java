package com.company.Kyu5;

import java.util.Map;

public class MaxCompleteSetsFromResources {
    public static int countMaxSets(Map<String, Integer> requirements, Map<String, Integer> availableResources) {
        int maxSets = Integer.MAX_VALUE;
        for (String key: requirements.keySet()) {
            if (!availableResources.containsKey(key)) return 0;

            maxSets = getNewMinSet(requirements, availableResources, maxSets, key);
        }

        return maxSets;
    }

    private static int getNewMinSet(Map<String, Integer> requirements, Map<String, Integer> availableResources, int maxSets, String key) {
        maxSets = Integer.min(maxSets, availableResources.get(key) / requirements.get(key));
        return maxSets;
    }
}
