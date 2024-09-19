package com.sample;

import java.util.ArrayList;
import java.util.List;

import org.example.FactA;
import org.example.FactB;
import org.example.FactC;
import org.example.FactD;
import org.example.FactE;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsTest {

    @Test
    public void testKjar() throws Exception {
        KieServices ks = KieServices.Factory.get();
        ReleaseId releaseId = ks.newReleaseId("com.sample", "basic-kjar-em", "1.0.0");

        long start = System.currentTimeMillis();
        KieContainer kcontainer = ks.newKieContainer(releaseId);
        System.out.println("newKieContainer time : " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        KieBase kbase = kcontainer.getKieBase();
        System.out.println("getKieBase time : " + (System.currentTimeMillis() - start) + " ms");

        System.gc();
        Thread.sleep(5000);
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used memory after getKieBase: " + usedMemory / 1024 / 1024 + " MB");

//        System.out.println("get dump!!!");
//        Thread.sleep(500000);

        KieSession ksession = kbase.newKieSession();
        List<String> resultList = new ArrayList<>();
        ksession.setGlobal("resultList", resultList);

        FactA factA = new FactA(1, "ABCDEFG9", "A", "A", "A", "A", "A", "A", "A", "A", "A");
        FactB factB = new FactB(1, "ABCDEFG9", "A", "A", "A", "A", "A", "A", "A", "A", "A");
        FactC factC = new FactC(1, "ABCDEFG9", "A", "A", "A", "A", "A", "A", "A", "A", "A");
        FactD factD = new FactD(1, "ABCDEFG9", "A", "A", "A", "A", "A", "A", "A", "A", "A");
        FactE factE = new FactE(1, "ABCDEFG9", "A", "A", "A", "A", "A", "A", "A", "A", "A");
        ksession.insert(factA);
        ksession.insert(factB);
        ksession.insert(factC);
        ksession.insert(factD);
        ksession.insert(factE);
        ksession.fireAllRules();

        System.out.println("resultList = " + resultList);
        ksession.dispose();
    }
}
