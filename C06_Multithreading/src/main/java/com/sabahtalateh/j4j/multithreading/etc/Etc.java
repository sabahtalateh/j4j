package com.sabahtalateh.j4j.multithreading.etc;

import java.util.BitSet;

/**
 * Etc.
 */
public class Etc {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        BitSet bs = new BitSet();
        bs.set(1);
        bs.set(2);
        bs.set(4);
        System.out.println(bs);

        System.out.println(bs.get(1));
        System.out.println(bs.get(2));
        System.out.println(bs.get(3));
        System.out.println(bs.get(4));
    }
}
