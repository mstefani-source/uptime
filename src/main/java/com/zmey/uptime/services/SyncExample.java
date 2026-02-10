package com.zmey.uptime.services;

import java.util.*;

public class SyncExample {

    // List<String> listOfString = Collections.synchronizedList(new ArrayList<>());

    List<String> simpleList = new ArrayList<>();

    public void go() {
        simpleList.add("string1");
        simpleList.add("string2");

        synchronized (simpleList) {

            // logic
            Iterator<String> it = simpleList.iterator();

            while (it.hasNext()) {
                System.out.println(it.next());
            }
            try {
                int f = 1;
            }
            catch(Exception r){ 
                 
            }
            finally {
                simpleList.clear();
            }
        }
    }

}
