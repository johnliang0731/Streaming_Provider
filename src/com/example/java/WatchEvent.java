package com.example.java;

import java.util.*;

public class WatchEvent {

    private static int watchViewerCount = 0;
    private static int demoIndex = -1;
    private static int watchSubscriptionFee = 0;
    private static int streamIndex = -1;
    private static String watchType = "";
    private static int watchPayPerViewPrice = 0;
    private static int watchViewingCost = 0;
    private static int movieCount = 0;
    private static int demoCurrentSpending;
    private static int streamCurrentRevenue;

    // Identify the demo group & the number of viewers affected
    public static void watchDemo(String demoGroup, int percentage, ArrayList<DemographicGroup> arrDemo) {

        for (DemographicGroup demo : arrDemo) {
            if (demo.getDemoShortName().equals(demoGroup)) {
                watchViewerCount = demo.getDemoAccounts() * percentage / 100;
                demoIndex = arrDemo.indexOf(demo);
            }
        }

    }

    // Identify the streaming service & the subscription fee
    public static void watchStream(String streaming, ArrayList<StreamingService> arrStream) {

        for (StreamingService streamServ : arrStream) {
            if (streamServ.getStreamShortName().equals(streaming)) {
                watchSubscriptionFee = streamServ.getStreamSubscription();
                streamIndex = arrStream.indexOf(streamServ);
            }
        }
    }

    // Identify the event selected & the Pay-Per-View price
    // For all events: determine event type
    public static void watchEventType(String streaming, String watchName, int year, ArrayList<OfferEvent> arrOffer) {

        for (OfferEvent offEvent : arrOffer) {
            if (offEvent.getOfferStream().equals(streaming) && offEvent.getOfferEventName().equals(watchName) && offEvent.getOfferEventYear() == year) {
                watchType = offEvent.getOfferType();
                watchPayPerViewPrice = offEvent.getOfferEventPrice();

            }
        }
    }

    // For movies: identify the increased percentage of new customers and subscription fee
    public static void watchEvent(String streaming, ArrayList<DemographicGroup> arrDemo, ArrayList<StreamingService> arrStream) {


        if (watchType.equals("movie")) {

            movieCount++;

            if (!arrDemo.get(demoIndex).getWatchGroupStreams().containsKey(streaming)) {
                arrDemo.get(demoIndex).setWatchGroupStreams(streaming, watchViewerCount);
                watchViewingCost = watchViewerCount * watchSubscriptionFee;
            }
            else {
                int demoViewerCount = arrDemo.get(demoIndex).getWatchGroupStreams().get(streaming);

                if (watchViewerCount > demoViewerCount) {
                    watchViewingCost = (watchViewerCount - demoViewerCount) * watchSubscriptionFee;
                    arrDemo.get(demoIndex).setWatchGroupStreams(streaming, watchViewerCount);
                }
                else {
                    watchViewingCost = 0;
                }
            }

        } else if (watchType.equals("ppv")) {
            // For Pay-Per-Views: identify the demo viewers affected and event price
            watchViewingCost = watchViewerCount * watchPayPerViewPrice;
        }

        // Adjust funds for watching events
        demoCurrentSpending = arrDemo.get(demoIndex).getDemoCurrentSpending();
        arrDemo.get(demoIndex).setDemoCurrentSpending(demoCurrentSpending + watchViewingCost);

        streamCurrentRevenue = arrStream.get(streamIndex).getStreamCurrentRevenue();
        arrStream.get(streamIndex).setStreamCurrentRevenue(streamCurrentRevenue + watchViewingCost);

    }

    public static boolean eventHasBeenWatched(String eventName, int year) {
        String[] tokens;
        for (String watchRecord:CLIReader.getWatches()) {
            tokens = watchRecord.split(",");
            if (tokens[4].equals(eventName) && Integer.parseInt(tokens[5]) == year) {
                return true;
            }
        }
        return false;
    }

    public static boolean demoHasWatchedEvent(String demoName) {
        String[] tokens;
        for (String watchRecord:CLIReader.getWatches()) {
            tokens = watchRecord.split(",");
            if (tokens[1].equals(demoName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean streamHasBeenAccessed(String streamName) {
        String[] tokens;
        for (String watchRecord:CLIReader.getWatches()) {
            tokens = watchRecord.split(",");
            if (tokens[3].equals(streamName)) {
                return true;
            }
        }
        return false;
    }

}