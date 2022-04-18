package com.example.java;

import java.util.*;

public class Studio {

    private String studioShortName;
    private String studioLongName;
    private int studioCurrentRevenue;
    private int studioPreviousRevenue;
    private int studioTotalRevenue;

    public String getStudioShortName() {
        return studioShortName;
    }

    public String getStudioLongName() { return studioLongName; }

    public int getStudioPreviousRevenue() { return studioPreviousRevenue; }

    public int getStudioCurrentRevenue() {
        return studioCurrentRevenue;
    }

    public void setStudioCurrentRevenue(int studioCurrentRevenue) {
        this.studioCurrentRevenue = studioCurrentRevenue;
    }

    public void setStudioPreviousRevenue(int studioPreviousRevenue) {
        this.studioPreviousRevenue = studioPreviousRevenue;
    }

    public int getStudioTotalRevenue() {
        return studioTotalRevenue;
    }

    public void setStudioTotalRevenue(int studioTotalRevenue) {
        this.studioTotalRevenue = studioTotalRevenue;
    }

    // Check to see if the short name is unique
    public boolean checkStudio(ArrayList<Studio> arrList, String shortName) {
        for (Studio studioOwner : arrList) {
            if (studioOwner.getStudioShortName().equals(shortName)) {
                return false;
            }

        }

        return true;
    }

    // Create a studio
    public static void createStudio(ArrayList<Studio> arrList, String shortName, String longName) {
        Studio studioCompany = new Studio();
        studioCompany.studioShortName = shortName;
        studioCompany.studioLongName = longName;

        if (studioCompany.checkStudio(arrList,shortName)) {
            arrList.add(studioCompany);
            System.out.println("Create Studios successful");
            GUI.addCommandWindow("Create Studios successful\n");
        } else {
            System.out.println("The studio already exists.\nPlease create another one.");
            GUI.addCommandWindow("The studio already exists.\nPlease create another one.\n");
        }
    }

    // Update current, previous and total dollar amounts
    public static void nextMonth(ArrayList<Studio> arrList) {
        for (Studio studios : arrList) {
            studios.setStudioTotalRevenue(studios.getStudioTotalRevenue() + studios.getStudioCurrentRevenue());
            studios.setStudioPreviousRevenue(studios.getStudioCurrentRevenue());
            studios.setStudioCurrentRevenue(0);
        }
    }

    // Display information about the studio
    public static void displayStudio(ArrayList<Studio> arrList, String shortName) {
        for (Studio studioName : arrList) {
            if (studioName.studioShortName.equals(shortName)) {
                System.out.println("studio," + studioName.studioShortName + "," + studioName.studioLongName);
                System.out.println("current_period," + studioName.studioCurrentRevenue);
                System.out.println("previous_period," + studioName.studioPreviousRevenue);
                System.out.println("total," + studioName.studioTotalRevenue);
            }
        }
    }

    public static String GUIdisplayStudio(ArrayList<Studio> arrList, String shortName) {
        for (Studio studioName : arrList) {
            if (studioName.studioShortName.equals(shortName)) {
                String studioInfo = "studio," + studioName.studioShortName + "," + studioName.studioLongName + "\n" +
                        "current_period," + studioName.studioCurrentRevenue + "\n" +
                        "previous_period," + studioName.studioPreviousRevenue + "\n" +
                        "total," + studioName.studioTotalRevenue + "\n";
                return studioInfo;
            }
        }
        System.out.println("Studio " + shortName + " not exist");
        return "Studio " + shortName + " not exist \n";
    }

}
