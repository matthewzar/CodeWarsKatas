package test.Kyu4;

import com.company.Kyu4.IntegerPartitions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IntegerPartitionsTest {

//    @Test
//    void part() {
//    }
@Test
public void givenOneShouldReturnLengthOnePartition() {
    List<List<Integer>> expectedPartition = new ArrayList<>(List.of(
            new ArrayList<>(List.of(1))
    ));
    List<List<Integer>> result = IntegerPartitions.generatePartitions(1);
    assertEquals(sortListOfLists(expectedPartition), sortListOfLists(result), "The partitions should be equal after sorting.");
}

    @Test
    public void givenTwoShouldReturnTwoPartitions() {
        List<List<Integer>> expectedPartition = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 1)),
                new ArrayList<>(List.of(2))
        ));
        List<List<Integer>> result = IntegerPartitions.generatePartitions(2);
        assertEquals(sortListOfLists(expectedPartition), sortListOfLists(result), "The partitions should be equal after sorting.");
    }

    @Test
    public void givenThreeShouldReturnThreePartitions() {
        List<List<Integer>> expectedPartition = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 1, 1)),
                new ArrayList<>(List.of(1, 2)),
                new ArrayList<>(List.of(3))
        ));
        List<List<Integer>> result = IntegerPartitions.generatePartitions(3);
        assertEquals(sortListOfLists(expectedPartition), sortListOfLists(result), "The partitions should be equal after sorting.");
    }

    @Test
    public void givenFourShouldReturnFivePartitions() {
        List<List<Integer>> expectedPartition = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 1, 1, 1)),
                new ArrayList<>(List.of(1, 1, 2)),
                new ArrayList<>(List.of(2, 2)),
                new ArrayList<>(List.of(1, 3)),
                new ArrayList<>(List.of(4))
        ));
        List<List<Integer>> result = IntegerPartitions.generatePartitions(4);
        assertEquals(sortListOfLists(expectedPartition), sortListOfLists(result), "The partitions should be equal after sorting.");
    }

    private List<List<Integer>> sortListOfLists(List<List<Integer>> listOfLists) {
        for (List<Integer> sublist : listOfLists) {
            Collections.sort(sublist);  // Sort each inner list
        }
        listOfLists.sort(Comparator.comparing(Object::toString));  // Sort the list of lists by their string representation
        return listOfLists;
    }
}
