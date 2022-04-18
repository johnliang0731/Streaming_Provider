package com.example.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JPanel;

public class GUI extends JFrame {
    private static String previousCMD = "";
    private static int count=0;
    private static JTextField usernameTF;
    private static JPasswordField passwordTF;
    private static JButton loginButton;
    private static JButton registerButton;
    private static JButton exitButton;
    private static JFrame frame;

    private static Box mainVerBox;
    private static Box commandHorBox;
    private static JTextField msgField;

    private static JButton commandExecButton;
    private static JButton mainExitButton;
    private static JButton startButton;
    private static JButton logoutButton;
    private static JFrame mainFrame;
    private static JLabel dateInfo;
    private static JTextArea commandWindow;
    private static JTextField commandLine;
    private static JPanel dateSelectPanel;
    private static JPanel dateInfoPanel;
    private static JPanel controlPanel;
    private static String dateInformation;
    private static JScrollPane cmdWinScroll;


    public GUI() {
        //Create a JFrame instance
        frame = new JFrame();
        frame.setTitle("Log In");
        frame.setSize(400, 350);

        //Close operation
        frame.setDefaultCloseOperation(3);

        //Center text
        frame.setLocationRelativeTo(null);

        //Create a FlowLayout instance
        FlowLayout flow = new java.awt.FlowLayout();
        frame.setLayout(flow);

        javax.swing.JLabel jla0 = new javax.swing.JLabel("Streaming War");
        frame.add(jla0);

        //Create some space
        javax.swing.JLabel enter = new javax.swing.JLabel();
        java.awt.Dimension dd = new java.awt.Dimension(400, 50);
        enter.setPreferredSize(dd);
        frame.add(enter);

        //Create a label for username
        javax.swing.JLabel usernameLabel = new javax.swing.JLabel("Username：");
        frame.add(usernameLabel);

        //Create a frame to enter username
        usernameTF = new JTextField();
        java.awt.Dimension dm2 = new java.awt.Dimension(250, 30);
        usernameTF.setPreferredSize(dm2);
        frame.add(usernameTF);

        //Create password label
        javax.swing.JLabel passwordLabel = new javax.swing.JLabel("Password： ");
        frame.add(passwordLabel);

        //Create a frame to enter password
        passwordTF = new JPasswordField();
        java.awt.Dimension dm3 = new java.awt.Dimension(250, 30);
        passwordTF.setPreferredSize(dm3);
        frame.add(passwordTF);

        java.awt.Dimension dmBTN = new java.awt.Dimension(100, 30);

        //Login button
        loginButton = new JButton("Log In");
        loginButton.setPreferredSize(dmBTN);
        frame.add(loginButton);

        //Register button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(dmBTN);
        frame.add(registerButton);

        //Exit button
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(dmBTN);
        frame.add(exitButton);

        //Create some space
        javax.swing.JLabel enter2 = new javax.swing.JLabel();
        java.awt.Dimension dd2 = new java.awt.Dimension(400, 50);
        enter2.setPreferredSize(dd2);
        frame.add(enter2);

        //Create a message field
        msgField = new JTextField(30);
        msgField.setEditable(false);
        msgField.setHorizontalAlignment(JTextField.CENTER);
        msgField.setBackground(null);
        msgField.setBorder(null);
        msgField.setText(null);
        frame.add(msgField);

        //Make the frame visible
        frame.setVisible(true);

        /*
        Main Menu UI Part
        */


        //Create the main frame
        mainFrame = new JFrame();
        mainFrame.setTitle("Main Menu");
        mainFrame.setSize(400, 350);

        //Create a Vertical Box
        mainVerBox = Box.createVerticalBox();
        commandHorBox = Box.createHorizontalBox();
        mainFrame.add(mainVerBox);

        //To exit
        mainFrame.setDefaultCloseOperation(3);

//        dateSelectPanel = new JPanel();
        dateInfoPanel = new JPanel();
        controlPanel = new JPanel();

        //Prompt to select date
        dateInfo = new JLabel("Streaming War");
        dateInfoPanel.add(dateInfo);
        mainVerBox.add(dateInfoPanel);

        mainVerBox.add(Box.createVerticalStrut(10));

        //Create a text area for display
        commandWindow = new JTextArea(10,1);
//        commandWindow.set
        commandWindow.setEditable(false);
        commandWindow.setLineWrap(true);
        commandWindow.setBackground(Color.WHITE);
        cmdWinScroll = new JScrollPane(commandWindow);
        mainVerBox.add(cmdWinScroll);

        mainVerBox.add(Box.createVerticalStrut(20));

        mainVerBox.add(commandHorBox);


        //Create a text field for command lines
        commandLine = new JTextField();
        commandLine.setMaximumSize(new Dimension(400,100));
        commandHorBox.add(commandLine);

        //Create a button for Enter
        commandExecButton = new JButton("Enter");
        commandHorBox.add(commandExecButton);

        mainVerBox.add(Box.createVerticalStrut(20));

        startButton = new JButton("Start");
        logoutButton = new JButton("Log Out");
        controlPanel.add(startButton);
        controlPanel.add(logoutButton);
        mainVerBox.add(controlPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args) {
        GUI tf = new GUI();
        Authentication auth = new Authentication();
        System.out.println("Start GUI");

        /*
        Login UI Action Part
         */

        //Get the username and password from a user
        ActionListener loginButton_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                String userName = usernameTF.getText();
                char[] password = passwordTF.getPassword();
                String strPW = String.valueOf(password);

                //Use Authentication class to check login password
                    if (auth.checkLogin(userName, strPW)) {

                        System.out.println(userName);
                        System.out.println(strPW);
                        tf.frame.setVisible(false);
                        tf.mainFrame.setVisible(true);

                    } else {

                        //Clear the username and password text fields if the login is incorrect
                        usernameTF.setText("");
                        passwordTF.setText("");

                        //Users only have 3 times of trials and errors
                        count++;
                        msgField.setText("Incorrect password!");
                        System.out.println("Incorrect password");

                        switch (count) {
                            case 0:
                            case 1:
                                break;

                            case 2: {
                                msgField.setText("Only one more attempt! Try again or register.");
                                break;
                            }

                            case 3: {
                                System.exit(0);
                            }
                        }
                    }

                }
        };
        loginButton.addActionListener(loginButton_listener);

