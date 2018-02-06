package edu.bsu.cs222.twoweekproject;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class RevisionParserTest {

    private Document document;
    private NodeList revisions;
    private Element revision;
    private NodeList revs;
    private Element rev;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cheesecake_test.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(inputStream);
        revisions = document.getElementsByTagName("revisions");
        revision = (Element) revisions.item(0);
        revs = revision.getElementsByTagName("rev");
        rev = (Element)revs.item(0);
    }

    @Test
    public void testReadAPIElement() {
        NodeList children = document.getChildNodes();
        Element apiElement = (Element)children.item(0);
        Assert.assertEquals("api", apiElement.getNodeName());
    }

    @Test
    public void testReadFirstRevisionAuthor() {
        Assert.assertEquals("BD2412", rev.getAttribute("user"));
    }

    @Test
    public void testNodeListNotEmptyRevisions() {
        Assert.assertTrue(revisions.getLength() > 0);
    }

    @Test
    public void testNodeListNotEmptyRevs() {
        Assert.assertTrue(revs.getLength() > 0);
    }

    @Test
    public void testNullNodeList(){
        Assert.assertTrue(revisions != null);
    }

    @Test
    public void testDocumentNotFound(){
        Assert.assertTrue(document != null);
    }
}