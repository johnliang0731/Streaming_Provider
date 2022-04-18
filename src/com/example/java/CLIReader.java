package com.example.java;

import java.util.ArrayList;
public class CLIReader {

    private static ArrayList<DemographicGroup> demo = new ArrayList<>();
    private static ArrayList<StreamingService> stream = new ArrayList<>();
    private static ArrayList<Studio> studios = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<OfferEvent> offerEvents = new ArrayList<>();
    private static ArrayList<String> watches = new ArrayList<>();

    private static int monthTimeStamp;
    private static int yearTimeStamp;

    public static void readSavedData() {
        IOmodule.readDemoFile();
        IOmodule.readStudioFile();
        IOmodule.readStreamFile();
        IOmodule.readEventFile();
        IOmodule.readOfferEventFile();
        IOmodule.readWatchFile();
        IOmodule.readTimeFile();
        GUI.changeCommandWindow("Initial System Successful\n");
        GUI.updateTimeInfo(getDateInformation());
    }

    public static void processInstructions(Boolean verboseMode, String inputLine) {

        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        try {

            wholeInputLine = inputLine;
            tokens = wholeInputLine.split(DELIMITER);
            System.out.println("> " + wholeInputLine);

            if (tokens[0].equals("create_demo")) {
                if (verboseMode) {
                    System.out.println("create_demo_acknowledged");
                    GUI.addCommandWindow("Create Demo Acknowledged\n");
                }
                DemographicGroup.createDemo(demo, tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                IOmodule.writeDemoFile(demo);

            } else if (tokens[0].equals("create_studio")) {
                if (verboseMode) {
                    System.out.println("create_studio_acknowledged");
                    GUI.addCommandWindow("Create Studios Acknowledged\n");
                }

                Studio.createStudio(studios, tokens[1], tokens[2]);
                IOmodule.writeStudioFile(studios);


            } else if (tokens[0].equals("create_event")) {
                if (verboseMode) {
                    System.out.println("create_event_acknowledged");
                    GUI.addCommandWindow("Create Event Acknowledged\n");}

                Event.createEvent(events, tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), studios);
                IOmodule.writeEventFile(events);


            } else if (tokens[0].equals("create_stream")) {
                if (verboseMode) {
                    System.out.println("create_stream_acknowledged");
                    GUI.addCommandWindow("Create Stream Acknowledged\n");
                }

                StreamingService.createStream(stream, tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                IOmodule.writeStreamFile(stream);


            } else if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
                if (verboseMode) {
                    System.out.println(tokens[0] + "_acknowledged");
                    GUI.addCommandWindow(tokens[0] + " _acknowledged\n");
                }

                if (tokens[0].equals("offer_ppv")) {
                    OfferEvent.offerPPV(tokens[0].substring(6), tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), offerEvents, stream, events);
                }

                else {
                    OfferEvent.offerMovie(tokens[0].substring(6), tokens[1], tokens[2], Integer.parseInt(tokens[3]), offerEvents, stream, events);
                }

                // The streaming service must license the event from the studio
                OfferEvent.licenseEvent(tokens[1], tokens[2], Integer.parseInt(tokens[3]), events, stream,studios);
                IOmodule.writeOfferEventFile(offerEvents);

            } else if (tokens[0].equals("watch_event")) {
                if (verboseMode) {
                    System.out.println("watch_event_acknowledged");
                    GUI.addCommandWindow("Watch Event Acknowledged\n");
                }

                WatchEvent.watchDemo(tokens[1], Integer.parseInt(tokens[2]), demo);
                WatchEvent.watchStream(tokens[3], stream);
                WatchEvent.watchEventType(tokens[3], tokens[4], Integer.parseInt(tokens[5]), offerEvents);
                WatchEvent.watchEvent(tokens[3], demo, stream);

                watches.add(wholeInputLine);
                IOmodule.writeWatchFile(watches);

            } else if (tokens[0].equals("next_month")) {
                if (verboseMode) { System.out.println("next_month_acknowledged"); }

                // Update the current timestamp
                if (monthTimeStamp == 12) { yearTimeStamp++; }
                monthTimeStamp = (monthTimeStamp % 12) + 1;

                DemographicGroup.nextMonth(demo);
                StreamingService.nextMonth(stream);
                Studio.nextMonth(studios);

                // Remove all movie and Pay-Per-View offerings
                offerEvents.clear();
                watches = new ArrayList<>();
                IOmodule.writeOfferEventFile(offerEvents);
                IOmodule.writeWatchFile(watches);
                IOmodule.writeTimeFile(monthTimeStamp,yearTimeStamp);
                GUI.updateTimeInfo(getDateInformation());
                GUI.addCommandWindow("Switch to next month successful\n");

            } else if (tokens[0].equals("display_demo")) {
                if (verboseMode) {
                    System.out.println("display_demo_acknowledged");
                    GUI.addCommandWindow("Display Demo Acknowledged\n");
                }

                DemographicGroup.displayDemo(demo, tokens[1]);
                GUI.addCommandWindow(DemographicGroup.GUIdisplayDemo(demo, tokens[1]));

            } else if (tokens[0].equals("display_events")) {
                if (verboseMode) {
                    System.out.println("display_events_acknowledged");
                    GUI.addCommandWindow("Display Events Acknowledged\n");
                }

                Event.displayEvents(events);
                GUI.addCommandWindow(Event.GUIdisplayEvents(events));

            } else if (tokens[0].equals("display_stream")) {
                if (verboseMode) {
                    System.out.println("display_stream_acknowledged");
                    GUI.addCommandWindow("Display Stream Acknowledged\n");
                }

                StreamingService.displayStream(stream, tokens[1]);
                GUI.addCommandWindow(StreamingService.GUIdisplayStream(stream, tokens[1]));

            } else if (tokens[0].equals("display_studio")) {
                if (verboseMode) {
                    System.out.println("display_studio_acknowledged");
                    GUI.addCommandWindow("Display Studio Acknowledged\n");
                }

                Studio.displayStudio(studios, tokens[1]);
                GUI.addCommandWindow(Studio.GUIdisplayStudio(studios, tokens[1]));

            } else if (tokens[0].equals("display_offers")) {
                if (verboseMode) {
                    System.out.println("display_offers_acknowledged");
                    GUI.addCommandWindow("Display Offers Acknowledged\n");
                }

                OfferEvent.displayOffers(offerEvents);
                GUI.addCommandWindow(OfferEvent.GUIdisplayOffers(offerEvents));

            } else if (tokens[0].equals("display_time")) {
                if (verboseMode) {
                    System.out.println("display_time_acknowledged");
                    GUI.addCommandWindow("Display Time Acknowledged\n");
                }
                System.out.println("time," + monthTimeStamp + "," + yearTimeStamp);
                GUI.addCommandWindow("Time: " + monthTimeStamp + "/" + yearTimeStamp);
            }

            // Four additional functional requirements

            else if (tokens[0].equals("update_demo")) {
                if (verboseMode) {
                    System.out.println("update_demo_acknowledged");
                    GUI.addCommandWindow("Update Demo Acknowledged\n");
                }

                DemographicGroup demoGroup = new DemographicGroup();
                String shortName = tokens[1];
                int numOfAcct = Integer.parseInt(tokens[3]);
                if (demoGroup.checkDemo(demo, shortName)) {
                    GUI.addCommandWindow("The demographic group does not exist.\n");
                } else {
                    if (WatchEvent.demoHasWatchedEvent(shortName)) {
                        GUI.addCommandWindow("The number of accounts can be changed only at the beginning of the month.\n");
                    } else {
                        DemographicGroup.updateDemo(demo, shortName, numOfAcct);
                        IOmodule.writeDemoFile(demo);   // save updated demo in file
                        GUI.addCommandWindow("The number of accounts changed successfully\n");
                    }
                }

            } else if (tokens[0].equals("update_event")) {
                if (verboseMode) {
                    System.out.println("update_event_acknowledged");
                    GUI.addCommandWindow("Update Event Acknowledged\n");
                }

                Event evt = new Event();
                String evtName = tokens[1];
                int evtYear = Integer.parseInt(tokens[2]);
                int fee = Integer.parseInt(tokens[4]);
                if (evt.checkEvent(events, evtName, evtYear)) {
                    GUI.addCommandWindow("The event does not exist.");
                } else {
                    if (WatchEvent.eventHasBeenWatched(evtName,evtYear)) {
                        GUI.addCommandWindow("The license fee can be changed only at the beginning of the month.\n");
                    } else {
                        Event.updateEvent(events, evtName, evtYear, fee);
                        IOmodule.writeEventFile(events);    // save updated event in file
                        GUI.addCommandWindow("The license fee changed successfully\n");
                    }
                }

            } else if (tokens[0].equals("update_stream")) {
                if (verboseMode) {
                    System.out.println("update_stream_acknowledged");
                    GUI.addCommandWindow("Update Stream Acknowledged\n");
                }
                String sName = tokens[1];
                int subPrice = Integer.parseInt(tokens[3]);
                StreamingService streamServ = new StreamingService();
                if (streamServ.checkStream(stream, sName)) {
                    GUI.addCommandWindow("The streaming service does not exist.\n");
                } else {
                    if (WatchEvent.streamHasBeenAccessed(sName)) {
                        GUI.addCommandWindow("The subscription price can be changed only at the beginning of the month.\n");
                    } else {
                        StreamingService.updateStream(stream, sName, subPrice);
                        IOmodule.writeStreamFile(stream);   // save updated stream in file
                        GUI.addCommandWindow("The subscription price changed successfully\n");
                    }
                }

            } else if (tokens[0].equals("retract_movie")) {
                if (verboseMode) {
                    System.out.println("retract_movie_acknowledged");
                    GUI.addCommandWindow("Retract Movie Acknowledged\n");
                }
                OfferEvent offEvt = new OfferEvent(tokens[0].substring(8), tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                if (!offEvt.validStream(tokens[1], stream) || !offEvt.validEvent(tokens[2], events)) {
                    GUI.addCommandWindow("Invalid command.\n");
                } else {
                    OfferEvent.retractMovie(offEvt, offerEvents);
                    IOmodule.writeOfferEventFile(offerEvents); // save updated offerEvent in file
                }

            } else if (tokens[0].equals("clear_all")) {
                IOmodule.clearData();   // clear all saved txt data
                clearAllTempData(); // clear all cache data
                System.out.println("All data has been cleared");
                GUI.changeCommandWindow("All data has been cleared\n");

            } else if (tokens[0].equals("refresh")) {
                GUI.changeCommandWindow("Refresh Screen\n");
            }

            else if (tokens[0].equals("test busy")) {
                while (true) {

                }
            }

            if (tokens[0].equals("test confirmation")) {
                Robust.setConfirmIndicator(false);
            }
            else {
                Robust.setConfirmIndicator(true);
            }
                /*
                else {
                    if (verboseMode) { System.out.println("command_" + tokens[0] + "_NOT_acknowledged"); }
                    GUI.addCommandWindow("Command \"" + tokens[0] + "\" NOT acknowledged");
                }

                 */

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }

    }

