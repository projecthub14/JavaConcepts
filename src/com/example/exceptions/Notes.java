//package com.example.exceptions;
//
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.NumberFormat;
//import java.util.Scanner;
//import java.util.logging.Logger;
//
//
//public class Notes {
//
//    Logger logger = Logger.getLogger("sowmya");
//    public static void main(String[] args) {
//
//
//    }
//
//    //throws Exception
//    //FIleNotFoundException is checked exception
//    public int getPlayScore(String playerFile) throws FileNotFoundException{
//        Scanner contents = new Scanner(new File(playerFile));
//        return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//    }
//
//    //try-catch rethrowing - throw new
//    public int getPlayScore1(String playerFile) {
//        try {
//            Scanner contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//        }
//        catch (FileNotFoundException ex){
//            throw new IllegalArgumentException("File not found");
//        }
//    }
//
//    //try-catch performing recovery steps
//    public int getPlayScore2(String playerFile) {
//
//        try {
//            Scanner contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//        }
//        catch (FileNotFoundException ex){
//            logger.warning("File not found , resetting score");
//            return 0;
//        }
//    }
//
//    //finally
//    public int getPlayScore3(String playerFile) throws FileNotFoundException{
//
//        Scanner contents = null;
//        try {
//            contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//        }
//        //even if FileNotFoundException is thrown up the call stack , Java will call the contents of finally before doing that
//        finally {
//            if(contents!=null)
//               contents.close();
//        }
//    }
//
//    //finally
//    public int getPlayScore4(String playerFile) {
//
//        Scanner contents = null;
//        try {
//            contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//        }catch (FileNotFoundException exc){
//            logger.warning("File not found so restting player score");
//            return 0;
//        }
//        //even if FileNotFoundException is thrown up the call stack , Java will call the contents of finally before doing that
//        //we can handle exception and make sure resource gets closed
//        finally {
////            try {
////                if (contents != null)
////                    contents.close();
////            }
////            catch (IOException io){
////                //logger.exiting("couldnt close reader" , io.getMessage());
////
////            }
//        }
//    }
//
//    //try multiple catch
//    public int getPlayScore5(String playerFile) {
//
//        Scanner contents = null;
//        try {
//            contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//        } // here we are not handling FileNotFoundException because it extends IOException - any of its subclasses also handled
//        //if we need to differential FileNotFoundException from general IOException then include
//        catch (FileNotFoundException e){
//            logger.warning("Player file not found");
//            return 0;
//        }
//        catch (IOException e){
//            logger.warning("Player file wouldnt load");
//            return 0;
//        }
//        catch (NumberFormatException e){
//            logger.warning("Player file was corrupted ");
//            return 0;
//        }
//
//    }
//
//
//    //union catch - multiple exception in same block
//    public int getPlayScore6(String playerFile) {
//
//        Scanner contents = null;
//        try {
//            contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine()); // parseInt can throw NumberFormatException but it unchecked
//        } // here we are not handling FileNotFoundException because it extends IOException - any of its subclasses also handled
//        //if we need to differential FileNotFoundException from general IOException then include
//        catch (IOException  |NumberFormatException e){
//            logger.warning("Failed to load score");
//            return 0;
//        }
//
//    }
//
//
//}
