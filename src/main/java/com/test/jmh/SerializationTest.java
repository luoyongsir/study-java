package com.test.jmh;

import com.alibaba.fastjson.JSON;
import com.test.utils.JacksonUtil;
import com.test.utils.ProtoStuffUtil;
import com.test.vo.BootPopup;
import com.test.vo.BuildUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 序列化性能测试<br/>
 *
 * 八线程测试结果：<br/>
 * Benchmark                          Mode  Cnt     Score     Error   Units
 * SerializationTest.testJackson     thrpt    8  2528.690 ±  31.759  ops/ms <br/>
 * SerializationTest.testFastJson    thrpt    8  4587.953 ±  22.959  ops/ms <br/>
 * SerializationTest.testProtoStuff  thrpt    8  6086.991 ± 100.611  ops/ms <br/>
 * 单线程测试结果：<br/>
 * Benchmark                          Mode  Cnt     Score    Error   Units <br/>
 * SerializationTest.testJackson     thrpt    8   631.153 ±  4.157  ops/ms <br/>
 * SerializationTest.testFastJson    thrpt    8  1173.026 ±  9.020  ops/ms <br/>
 * SerializationTest.testProtoStuff  thrpt    8  1657.712 ± 32.433  ops/ms <br/>
 *
 * @author luoyong
 * @date 2018/5/14
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2)
@Threads(8)
//@Threads(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 4, time = 3)
public class SerializationTest {

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder().include(SerializationTest.class.getSimpleName()).forks(2).build();
		new Runner(options).run();
	}

	@Benchmark
	public void testJackson() {
		String json = JacksonUtil.toJson(BuildUtil.getBootPopup());
		BootPopup bp = JacksonUtil.jsonToBean(json, BootPopup.class);
	}

	@Benchmark
	public void testFastJson() {
		String text = JSON.toJSONString(BuildUtil.getBootPopup());
		BootPopup bp = JSON.parseObject(text, BootPopup.class);
	}

	@Benchmark
	public void testProtoStuff() {
		byte[] bytes = ProtoStuffUtil.toByteArray(BuildUtil.getBootPopup());
		BootPopup bp = ProtoStuffUtil.toBean(bytes, BootPopup.class);
	}
}
