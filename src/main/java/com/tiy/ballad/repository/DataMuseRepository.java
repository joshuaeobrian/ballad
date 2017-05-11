package com.tiy.ballad.repository;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLConnection;

/**
 * Created by josh on 4/27/17.
 */
@Component
public class DataMuseRepository {
        private final String SUGGESTED = "sug?s=";
        private final String RHYMING = "https://api.datamuse.com/words?rel_rhy=";
        private final String RELATED_TO = "/words?ml=breakfast&rel_rhy=";

        private final String API_URL = "https://api.datamuse.com/";

//        public String listWords(String word){
//            String words = getJsonAsString("words?sl="+word);
//            System.out.println(words);
//            return words;
//        }

        public String getSuggestedWords(String  word){
            return getJsonAsString(API_URL + SUGGESTED + word);
        }
        public String getWordsThatRhymeWith(String word){
            String url = RHYMING+word;
            return getJsonAsString(url);
        }



        private String getJsonAsString(String apiUrl) {
            URL url;
            URLConnection connection;
            StringBuilder stringBuilder = null;
            try {
                url = new URL(apiUrl);
                connection = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;
                stringBuilder = new StringBuilder();
                while ((inputLine = reader.readLine()) != null)
                    stringBuilder.append(inputLine);
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder != null ? stringBuilder.toString() : null;
        }


    public String getRhymingWordsRelatedTo(String word, String category) {
        return null;
    }
}
