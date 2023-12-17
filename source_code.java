import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static Scanner in = new Scanner(System.in);
    
    //The converter function:
    public static String converter(String number, int toConvertSys, int answerSys) {
        return Long.toString(Long.parseLong(number, toConvertSys), answerSys);
    }
    
    public static int validateSystem(int system) {
        while (system != 2 && system != 4 && 
               system != 8 && system != 10 &&
               system != 16) {
                System.out.println("Sorry, The available number systems are: (2, 4, 8, 10, 16),"
                           + "please try again and enter one of them:");
                system = in.nextInt();
        }
        return system;
    }
    
    public static int validateMode(int mode) {
        while (mode != 1 && mode != 2) {
            System.out.println("Enter a valid mode number, please.\n");
            mode = in.nextInt();
            
        }
        
        return mode;
    }
    
    public static void mode_1() {
        System.out.println("What is the number system of your number? ");
        int toConvertSys = validateSystem(in.nextInt());
        
        System.out.println("Enter your number: ");
        String s = in.next();
        
        System.out.println("What is the number system you want to convert to? ");
        int answerSys = validateSystem(in.nextInt());
        
        String answer = converter(s, toConvertSys, answerSys);
        System.out.println("The answer is: " + answer + '\n');
    }
    
    //This function gets the specifications of the random
    //number from the user.
    public static int[] getSpecs() {
        
        //this array will contain the, respectively:
        // * the number system of the number to convert.
        // * the desired length of the number to convert.
        // * the number system the user want to convert to.
        
        int specs[] = new int[3];
        
        System.out.println("What is the number system of the number you want to convert? ");
            
        specs[0] = validateSystem(in.nextInt());
            
        System.out.println("What is the length of the number you want to convert (number of digits)? ");
        
        specs[1] = in.nextInt();
        
        System.out.println("To any number system you want to convert this number?");
        
        specs[2] = validateSystem(in.nextInt());
        
        return specs;
    }
    
    public static String getRandomNumberToConvert(int[] specs) {
        int toConvertSys = specs[0];
        int len = specs[1];
        
        String toConvert = "";
        
        //I used the do-while statement to re-prompt the user to enter the System
        //number if he entered an unavailable system number.
        
        if (toConvertSys == 2) {
            for (int j = 0; j < len; j++) {
                
                //Giving a random digit:
                int digit = (int)(Math.random() * 2);
                
                //Preventing leading zero in the random number to convert:
                while (j == 0 && digit == 0) digit = (int)(Math.random() * 10);
                
                //Adding the digit to the toConvert string:
                toConvert += digit;
            }
        } 
        else if (toConvertSys == 4) {
            for (int j = 0; j < len; j++) {
                int digit = (int)(Math.random() * 4);
                while (j == 0 && digit == 0) digit = (int)(Math.random() * 10);
                toConvert += digit;
            }
        }
        else if (toConvertSys == 8) {
            for (int j = 0; j < len; j++) {
                int digit = (int)(Math.random() * 8);
                while (j == 0 && digit == 0) digit = (int)(Math.random() * 10);
                toConvert += digit;
            }
        }
        else if (toConvertSys == 10) {
            for (int j = 0; j < len; j++) {
                int digit = (int)(Math.random() * 10);
                while (j == 0 && digit == 0) digit = (int)(Math.random() * 10);
                toConvert += digit;
            }
        }
        else if (toConvertSys == 16) {
            for (int j = 0; j < len; j++) {
                int digit = (int)(Math.random() * 16);
                while (j == 0 && digit == 0) digit = (int)(Math.random() * 10);
                
                //Dealing with the digits that is greater than or equal to 9
                //by replacing them with corresponding char representation
                //in the Hexadecimal system:
                
                if (digit > 9) {
                    if (digit == 10) toConvert += 'A';
                    else if (digit == 11) toConvert += 'B';
                    else if (digit == 12) toConvert += 'C';
                    else if (digit == 13) toConvert += 'D';
                    else if (digit == 14) toConvert += 'E';
                    else if (digit == 15) toConvert += 'F';
                } else toConvert += digit;
            }
        }    
        return toConvert;
    }
    
    public static String getUserAnswer(String toConvert) {
        System.out.println("Convert this number: " + toConvert);
        
        try {TimeUnit.SECONDS.sleep(1);} catch(Exception e){};
        
        System.out.println("Enter your answer here: ");
        String s = in.nextLine();
        
        return s;
    }
    
    public static void checkAnswer(String correctAnswer, String userAnswer) {
        int rep = 0;
        boolean answerIsGiven = false;
        boolean giveUp = false;
        while (!userAnswer.equals(correctAnswer) && !answerIsGiven && !giveUp) {
            
            if (userAnswer.toLowerCase().equals("i gave up")) {
                System.out.println("The answer is : " + correctAnswer + '\n');
                giveUp = true;
                
            } else {
            
                //if the number of tries is positive this
                //means that the answer is not correct.
                if (rep > 0) System.out.println("Your answer isn't correct, please try again: ");
                
                //A suggestion to give the answer each 3 faild tries:
                if (rep % 3 == 0 && rep > 0) {
            
                    System.out.println("Do you want to know the answer? ");
                    
                    String ans = in.nextLine();
                    
                    if (ans.toLowerCase().equals("yes")) {
                        
                        System.out.println("The answer is : " + correctAnswer + '\n');
                
                        answerIsGiven = true;
                        
                    } else {
                        System.out.println("Okay, as you want. If you want to know the answer" +
                                            " just enter \"I gave up\". \nKeep going!");
                        
                    }
                }
            }
            if (!answerIsGiven && !giveUp) {
                userAnswer = in.nextLine();
                rep++;
            }    
        }
        
        //This works when the answer is correct and the user didn't 
        //request from the program to give him the answer.
        if (!answerIsGiven && !giveUp) {
            System.out.println("GOOD JOB!\n");
        } 
    }
    
    public static void mode_2(int reps) {
        
        for (int i = 0; i < reps; i++) {
            
            System.out.println("#Number " + (i + 1) + ':');
            
            int[] specs = getSpecs();
            
            int toConvertSys = specs[0];
            int answerSys = specs[2];

            String random = getRandomNumberToConvert(specs);
        
            String correctAnswer = converter(random, toConvertSys, answerSys);
        
            String userAnswer = getUserAnswer(random);
        
            checkAnswer(correctAnswer, userAnswer);
        }    
    }
    
	public static void main(String[] args) {
	    
	    //This variable is for asking the user if he wants to do another conversion.
    	String again = "yes";
    	
    	//This while loop is for restart the program in case of occuring an exception.
    	//If there is no exceptions, the break statement will stop this loop.
	    while (true) {
	    
    	    while (again.toLowerCase().equals("yes")) {
    	        System.out.println("1 Do you want to enter the number you want to convert\n" +
    	                           "2 or you want to give you a random number?\n" +
        	                           "Enter 1 or 2: ");
        	    try {
        	        int mode = validateMode(in.nextInt());
                    if (mode == 1) mode_1();
                    else {
                        System.out.println("How many numbers you want to convert?\n ");
                            
                        int reps = in.nextInt();
                        mode_2(reps);
                    } 
        	    } catch (NumberFormatException e) {
        	        System.out.println("The random number you want to get or the number" +
                                       " you entered is so big! Please, try again.\n");
        	    } catch (InputMismatchException e) {
        	        System.out.println("Please enter a valid number.");
        	    }
        	    
        	    //Time interval:
        	    try {TimeUnit.SECONDS.sleep(1);} catch(Exception e){};
                
                System.out.println("Do you want to continue?\n");
                
                //This is just for escaping "the newline character issue"
                //that might occur in some cases.
                in.nextLine();
                
                //Yes or No:
                again = in.nextLine();
        	     
            }
            break;
	    }
	}    
}
