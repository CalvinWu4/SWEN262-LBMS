package Library.BookQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
import java.util.Scanner;
/*
* */
class RequestAPIExample {

    public static void main(String[] args) throws IOException {
        String url = "https://www.googleapis.com/books/v1/volumes/?q=";

        //TODO: auth key not working; investigate why
        //String authCode = "&key=AIzaSyBgIxRv3oqcXzLTH0JpIVoDutzOL7yf5k4";

        Scanner input = new Scanner(System.in);
        System.out.println("Input search request here: ");
        String searchParam = input.nextLine();

        //String searchParam = "Harry Potter and the order of the Phoenix";//temp search term for testing

        //Get user input search feature from gui search here
        searchParam = searchParam.replace(" ", "+");

        // Create a URL and open a connection
        URL GoogleURL = new URL(url+ searchParam ); //+ authCode <- put this back in once I figure out why the key isn't working
        HttpURLConnection con = (HttpURLConnection) GoogleURL.openConnection();

        // Set the HTTP Request type method to GET (Default: GET)
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        // Created a BufferedReader to read the contents of the request.
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        try {
            JSONObject obj = new JSONObject(response.toString());

            JSONArray arr = obj.getJSONArray("items");

            for(int i=0; i<arr.length(); i++){
                JSONObject jobj = arr.getJSONObject(i).getJSONObject("volumeInfo");


                String title = jobj.getString("title");
                System.out.println(title);

                if(jobj.has("authors")) {
                    JSONArray authors = jobj.getJSONArray("authors");
                    System.out.println(authors);
                }
                if(jobj.has("publisher")){
                    String publisher = jobj.getString("publisher");
                    System.out.println(publisher);
                }
                if(jobj.has("publishedDate")) {
                    String published = jobj.getString("publishedDate");
                    System.out.println(published);
                }
                if(jobj.has("pageCount")) {
                    String description =jobj.getString("description");
                    System.out.println(description + "\n");
                }



            }

        } catch (JSONException je) {
            System.out.println("Error: "+je);
        }
        in.close();
    }

}