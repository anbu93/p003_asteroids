package com.vova_cons.hundread_games.tds.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtils {
    public static final Random random = new Random(System.currentTimeMillis());

    public static <T> T randomOne(List<T> values) {
        return randomOne(values, random);
    }

    public static <T> T randomOne(List<T> values, Random random) {
        if (values.isEmpty()) {
            return null;
        }
        int index = random.nextInt(values.size());
        return values.get(index);
    }

    public static <T> T randomOne(T[] values) {
        return randomOne(values, random);
    }

    public static <T> T randomOne(T[] values, Random random) {
        if (values.length == 0) {
            return null;
        }
        int index = random.nextInt(values.length);
        return values[index];
    }

    public static <T> T randomOne(T[] values, T... excluded) {
        return randomOne(values, random, excluded);
    }

    public static <T> T randomOne(T[] values, Random random, T... excluded) {
        int size = values.length - excluded.length;
        if (size <= 0) {
            return null;
        }
        int index = random.nextInt(size);
        while(isExcluded(values[index], excluded)) {
            index++;
        }
        return values[index];
    }

    private static <T> boolean isExcluded(T val, T... vals) {
        for(T v : vals) {
            if (v == val) {
                return true;
            }
        }
        return false;
    }

    public static <T> List<T> randomCount(List<T> values, int count) {
        return randomCount(values, count, random);
    }

    private static <T> List<T> randomCount(List<T> values, int count, Random random) {
        if (values.size() <= count) {
            return new LinkedList<T>(values);
        }
        List<T> result = new LinkedList<T>();
        List<T> last = new LinkedList<T>(values);
        for(int i = 0; i < count; i++) {
            T value = RandomUtils.randomOne(last, random);
            last.remove(value);
            result.add(value);
        }
        return result;
    }

    public static int randomInRange(int from, int to) {
        return randomInRange(from, to, random);
    }

    private static int randomInRange(int from, int to, Random random) {
        int min = Math.min(from, to);
        int max = Math.max(from, to);
        if (max - min == 1) {
            return max;
        }
        int delta = max - min + 1;
        return min + random.nextInt(delta);
    }

    public static long randomInRange(long from, long to) {
        return randomInRange(from, to, random);
    }

    private static long randomInRange(long from, long to, Random random) {
        long min = Math.min(from, to);
        long max = Math.max(from, to);
        if (max - min == 1) {
            return max;
        }
        long delta = max - min + 1;
        return min + random.nextLong() % delta;
    }

    public static boolean randomChance(float chance) {
        return randomChance(chance, random);
    }

    public static boolean randomChance(float chance, Random random) {
        return random.nextFloat() <= chance;
    }

    /**
     * Chooses one choice, based on chances
     * @param chanceWeights - determines weight of a chance. For example 0.25f, 0.75f. Or 50.0f, 50.0f.
     * @return integer number, from 0 to (length of chanceWeights) - 1.
     */
    public static int randomChoice(Float... chanceWeights) {
        return randomChoice(random, chanceWeights);
    }

    public static int randomChoice(List<Float> chanceWeights) {
        return randomChoice(random, chanceWeights);
    }

    public static int randomChoice(Random random, Float... chanceWeights) {
        return randomChoice(random, new ArrayList<Float>(Arrays.asList(chanceWeights)));
    }

    public static int randomChoice(Random random, List<Float> chanceWeights) {
        float randomValue = random.nextFloat();
        List<Float> normalizedChoices = normalizeChoices(chanceWeights);
        float curChanceEdge = 0.0f;
        for (int i = 0; i < normalizedChoices.size(); i++) {
            float chance = normalizedChoices.get(i);
            curChanceEdge += chance;
            if (chance == 0.0f) {
                continue;
            }
            if (randomValue < curChanceEdge) {
                return i;
            }
        }
        return normalizedChoices.size() - 1;
    }

    private static List<Float> normalizeChoices(List<Float> chanceWeights) {
        float sum = floatSum(chanceWeights);
        List<Float> result = new LinkedList<Float>();
        for (float chanceWight: chanceWeights) {
            result.add(chanceWight / sum);
        }
        return result;
    }

    private static float floatSum(List<Float> floats) {
        float result = 0;
        for (float f: floats) {
            result += f;
        }
        return result;
    }

    public static <T> List<T> random(List<T> collection, int minCount, int maxCount){
        List<T> result = new LinkedList<T>();
        int count = minCount + random.nextInt(maxCount - minCount + 1);
        if (count >= collection.size()){
            return collection;
        }
        Set<Integer> indexSet = new HashSet<Integer>();
        for(int i = 0; i < count; i++) {
            int value = random.nextInt(collection.size());
            while(indexSet.contains(value)){
                value = random.nextInt(collection.size());
            }
            indexSet.add(value);
            result.add(collection.get(value));
        }
        return result;
    }

    public static <T> List<T> random(List<T> collection, int count){
        List<T> result = new LinkedList<T>();
        if (count >= collection.size()){
            return collection;
        }
        Set<Integer> indexSet = new HashSet<Integer>();
        for(int i = 0; i < count; i++) {
            int value = random.nextInt(collection.size());
            while(indexSet.contains(value)){
                value = random.nextInt(collection.size());
            }
            indexSet.add(value);
            result.add(collection.get(value));
        }
        return result;
    }

    public static int random(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
}