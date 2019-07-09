package com.test.jmh;

import com.fast.ip.IP;
import net.ipip.ipdb.City;
import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * ip 读取性能测试
 *
 * Benchmark             Mode  Cnt   Score   Error    Units
 * IpReadTest.testFast  thrpt    6  51.347 ± 0.561  ops/min
 * IpReadTest.testIpdb  thrpt    6  31.894 ± 0.921  ops/min
 *
 * @author luoyong
 * @date 2019/7/2 17:00
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2)
@Threads(4)
@OutputTimeUnit(TimeUnit.MINUTES)
@Measurement(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class IpReadTest {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(IpReadTest.class.getSimpleName()).forks(2).build();
        new Runner(options).run();
    }

    private static City db;

    @Setup(Level.Trial)
    public void setup() throws IOException {
        db = new City("D:\\Workspaces\\IntelliJ\\github\\fast-ip\\src\\main\\resources\\dat\\ipipfree.ipdb");
    }

    @Benchmark
    public void testIpdb() {
        try {
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < 256; j++) {
                    for (int k = 0; k < 256; k++) {
                        String ip = i + "." + j + "." + k + "." + RandomUtils.nextInt(0, 256);
                        db.find(ip, "CN");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void testFast() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    String ip = i + "." + j + "." + k + "." + RandomUtils.nextInt(0, 256);
                    IP.findArr(ip);
                }
            }
        }
    }

}
