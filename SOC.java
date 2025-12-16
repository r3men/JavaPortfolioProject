/*
Name: Raymond Zhang
Teacher: Mr. Scimeca
Class: Computer Programing II
Date: 11/25/25
Program Name: Geo-Tracker Simulator

*/

import java.util.*;

public class SOC {
    static ArrayList<AttackEntry> attacks = new ArrayList();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean run = true;
        intro();
        while (run) {
            System.out.println("What would you like to do?");
            System.out.println("A. View Instructions");
            System.out.println("B. Start Investigation");
            System.out.println("C. View Latest Investigation Report");
            System.out.println("D. Go home and relax!");
            System.out.print("Enter the corresponding letter of the option: ");
            char option = Character.toUpperCase(s.next().charAt(0));
            switch (option) {
                case 'A':
                    displayInstructions();
                    break;
                case 'B':
                    startInvestigation(attacks);
                    break;
                case 'C':
                    viewLatestReport();
                    break;
                case 'D':
                    run = false;
                    System.out.println("Thank you for your hard work today! Come back tomorrow for your next session!");
                    break;
                default:
                    System.out.println("You did not enter a valid option.");
            }
        }
    }

    public static void intro() {
        System.out.println("Welcome to the Geo-Tracker Simulator!\n");
        System.out.println("In this program, you will analyze a series of suspicious login attempts using clues such as IP address ranges, ISPs, time zones, and user behavior patterns.\n");
        System.out.println("Your goal is to determine the most likely country of origin for each login, just like a real SOC (Security Operations Center) analyst investigating unauthorized access attempts.\n");
        System.out.println("Use your intuition, cybersecurity knowledge, pattern recognition, and geographic knowledge to trace where these logins came from.\n");
        System.out.println("See how well you can track down the source. Remember, your job is on the line!\n");
    }

    public static void displayInstructions() {
        System.out.println("\n===== INSTRUCTIONS =====\n");
        System.out.println("Each investigation will provide you with a series of clues such as:");
        System.out.println("- IP address prefix");
        System.out.println("- Internet Service Provider (ISP)");
        System.out.println("- Time zone information");
        System.out.println("- Language or behavior hints\n");
        System.out.println("Your goal is to:");
        System.out.println("1. Analyze the clues carefully");
        System.out.println("2. Determine the most likely country of origin");
        System.out.println("3. Enter your guess when prompted\n");
        System.out.println("Each investigation begins with a value of 100 points. Each hint used is a deduction of 5 points, while each wrong answer will deduct 10 points from your total score for that investigation.");
        System.out.println("Answer wisely!");
        System.out.println("Accuracy and efficiency matter—your SOC team is counting on you.\n");
        System.out.println("When you're ready, return to the main menu and start a new investigation.");
        System.out.println("\n==========================\n");
    }

    public static String[] startInvestigation(ArrayList<AttackEntry> attacks) {
        Scanner s = new Scanner(System.in);
        int rIDX = (int) (Math.random() * attacks.size());
        boolean giveUp = false;
        int score = 100;
        int hints = 0;
        int wa = 0;
        while (!giveUp) {
            AttackEntry randomAttack = attacks.get(rIDX);
            System.out.println("A suspicious login was detected from an unknown country. Help us identify where it came from!");
            System.out.println("Here is what you know so far: ");
            System.out.println("IP Prefix: " + randomAttack.ipPrefix);
            System.out.println("ISP: " + randomAttack.ISP);
            System.out.println("Timezone: " + randomAttack.timezone);
            System.out.println("What would you like to do?");
            System.out.println("1. Make a Guess");
            System.out.println("2. Receive a Hint");
            int ans = s.nextInt();
            if (ans == 1) {
                String guess = s.nextLine();
                if (guess.equals(randomAttack.countryName)) {
                    System.out.println("Congratulations, you correctly identified the country of origin!");
                    score -= ((wa * 10) + (hints * 5));
                    System.out.println("For this investigation, you received a score of " + score + " . Nice job!");
                    giveUp = true;
                }
                else {
                    wa += 1;
                }
            }
            else if (ans == 2) {
                hints += 1;
            }
            else {
                System.out.println("You did not enter a valid option.");
            }
        }
    }

    public static void viewLatestReport(String[] report) {
        System.out.println("Here is information regarding your latest report: ");
    }
}
