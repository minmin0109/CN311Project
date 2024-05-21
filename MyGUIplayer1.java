import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class MyGUIplayer1 implements ActionListener {
    private JFrame frame;
    private JButton Submitbutton;
    private JButton Guessbutton;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JLabel GuessNumberLabel;
    private JTextField nametextField;
    private JTextField iptextField;
    private JTextField porttextField;
    private JTextField GuessNumbertextField;
    private getInput getInput;
    private gameApp gameApp = new gameApp();
    private OperateSecret OperateSecret;
    

    public MyGUIplayer1() {
        frame = new JFrame("Guess the Number Game");
        frame.setSize(600, 400);

        Submitbutton = new JButton("Submit");
        Guessbutton = new JButton("Guess!");
        nameLabel = new JLabel("Name: ");
        ipLabel = new JLabel("ip: ");
        portLabel = new JLabel("Port: ");
        nametextField = new JTextField(15);
        iptextField = new JTextField(10);
        porttextField = new JTextField(5);
        numberLabel = new JLabel("");

        

        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(nametextField);
        panel.add(ipLabel);
        panel.add(iptextField);
        panel.add(portLabel);
        panel.add(porttextField);
        panel.add(Submitbutton);
        panel.add(numberLabel);

        Submitbutton.addActionListener(this);
        Guessbutton.addActionListener(this);
        
        frame.add(panel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        getInput = new getInput();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Submitbutton) {
            String name = nametextField.getText();
            String ip = getInput.getIP();
            String port = getInput.getPort();
            JOptionPane.showMessageDialog(null,"Hello, " + name + " wait for another player on" + " ip: " + ip + " port:" + port);
            }
            
            JPanel panel = (JPanel) frame.getContentPane().getComponent(0);
            panel.remove(nametextField);
            panel.remove(iptextField);
            panel.remove(porttextField);
            panel.remove(Submitbutton);
            panel.revalidate();
            panel.repaint();
            
            String name = nametextField.getText();
            String ip = getInput.getIP();
            String port = getInput.getPort();
            
            nameLabel.setText("Name: " + name);
            ipLabel.setText("ip: " + ip);
            portLabel.setText("Port: " + port);
            
            // Adding updated labels to the south panel
            JPanel southPanel = (JPanel) frame.getContentPane().getComponent(1);
            southPanel.add(nameLabel);
            southPanel.add(ipLabel);
            southPanel.add(portLabel);
            southPanel.revalidate();
            southPanel.repaint();
            
            gameApp.runGame(); 

            if (e.getSource() == Guessbutton) {
                
            }


            
        // if (e.getSource() == Guessbutton) {
        //     getInput guessinput = new getInput();
        //     int[] guessArray = guessinput.getNumberGuess();
        //     for (int number : guessArray) {
        //         System.out.println("guessinput: " + number + " ");
        //     }
        // }
    }
    

    public static void main(String[] args) {
        new MyGUIplayer1();
    }

    public class OperateSecret extends Thread {
        ArrayList<String> setOfDigits = initArray();
        ArrayList<String> secret = new ArrayList<>();
        String secretString = "";
        int digit = 0;
        int position = 0;
    
        
        public void run() {
            createSecret();
        }
    
        private ArrayList<String> initArray() {
            ArrayList<String> lst = new ArrayList<>();
            lst.add("1");
            lst.add("2");
            lst.add("3");
            lst.add("4");
            lst.add("5");
            lst.add("6");
            lst.add("7");
            lst.add("8");
            lst.add("9");
            lst.add("0");
            return lst;
        }
    
        private void createSecret() {
            Random ran = new Random();
    
            for (int i = 0; i < 5; i++) {
                int index = ran.nextInt(setOfDigits.size());
                secret.add(setOfDigits.get(index));
                setOfDigits.remove(index);
            }
    
            for (String i : secret) {
                this.secretString += i;
            }
        }
    
        public String getSecret() {
            return secretString;
        }
    
        public boolean checkResult(String uesrGuess) {
            position = 0;
            digit = 0;
            for (int i = 0; i < uesrGuess.length(); i++) {
                if (uesrGuess.charAt(i) == secretString.charAt(i)) {
                    position += 1;
                }
                if (secretString.indexOf(uesrGuess.charAt(i)) != -1) {
                    digit += 1;
                }
            }
            System.out.println("position: " + position + "\tdigit: " + digit + "\n");
    
            return position == 5;
        }
        public void runGame() {
            throw new UnsupportedOperationException("Unimplemented method 'runGame'");
        }
    }

    public class gameApp {
        public int runGame() {
            OperateSecret secret = new OperateSecret();
            secret.start();
            try {
                secret.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("the secret is: " + secret.getSecret());
    
            int times = 1;
            //Scanner sc;
            String userTyping;
            ArrayList<String> lastGuess = new ArrayList<>();
            ArrayList<String> lastDigitPosition = new ArrayList<>();
            JLabel GuessNumberLabel = new JLabel("");
            JTextField GuessNumbertextField = new JTextField(5);
            boolean firstGuess = true;
            while (times <= 5) {
                if (firstGuess) { // ถ้าเป็นครั้งแรก
                    JPanel panel = (JPanel) frame.getContentPane().getComponent(0);
                    panel.add(GuessNumberLabel, BorderLayout.CENTER);
                    panel.add(GuessNumbertextField, BorderLayout.CENTER);
                    panel.add(Box.createVerticalStrut(10));
                    panel.add(Guessbutton, BorderLayout.CENTER); 
                    GuessNumberLabel.setText("GuessNumber " + times + " :"); // ตั้งค่าเลขที่ให้กับ JLabel
                    firstGuess = false; // เปลี่ยนเป็น false เพื่อไม่ให้เข้าสู่ส่วนนี้อีก
                }
                //System.out.print("enter your guess number #" + times + ": ");
                // userTyping = sc.nextLine();
                userTyping  =  GuessNumbertextField.getText();
                System.out.println(userTyping + "print");
                // while (checkInput(userTyping) != 5 || userTyping.length() > 5) {
                //     JOptionPane.showMessageDialog(null,"your text input is wrong!");
                //     //System.out.println("\nyour text input is wrong!");
                //     //sc = new Scanner(System.in);
                //     //System.out.print("enter your guess number #" + times + ": ");
                //     //userTyping = sc.nextLine();
                // }
                times += 1;
                if (secret.checkResult(userTyping)) {
                    JOptionPane.showMessageDialog(null,"You found the secret number! --> (" + secret.getSecret() + ")");
                    //System.out.println("\nYou found the secret number! --> (" + secret.getSecret() + ")");
                    break;
                }
                lastGuess.add(userTyping);
                lastDigitPosition.add(secret.position + ":" + secret.digit);
                //System.out.println("Guess history: " + lastGuess);
                //System.out.println("correction history (digit:position): " + lastDigitPosition);
            }
            // if (secret.position != 5) {
            //     JOptionPane.showMessageDialog(null,"you lose! The secret number is " + secret.secretString);
            //     //System.out.println("you lose! The secret number is " + secret.secretString);
            // }
            return times;
    
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
    

    public class getInput {
        public String getIP() {
            return iptextField.getText();
        }

        public String getPort() {
            return porttextField.getText();
        }

        public int[] getNumberGuess() {
            String numberString = GuessNumbertextField.getText();
            int[] digitsArray = new int[numberString.length()];

            for (int i = 0; i < numberString.length(); i++) {
                digitsArray[i] = Character.getNumericValue(numberString.charAt(i));
            }
            return digitsArray;
        }

    }
}