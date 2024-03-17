package com.yupi.springbootinit.config;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Data
public class ThreadPoolExecutorConfig {

    /**
     * 自定义线程工厂,为线程设置线程名
     */
    ThreadFactory threadFactory = new ThreadFactory() {
        private int count = 1;
        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("线程" + count);
            count++;
            return thread;
        }
    };

    /**
     *  自定义线程池
     * @return
     */
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                threadFactory);
        return threadPoolExecutor;
    }
}
