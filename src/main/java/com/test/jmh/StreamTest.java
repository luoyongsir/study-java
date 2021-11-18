package com.test.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * stream字符串拼接性能测试
 *
 * @author luoyong
 * @date 2019/6/11 16:28
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Threads(8)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 6, time = 3, timeUnit = TimeUnit.SECONDS)
public class StreamTest {

    private static String[] array = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static List<String> list = Arrays.asList(array);

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(StreamTest.class.getSimpleName()).forks(2).build();
        new Runner(options).run();
    }

    @Benchmark
    public void testStreamAdd() {
        String s = list.stream().collect(Collectors.joining(","));
    }

    @Benchmark
    public void testStringBuilderAdd() {
        final StringBuilder bud = new StringBuilder();
        // 是否拼接分隔符的标记
        boolean flag = false;
        for (Object o : list) {
            if (o != null) {
                if (flag) {
                    bud.append(",").append(o);
                } else {
                    bud.append(o);
                    flag = true;
                }
            }
        }
        String s = bud.toString();
    }

}
