package test.Kyu5;

import com.company.Kyu5.MaxCompleteSetsFromResources;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MaxCompleteSetsFromResourcesTest {
    @Test
    public void testNoResourcesReturnsZero() {
        Map<String, Integer> requirements = Map.of(
                "flour", 500,
                "sugar", 200,
                "eggs", 1);
        Map<String, Integer> resources = Map.of();
        assertEquals(0, MaxCompleteSetsFromResources.countMaxSets(requirements, resources));
    }

    @Test
    public void testOneToOneFlourReturnOne() {
        Map<String, Integer> requirements = Map.of("flour", 500);
        Map<String, Integer> resources = Map.of("flour", 500);
        assertEquals(1, MaxCompleteSetsFromResources.countMaxSets(requirements, resources));
    }

    @Test
    public void testTwoToOneSugarReturnOne() {
        Map<String, Integer> requirements = Map.of("sugar", 500);
        Map<String, Integer> resources = Map.of("sugar", 1000);
        assertEquals(2, MaxCompleteSetsFromResources.countMaxSets(requirements, resources));
    }

    @Test
    public void testTwoResourceTypesMixedRatio() {
        Map<String, Integer> requirements = Map.of(
                "flour", 500,
                "sugar", 1000);
        Map<String, Integer> resources = Map.of(
                        "flour", 1000,
                        "sugar", 1000);
        assertEquals(1, MaxCompleteSetsFromResources.countMaxSets(requirements, resources));
    }

    @Test void testImperfectDivisions() {

        Map<String, Integer> requirements = Map.of(
                "flour", 550,
                "sugar", 1000);
        Map<String, Integer> resources = Map.of(
                "flour", 1000,
                "sugar", 1100);
        assertEquals(1, MaxCompleteSetsFromResources.countMaxSets(requirements, resources));
    }
}