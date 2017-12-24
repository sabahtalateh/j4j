package com.sabahtalateh.j4j.jdbc.xml_xslt_jdbc;

import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * RunnerTest.
 */
public class RunnerTest {
    @Test
    public void test() throws TransformerException {
        String testDBPath = Paths.get("", "src", "test", "test_db.db").toAbsolutePath().toString();
        Path XMLDir = Paths.get("", "src", "test");
        new Runner(testDBPath, XMLDir, 1_000_000).run();
    }
}