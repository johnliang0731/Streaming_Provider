package com.example.java;

import java.util.*;

public class Event {

    private String eventType;
    private String eventFullName;
    private String eventStudioOwner;
    private int eventYear;
    private int eventDuration;
    private int eventLicenseFee;

    public String getEventType() {
        return eventType;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public String getEventFullName() {
        return eventFullName;
    }

    public String getEventStudioOwner() {
        return eventStudioOwner;
    }

    public int getEventYear() {
        return eventYear;
    }

    public int getEventLicenseFee() {
        return eventLicenseFee;
    }

    public void setEventLicenseFee(int eventLicenseFee) {
        this.eventLicenseFee = eventLicenseFee;
    }

    // Check to see if the movie or PPV event is unique
    public boolean checkEvent(ArrayList<Event> arrList, String name, int year) {
        for (Event evt : arrList) {
            if (evt.eventFullName.equals(name) && evt.eventYear == year) {
                return false;
            }

        }

        return true;
    }

    // Check to see if the studio has been created before
    public Boolean validStudio(String studioShortName, ArrayList<Studio> arrStudio) {

        for (Studio studioOwner : arrStudio) {
            if (studioOwner.getStudioShortName().equals(studioShortName)) {
                return true;
            }
        }

        return false;
    }

    // Create an event
    public static void createEvent(ArrayList<Event> arrList, String type, String name, int year, int duration, String studioOwner, int licenseFee, ArrayList<Studio> arrStudio) {
        Event eventName = new Event();
        eventName.eventType = type;
        eventName.eventFullName = name;
        eventName.eventYear = year;
        eventName.eventDuration = duration;
        eventName.eventStudioOwner = studioOwner;
        eventName.eventLicenseFee = licenseFee;

        if (eventName.checkEvent(arrList, name, year) && eventName.validStudio(studioOwner, arrStudio)) {
            arrList.add(eventName);
            System.out.println("Create Event successful");
            GUI.addCommandWindow("Create Event successful\n");
        } else {
            System.out.println("The event already exists.\nPlease create another one.");
            GUI.addCommandWindow("The event already exists.\nPlease create another one.\n");
        }

    }

    // Display all movies and PPV events
    public static void displayEvents(ArrayList<Event> arrList) {
        for (Event evt : arrList) {
            System.out.println(evt.eventType + "," + evt.eventFullName + "," + evt.eventYear + "," + evt.eventDuration
                    + "," + evt.eventStudioOwner + "," + evt.eventLicenseFee);
        }

    }

    public static String GUIdisplayEvents(ArrayList<Event> arrList) {
        StringBuilder eventInfo = new StringBuilder("");
        for (Event evt : arrList) {
            eventInfo.append(evt.eventType + "," + evt.eventFullName + "," + evt.eventYear + "," + evt.eventDuration
                    + "," + evt.eventStudioOwner + "," + evt.eventLicenseFee + "\n");
        }
        if (eventInfo.length() == 0) {
            System.out.println("No Event Exist");
            return "No Event Exist \n";
        } else {
            return eventInfo.toString();
        }
    }


    // Update the license fee for an event
    public static void updateEvent(ArrayList<Event> arrList, String name, int year, int licenseFee) {
        for (Event evt : arrList) {
            if (evt.eventFullName.equals(name) && evt.eventYear == year) {
                evt.setEventLicenseFee(licenseFee);
            }
        }
    }
}
