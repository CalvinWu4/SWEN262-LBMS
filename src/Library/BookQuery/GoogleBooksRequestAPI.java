package Library.BookQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
import java.util.Scanner;

/*
 * Currently requests a search term from user through a scanner input. The search words are parsed by comma parser and
  * prepared to put in googleBookAPI url format. The url string then returns a json array of up to 40 json objects
  * representing books. These objects (with the exception of the book title) are checked to ensure they have author,
  * publisher, for_sale, etc. Once those 40 books are determined to be valid their information is extracted and
  * outputted to the terminal.
  * TODO: Once we decide how we are calling our local search or google search, this class needs to be modified to:
  *     TODO: return the information to the user through the gui, with all necessary information so a book can be made
  *     TODO: from the returned information (name, authors, publisher, date published, price.*/
class RequestAPIExample {

    public static void main(String[] args) throws IOException {
        //starting google api url for submitting a query of search strings
        String url = "https://www.googleapis.com/books/v1/volumes/?langRestrict=en&q=";

        //code used to access full google api privileges using an api_auth key, will return max of 40 "books"
        String authCode = "&maxResults=40&key=AIzaSyBgIxRv3oqcXzLTH0JpIVoDutzOL7yf5k4";

        Scanner input = new Scanner(System.in);
        System.out.println("Input search request here: ");
        //request search words from user
        String searchParam = input.nextLine();

        //array list storing and separating the search from the user by commas
        String[] paramList = searchParam.split(",");

        //
        String titleParam = (paramList.length >= 1) ? "intitle%3A" + paramList[0] + "+" : "";
        titleParam = titleParam.replace(" ", "%20");
        String authorParam = (paramList.length >= 2) ? "inauthor%3A" + paramList[1] + "+" : "";
        authorParam = authorParam.replace(" ", "%20");
        String isbnParam = (paramList.length >= 3) ? "isbn%3A" + paramList[2] + "+" : "";
        isbnParam = isbnParam.replace(" ", "%20");
        String publisherParam = (paramList.length >= 4) ? "inpublisher%3A" + paramList[3] + "+" : "";
        publisherParam = publisherParam.replace(" ", "%20");


        System.out.println(url + authorParam + titleParam + isbnParam + publisherParam + authCode);

        // Create a URL from beginning url, auth_key, and search param list and open a connection
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

        String requestResponse="";

        try {
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
                        requestResponse += (saleability+"|");
                    }
                    if (saleInfoObj.getJSONObject("listPrice").has("amount")){
                        double listPrice = saleInfoObj.getJSONObject("listPrice").getDouble("amount");
                        requestResponse += (listPrice+"\n");
                    }
                }
            }
            System.out.println(requestResponse);
        } catch (JSONException je) {
            System.out.println("Error: " + je);
        }
        in.close();
    }
}