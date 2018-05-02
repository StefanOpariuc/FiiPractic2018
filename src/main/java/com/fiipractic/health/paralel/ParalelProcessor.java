package com.fiipractic.health.paralel;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author stefan (Stefan.Opariuc@tss-yonder.com)
 * @since 29/04/2018
 **/
@Component
public class ParalelProcessor {

    @Async("uiteExecutor")
    public Future<String> processingStuffHere() throws InterruptedException {
        Thread.sleep(500L);
        return new AsyncResult<>("Am terminat sefu");

    }
}
