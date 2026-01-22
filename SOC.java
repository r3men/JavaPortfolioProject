/**
 * Name: Raymond Zhang
 * Teacher: Mr. Scimeca
 * Class: Computer Programing II
 * Date: 11/25/25
 * Program Description:
 *    - This program is designed for those interested in learning about the career of SOC analysts,
 *      or those who want to learn a bit of geography instead! The program utilizes several methods
 *      for increased program readability.
 */

import java.util.*;

public class SOC {
    static ArrayList<AttackEntry> attacks = new ArrayList<>();
    static String[] latestReport = null;

    /**
     * The main entry point of the program. Initializes the attack list,
     * displays the introduction, and manages the main menu loop where
     * the user can view instructions, start investigations, view reports,
     * or exit the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean run = true;
        ArrayList<Integer> usedIndices = new ArrayList<>();
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
                    latestReport = startInvestigation(attacks, usedIndices);
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

    /**
     * Displays the introduction message for the Geo-Tracker Simulator.
     * This method explains the purpose of the program and sets the scene
     * for the user, describing how they will analyze suspicious login attempts
     * using SOC-style investigative techniques.
     */
    public static void intro() {
        System.out.println("Welcome to the Geo-Tracker Simulator!\n");
        System.out.println("In this program, you will analyze a series of suspicious login attempts using clues such as IP address ranges, ISPs, time zones, and user behavior patterns.\n");
        System.out.println("Your goal is to determine the most likely country of origin for each login, just like a real SOC (Security Operations Center) analyst investigating unauthorized access attempts.\n");
        System.out.println("Use your intuition, cybersecurity knowledge, pattern recognition, and geographic knowledge to trace where these logins came from.\n");
        System.out.println("See how well you can track down the source. Remember, your job is on the line!\n");
    }

    /**
     * Displays detailed instructions for how the investigation game works.
     * This includes how clues are presented, how scoring works, and how
     * the user should approach each investigation.
     */
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

    /**
     * Begins a new investigation by selecting a random unused attack entry.
     * The user is presented with clues and can choose to guess the country,
     * request hints, or give up. The method tracks score deductions based on
     * hints used and incorrect guesses.
     *
     * @param attacks      the list of all possible attack entries
     * @param usedIndices  a list of indices representing attacks already used
     * @return a String array containing the final score, number of hints used,
     *         number of wrong answers, correct country, and the index of the attack;
     *         returns null if all investigations have been completed
     */
    public static String[] startInvestigation(ArrayList<AttackEntry> attacks, ArrayList<Integer> usedIndices) {
        Scanner s = new Scanner(System.in);
        if (usedIndices.size() == attacks.size()) {
            System.out.println("All investigations have been completed. No new cases are available.");
            return null;
        }
        int returnIndex;
        do {
            returnIndex = (int) (Math.random() * attacks.size());
        } while (usedIndices.contains(returnIndex));
        usedIndices.add(returnIndex);
        boolean giveUp = false;
        boolean allHints = false;
        int score = 100;
        int hints = 0;
        int wrongAnswers = 0;
        String country;
        AttackEntry randomAttack = attacks.get(returnIndex);
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
            int userChoice = s.nextInt();
            s.nextLine();
            if (userChoice == 1) {
                String guess = s.nextLine();
                if (guess.equalsIgnoreCase(randomAttack.countryName)) {
                    System.out.println("Congratulations, you correctly identified the country of origin!");
                    score -= ((wrongAnswers * 10) + (hints * 5));
                    System.out.println("For this investigation, you received a score of " + score + ". Nice job!");
                    break;
                }
                else {
                    System.out.println("Unfortunately that was the wrong answer. 10 points have been deducted from your total score for this investigation.");
                    wrongAnswers += 1;
                }
            }
            else if (userChoice == 2) {
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
            else if (userChoice == 3) {
                System.out.println("Nice try. Unfortunately you received a score of 0 for this investigation. Better luck next time!");
                score = 0;
                giveUp = true;
            }
            else {
                System.out.println("You did not enter a valid option.");
            }
            System.out.println("\n");
        }
        String[] investigationReport = new String[5];
        investigationReport[0] = Integer.toString(score);
        investigationReport[1] = Integer.toString(hints);
        investigationReport[2] = Integer.toString(wrongAnswers);
        investigationReport[3] = country;
        investigationReport[4] = Integer.toString(returnIndex);
        return investigationReport;
    }

    /**
     * Displays the results of the most recently completed investigation.
     * This includes the correct country, the user's score, and statistics
     * about hints and incorrect guesses.
     *
     * @param report a String array containing the latest investigation data:
     *               [0] score, [1] hints used, [2] wrong answers,
     *               [3] country, [4] attack index
     */
    public static void viewLatestReport(String[] report) {
        System.out.println("Here is information regarding your latest report: ");
        System.out.println("Country of Origin: " + report[3]);
        System.out.println("Total Score for Investigation: " + report[0]);
        System.out.println("# of hints used: " + report[1]);
        System.out.println("# of wrong answers submitted: " + report[2]);
    }

    /**
     * Populates the list of attack entries with predefined sample data.
     * Each AttackEntry contains a country, IP prefix, ISP, timezone,
     * and three progressively revealing hints used during investigations.
     */
    public static void populateAttacks() {
        attacks.add(new AttackEntry("Germany", "192.168.1.", "Deutsche Telekom", "CET",
        "Uses umlauts in text", "Known for beer", "Famous for cars"));
        attacks.add(new AttackEntry("Japan", "203.0.113.", "NTT Communications", "JST",
        "Drives on the left side of the road", "Has stunning cherry blossoms", "It's very well known for its anime industry"));
        attacks.add(new AttackEntry("Brazil", "200.0.0.", "Oi", "BRT",
        "Home to the city of Fortaleza.", "The Amazon River runs through this country.", "Prominence in soccer"));
    }
}
