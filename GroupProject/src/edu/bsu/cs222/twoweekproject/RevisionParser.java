package edu.bsu.cs222.twoweekproject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public class RevisionParser {

    private Document document;

    public RevisionParser(Document doc){

        this.document = doc;
    }

    public ArrayList<Revision> getRevisionList() {
            NodeList revs = getRevsNodeList();
            return addRevisionsToList(revs);
    }

    private NodeList getRevsNodeList() {
        NodeList revisions = document.getElementsByTagName("revisions");
        Element revision = (Element) revisions.item(0);
        return revision.getElementsByTagName("rev");
    }

    private ArrayList<Revision> addRevisionsToList(NodeList revs){
        ArrayList<Revision> revisionList = new ArrayList<>();
        for (int i=0; i< revs.getLength(); i++){ //changed 10 to getLength
            Element rev = (Element)revs.item(i);
            Revision revObj = createRevisionObject(rev.getAttribute("user"),rev.getAttribute("timestamp"));
            revisionList.add(revObj);
        }
        return revisionList;
    }

    private Revision createRevisionObject (String user,String timeStamp){
        Revision.Builder revObj = Revision.create();
        revObj.setUser(user);
        revObj.setTimestamp(timeStamp);
        return revObj.build();
    }
}