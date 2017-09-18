package com.sabahtalateh.j4j.multithreading.text_finder;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TextFinderTest.
 */
public class TextFinderTest {

    final String testingFilesSources = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/test/testing_resources/text_finder";

    @Test
    public void searchHelloStringInTxtFiles() throws Exception {

        List<String> expected = new ArrayList<String>() {{
            add(testingFilesSources + "/Hello.txt");
        }};

        TextFinder textFinder = new TextFinder();
        List<String> result = textFinder.parallelSearch(
                testingFilesSources,
                "Hello",
                new ArrayList<String>() {{
                    add("txt");
                }}
        );

        assertThat(result.size(), is(1));
        assertThat(result, is(expected));
    }

    @Test
    public void searchHelloInTxtJsAndHtml() throws Exception {
        List<String> expected = new ArrayList<String>() {{
            add(testingFilesSources + "/Hello.txt");
            add(testingFilesSources + "/1/1.1/test.js");
            add(testingFilesSources + "/1/1.1/test.html");
        }};

        TextFinder textFinder = new TextFinder();
        List<String> result = textFinder.parallelSearch(
                testingFilesSources,
                "Hello",
                new ArrayList<String>() {{
                    add("txt");
                    add("html");
                    add("js");
                }}
        );
        result.sort(Comparator.reverseOrder());

        assertThat(result, is(expected));
    }
}