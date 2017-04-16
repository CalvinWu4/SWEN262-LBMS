package Library.BookQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import java.util.Scanner;

class RequestAPIExample {

    public static void main(String[] args) throws IOException {
        String url = "https://www.googleapis.com/books/v1/volumes/?langRestrict=en&q=";

        String authCode = "&key=AIzaSyBgIxRv3oqcXzLTH0JpIVoDutzOL7yf5k4";

        //String searchParam = "Harry Potter"; //temp search string for testing to make project work
        Scanner input = new Scanner(System.in);
        System.out.println("Input search request here: ");
        String searchParam = input.nextLine();

        String[] paramList = searchParam.split(",");

        String titleParam = (paramList.length > 1) ? "intitle%3A" + paramList[0] + "+" : "";
        titleParam = titleParam.replace(" ", "%20");
        String authorParam = (paramList.length >= 2) ? "inauthor%3A" + paramList[1] + "+" : "";
        authorParam = authorParam.replace(" ", "%20");
        String isbnParam = (paramList.length >= 3) ? "isbn%3A" + paramList[2] + "+" : "";
        isbnParam = isbnParam.replace(" ", "%20");
        String publisherParam = (paramList.length >= 4) ? "inpublisher%3A" + paramList[3] + "+" : "";
        publisherParam = publisherParam.replace(" ", "%20");

        //Get user input search feature from gui search here
        //searchParam = searchParam.replace(" ", "+");

        System.out.println(url + authorParam + titleParam + isbnParam + publisherParam + authCode);

        // Create a URL and open a connection
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

        try {
            JSONObject obj = new JSONObject(response.toString());

            JSONArray arr = obj.getJSONArray("items");

            for (int i = 0; i < arr.length(); i++) {

                JSONObject objInfo = arr.getJSONObject(i).getJSONObject("saleInfo");

                if (objInfo.getString("country").equals("US") && objInfo.getString("saleability").equals("FOR_SALE")) {
                    JSONObject volumeInfoObj = arr.getJSONObject(i).getJSONObject("volumeInfo");
                    JSONObject saleInfoObj = arr.getJSONObject(i).getJSONObject("saleInfo");

                    String title = volumeInfoObj.getString("title");
                    System.out.println(title);

                    if (volumeInfoObj.has("authors")) {
                        JSONArray authors = volumeInfoObj.getJSONArray("authors");
                        System.out.println(authors);
                    }
                    if (volumeInfoObj.has("publisher")) {
                        String publisher = volumeInfoObj.getString("publisher");
                        System.out.println(publisher);
                    }
                    if (volumeInfoObj.has("publishedDate")) {
                        String published = volumeInfoObj.getString("publishedDate");
                        System.out.println(published);
                    }
                    if (volumeInfoObj.has("pageCount")) {
                        int pageCount = volumeInfoObj.getInt("pageCount");
                        System.out.println(pageCount);
                    }
                    if (volumeInfoObj.has("industryIdentifiers")) {
                        JSONArray isbnArr = volumeInfoObj.getJSONArray("industryIdentifiers");
                        for (int j = 0; j < isbnArr.length(); j++) {
                            if (isbnArr.getJSONObject(j).has("identifier")) {
                                String isbn = isbnArr.getJSONObject(j).getString("identifier");
                                if (isbn.length() > 12) {
                                    String isbn13 = isbn;
                                    System.out.println(isbn13);
                                }
                            }
                        }
                    }
                    if (saleInfoObj.has("country")) {
                        String country = saleInfoObj.getString("country");
                        System.out.println(country);
                    }
                    if (saleInfoObj.has("saleability")) {
                        String saleability = saleInfoObj.getString("saleability");
                        System.out.println(saleability + "\n");
                    }
                }
            }
        } catch (JSONException je) {
            System.out.println("Error: " + je);
        }
        in.close();

    }
}