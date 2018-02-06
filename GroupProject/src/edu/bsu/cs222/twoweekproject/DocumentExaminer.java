package edu.bsu.cs222.twoweekproject;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@SuppressWarnings("WeakerAccess")
public class DocumentExaminer {

    public static boolean checkRevisionTagExists(Document document) {
        NodeList revisions = document.getElementsByTagName("revisions");
        return (revisions.getLength() != 0);
    }
}
