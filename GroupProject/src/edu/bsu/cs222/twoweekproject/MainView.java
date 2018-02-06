package edu.bsu.cs222.twoweekproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class MainView extends Application {

    private final TextField  wikiTitlePageSearch = new TextField();
    private final TextArea textArea = new TextArea();
    private final Button searchButton = new Button("Search");
    private Connection wikiConnection = new Connection();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private Scene createScene(){
        searchButton.setOnAction(event -> {
            try {
                executeActionItems();
            } catch (IOException e) {
                textArea.appendText("We cannot get connected.");
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        });
        VBox root = new VBox(
                new Label("Enter title to search:"),
                wikiTitlePageSearch,
                searchButton,
                textArea);
        return new Scene(root);
    }
    private void executeActionItems() throws IOException, SAXException, ParserConfigurationException {
        disableInput();
        disableButton();
        textArea.clear();
        Document doc = retrieveXMLDocument();
        if (DocumentExaminer.checkRevisionTagExists(doc)){
            ArrayList<Revision> revisionList = parseXMLDocumentToRevisionList(doc);
            displayParsedData(revisionList);
        } else{
            textArea.appendText("404 Error: Page not found. Please try another search.");
        }
        enableButton();
        enableInput();
    }

    private void disableInput(){
        wikiTitlePageSearch.setDisable(true);
    }

    private void disableButton(){
        searchButton.setDisable(true);
    }

    private Document retrieveXMLDocument() throws ParserConfigurationException, SAXException, IOException {
        return wikiConnection.retrieveXmlFromRequestAsDocument(retrieveSearchText());
    }

    private String retrieveSearchText() {
        return wikiTitlePageSearch.getText();
    }

    private ArrayList<Revision> parseXMLDocumentToRevisionList(Document xmlData){
        RevisionParser parse = new RevisionParser(xmlData);
        return parse.getRevisionList();
    }

        private void displayParsedData(ArrayList<Revision> revisionArrayList){
            for (int i=0; i < revisionArrayList.size(); i++){
                String username = revisionArrayList.get(i).getUser();
                String timestamp = revisionArrayList.get(i).getTimestamp();
                textArea.appendText("User: " + username +"\n" + "Timestamp: " + timestamp + "\n \n");
            }
        }

        private void enableButton(){
            wikiTitlePageSearch.setDisable(false);
        }

        private void enableInput(){
            searchButton.setDisable(false);
        }
}
