package com.example.java;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Robust {
    private static boolean busyIndicator = false;
    private static boolean confirmIndicator = false;
    private String errorMessage;



    public static boolean checkCMD(String cmdstring) {
        // check the cmd name and argument\
        String[] tokens;
        final String DELIMITER = ",";
        tokens = cmdstring.split(DELIMITER);
        if (tokens[0].equals("create_demo")) {
            if (tokens.length == 4) {
                if (checkInteger(tokens[3])) {
                    return true;
                }
            }
        } else if (tokens[0].equals("create_studio")) {
            if (tokens.length == 3) {
                return true;
            }
        } else if (tokens[0].equals("create_event")) {
            if (tokens.length == 6) {
                if (checkInteger(tokens[3]) && checkInteger(tokens[4]) && checkInteger(tokens[6])) {
                    return true;
                }
            }
        } else if (tokens[0].equals("create_stream")) {
            if ((tokens.length == 4)) {
                if (checkInteger(tokens[3])) {
                    return true;
                }
            }
        } else if (tokens[0].equals("offer_movie")) {
            if ((tokens.length == 4)) {
                if (checkInteger(tokens[3])) {
                    return true;
                }
            }
        } else if (tokens[0].equals("offer_ppv")) {
            if ((tokens.length == 5)) {
                if (checkInteger(tokens[3]) && checkInteger(tokens[4])) {
                    return true;
                }
            }
        } else if (tokens[0].equals("watch_event")) {
            if ((tokens.length == 6)) {
                if (checkInteger(tokens[2]) && checkInteger(tokens[5])) {
                    return true;
                }
            }
        } else if (tokens[0].equals("next_month")) {
            if ((tokens.length == 1)) {
                return true;
            }
        } else if (tokens[0].equals("display_demo")) {
            if ((tokens.length == 2)) {
                return true;
            }
        } else if (tokens[0].equals("display_events")) {
            if ((tokens.length == 1)) {
                return true;
            }
        } else if (tokens[0].equals("display_stream")) {
            if ((tokens.length == 2)) {
                return true;
            }
        } else if (tokens[0].equals("display_offers")) {
            if ((tokens.length == 1)) {
                return true;
            }
        } else if (tokens[0].equals("display_time")) {
            if ((tokens.length == 1)) {
                return true;
            }
        } else if (tokens[0].equals("stop")) {
            if ((tokens.length == 1)) {
                return true;
            }
            else {
                return false;
            }
        }

        else if (tokens[0].equals("display_studio")) {
            if ((tokens.length == 2)) {
                return true;
            }
        }

        else if (tokens[0].equals("test confirmation")) {
            if ((tokens.length == 1)) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (tokens[0].equals("test busy")) {
            if ((tokens.length == 1)) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (tokens[0].equals("clear_all")) {
            if ((tokens.length == 1)) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (tokens[0].equals("refresh")) {
            if ((tokens.length == 1)) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (tokens[0].equals("update_demo")) {
            if ((tokens.length == 4)) {
                if (checkInteger(tokens[3])) {
                    return true;
                }
            }
        }
        else if (tokens[0].equals("update_event")) {
            if ((tokens.length == 5)) {
                if (checkInteger(tokens[2]) && checkInteger(tokens[4])) {
                    return true;
                }
            }
        }
        else if (tokens[0].equals("update_stream")) {
            if ((tokens.length == 4)) {
                if (checkInteger(tokens[3])) {
                    return true;
                }
            }
        }
        else if (tokens[0].equals("retract_movie")) {
            if ((tokens.length == 4)) {
                if (checkInteger(tokens[3])) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean checkInteger(String cmdToken){
        try {
            Integer.parseInt(cmdToken);
            return true;
        }
        catch( Exception e){
            return false;
        }
    }


    public static void excute(String cmdInfo) throws InterruptedException {
        busyIndicator = true;
        confirmIndicator = false;
        Integer count = 0;
        GUI.addCommandWindow("\n> " + cmdInfo + "\n");
        while ((!confirmIndicator) && (count < 100)) {
            TimeUnit.MILLISECONDS.sleep(100);
            CLIReader.processInstructions(true, cmdInfo);

            count = count + 1;
            /*
            if (count % 30 == 0) {
                GUI.addCommandWindow(" \n Executing \"" + cmdInfo + "\", please be patient...");
                System.out.println(count);
            }
             */
        }
        if (confirmIndicator){
            busyIndicator = false;
            //GUI.addCommandWindow(" \n \"" + cmdInfo + "\" executed");
        }
        else {
            busyIndicator = false;
            GUI.addCommandWindow(" \n Time out:  \"" + cmdInfo + "\" not executed, please try again later\n");
        }
    }

    public static boolean getBusyIndicator(){
        return busyIndicator;
    }
    public static boolean getConfirmIndicator(){
        return confirmIndicator;
    }

    //
    public static void setBusyIndicator(boolean Indicator){
        busyIndicator = Indicator;
    }
    public static void setConfirmIndicator(boolean Indicator){
        confirmIndicator = Indicator;
    }

    //Robust.setConfirmIndicator(True);



}
