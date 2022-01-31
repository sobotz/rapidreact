// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//subsystem import
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//scanner import
import java.util.*;
//

/** Add your docs here. */
public class ColorSensor extends SubsystemBase {

  //Instance Variables
  private String currColor;
  private boolean weAreBlue;
  private boolean weAreRed;
  private boolean currBallColor;
  
  //Constructor
  /** Sets the booleans weAreBlue and weAreRed
   * Will be used in running the launchers
   */
  public ColorSensor(String color){
    if(currColor.equals("red")){
      weAreRed = true;
      weAreBlue = false;
    }
    if(currColor.equals("blue")){
      weAreBlue = true;
      weAreRed = false;
    }
  }
  /** 
  *Declares a String variable of our color(might delete later)
  * Takes a user input for the color
  * Will likely be changed to a button
  */
  
  public String getColor(){
    System.out.println("What is our color?");
    System.out.println("Insert \"red\" if we are red and \"blue\" if we are blue: ");
    Scanner colorInsert = new Scanner(System.in);
    while(true){
      if(colorInsert.equals("red")){
        currColor = "red";
        break;
      }
      if(colorInsert.equals("blue")){
        currColor = "blue";
        break;
      }
      else{
        System.out.println("What is our color?");
        System.out.println("Insert \"red\" if we are red and \"blue\" if we are blue: ");
        colorInsert = new Scanner(System.in);
      }
      return currColor;
    }
  }
  
      
      /** This is where the ball's color will actually be tested
       * Will effect a boolean value that will change throughout
       * True means the color is blue
       * False means the color is red
       */
  public void getBallColor(){
    currBallColor = false; //temporary because errors are annoying
  }
      
      /**Determines what the ball will do based on the color
       * If the color matches the team color, the ball will shoot correctly
       * Else, the ball will miss
       **/
  public void launcherAction(){
    if(weAreBlue && currBallColor){
      //will later run the code that makes the ball shoot into the hub
    }
    else if(weAreRed && !currBallColor){
        //will later run the code that makes the ball shoot into the hub
    }
    else{
      //will later run the code that makes the ball miss the hub
    }
  }

  //Runs everything
  public void runColorSensor(){
      getColor();

       


    }
}






