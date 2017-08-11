package com.sabahtalateh.j4j.collections_advanced.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * DictTest.
 */
public class DictTest {

    @Test
    public void whenInsertElementIntoDictThenElementsCanBeRetrieved() throws Exception {
        Dict<String, String> dict = new Dict<>(1);

        dict.insert("First", "1");
        dict.insert("Second", "2");
        dict.insert("Third", "3");
        dict.insert("Fourth", "4");
        dict.insert("Fifth", "5");

        assertThat(dict.get("First"), is("1"));
        assertThat(dict.get("Second"), is("2"));
        assertThat(dict.get("Third"), is("3"));
        assertThat(dict.get("Fourth"), is("4"));
        assertThat(dict.get("Fifth"), is("5"));
        assertThat(dict.get("Sixth"), nullValue());
    }

    @Test
    public void whenElementDeletedThanGetWillReturnNull() throws Exception {
        Dict<String, String> dict = new Dict<>(1);

        dict.insert("First", "1");
        dict.insert("Second", "2");
        dict.delete("First");

        assertThat(dict.get("First"), nullValue());
    }

    @Test
    public void canNotInsertElementWithExistingKey() throws Exception {
        Dict<String, String> dict = new Dict<>();

        dict.insert("First", "1");
        assertThat(dict.insert("First", "2"), is(false));
        assertThat(dict.get("First"), is("1"));
    }

    @Test
    public void canIterateThroughMap() throws Exception {
        Dict<String, String> dict = new Dict<>(1);

        dict.insert("First", "1");
        dict.insert("Second", "2");
        dict.insert("Third", "3");
        dict.insert("Fourth", "4");
        dict.insert("Fifth", "5");

        Iterator<String> iterator = dict.iterator();
        ArrayList<String> arrayList = new ArrayList<>();
        iterator.forEachRemaining(arrayList::add);
        arrayList.sort(String::compareTo);
        assertThat(arrayList.get(0), is("1"));
        assertThat(arrayList.get(1), is("2"));
        assertThat(arrayList.get(2), is("3"));
        assertThat(arrayList.get(3), is("4"));
        assertThat(arrayList.get(4), is("5"));
    }
}