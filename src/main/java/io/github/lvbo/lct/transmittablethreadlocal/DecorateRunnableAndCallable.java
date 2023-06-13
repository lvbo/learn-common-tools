package io.github.lvbo.lct.transmittablethreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecorateRunnableAndCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
//        ThreadLocal<String> context = new ThreadLocal<>();

        // 在父线程中设置
        context.set("value-set-in-parent");
        Runnable task = () -> System.out.println("son:" + context.get());

        // 提交给线程池执行
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(task).get();

        // ...业务逻辑代码，
        // 并且修改了 TransmittableThreadLocal上下文 ...
        context.set("value-modified-in-parent");

        // 再次提交
        executorService.submit(task).get();
        executorService.shutdown();
    }
}
