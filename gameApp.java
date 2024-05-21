import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
public class gameApp {
    public void runGame() {
        OperateSecret secret = new OperateSecret();
        secret.start();
        try {
            secret.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("the secret is: " + secret.getSecret());

        int times = 1;
        Scanner sc;
        String userTyping;
        ArrayList<String> lastGuess = new ArrayList<>();
        ArrayList<String> lastDigitPosition = new ArrayList<>();
        while (times <= 5) {
            sc = new Scanner(System.in);
            System.out.print("enter your guess number #" + times + ": ");
            userTyping = sc.nextLine();
            while (checkInput(userTyping) != 5 || userTyping.length() > 5) {
                System.out.println("\nyour text input is wrong!");
                sc = new Scanner(System.in);
                System.out.print("enter your guess number #" + times + ": ");
                userTyping = sc.nextLine();
            }
            times += 1;
            if (secret.checkResult(userTyping)) {
                System.out.println("\nYou found the secret number! --> (" + secret.getSecret() + ")");
                break;
            }
            lastGuess.add(userTyping);
            lastDigitPosition.add(secret.position + ":" + secret.digit);
            System.out.println("Guess history: " + lastGuess);
            System.out.println("correction history (digit:position): " + lastDigitPosition);
        }
        if (secret.position != 5) {
            System.out.println("you lose! The secret number is " + secret.secretString);
        }

    }

    private static int checkInput(String data) {
        // นับจำนวน element ที่ไม่ซ้ำกัน
        // HashSet is a collection of items where every item is unique
        HashSet<Character> setElement = new HashSet<>();

        for (int i = 0; i < data.length(); i++) {
            // lst.add(data.charAt(i));
            setElement.add(data.charAt(i));
        }
        return setElement.size();
    }
}
