package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte one = 1;
        short two = 2;
        int three = 3;
        long four = 4L;
        float five = 5.5f;
        double six = 6.6;
        boolean seven = true;
        char eight = 88;
        LOG.debug("\nByte: {}\nShort: {}\nInteger: {}\nLong: {}\nFloat: {}\nDouble: {}\nBoolean: {}\nChar: {}",
                one, two, three, four, five, six, seven, eight);
    }
}