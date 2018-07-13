package com.test.jmh;

import com.test.utils.BeanDozerMapper;
import com.test.utils.BeanOrikaMapper;
import com.test.vo.BootPopupVO;
import com.test.vo.BuildUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.TimeUnit;

/**
 * 对象复制性能测试<br/>
 * <p>
 * 四线程测试结果<br/>
 * Benchmark                   Mode  Cnt        Score        Error  Units <br/>
 * BeanMapperTest.testDozer   thrpt    8   394370.208 ±  61072.718  ops/s <br/>
 * BeanMapperTest.testOrika   thrpt    8  1960062.401 ± 315229.220  ops/s <br/>
 * BeanMapperTest.testSpring  thrpt    8  8271428.367 ± 296817.303  ops/s <br/>
 * <p>
 * 单线程测试结果<br/>
 * BeanMapperTest.testDozer   thrpt    8   157457.193 ±   7219.254  ops/s <br/>
 * BeanMapperTest.testOrika   thrpt    8  2968882.380 ±  90117.407  ops/s <br/>
 * BeanMapperTest.testSpring  thrpt    8  2230255.042 ± 177015.963  ops/s <br/>
 * <p>
 * 注意：orika在创建某个类的那些反射代码时，有并发问题bug，到目前1.5.2版本尚未解决 <br/>
 * Spring的BeanUtils是浅复制
 *
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
        BootPopupVO bpv = BeanDozerMapper.map(BuildUtil.getBootPopup(), BootPopupVO.class);
    }

    @Benchmark
    public void testOrika() {
        BootPopupVO bpv = BeanOrikaMapper.map(BuildUtil.getBootPopup(), BootPopupVO.class);
    }

    @Benchmark
    public void testSpring() {
        BootPopupVO bpv = new BootPopupVO();
        BeanUtils.copyProperties(BuildUtil.getBootPopup(), bpv);
    }
}
