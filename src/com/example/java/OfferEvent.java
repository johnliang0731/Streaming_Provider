package com.example.java;

import java.util.*;

public class OfferEvent {

    private String offerStream;
    private String offerType;
    private String offerEventName;
    private int offerEventYear;
    private int offerEventPrice;
    private static String payStudio = "";
    private static int payLicenseFee = 0;
    private static int studioCurrentRev = 0;


    public String getOfferStream() {
        return offerStream;
    }

    public String getOfferType() {
        return offerType;
    }

    public String getOfferEventName() {
        return offerEventName;
    }

    public int getOfferEventYear() {
        return offerEventYear;
    }

    public int getOfferEventPrice() {
        return offerEventPrice;
    }

    // Constructor #1 for movies
    public OfferEvent(String offerType, String offerStream, String offerEventName, int offerEventYear) {
        this.offerStream = offerStream;
        this.offerType = offerType;
        this.offerEventName = offerEventName;
        this.offerEventYear = offerEventYear;
    }

    // Constructor #2 for PPV
    public OfferEvent(String offerType, String offerStream, String offerEventName, int offerEventYear, int offerEventPrice) {
        this.offerStream = offerStream;
        this.offerType = offerType;
        this.offerEventName = offerEventName;
        this.offerEventYear = offerEventYear;
        this.offerEventPrice = offerEventPrice;
    }

    // Check to see if the streaming service is valid
    public Boolean validStream(String streaming, ArrayList<StreamingService> arrStream) {

        for (StreamingService streamServ : arrStream) {
            if (streamServ.getStreamShortName().equals(streaming)) {
                return true;
            }
        }
        System.out.println("Invalid Stream");
        GUI.addCommandWindow("Invalid Stream\n");
        return false;
    }

    // Check to see if the movie or PPV event is valid
    public Boolean validEvent(String eventName, ArrayList<Event> arrEvent) {

        for (Event evt : arrEvent) {
            if (evt.getEventFullName().equals(eventName)) {
                return true;
            }
        }
        System.out.println("Invalid Event");
        GUI.addCommandWindow("Invalid Event\n");
        return false;
    }

    // Offer the PPV event
    public static void offerPPV(String type, String streaming, String eventName, int year, int price, ArrayList<OfferEvent> arrOffer, ArrayList<StreamingService> arrStream, ArrayList<Event> arrEvent) {
        OfferEvent offEvent = new OfferEvent(type, streaming, eventName, year, price);

        if (offEvent.validStream(streaming, arrStream) && offEvent.validEvent(eventName, arrEvent)) {
            arrOffer.add(offEvent);
            System.out.println("Offer PPV Successful");
            GUI.addCommandWindow("Offer PPV Successful\n");
        }

    }

    // Offer the movie event
    public static void offerMovie(String type, String streaming, String eventName, int year, ArrayList<OfferEvent> arrOffer, ArrayList<StreamingService> arrStream, ArrayList<Event> arrEvent) {
        OfferEvent offEvent = new OfferEvent(type, streaming, eventName, year);

        if (offEvent.validStream(streaming, arrStream) && offEvent.validEvent(eventName, arrEvent)) {
            arrOffer.add(offEvent);
            System.out.println("Offer Movie Successful");
            GUI.addCommandWindow("Offer Movie Successful\n");
        }

    }

    // The streaming service licenses the event from the studio
    public static void licenseEvent(String streaming, String eventName, int year, ArrayList<Event> arrEvent, ArrayList<StreamingService> arrStream, ArrayList<Studio> arrStudio) {

        for (Event event : arrEvent) {
            if (event.getEventFullName().equals(eventName) && event.getEventYear() == year) {
                payStudio = event.getEventStudioOwner();
                payLicenseFee = event.getEventLicenseFee();
            }
        }

        for (StreamingService streamServ : arrStream) {
            if (streamServ.getStreamShortName().equals(streaming)) {
                streamServ.setStreamLicensing(streamServ.getStreamLicensing() + payLicenseFee);
            }
        }

        for (Studio studioCo : arrStudio) {
            if (studioCo.getStudioShortName().equals(payStudio)) {
                studioCurrentRev = studioCo.getStudioCurrentRevenue();
                studioCo.setStudioCurrentRevenue(studioCurrentRev + payLicenseFee);
            }
        }
    }

    // Display all licensed events made from the streaming services to the studios
    public static void displayOffers(ArrayList<OfferEvent> arrOfferEvent) {
        for (OfferEvent eventOffered : arrOfferEvent) {
            System.out.print(eventOffered.offerStream + "," + eventOffered.offerType + "," + eventOffered.offerEventName
                    + "," + eventOffered.offerEventYear);

            if (eventOffered.offerType.equals("ppv")) {
                System.out.print("," + eventOffered.offerEventPrice);
            }

            System.out.println();
        }
    }

    public static String GUIdisplayOffers(ArrayList<OfferEvent> arrOfferEvent) {
        StringBuilder offersInfo = new StringBuilder("");
        for (OfferEvent eventOffered : arrOfferEvent) {
            offersInfo.append(eventOffered.offerStream + "," + eventOffered.offerType + "," + eventOffered.offerEventName
                    + "," + eventOffered.offerEventYear);

            if (eventOffered.offerType.equals("ppv")) {
                offersInfo.append("," + eventOffered.offerEventPrice + "\n");
            } else {
                offersInfo.append("\n");
            }

        }
        if (offersInfo.length() == 0) {
            System.out.println("No Offer Exist");
            return "No Offer Exist \n";
        } else {
            return offersInfo.toString();
        }

    }


    public static void retractMovie (OfferEvent offEvt, ArrayList<OfferEvent> arrOffer) {

        int index = -1;

        for (OfferEvent eventOffered : arrOffer) {

            if (eventOffered.getOfferEventName().equals(offEvt.getOfferEventName()) && eventOffered.getOfferStream().equals(offEvt.getOfferStream())) {
                index = arrOffer.indexOf(eventOffered);
            }
        }

        arrOffer.remove(index);
        System.out.println("The movie has been removed.");
        GUI.addCommandWindow("The movie has been removed. \n");
    }

}