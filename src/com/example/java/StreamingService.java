package com.example.java;

import java.util.*;

public class StreamingService {

    private String streamShortName;
    private String streamLongName;
    private int streamSubscription;
    private int streamCurrentRevenue;
    private int streamPreviousRevenue;
    private int streamTotalRevenue;
    private int streamLicensing;

    public String getStreamShortName() {
        return streamShortName;
    }

    public String getStreamLongName() { return streamLongName; }

    public int getStreamPreviousRevenue() { return streamPreviousRevenue; }

    public int getStreamSubscription() {
        return streamSubscription;
    }

    public int getStreamCurrentRevenue() {
        return streamCurrentRevenue;
    }

    public void setStreamCurrentRevenue(int streamCurrentRevenue) {
        this.streamCurrentRevenue = streamCurrentRevenue;
    }

    public void setStreamPreviousRevenue(int streamPreviousRevenue) {
        this.streamPreviousRevenue = streamPreviousRevenue;
    }

    public int getStreamTotalRevenue() {
        return streamTotalRevenue;
    }

    public void setStreamTotalRevenue(int streamTotalRevenue) {
        this.streamTotalRevenue = streamTotalRevenue;
    }

    public int getStreamLicensing() {
        return streamLicensing;
    }

    public void setStreamLicensing(int streamLicensing) {
        this.streamLicensing = streamLicensing;
    }

    public void setStreamSubscription(int streamSubscription) {
        this.streamSubscription = streamSubscription;
    }

    // Check to see if the streaming service has been created before
    public boolean checkStream(ArrayList<StreamingService> arrList, String shortName) {
        for (StreamingService streaming : arrList) {
            if (streaming.getStreamShortName().equals(shortName)) {
                return false;
            }

        }

        return true;
    }

    // Create a streaming service
    public static void createStream(ArrayList<StreamingService> arrList, String shortName, String longName, int subscriptionFee) {
        StreamingService streamServ = new StreamingService();
        streamServ.streamShortName = shortName;
        streamServ.streamLongName = longName;
        streamServ.streamSubscription = subscriptionFee;

        if (streamServ.checkStream(arrList, shortName)) {
            arrList.add(streamServ);
            System.out.println("Create Stream Successful");
            GUI.addCommandWindow("Create Stream Successful\n");
        }

        else {
            System.out.println("The streaming service already exists.\nPlease create another one.");
            GUI.addCommandWindow("The streaming service already exists.\nPlease create another one.\n");
        }
    }

    // Update current, previous and total dollar amounts
    public static void nextMonth(ArrayList<StreamingService> arrList) {
        for (StreamingService streaming : arrList) {
            streaming.setStreamTotalRevenue(streaming.getStreamTotalRevenue() + streaming.getStreamCurrentRevenue());
            streaming.setStreamPreviousRevenue(streaming.getStreamCurrentRevenue());
            streaming.setStreamCurrentRevenue(0);
        }
    }

    // Display information about the streaming service
    public static void displayStream(ArrayList<StreamingService> arrList, String shortName) {
        for (StreamingService streaming : arrList) {
            if (streaming.streamShortName.equals(shortName)) {
                System.out.println("stream," + streaming.streamShortName + "," + streaming.streamLongName);
                System.out.println("subscription," + streaming.streamSubscription);
                System.out.println("current_period," + streaming.streamCurrentRevenue);
                System.out.println("previous_period," + streaming.streamPreviousRevenue);
                System.out.println("total," + streaming.streamTotalRevenue);
                System.out.println("licensing," + streaming.streamLicensing);
            }
        }
    }

    public static String GUIdisplayStream(ArrayList<StreamingService> arrList, String shortName) {

        for (StreamingService streaming : arrList) {
            if (streaming.streamShortName.equals(shortName)) {
                String streamInfo = "stream," + streaming.streamShortName + "," + streaming.streamLongName + "\n" +
                        "subscription," + streaming.streamSubscription + "\n" +
                        "current_period," + streaming.streamCurrentRevenue + "\n" +
                        "previous_period," + streaming.streamPreviousRevenue + "\n" +
                        "total," + streaming.streamTotalRevenue + "\n" +
                        "licensing," + streaming.streamLicensing + "\n";
                return streamInfo;
            }
        }
        return "Stream " + shortName + " not exist \n";
    }

    // Update the subscription price for a streaming service
    public static void updateStream(ArrayList<StreamingService> arrList, String shortName, int price) {
        for (StreamingService streaming : arrList) {
            if (streaming.getStreamShortName().equals(shortName)) {
                streaming.setStreamSubscription(price);
            }
        }
    }

}