        //Get the username and password to register a user
        ActionListener registerButton_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                String user = usernameTF.getText();
                char[] password = passwordTF.getPassword();
                String pwdStr = String.valueOf(password);

                //Check if a user exists before the register
                if(auth.checkUser(user)) {
                    msgField.setText("The user already exists.");

                }
                else {
                    auth.register(user, pwdStr);
                    msgField.setText("You're now registered. Please login again.");
                    System.out.println("You're now registered. Please login again.");
                }

                usernameTF.setText("");
                passwordTF.setText("");
                count = 0;

            }
        };
        registerButton.addActionListener(registerButton_listener);

        //To exit the program
        ActionListener exitButton_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit System");
                System.exit(0);//To exit
            }
        };
        exitButton.addActionListener(exitButton_listener);


        /*
        Main Menu UI Action Part
         */

        // Logout action listener
        ActionListener logoutButton_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Logout Confirm");
                tf.frame.setVisible(true);
                tf.mainFrame.setVisible(false);
                usernameTF.setText("");
                passwordTF.setText("");
                msgField.setText(null);
                commandWindow.setText("");
                count = 0;
            }
        };
        logoutButton.addActionListener(logoutButton_listener);


        // Start Button: creating sample studios, events, streaming services, and demographic groups
        ActionListener startButton_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CLIReader.runCLI();
            }
        };
        startButton.addActionListener(startButton_listener);

        // Command Execute Button listener
        ActionListener commandExec_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmdInfo = commandLine.getText();
                BackgroundRunning backgroundCMD = new BackgroundRunning(cmdInfo);
                // verify the command
                if (cmdInfo.equals("kill running")){
                    try{
                        backgroundCMD.cancel(true);
                        addCommandWindow("\n\"" + previousCMD + "\" is killed\n" );
                        commandLine.setText("");
                        Robust.setBusyIndicator(false);
                    }
                    catch(Exception ignore) {
                        addCommandWindow("\n Nothing to kill \n");
                    }
                }
                else if(!Robust.getBusyIndicator()) {
                    if (Robust.checkCMD(cmdInfo)) {
                        backgroundCMD.execute();
                        previousCMD = cmdInfo;
//                        dateInfo.setText("Current Month：" + CLIReader.getDateInformation());
                        commandLine.setText("");
                    }
                    else {
                        addCommandWindow("\n\n               Warning !! \n \"" + cmdInfo + "\" is an invalid command\n Please double check\n");
                    }
                }
                else{
                    addCommandWindow("\n\n               System busy!! \"" + cmdInfo + "\" cannot be executed at this time, \n Please try again later. \n" );
                }
            }
        };
        commandExecButton.addActionListener(commandExec_listener);

    }



    public static void changeCommandWindow(String showMessage) {
        commandWindow.setText(showMessage);
    }

    public static void addCommandWindow(String addMessage) {commandWindow.append(addMessage);}

    public static void updateTimeInfo(String timeInfo) {dateInfo.setText("Current Month：" + timeInfo);}
}

