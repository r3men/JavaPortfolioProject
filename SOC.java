/*
Name: Raymond Zhang
Teacher: Mr. Scimeca
Class: Computer Programing II
Date: 11/25/25
Program Name: Geo-Tracker Simulator

*/
import java.util.Scanner;

public class SOC {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean run = true;
        intro();
        while (run) {
            
        }
    }

    public static void intro() {
        System.out.println("Welcome to the Geo-Tracker Simulator!\n");
        System.out.println("In this program, you will analyze a series of suspicious login attempts using clues such as IP address ranges, ISPs, time zones, and user behavior patterns.\n");
        System.out.println("Your goal is to determine the most likely country of origin for each login, just like a real SOC (Security Operations Center) analyst investigating unauthorized access attempts.\n");
        System.out.println("Use your intuition, cybersecurity knowledge, pattern recognition, and geographic knowledge to trace where these logins came from.\n");
        System.out.println("See how well you can track down the source. Remember, your job is on the line!\n");
    }
}
