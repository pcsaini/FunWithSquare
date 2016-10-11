package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;


    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if (prefix == null){
            Random random = new Random();
            int n = random.nextInt(words.size());
            return words.get(n);
        }
        else{
            return binarySearchToFindWord(prefix,0,words.size()-1);
        }
    }

    public String binarySearchToFindWord(String prefix, int start, int end){
        int mid = (start+end)/2;
        if (end > start){
            String stringMid = words.get(mid).toString();
            if (stringMid.equals(prefix)){
                return null;
            }
            else if ((stringMid.length() > (prefix.length()-1)) && stringMid.substring(0, prefix.length() - 1).equals(prefix)){
                return stringMid;
            }
            else if (stringMid.compareTo(prefix) < 0){
                return binarySearchToFindWord(prefix,mid+1,end);
            }
            else if(stringMid.compareTo(prefix)>0){
                return binarySearchToFindWord(prefix,start,mid-1);
            }

        }
        return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
