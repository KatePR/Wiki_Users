package edu.bsu.cs222.twoweekproject;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@SuppressWarnings("WeakerAccess")
public class Connection {

    private BufferedReader in;

    public Connection(){

    }

    public Document retrieveXmlFromRequestAsDocument(String searchString) throws IOException, ParserConfigurationException, SAXException {
        String fullURL = makeUrl(searchString);
        setUpUrlConnection(fullURL);
        return convertToDocument(returnXMLAsString());
    }

    private String makeUrl(String search) throws UnsupportedEncodingException {
        String firstHalfWikiUrl = "https://en.wikipedia.org/w/api.php?action=query&titles=";
        String finalWikiUrl = "&prop=revisions&rvprop=timestamp|user&format=xml&rvlimit=10";
        return firstHalfWikiUrl +URLEncoder.encode(search, "UTF-8")+ finalWikiUrl;
    }

    private void setUpUrlConnection(String fullURL) throws IOException {
        URL url = new URL(fullURL);
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            System.out.println("No connection established.");
        }
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Fa16; drsiscoe@bsu.edu, kprader@bsu.edu)");
        in = new BufferedReader((new InputStreamReader(connection.getInputStream())));
    }

    private Document convertToDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    private String returnXMLAsString() throws IOException {
        String inputLine;
        String xml = "";
        while ((inputLine = in.readLine()) != null) {
            xml = xml + inputLine;
        }
        in.close();
        return xml;
    }
}
