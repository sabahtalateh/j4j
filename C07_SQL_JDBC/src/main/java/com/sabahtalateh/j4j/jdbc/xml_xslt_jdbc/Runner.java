package com.sabahtalateh.j4j.jdbc.xml_xslt_jdbc;

import org.apache.commons.io.FileUtils;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.*;
import java.util.UUID;

import static java.lang.String.format;

/**
 * Runner.
 */
public class Runner {

    private String SQLiteDBConnectionString;

    private Path XMLOutputDir;

    private int n;

    /**
     * @param SQLiteDBPath path to sqlite db (will be created automatically).
     * @param XMLOutputDir where to put result XML.
     * @param n            number of rows to insert.
     */
    public Runner(String SQLiteDBPath, Path XMLOutputDir, int n) {
        this.SQLiteDBConnectionString = format("jdbc:sqlite:%s", SQLiteDBPath);
        this.XMLOutputDir = XMLOutputDir.toAbsolutePath();
        this.n = n;
    }

    /**
     * Run!
     *
     * @throws TransformerException exception.
     */
    public void run() throws TransformerException {
        this.createAndFillDB();
        this.createFirstXMLFile();
        this.transformXML();
    }

    /**
     * Create SQLite DB and fill it with numbers.
     */
    private void createAndFillDB() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(this.SQLiteDBConnectionString);

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS test (field INTEGER);");
                statement.executeUpdate("DELETE FROM test;");
            }

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test(field) VALUES (?)")) {
                for (int i = 0; i < n; i++) {
                    preparedStatement.setInt(1, i);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Create XML.
     */
    private void createFirstXMLFile() {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        File firstXMLPath = new File(XMLOutputDir.toFile(), "1.xml");
        try (FileWriter file = new FileWriter(firstXMLPath)) {
            XMLStreamWriter XMLWriter = factory.createXMLStreamWriter(file);
            XMLWriter.writeStartDocument();
            XMLWriter.writeStartElement("document");

            try (Connection connection = DriverManager.getConnection(this.SQLiteDBConnectionString)) {
                try (Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery("SELECT field FROM test;");
                    while (resultSet.next()) {
                        XMLWriter.writeStartElement("entry");
                        XMLWriter.writeStartElement("field");
                        XMLWriter.writeCharacters(String.valueOf(resultSet.getInt(1)));
                        XMLWriter.writeEndElement();
                        XMLWriter.writeEndElement();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            XMLWriter.writeEndElement();
            XMLWriter.writeEndDocument();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transform XML.
     *
     * @throws TransformerException exception.
     */
    private void transformXML() throws TransformerException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("xslt/transform.xslt");
        UUID uuid = UUID.randomUUID();
        // Copy xslt to regular file because of some issue with Transformer
        // that rices a malformed url exception when trying to load resource from
        // within a jar file.
        File temporaryFile = new File(format("/tmp/%s.xslt", uuid.toString()));
        try {
            FileUtils.copyURLToFile(resource, temporaryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(temporaryFile);
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File(XMLOutputDir.toFile(), "1.xml"));
        transformer.transform(text, new StreamResult(new File(XMLOutputDir.toFile(), "2.xml")));
        temporaryFile.delete();
    }

}
