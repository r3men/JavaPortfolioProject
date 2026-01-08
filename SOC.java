/*
Name: Raymond Zhang
Teacher: Mr. Scimeca
Class: Computer Programing II
Date: 11/25/25
Program Name: Geo-Tracker Simulator
*/

import java.util.*;

public class SOC {
    static ArrayList<AttackEntry> attacks = new ArrayList<>();
    static String[] latestReport = null;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean run = true;
        ArrayList<Integer> rIDXs = new ArrayList<>();
        populateAttacks();
        intro();
        while (run) {
            System.out.println("\nWhat would you like to do?");
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
                    latestReport = startInvestigation(attacks, rIDXs);
                    break;
                case 'C':
                    if (latestReport == null) {
                        System.out.println("No investigations have been completed yet. Please start an investigation in order to receive a report.");
                    }
                    else {
                        viewLatestReport(latestReport);
                    }
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

    public static String[] startInvestigation(ArrayList<AttackEntry> attacks, ArrayList<Integer> usedIndexes) {
        Scanner s = new Scanner(System.in);
        if (usedIndexes.size() == attacks.size()) {
            System.out.println("All investigations have been completed. No new cases are available.");
            return null;
        }
        int rIDX;
        do {
            rIDX = (int) (Math.random() * attacks.size());
        } while (usedIndexes.contains(rIDX));
        usedIndexes.add(rIDX);
        boolean giveUp = false;
        boolean allHints = false;
        int score = 100;
        int hints = 0;
        int wa = 0;
        String country;
        AttackEntry randomAttack = attacks.get(rIDX);
        country = randomAttack.countryName;
        while (!giveUp) {
            System.out.println("A suspicious login was detected from an unknown country. Help us identify where it came from!");
            System.out.println("Here is what you know so far: ");
            System.out.println("IP Prefix: " + randomAttack.ipPrefix);
            System.out.println("ISP: " + randomAttack.ISP);
            System.out.println("Timezone: " + randomAttack.timezone);
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Make a Guess");
            System.out.println("2. Receive a Hint");
            System.out.println("3. Give Up");
            int ans = s.nextInt();
            s.nextLine();
            if (ans == 1) {
                String guess = s.nextLine();
                if (guess.equalsIgnoreCase(randomAttack.countryName)) {
                    System.out.println("Congratulations, you correctly identified the country of origin!");
                    score -= ((wa * 10) + (hints * 5));
                    System.out.println("For this investigation, you received a score of " + score + ". Nice job!");
                    break;
                }
                else {
                    System.out.println("Unfortunately that was the wrong answer. 10 points have been deducted from your total score for this investigation.");
                    wa += 1;
                }
            }
            else if (ans == 2) {
                if (!allHints) {
                    hints += 1;
                }
                if (hints == 1) {
                    System.out.println("Hint 1: " + randomAttack.hint1);
                }
                else if (hints == 2) {
                    System.out.println("Hint 2: " + randomAttack.hint2);
                }
                else if (hints == 3) {
                    System.out.println("Hint 3: " + randomAttack.hint3);
                }
                else {
                    System.out.println("You have used the three available hints. Use what you know to figure out the correct country!");
                    allHints = true;
                }
            }
            else if (ans == 3) {
                System.out.println("Nice try. Unfortunately you received a score of 0 for this investigation. Better luck next time!");
                score = 0;
                giveUp = true;
            }
            else {
                System.out.println("You did not enter a valid option.");
            }
            System.out.println("\n");
        }
        String[] reportInfo = new String[5];
        reportInfo[0] = Integer.toString(score);
        reportInfo[1] = Integer.toString(hints);
        reportInfo[2] = Integer.toString(wa);
        reportInfo[3] = country;
        reportInfo[4] = Integer.toString(rIDX);
        return reportInfo;
    }

    public static void viewLatestReport(String[] report) {
        System.out.println("Here is information regarding your latest report: ");
        System.out.println("Country of Origin: " + report[3]);
        System.out.println("Total Score for Investigation: " + report[0]);
        System.out.println("# of hints used: " + report[1]);
        System.out.println("# of wrong answers submitted: " + report[2]);
    }

    public static void populateAttacks() {
        attacks.add(new AttackEntry("Germany", "192.168.1.", "Deutsche Telekom", "CET",
        "Uses umlauts in text", "Known for beer", "Famous for cars"));
    attacks.add(new AttackEntry("Japan", "203.0.113.", "NTT Communications", "JST",
        "Drives on the left side of the road", "Has stunning cherry blossoms", "It's very well known for its anime industry"));
    attacks.add(new AttackEntry("Brazil", "200.0.0.", "Oi", "BRT",
        "Home to the city of Fortaleza.", "The Amazon River runs through this country.", "Prominence in soccer"));
    }
}
