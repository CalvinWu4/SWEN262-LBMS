package Library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

class RequestAPIExample {

    public static void main(String[] args) throws IOException {
        String url = "https://www.googleapis.com/books/v1/volumes?q=id:";

        // Create a URL and open a connection
        URL GoogleURL = new URL(url);
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
                //JSONArray authors = jobj.getJSONArray("authors");

//                String publisher = jobj.getString("publisher");
//                String published = jobj.getString("publishedDate");
//                String description =jobj.getString("description");

                System.out.println(title);
            }

        } catch (JSONException je) {
            System.out.println("Error: "+je);
        }
        in.close();
    }

}