    public static String getDateInformation() {
        String year = Integer.toString(yearTimeStamp);
        String month = Integer.toString(monthTimeStamp);
        return year + "-" + month;
    }

    public static void clearAllTempData() {
        demo = new ArrayList<>();
        stream = new ArrayList<>();
        studios = new ArrayList<>();
        events = new ArrayList<>();
        offerEvents = new ArrayList<>();
        watches = new ArrayList<>();
        monthTimeStamp = 10;
        yearTimeStamp = 2020;
        IOmodule.writeTimeFile(monthTimeStamp,yearTimeStamp);
        GUI.updateTimeInfo(getDateInformation());
    }

    public static ArrayList<DemographicGroup> getDemo() {
        return demo;
    }

    public static ArrayList<Studio> getStudios() {
        return studios;
    }

    public static ArrayList<StreamingService> getStream() {
        return stream;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static ArrayList<OfferEvent> getOfferEvents() {
        return offerEvents;
    }

    public static ArrayList<String> getWatches() { return watches; }

    public static void setMonthTimeStamp(int monthTimeStamp) {
        CLIReader.monthTimeStamp = monthTimeStamp;
    }

    public static void setYearTimeStamp(int yearTimeStamp) {
        CLIReader.yearTimeStamp = yearTimeStamp;
    }

    public static void runCLI() {
        String[] commandLines = {"create_studio,warner,Warner Brothers", "create_studio,columbia,Columbia Pictures",
                "create_studio,20cent,20th Century Fox", "create_studio,espn,Entertainment Sports Network",
                "create_event,movie,Batman Begins,2005,140,warner,1000", "create_event,movie,Tenet,2020,150,warner,4000",
                "create_event,movie,Wonder Woman,2017,114,warner,2000", "create_event,movie,Hollow Man,2000,112,columbia,1000",
                "create_event,movie,The Grudge,2004,91,columbia,1000", "create_event,movie,The New Mutants,2020,94,20cent,3000",
                "create_event,ppv,Justice League Live,2020,180,warner,12000", "create_event,ppv,World Blitz Championship,2020,180,espn,3000",
                "create_event,ppv,EuroCup Football,2020,120,espn,5000", "create_stream,net,Netflix,12",
                "create_stream,apv,Amazon Prime Video,15", "create_stream,hulu,Hulu Plus,11",
                "create_demo,age_40_50,Viewers between 40 and 50,800", "create_demo,age_20_heroes,Viewers of Marvel/DC under 20,10000",
                "create_demo,sci_fi,Viewers of Science Fiction,2000"};

        for (String line : commandLines) {
            processInstructions(true, line);
        }

        GUI.changeCommandWindow("Created studios, events, streaming services, and demographic groups successfully! \n");


    }
}
