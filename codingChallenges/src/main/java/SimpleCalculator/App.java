/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCalculator;

import java.util.Scanner;

/**
 *
 * @author jesse
 */
public class App {
    
    public static void main(String[] args) {
        
        SimpleCalculator mySimpleCalculator = new SimpleCalculator();
        // intitialized class SimpleCalculator
        Scanner myScanner = new Scanner(System.in);
        // created scanner object for user input 
        String operation; // operation user wants to use stored in a variable
        double firstVal, secondVal; // the two values the user wants to calculate;
        boolean calculateAgain = true;
       
        System.out.println("Welcome I am a Simple Calculator");
        System.out.println("I can Add, Subtract, Multiply, and Divide \n");
       // create loop to repeat
        
       while(calculateAgain) {
       
       firstVal = readValue("What is the first number that you would like to calculate?\n");
        
        System.out.println("What operation would you like to use ");
        System.out.println("Enter + for Addition \nEnter - for Subtraction");
        System.out.println("Enter * for Multiplication \n Enter / for Divison");
        operation = myScanner.nextLine();
         if(!(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/"))){
            System.out.println("Please enter a correct operation");
            operation = myScanner.nextLine();
        }    
        
        secondVal = readValue("What is the second number that you would like to calculate?\n");
        
        
            switch (operation) {
                case "+":
                    System.out.println(mySimpleCalculator.addNums(firstVal, secondVal));
                    break;
                case "-":
                    System.out.println(mySimpleCalculator.subtractNums(firstVal, secondVal));
                    break;
                case "*":
                    System.out.println(mySimpleCalculator.multiplyNums(firstVal, secondVal)); 
                    break;
                case "/":
                    System.out.println(mySimpleCalculator.divideNums(firstVal, secondVal));
                    break;
                default:
                    break;
            }
         
        
           System.out.println("Do you want to calculate again?");
           System.out.println("Please enter yes or no");
           String answer = myScanner.nextLine();
          
           if(answer.equals("yes")){
           calculateAgain = true;
       }else {
            return;
           }
        
       }
    
    }
    public static double readValue(String prompt){ // method created to read in values user wants to input
           Scanner myScanner = new Scanner(System.in);
           System.out.println(prompt);
           String intput = myScanner.nextLine();
           double doubleVal = Double.parseDouble(intput);
   
           return doubleVal;
  
    }
}
