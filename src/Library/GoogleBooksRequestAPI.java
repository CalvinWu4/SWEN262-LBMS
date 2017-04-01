package Library;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Tyler Lima on 4/1/2017.
 */
public class GoogleBooksRequestAPI {
    public static void main(String[] args) throws IOException {

        //initial url we will be using; commented out for testing
        //String url = "https://www.googleapis.com/books/v1/volumes?q=";

        //testing url
        String url = "https://www.googleapis.com/books/v1/volumes?q=id:pDsFCAAAQBAJ&output=(xml)";
        // Create a URL and open a connection
        URL GoogleURL = new URL(url);

        HttpURLConnection con = (HttpURLConnection) GoogleURL.openConnection();

        // Set the HTTP Request type method to GET (Default: GET)
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        // Created a BufferedReader to read the contents of the request.
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        StringBuilder response = new StringBuilder();

        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // MAKE SURE TO CLOSE YOUR CONNECTION!
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Get the DOM Builder
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        //TODO:Fix parser to read in the googleAPI information.
        // The issue is I'm not sure if this is an xml file from google that we can use

        //Load and Parse the XML document
        //document contains the complete XML as a Tree.
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                    //TODO: issue is happening here
                    parse(new InputSource(new StringReader(response.toString())));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }


        //Using Books class to create objects to search through and return information wanted

    }
}
