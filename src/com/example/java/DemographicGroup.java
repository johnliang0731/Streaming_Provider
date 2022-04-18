package com.example.java;

import java.util.*;

public class DemographicGroup {

    private String demoShortName;
    private String demoLongName;
    private int demoAccounts;
    private int demoCurrentSpending = 0;
    private int demoPreviousSpending = 0;
    private int demoTotalSpending = 0;
    private Map<String, Integer> watchGroupStreams = new HashMap<>();

    public Map<String, Integer> getWatchGroupStreams() {
        return watchGroupStreams;
    }

    public void setWatchGroupStreams(String streamName, int count) {
        this.watchGroupStreams.put(streamName, count);
    }

    public String getDemoShortName() {
        return demoShortName;
    }

    public String getDemoLongName() { return demoLongName; }

    public void setDemoAccounts(int demoAccounts) {
        this.demoAccounts = demoAccounts;
    }

    public int getDemoAccounts() {
        return demoAccounts;
    }

    public int getDemoCurrentSpending() {
        return demoCurrentSpending;
    }

    public void setDemoCurrentSpending(int demoCurrentSpending) {
        this.demoCurrentSpending = demoCurrentSpending;
    }

    public int getDemoPreviousSpending() { return demoPreviousSpending; }

    public void setDemoPreviousSpending(int demoPreviousSpending) {
        this.demoPreviousSpending = demoPreviousSpending;
    }

    public int getDemoTotalSpending() {
        return demoTotalSpending;
    }

    public void setDemoTotalSpending(int demoTotalSpending) {
        this.demoTotalSpending = demoTotalSpending;
    }

    // Check to see if the short name is unique
    public boolean checkDemo(ArrayList<DemographicGroup> arrList, String shortName) {
        for (DemographicGroup demo: arrList) {
            if (demo.getDemoShortName().equals(shortName)) {
                return false;
            }

        }

        return true;
    }

    // Create a demographic group
    public static void createDemo(ArrayList<DemographicGroup> arrList, String shortName, String longName, int accountNum) {
        DemographicGroup demoGroup = new DemographicGroup();
        demoGroup.demoShortName = shortName;
        demoGroup.demoLongName = longName;
        demoGroup.demoAccounts = accountNum;

        if (demoGroup.checkDemo(arrList, shortName)) {
            arrList.add(demoGroup);
            System.out.println("Create Demo successful");
            GUI.addCommandWindow("Create Demo successful\n");
        } else {
            System.out.println("The demographic group already exists.\nPlease create another one.");
            GUI.addCommandWindow("The demographic group already exists.\nPlease create another one.\n");
        }
    }

    // Update current, previous and total dollar amounts
    public static void nextMonth(ArrayList<DemographicGroup> arrList) {
        for (DemographicGroup demo : arrList) {
            demo.setDemoTotalSpending(demo.getDemoTotalSpending() + demo.getDemoCurrentSpending());
            demo.setDemoPreviousSpending(demo.getDemoCurrentSpending());
            demo.setDemoCurrentSpending(0);

            // Reset the subscription counts for the month
            demo.getWatchGroupStreams().clear();
        }
    }

    // Display information about the demographic group
    public static void displayDemo(ArrayList<DemographicGroup> arrList, String shortName) {
        for (DemographicGroup demoGroup : arrList) {
            if (demoGroup.demoShortName.equals(shortName)) {
                System.out.println("demo," + demoGroup.demoShortName + "," + demoGroup.demoLongName);
                System.out.println("size," + demoGroup.demoAccounts);
                System.out.println("current_period," + demoGroup.demoCurrentSpending);
                System.out.println("previous_period," + demoGroup.demoPreviousSpending);
                System.out.println("total," + demoGroup.demoTotalSpending);
            }
        }
    }

    // Display the demo info in GUI
    public static String GUIdisplayDemo(ArrayList<DemographicGroup> arrList, String shortName) {
        for (DemographicGroup demoGroup : arrList) {
            if (demoGroup.demoShortName.equals(shortName)) {
                String DemoInfo = "demo," + demoGroup.demoShortName + "," + demoGroup.demoLongName + "\n" +
                        "size," + demoGroup.demoAccounts + "\n" +
                        "current_period," + demoGroup.demoCurrentSpending + "\n" +
                        "previous_period," + demoGroup.demoPreviousSpending + "\n" +
                        "total," + demoGroup.demoTotalSpending + "\n";
                return DemoInfo;
            }
        }
        System.out.println("DemographicGroup " + shortName + " not exist");
        return "DemographicGroup " + shortName + " not exist \n";
    }

    // Update the number of accounts for a demo group
    public static void updateDemo(ArrayList<DemographicGroup> arrList, String shortName, int numAccount) {
        for (DemographicGroup demo: arrList) {
            if (demo.getDemoShortName().equals(shortName)) {
                demo.setDemoAccounts(numAccount);
            }
        }
    }

}