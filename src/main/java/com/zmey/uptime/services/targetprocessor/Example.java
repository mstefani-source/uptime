package com.zmey.uptime.services.targetprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class Example {
    private static List<String> exampleList = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Example.class.getName());

    static {

        exampleList = Arrays.asList("example_list", "a", "b", "c");

    }

    public static void main(String[] args) throws NoSuchElementException {
        Iterator<String> itr = exampleList.iterator();

        try {
            while (itr.hasNext()) {
                String item = itr.next();
                if (!item.isEmpty()) {
                    LOGGER.info(item);
                }
            }
        } catch (Exception e) {
            LOGGER.warning(e.getLocalizedMessage());
        }
    }
}
