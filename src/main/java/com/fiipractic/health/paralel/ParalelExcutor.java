package com.fiipractic.health.paralel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author stefan (Stefan.Opariuc@tss-yonder.com)
 * @since 29/04/2018
 **/
@Component
public class ParalelExcutor {

    @Autowired
    private ParalelProcessor paralelProcessor;

    public void execute() throws Exception {
        Long timeOfStart = System.currentTimeMillis();
        List<Future> listOfFutures = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            listOfFutures.add(paralelProcessor.processingStuffHere());

        }
        for (Future listOfFuture : listOfFutures) {
            listOfFuture.get();
        }
        System.out.print("Time with paralel XXXX =" + String.valueOf(System.currentTimeMillis() - timeOfStart));

        timeOfStart = System.currentTimeMillis();
        for (int i = 1; i < 50; i++) {
            Thread.sleep(500L);
        }
        System.out.println();
        System.out.print("Time with simple YYYY =" + String.valueOf(System.currentTimeMillis() - timeOfStart));
    }
}
