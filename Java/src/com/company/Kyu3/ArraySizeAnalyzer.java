package com.company.Kyu3;

import java.util.Arrays;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ArraySizeAnalyzer {

    ///

    ///


    /**
     * Counts how many elements to the left of a given index are smaller than that indexe's value.
     * EG: [1, 5, 4, 5, 0] --> [1, 2, 1, 0, 0]
     * Note that this implementation is naive, and does not deal with larger lists quickly.
     * @param testData The array being analysed
     * @return
     */
    public int[] countSmaller(int[] testData) {
        var result = new int[testData.length];

        for (int i = result.length-1; i >= 0; i--) {
            for (int j = i + 1; j < result.length; j++) {
                // This is a small optimisation that tries to short-circuit things.
                if(testData[i] == testData[j])
                {
                    result[i] += result[j];
                    break;
                }

                if(testData[j] < testData[i])
                    result[i]++;
            }
        }

        return result;
    }

    ///

    ///


    /**
     * As with countSmaller() - except this version uses a modified Merge Sort to speed things up.
     * It is still not fast enough to deal with arrays in the range of 250,000 in a timely manner.
     *
     * A different data structure will probably be required.
     * @param arr The array being analysed
     * @return
     */
    public static int[] smaller(int[] arr)
    {
        // list of relative position of original list
        int[] positions = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            positions[i] = i;
        }

        // dictionary of counts of smaller element
        int[] cntdict = new int[arr.length];

        countSort(arr, positions, cntdict);
        var solution = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            solution[i] = cntdict[i];
        }
        return solution;
    }

    private static void countSort(int[] arr, int[] countArray, int[] finalCounts)
    {
        if(arr.length <= 1) return;

        var mid = arr.length/2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] leftCount = Arrays.copyOfRange(countArray, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        int[] rightCount = Arrays.copyOfRange(countArray, mid, countArray.length);

        countSort(left, leftCount, finalCounts);
        countSort(right, rightCount, finalCounts);

        var i = 0;
        var j = 0;
        var k = 0;
        while(i < left.length && j < right.length)
        {
            if(left[i] > right[j]) {
                arr[k] = right[j];

                // Increase count of smaller eleme
                for(int n = i; n < left.length; n++)
                {
                    var temp = leftCount[n];
                    finalCounts[temp] = finalCounts[temp] + 1;
                }

                // updates position of indices wrt original position
                countArray[k] = rightCount[j];
                j++;
            } else{
                arr[k] = left[i];
                countArray[k] = leftCount[i];
                i++;
            }
            k++;
        }

        while(i < left.length){
            arr[k] = left[i];
            countArray[k] = leftCount[i];
            i++;
            k++;
        }
        while(j < right.length){
            arr[k] = right[j];
            countArray[k] = rightCount[j];
            j++;
            k++;
        }
    }

}