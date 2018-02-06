package edu.bsu.cs222.twoweekproject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DocumentExaminerTest {

    private Document cheesecakeDocument;
    private Document applePieDocument;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cheesecake_test.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        cheesecakeDocument = builder.parse(inputStream);

        InputStream inputStreamPie = getClass().getClassLoader().getResourceAsStream("applepie_test.xml");
        DocumentBuilderFactory factoryPie = DocumentBuilderFactory.newInstance();
        DocumentBuilder builderPie = factoryPie.newDocumentBuilder();
        applePieDocument = builderPie.parse(inputStreamPie);
    }
    
    @Test 
    public void testRevisionTagExists(){
        Assert.assertTrue(DocumentExaminer.checkRevisionTagExists(cheesecakeDocument));
    }

    @Test
    public void testRevisionTagDoesNotExists() {
        Assert.assertFalse(DocumentExaminer.checkRevisionTagExists(applePieDocument));
    }
}