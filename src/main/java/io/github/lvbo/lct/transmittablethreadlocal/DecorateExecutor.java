package io.github.lvbo.lct.transmittablethreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecorateExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        // 在父线程中设置
        context.set("value-set-in-parent");

        Runnable task = () -> System.out.println("son:" + context.get());
        executorService.submit(task);
        executorService.shutdown();
    }

}
