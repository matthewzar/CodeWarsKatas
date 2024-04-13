package com.company.Kyu4;

import java.util.*;

// https://www.codewars.com/kata/55cf3b567fc0e02b0b00000b/train/java
public class IntegerPartitions {
    public static String part(long n) {
        List<Integer> products = getProductSetFromPartitions(generatePartitions(n));

        return String.format("Range: %d Average: %.2f Median: %.2f",
                range(products),
                average(products),
                median(products));
    }

    public static List<List<Integer>> generatePartitions(long n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), (int)n, 1 );
        return result;
    }

    private static List<List<Integer>> backtrack(List<List<Integer>> result,
                                                List<Integer> current,
                                                int remain, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return result;
        }

        for(int s = start; s <= remain; s++) {
            current.add(s);
            backtrack(result, current, remain - s, s);
            current.remove(current.size()-1);
        }

        return result;
    }

    public static List<Integer> getProductSetFromPartitions(List<List<Integer>> partitions) {
        Set<Integer> productSet = new HashSet<>();
        for (List<Integer> partition : partitions) {
            productSet.add(partitionProduct(partition));
        }
        List<Integer> sortedProducts = new ArrayList<>(productSet);
        Collections.sort(sortedProducts);
        return sortedProducts;
    }

    private static int partitionProduct(List<Integer> inputPartition) {
        int result = 1;
        for (int num: inputPartition) { result *= num; }
        return result;
    }

    private static double median(List<Integer> values) {
        int middle = values.size() / 2;
        if (values.size() % 2 == 1) {
            return values.get(middle);
        } else {
            return (values.get(middle - 1) + values.get(middle)) / 2.0;
        }
    }

    private static double average(List<Integer> values) {
        return values.stream()
                .mapToInt(Integer::intValue) // or just .mapToInt(x -> x)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("List cannot be empty"));
    }

    private static int range(List<Integer> values) {
        return values.get(values.size() - 1) - values.get(0);
    }
}

