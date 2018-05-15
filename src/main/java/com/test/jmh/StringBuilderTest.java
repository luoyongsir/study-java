package com.test.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 字符串拼接性能测试
 *
 * @author luoyong
 * @date 2018/5/14
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2)
@Threads(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 6, time = 3, timeUnit = TimeUnit.SECONDS)
public class StringBuilderTest {

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder().include(StringBuilderTest.class.getSimpleName()).forks(2).build();
		new Runner(options).run();
	}

	@Benchmark
	public void testStringAdd() {
		String a = "";
		for (int i = 0; i < 10; i++) {
			a += i;
		}
	}

	@Benchmark
	public void testStringBuilderAdd() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(i);
		}
	}

}
