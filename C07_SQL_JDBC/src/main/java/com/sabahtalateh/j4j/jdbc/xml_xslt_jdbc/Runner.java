package com.sabahtalateh.j4j.jdbc.xml_xslt_jdbc;

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
import java.nio.file.Path;
import java.sql.*;

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
        this.SQLiteDBConnectionString = String.format("jdbc:sqlite:%s", SQLiteDBPath);
        this.XMLOutputDir = XMLOutputDir;
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
        try (Connection connection = DriverManager.getConnection(this.SQLiteDBConnectionString)) {

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

            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
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
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("src/main/resources/xslt/transform.xslt"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File(XMLOutputDir.toFile(), "1.xml"));
        transformer.transform(text, new StreamResult(new File(XMLOutputDir.toFile(), "2.xml")));
    }

}
