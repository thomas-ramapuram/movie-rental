package net.ramapuram.elia;

/**
 * Created by Elia Thomas Ramapuram on 24/10/16.
 * as part of the Computer Science Project 2016-17
 */
public class Util {
    /**
     * Is a util method which returns a string that repeats a character count number of times
     * @param c
     * @param count
     * @return
     */
    public static String repeat(char c, int count){
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x < count; x++){
            sb.append(c);
        }
        return sb.toString();
    }
}
