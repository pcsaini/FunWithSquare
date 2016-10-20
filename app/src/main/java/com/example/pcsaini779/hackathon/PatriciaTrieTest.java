package com.example.pcsaini779.hackathon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by jsrathore on 10/11/16.
 */
public class PatriciaTrieTest {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Random r=new Random();
        /* Creating object of PatriciaTrie */
        PatriciaTrie pt = new PatriciaTrie();
        System.out.println("Patricia Trie Test\n");
        String Square="YES";
        //String Qube="YES";
        //String Qurter="YES";
        int digit3=3;//digit4=4,digit5=5;

        /*  Perform trie operations  */





//System.out.println("Start is = "+start);
//System.out.println("End is "+end);

    }
       /* if(Qube=="YES"){
            for(int i=1; i<100; i++){
                pt.insert(i*i*i);
            }}
        if(Qurter=="YES"){
            for(int i=1; i<50; i++){
                pt.insert(i*i*i*i);
            }}



        do
        {
            System.out.println("\nPatricia Trie Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. check emepty");
            System.out.println("4. make emepty");

            int choice = scan.nextInt();
            switch (choice)
            {
            case 1 :
                System.out.println("Enter key element to insert");
                pt.insert( scan.nextInt() );
                break;
            case 2 :
                System.out.println("Enter key element to search");
                System.out.println("Search result : "+ pt.search( scan.nextInt() ));
                break;
            case 3 :
                System.out.println("Empty Status : "+ pt.isEmpty() );
                break;
            case 4 :
                System.out.println("Patricia Trie cleared");
                pt.makeEmpty();
                break;
            default :
                System.out.println("Wrong Entry \n ");
                break;
            }

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');*/

    public  static ArrayList<Integer> square(int X,int Z){
        ArrayList<Integer> squar = new ArrayList<Integer>();
        for(int i=X; i<Z; i++){
            squar.add(i*i);
        }
        return squar;
    }
    public   ArrayList<Integer>  game(PatriciaTrie pt,int digit3,String Square){
        Random r= new Random();
        int y=0;
        ArrayList<Integer> show =new ArrayList<Integer>();
        if(Square=="YES"){
            for(int i=1; i<100; i++){
                pt.insert(i*i);
            }

            if(digit3==3){
                int count=0,start=0,end=0;
                for(int i=1; i<100; i++)
                {
                    int w=0;
                    int k=i*i;
                    while(k>0){
                        k=k/10;
                        w++;
                    }
                    if(w==3){
                        if(count==0){
                            start =i;
                            count=1;
                        }
                        else{
                            end=i;
                        }

                    }

                }
                show=square(start,end);
            }
            if(digit3==4){
                int count=0,start=0,end=0;
                for(int i=1; i<100; i++)
                {
                    int w=0;
                    int k=i*i;
                    while(k>0){
                        k=k/10;
                        w++;
                    }
                    if(w==4){
                        if(count==0){
                            start =i;
                            count=1;
                        }
                        else{
                            end=i;
                        }

                    }

                }
                show=square(start,end);
            }
            if(digit3==5){
                int count=0,start=0,end=0;
                for(int i=1; i<320; i++)
                {
                    int w=0;
                    int k=i*i;
                    while(k>0){
                        k=k/10;
                        w++;
                    }
                    if(w==5){
                        if(count==0){
                            start =i;
                            count=1;
                        }
                        else{
                            end=i;
                        }

                    }

                }
                show=square(start,end);
            }
        }


        return show;
    }

    /*public  static ArrayList<Integer> qube(int X,int Z){
        ArrayList<Integer> qub = new ArrayList<Integer>();
        int x=5,y=10;
        for(int i=x; i<y; i++){
            qub.add(i*i*i);
        }
        return qub;
    }
    public  static  ArrayList<Integer> Quater(int X,int Z){
        ArrayList<Integer> Quat = new ArrayList<Integer>();
        int x=5,y=10;
        for(int i=x; i<y; i++){
            Quat.add(i*i*i*i);
        }
        return Quat;
    }*/
}
