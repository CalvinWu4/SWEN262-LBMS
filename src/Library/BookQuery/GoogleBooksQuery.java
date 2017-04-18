package Library.BookQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jp on 4/17/17.
 */
public class GoogleBooksQuery {

    public String search(String searchParam){
        String url = "https://www.googleapis.com/books/v1/volumes/?langRestrict=en&q=";
        String authCode = "&maxResults=40&key=AIzaSyBgIxRv3oqcXzLTH0JpIVoDutzOL7yf5k4";

        String[] paramList = searchParam.split(",");

        String titleParam = (paramList.length >= 1) ? "intitle%3A" + paramList[0] + "+" : "";
        titleParam = titleParam.replace(" ", "%20");
        String authorParam = (paramList.length >= 2) ? "inauthor%3A" + paramList[1] + "+" : "";
        authorParam = authorParam.replace(" ", "%20");
        String isbnParam = (paramList.length >= 3) ? "isbn%3A" + paramList[2] + "+" : "";
        isbnParam = isbnParam.replace(" ", "%20");
        String publisherParam = (paramList.length >= 4) ? "inpublisher%3A" + paramList[3] + "+" : "";
        publisherParam = publisherParam.replace(" ", "%20");

        String requestResponse="";

        try {
            URL GoogleURL = new URL(url + authorParam + titleParam + isbnParam + publisherParam + authCode);
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

            JSONObject obj = new JSONObject(response.toString());
            JSONArray arr = obj.getJSONArray("items");

            for (int i = 0; i < arr.length(); i++) {
                JSONObject objInfo = arr.getJSONObject(i).getJSONObject("saleInfo");

                if (objInfo.getString("country").equals("US") && objInfo.getString("saleability").equals("FOR_SALE")) {
                    JSONObject volumeInfoObj = arr.getJSONObject(i).getJSONObject("volumeInfo");
                    JSONObject saleInfoObj = arr.getJSONObject(i).getJSONObject("saleInfo");

                    String title = volumeInfoObj.getString("title");

                    requestResponse += (title+"|");

                    if (volumeInfoObj.has("authors")) {
                        JSONArray authors = volumeInfoObj.getJSONArray("authors");
                        requestResponse += (authors+"|");
                    }
                    if (volumeInfoObj.has("publisher")) {
                        String publisher = volumeInfoObj.getString("publisher");
                        requestResponse += (publisher+"|");
                    }
                    if (volumeInfoObj.has("publishedDate")) {
                        String published = volumeInfoObj.getString("publishedDate");
                        requestResponse += (published+"|");
                    }
                    if (volumeInfoObj.has("pageCount")) {
                        int pageCount = volumeInfoObj.getInt("pageCount");
                        requestResponse += (pageCount+"|");
                    }
                    if (volumeInfoObj.has("industryIdentifiers")) {
                        JSONArray isbnArr = volumeInfoObj.getJSONArray("industryIdentifiers");
                        for (int j = 0; j < isbnArr.length(); j++) {
                            if (isbnArr.getJSONObject(j).has("identifier")) {
                                String isbn = isbnArr.getJSONObject(j).getString("identifier");
                                if (isbn.length() > 12) {
                                    String isbn13 = isbn;
                                    requestResponse += (isbn13+"|");
                                }
                            }
                        }
                    }
                    if (saleInfoObj.has("country")) {
                        String country = saleInfoObj.getString("country");
                        requestResponse += (country+"|");
                    }
                    if (saleInfoObj.has("saleability")) {
                        String saleability = saleInfoObj.getString("saleability");
                        //System.out.println(saleability + "\n");
                        requestResponse += (saleability+"|\n");
                    }
                }
            }
            in.close();
        }
        catch (IOException ioe){
            System.out.println("IO Error: "+ioe);
        }
        catch (JSONException je) {
            System.out.println("JSON Error: " + je);
        }
        return requestResponse;
    }
}
