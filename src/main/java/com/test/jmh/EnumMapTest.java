package com.test.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 对比测试两种HashMap与EnumMap法的性能
 * <p>
 * 结果相差一倍
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@State(Scope.Benchmark)
public class EnumMapTest {

    private EnumMap<TestEnum, Integer> enumMap;
    private HashMap<TestEnum, Integer> hashEnumKeyMap;
    private HashMap<String, Integer> hashStringKeyMap;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(EnumMapTest.class.getSimpleName()).forks(2).build();
        new Runner(options).run();
    }

    @Setup(Level.Trial)
    public void setup() {

        hashStringKeyMap = new HashMap<String, Integer>();
        initStringKeyMap(hashStringKeyMap);

        hashEnumKeyMap = new HashMap<TestEnum, Integer>();
        initEnumKeyMap(hashEnumKeyMap);

        enumMap = new EnumMap<TestEnum, Integer>(TestEnum.class);
        initEnumKeyMap(enumMap);
    }

    private void initEnumKeyMap(Map<TestEnum, Integer> map) {
        map.put(TestEnum.ONE, new Integer(1));
        map.put(TestEnum.TWO, new Integer(2));
        map.put(TestEnum.FOUR, new Integer(4));
        map.put(TestEnum.SIX, new Integer(6));
        map.put(TestEnum.SEVEN, new Integer(7));
    }

    private void initStringKeyMap(Map<String, Integer> map) {
        map.put("ONE", new Integer(1));
        map.put("TWO", new Integer(2));
        map.put("FOUR", new Integer(4));
        map.put("SIX", new Integer(6));
        map.put("SEVEN", new Integer(7));
    }

    private int caculateEnumKeyMap(Map<TestEnum, Integer> map) {
        int result = 0;
        result += map.get(TestEnum.ONE);
        result += map.get(TestEnum.TWO);
        result += map.get(TestEnum.FOUR);
        result += map.get(TestEnum.SIX);
        return result;
    }

    private Integer caculateStringKeyMap(HashMap<String, Integer> map) {
        int result = 0;
        result += map.get("ONE");
        result += map.get("TWO");
        result += map.get("FOUR");
        result += map.get("SIX");
        return result;
    }

    @Benchmark
    @Fork(value = 1)
    public Integer hashMapWithStringKey() {
        return caculateStringKeyMap(hashStringKeyMap);
    }

    @Benchmark
    @Fork(value = 1)
    public Integer hashMapWithEnumKey() {
        return caculateEnumKeyMap(hashEnumKeyMap);
    }

    @Benchmark
    @Fork(value = 1)
    public Integer enumMap() {
        return caculateEnumKeyMap(enumMap);
    }

    public enum TestEnum {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
    }

}
