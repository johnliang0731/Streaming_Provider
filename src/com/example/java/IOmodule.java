package com.example.java;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IOmodule {

    static void main(String args) {
    }

    /*
     *  DemographicGroup I/O Part
     */

    public static void writeDemoFile(ArrayList demoList) {

        try {
            File writename = new File("./data/DemographicGroup.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(demoList2String(demoList));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String demoList2String(ArrayList demoList) {
        ArrayList<DemographicGroup> demoL = demoList;
        String demoString = "";
        String outputString = "";
        for (DemographicGroup demo:demoL) {
            demoString = demo.getDemoShortName() + "," + demo.getDemoLongName() + "," + demo.getDemoAccounts();
            outputString = outputString + demoString + "\n";
        }
        return outputString;
    }


    public static void readDemoFile() {
        try {
            String[] tokens;
            ArrayList<DemographicGroup> newDemoArray = CLIReader.getDemo();

            String pathname = "./data/DemographicGroup.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String demoLine = "";
            while ((demoLine = br.readLine()) != null) {    // read each line in DemoFile
                tokens = demoLine.split(",");   // split demoLine

                // create one record of DemographicGroup
                DemographicGroup.createDemo(newDemoArray,tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  Studio I/O Part
     */

    public static void writeStudioFile(ArrayList studios) {

        try {
            File writeFile = new File("./data/Studio.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(studioList2String(studios));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String studioList2String(ArrayList studios) {
        ArrayList<Studio> studioList = studios;
        String studioString = "";
        String outputString = "";
        for (Studio studio:studioList) {
            studioString = studio.getStudioShortName() + "," + studio.getStudioLongName();
            outputString = outputString + studioString + "\n";
        }
        return outputString;
    }


    public static void readStudioFile() {
        try {
            String[] tokens;
            ArrayList<Studio> newStudioArray = CLIReader.getStudios();

            String pathname = "./data/Studio.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String studioLine = "";
            while ((studioLine = br.readLine()) != null) {
                tokens = studioLine.split(",");

                // create one record of Studio
                Studio.createStudio(newStudioArray,tokens[0], tokens[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  StreamingService I/O Part
     */

    public static void writeStreamFile(ArrayList stream) {

        try {
            File writeFile = new File("./data/StreamingService.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(streamList2String(stream));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String streamList2String(ArrayList streams) {
        ArrayList<StreamingService> streamList = streams;
        String streamString = "";
        String outputString = "";
        for (StreamingService stream:streamList) {
            streamString = stream.getStreamShortName() + "," + stream.getStreamLongName() + "," + stream.getStreamSubscription();
            outputString = outputString + streamString + "\n";
        }
        return outputString;
    }


    public static void readStreamFile() {
        try {
            String[] tokens;
            ArrayList<StreamingService> newStreamArray = CLIReader.getStream();

            String pathname = "./data/StreamingService.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String streamLine = "";
            while ((streamLine = br.readLine()) != null) {
                tokens = streamLine.split(",");

                // create one record of StreamingService
                StreamingService.createStream(newStreamArray,tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  Event I/O Part
     */

    public static void writeEventFile(ArrayList events) {

        try {
            File writeFile = new File("./data/Event.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(eventList2String(events));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String eventList2String(ArrayList events) {
        ArrayList<Event> eventList = events;
        String eventString = "";
        String outputString = "";
        for (Event event:eventList) {
            eventString = event.getEventType() + "," + event.getEventFullName() + "," + event.getEventYear() +
                    "," + event.getEventDuration() + "," + event.getEventStudioOwner() + "," + event.getEventLicenseFee();
            outputString = outputString + eventString + "\n";
        }
        return outputString;
    }

    public static void readEventFile() {
        try {
            String[] tokens;
            ArrayList<Event> newEventArray = CLIReader.getEvents();
            ArrayList<Studio> studios = CLIReader.getStudios();

            String pathname = "./data/Event.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String eventLine = "";
            while ((eventLine = br.readLine()) != null) {
                tokens = eventLine.split(",");

                // create one record of Event
                Event.createEvent(newEventArray,tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]),
                        tokens[4],Integer.parseInt(tokens[5]),studios);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     *  OfferEvent I/O Part
     */

    public static void writeOfferEventFile(ArrayList offerEvents) {

        try {
            File writeFile = new File("./data/OfferEvent.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(offerEventList2String(offerEvents));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String offerEventList2String(ArrayList offerEvents) {
        ArrayList<OfferEvent> offerEventList = offerEvents;
        String offerString = "";
        String outputString = "";
        for (OfferEvent offer:offerEventList) {
            if (offer.getOfferType().equals("movie")) {
                offerString = offer.getOfferType() + "," + offer.getOfferStream() + "," + offer.getOfferEventName() +
                        "," + offer.getOfferEventYear();
            } else if (offer.getOfferType().equals("ppv")) {
                offerString = offer.getOfferType() + "," + offer.getOfferStream() + "," + offer.getOfferEventName() +
                        "," + offer.getOfferEventYear() + "," + offer.getOfferEventPrice();
            }

            outputString = outputString + offerString + "\n";
        }
        return outputString;
    }

    public static void readOfferEventFile() {
        try {
            String[] tokens;
            ArrayList<OfferEvent> newOfferEventArray = CLIReader.getOfferEvents();
            ArrayList<StreamingService> arrStream = CLIReader.getStream();
            ArrayList<Event> arrEvent = CLIReader.getEvents();
            ArrayList<Studio> arrStudio = CLIReader.getStudios();

            String pathname = "./data/OfferEvent.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String offerLine = "";
            while ((offerLine = br.readLine()) != null) {
                tokens = offerLine.split(",");

                // create one record of OfferEvent
                if (tokens[0].equals("movie")) {
                    OfferEvent.offerMovie(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                            newOfferEventArray, arrStream, arrEvent);
                } else if (tokens[0].equals("ppv")) {
                    OfferEvent.offerPPV(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[4]), newOfferEventArray, arrStream, arrEvent);
                }
                OfferEvent.licenseEvent(tokens[1], tokens[2], Integer.parseInt(tokens[3]), arrEvent, arrStream, arrStudio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  WatchEvent I/O Part
     */

    public static void writeWatchFile(ArrayList watches) {

        try {
            File writeFile = new File("./data/WatchEvent.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(watchList2String(watches));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String watchList2String(ArrayList watches) {
        ArrayList<String> watchList = watches;
        String outputString = "";
        for (String watch:watchList) {
            outputString = outputString + watch + "\n";
        }
        return outputString;
    }

    public static void readWatchFile() {
        try {
            String[] tokens;
            ArrayList<DemographicGroup> demo = CLIReader.getDemo();
            ArrayList<StreamingService> stream = CLIReader.getStream();
            ArrayList<OfferEvent> offerEvents = CLIReader.getOfferEvents();

            String pathname = "./data/WatchEvent.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String watchLine = "";
            while ((watchLine = br.readLine()) != null) {
                tokens = watchLine.split(",");

                // create one record of Event
                WatchEvent.watchDemo(tokens[1], Integer.parseInt(tokens[2]), demo);
                WatchEvent.watchStream(tokens[3], stream);
                WatchEvent.watchEventType(tokens[3], tokens[4], Integer.parseInt(tokens[5]), offerEvents);
                WatchEvent.watchEvent(tokens[3], demo, stream);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  Time I/O Part
     */

    public static void writeTimeFile(int month, int year) {

        try {
            File writeFile = new File("./data/Time.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(month + "," + year);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readTimeFile() {
        try {
            String[] tokens;

            String pathname = "./data/Time.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);

            String timeLine = br.readLine();

            if (timeLine == null) {
                writeTimeFile(10,2010);
                timeLine = "10,2010";
            }

            tokens = timeLine.split(",");
            CLIReader.setMonthTimeStamp(Integer.parseInt(tokens[0]));
            CLIReader.setYearTimeStamp(Integer.parseInt(tokens[1]));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Authentication I/O
     */

    public static void writeAuthFile(HashMap userMap) {
        String outputString = "";
        String oneUserString = "";
        HashMap<String, String> localUserMap = new HashMap<>();
        localUserMap.putAll(userMap);

        for (String user:localUserMap.keySet()) {
            oneUserString = user + "," + localUserMap.get(user);
            outputString = outputString + oneUserString + "\n";
        }

        try {
            File writeFile = new File("./user/Authentication.txt");
            writeFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write(outputString);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap readAuthFile() {
        HashMap<String, String> userHashMap = new HashMap<>();
        try {
            String[] tokens;
            String pathname = "./user/Authentication.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String userLine = "";
            while ((userLine = br.readLine()) != null) {
                tokens = userLine.split(",");
                userHashMap.put(tokens[0], tokens[1]);
            }
            return userHashMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userHashMap;
    }

    /*
     * Clear all the saved data
     */
    public static void clearData() {

        ArrayList<String> addressBook = new ArrayList<>();
        addressBook.add("./data/WatchEvent.txt");
        addressBook.add("./data/OfferEvent.txt");
        addressBook.add("./data/Event.txt");
        addressBook.add("./data/StreamingService.txt");
        addressBook.add("./data/Studio.txt");
        addressBook.add("./data/DemographicGroup.txt");

        for (String address:addressBook) {
            try {
                File writeFile = new File(address);
                writeFile.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
                out.write("");
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
