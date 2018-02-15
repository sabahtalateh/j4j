package com.sabahtalateh.j4j.algo.linked_list_reversion;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ReversionTest.
 */
public class ReversionTest {
    @Test
    public void testSingle() throws Exception {
        Node<String> n1 = new Node<>("First");

        Node<String> expected = new Node<>("First");

        assertThat(Reversion.revert(n1), is(expected));
    }

    @Test
    public void testComplex1() throws Exception {
        Node<String> n1 = new Node<>("I");
        Node<String> n2 = new Node<>("II");
        Node<String> n3 = new Node<>("III");
        Node<String> n4 = new Node<>("IV");
        Node<String> n5 = new Node<>("V");
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);

        Node<String> expected = new Node<>("V");
        Node<String> e2 = new Node<>("IV");
        Node<String> e3 = new Node<>("III");
        Node<String> e4 = new Node<>("II");
        Node<String> e5 = new Node<>("I");
        expected.setNext(e2);
        e2.setNext(e3);
        e3.setNext(e4);
        e4.setNext(e5);


        Node<String> reverted = Reversion.revert(n1);

        assertThat(reverted, is(expected));

    }
}