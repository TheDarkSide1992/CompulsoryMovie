package dk.easv.dataaccess;
        import dk.easv.entities.Movie;

        import java.io.IOException;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class MyOMDBConnector {

    public static String C1;

    /**
     * Queries the OMDb for the specific text the person has written in the search field
     * @param query the input the user wrote in the search field
     * @return a list of movies for the user to choose from
     */

    public List<Movie> searchQuery(String query) {
        List<Movie> searchMovies = new ArrayList<>();
        try { //Set up the connection with the query the user has written
            URL url = new URL("http://www.omdbapi.com/?" + "s=" + query + "&type=movie&apikey=40237601");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            //Checks the response code the server sends back, 200 = OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream()); //Opens up a Scanner which reads the info retrieved from OMDb
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine()); //Uses the StringBuilder to append all the lines from the data received
                }
                //Close the scanner
                scanner.close();

                String input = informationString.toString(); //Change the input to a String
                String[] splitSearch = input.split("\\{"); //Split the String into an Array at the start of each "{"

                for (int i = 2; i < splitSearch.length; i++) { //Ignore the first 2 "{" and loop through the rest;
                    String searchResults;
                    searchResults = splitSearch[i];
                    //Map the title, year and ID of the movies from the search result
                    String title = searchResults.substring(searchResults.indexOf("Title\":\"")+8, searchResults.indexOf("\","));
                    int year = Integer.parseInt(searchResults.substring(searchResults.indexOf("Year\":\"")+7, searchResults.indexOf("\",\"imdbID")));
                    String imdbID = searchResults.substring(searchResults.indexOf("imdbID\":\"")+11, searchResults.indexOf("\",\"Type"));

                    //Map the data into a Movie Object
                    Movie m = new Movie(title, year, imdbID);
                    searchMovies.add(m);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searchMovies;
    }

    /**
     * When the user chooses a specific Movie from the list provided,
     * that info gets automatically placed into the text fields.
     * @param imdbID the ID which the Movie has on IMDb
     * @return the Movie which the user chose
     */
    public Movie chosenMovieMoreInfo(String imdbID)  {
        C1 = null;
        Movie m;
        try { //Set up the connection with the IMDbID the user has chosen
            URL url = new URL("http://www.omdbapi.com/?" + "i=tt" + imdbID + "&apikey=40237601");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            //Checks the response code the server sends back, 200 = OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream()); //Opens up a Scanner which reads the info retrieved from OMDb
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine()); //Uses the StringBuilder to append all the lines from the data received
                }
                //Close the scanner
                scanner.close();
                //Map the data into relevant info

                String length = informationString.substring(informationString.indexOf("Runtime\":\"")+10, informationString.indexOf(" min\","));
                double imdbRating = Double.parseDouble(informationString.substring(informationString.indexOf("imdbRating\":\"")+13, informationString.indexOf("\",\"imdbVotes")));
                if (informationString.toString().contains("Genre")) {
                    //Save the string which contains all categories to C1 for later use.
                    C1 = informationString.substring(informationString.indexOf("Genre\":\"") + 8, informationString.indexOf("\",\"Director"));
                }
                m = new Movie(1, "test", 2023); //Create a movie with the information we received
            }
        } catch (IOException e) {throw new RuntimeException(e);}
        return m;
    }

    /**
     * Returns the earlier saved string which contains all categories for the specific movie the user chose.
     * @return a String of categories attached to the specific Movie the user chose
     */
    public String getMovieCategories() {
        return C1;
    }

    public String searchAddMovieGetimdbID(String query) {
        List<Movie> searchMovies = new ArrayList<>();
        try { //Set up the connection with the query the user has written
            URL url = new URL("http://www.omdbapi.com/?" + "s=" + query + "&type=movie&apikey=40237601");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            //Checks the response code the server sends back, 200 = OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream()); //Opens up a Scanner which reads the info retrieved from OMDb
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine()); //Uses the StringBuilder to append all the lines from the data received
                }
                //Close the scanner
                scanner.close();

                String input = informationString.toString(); //Change the input to a String
                String[] splitSearch = input.split("\\{"); //Split the String into an Array at the start of each "{"

                for (int i = 2; i < splitSearch.length; i++) { //Ignore the first 2 "{" and loop through the rest;
                    String searchResults;
                    searchResults = splitSearch[i];
                    return searchResults.substring(searchResults.indexOf("imdbID\":\"")+11, searchResults.indexOf("\",\"Type"));

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String searchSelectedMovieGetPosterURL(String imdbID) {
        C1 = null;
        Movie m;
        try { //Set up the connection with the IMDbID the user has chosen
            URL url = new URL("http://www.omdbapi.com/?" + "i=tt" + imdbID + "&apikey=40237601");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            //Checks the response code the server sends back, 200 = OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream()); //Opens up a Scanner which reads the info retrieved from OMDb
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine()); //Uses the StringBuilder to append all the lines from the data received
                }
                //Close the scanner
                scanner.close();
                //Map the data into relevant info
                String poster = informationString.substring(informationString.indexOf("Poster\":\"") + 9, informationString.indexOf("\",\"Rating"));
                return poster;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}