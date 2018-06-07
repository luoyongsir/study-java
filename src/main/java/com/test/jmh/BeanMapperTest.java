package com.test.jmh;

import com.test.utils.BeanDozerMapper;
import com.test.utils.BeanOrikaMapper;
import com.test.vo.BootPopup;
import com.test.vo.BootPopupVO;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 对象复制性能测试<br/>
 * 我的测试结果如下：<br/>
 * 四线程测试结果<br/>
 * Benchmark                   Mode  Cnt        Score        Error  Units <br/>
 * BeanMapperTest.testDozer   thrpt    8   394370.208 ±  61072.718  ops/s <br/>
 * BeanMapperTest.testOrika   thrpt    8  1960062.401 ± 315229.220  ops/s <br/>
 * BeanMapperTest.testSpring  thrpt    8  8271428.367 ± 296817.303  ops/s <br/>
 * 单线程测试结果<br/>
 * BeanMapperTest.testDozer   thrpt    8   157457.193 ±   7219.254  ops/s <br/>
 * BeanMapperTest.testOrika   thrpt    8  2968882.380 ±  90117.407  ops/s <br/>
 * BeanMapperTest.testSpring  thrpt    8  2230255.042 ± 177015.963  ops/s <br/>
 * @author luoyong
 * @date 2018/5/14
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Threads(4)
//@Threads(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@Measurement(iterations = 4, time = 3, timeUnit = TimeUnit.SECONDS)
public class BeanMapperTest {

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder().include(BeanMapperTest.class.getSimpleName()).forks(2).build();
		new Runner(options).run();
	}

	@Benchmark
	public void testDozer() {
		BootPopupVO bpv = BeanDozerMapper.map(bootPopup, BootPopupVO.class);
	}

	@Benchmark
	public void testOrika() {
		BootPopupVO bpv = BeanOrikaMapper.map(bootPopup, BootPopupVO.class);
	}

	@Benchmark
	public void testSpring() {
		BootPopupVO bpv = new BootPopupVO();
		BeanUtils.copyProperties(bootPopup, bpv);
	}

	private static BootPopup bootPopup;
	static {
		bootPopup = new BootPopup();
		bootPopup.setId(1);
		bootPopup.setStatus((byte) 1);
		bootPopup.setPopupName("对象复制性能测试");
		bootPopup.setStartTime(new Date());
		bootPopup.setShowTime((byte) 5);
		bootPopup.setPicUrl("https://www.baidu.com/");
		bootPopup.setActivityUrl("https://www.baidu.com/");
		bootPopup.setEditPerson("luoyong");
		bootPopup.setCreateTime(new Date());
		bootPopup.setUpdateTime(new Date());
	}
}
