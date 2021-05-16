package com.company.Kyu3;

import java.util.Arrays;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ArraySizeAnalyzer {

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

    public static int[] smaller(int[] arr)
    {
        // list of relative position of original list
        int[] posarr = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            posarr[i] = i;
        }

        // dictionary of counts of smaller element
        int[] cntdict = new int[arr.length];

        countSort(arr, posarr, cntdict);
        var solution = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            solution[i] = cntdict[i];
        }
        return solution;
    }

    private static void countSort(int[] arr, int[] cntarr, int[] cntdict)
    {
        if(arr.length <= 1) return;

        var mid = arr.length/2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] leftCount = Arrays.copyOfRange(cntarr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        int[] rightCount = Arrays.copyOfRange(cntarr, mid, cntarr.length);

        countSort(left, leftCount, cntdict);
        countSort(right, rightCount, cntdict);

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
                    cntdict[temp] = cntdict[temp] + 1;
                }

                // updates position of indices wrt original position
                cntarr[k] = rightCount[j];
                j++;
            } else{
                arr[k] = left[i];
                cntarr[k] = leftCount[i];
                i++;
            }
            k++;
        }

        while(i < left.length){
            arr[k] = left[i];
            cntarr[k] = leftCount[i];
            i++;
            k++;
        }
        while(j < right.length){
            arr[k] = right[j];
            cntarr[k] = rightCount[j];
            j++;
            k++;
        }
    }

}