package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int aika = 4900;
        String tulos = TimeUtils.secToTime(aika);
        System.out.println("Aika " + tulos);  
    }

}
