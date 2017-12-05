package ru.spbau.mit.tukh.streams;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        List<String> paths = new ArrayList<>(Arrays.asList("src/test/resources/input.txt",
                "src/test/resources/output.txt"));
        List<String> ans = new ArrayList<>(Arrays.asList("abacaba", "abacabadabacaba", "abacabadabacaba"));
        assertEquals(ans, SecondPartTasks.findQuotes(paths, "abacaba"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, SecondPartTasks.piDividedBy4(), 1e-2);
    }

    @Test
    public void testFindPrinter() {
        //Just funny :)
        Map<String, List<String>> compositions = new HashMap<>();
        String[] DK = new String[]{"Volume 1 – Fundamental Algorithms", "Volume 2 – Seminumerical Algorithms",
                "Volume 3 – Sorting and Searching", "Volume 4A – Combinatorial Algorithms"};
        String[] AP = new String[]{"Ruslan i Ludmila", "Cawcazskiy plennik", "Boris Godunov", "Arap Petra Velikogo",
                "Pikovaa dama", "Capitanskaa dochka", "Dubrovsky", "Poltava", "Metel", "Vystrel", "Baryshnya-krestianka"};
        String[] N = new String[]{"Nothing"};


        compositions.put("Alexander Pushkin", new ArrayList<>(Arrays.asList(AP)));
        assertEquals("Alexander Pushkin", SecondPartTasks.findPrinter(compositions));
        compositions.put("Donald Knuth", new ArrayList<>(Arrays.asList(DK)));
        assertEquals("Alexander Pushkin", SecondPartTasks.findPrinter(compositions));
        compositions.remove("Alexander Pushkin");
        compositions.remove("Donald Knuth");
        compositions.put("Nobody", new ArrayList<>(Arrays.asList(N)));
        assertEquals("Nobody", SecondPartTasks.findPrinter(compositions));
        compositions.put("Donald Knuth", new ArrayList<>(Arrays.asList(DK)));
        assertEquals("Donald Knuth", SecondPartTasks.findPrinter(compositions));
        compositions.put("Alexander Pushkin", new ArrayList<>(Arrays.asList(AP)));
        assertEquals("Alexander Pushkin", SecondPartTasks.findPrinter(compositions));
    }

    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> items = new ArrayList<>();

        Map<String, Integer> requests1 = new HashMap<>();
        requests1.put("1", 10);
        requests1.put("2", 20);

        Map<String, Integer> requests2 = new HashMap<>();
        requests2.put("1", 30);
        requests2.put("3", 15);

        Map<String, Integer> requests3 = new HashMap<>();
        requests3.put("2", 10);
        requests3.put("3", 10);
        requests3.put("4", 10);

        items.add(requests1);
        items.add(requests2);
        items.add(requests3);

        Map<String, Integer> res = new HashMap<>();
        res.put("1", 40);
        res.put("2", 30);
        res.put("3", 25);
        res.put("4", 10);

        assertEquals(res, SecondPartTasks.calculateGlobalOrder(items));
    }
}