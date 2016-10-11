package com.google.engedu.ghost;

import java.util.HashMap;


public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        HashMap<Character, TrieNode> temp = children;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            TrieNode trieNode;
            if (temp.containsKey(c)){
                trieNode = temp.get(c);
            }
            else{
                trieNode = new TrieNode();
                temp.put(c,trieNode);
            }
            temp = trieNode.children;
            //set leaf node
            if (i == s.length()-1){
                trieNode.isWord = true;
            }
        }
    }

    public boolean isWord(String s) {
        TrieNode trieNode = searchNode(s);
        if (trieNode != null && trieNode.isWord)
            return true;
        else
            return false;
    }
    public TrieNode searchNode(String s){
        HashMap<Character,TrieNode> temp = children;
        TrieNode trieNode = null;
        for (int i = 0; i<s.length();i++){
            char c = s.charAt(i);
            if (temp.containsKey(c)){
                trieNode = temp.get(c);
                temp = trieNode.children;
            }
            else {
                return null;
            }
        }
        return trieNode;
    }

    public String getAnyWordStartingWith(String s) {
        TrieNode trieNode = searchNode(s);
        String result =s;
        HashMap<Character,TrieNode> temp;
        if (trieNode==null){
            return null;
        }
        else{
            while (!trieNode.isWord){
                temp = trieNode.children;
                Character next = (Character)temp.keySet().toArray()[0];
                result = result + next;
                trieNode = temp.get(next);
            }
        }
        return result;
    }

    public String getGoodWordStartingWith(String s) {
        TrieNode currentNode =  this;
        return null;
    }
}
