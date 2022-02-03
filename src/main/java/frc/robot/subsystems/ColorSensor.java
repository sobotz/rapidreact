// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//packages
package frc.robot.subsystems;

//subsystem import
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

//scanner import
import java.util.*;
//color sensors
//import com.revrobotics.ColorMatchResult;
//import com.revrobotics.ColorMatch;
//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;


/** Add your docs here. */
public class ColorSensor extends SubsystemBase {

  //Instance Variables
  //color sensor
  private final I2C.Port i2cPort = I2C.Port.kOnboard; //port
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort); //color sensor
  //detecting color
  private final ColorMatch ColorMatcher = new ColorMatch();
  private final Color BlueBall = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color RedBall = ColorMatch.makeColor();

  //extra
  private String currColor;
  private boolean currBallColor;
  private boolean weAreBlue;
  
  //Constructor
  /** Sets the booleans weAreBlue and weAreRed
   * Will be used in running the launchers
   */
  public ColorSensor(){

    weAreBlue = SmartDashboard.getBoolean("weAreBlue", true);

  }
  /**
  
  
      
      /** This is where the ball's color will actually be tested
       * Will effect a boolean value that will change throughout
       * True means the color is blue
       * False means the color is red
       */
  public boolean getBallColor(){
    Color ballColor = colorSensor.getColor();
    
    double IR = colorSensor.getIR();

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);

    int proximity = colorSensor.getProximity();
    SmartDashboard.putNumber("Proximity", proximity);

    if(ballColor.equals(ballColor.blue)){
      currBallColor = true;
    }
    else{
      currBallColor = false;
    }
    return currBallColor; //temporary because errors are annoying
  }
      
      /**Determines what the ball will do based on the color
       * If the color matches the team color, the ball will shoot correctly
       * Else, the ball will miss
       **/
  public void launcherAction(){
    if(weAreBlue && currBallColor){
      //will later run the code that makes the ball shoot into the hub
    }
    else if(!weAreBlue && !currBallColor){
        //will later run the code that makes the ball shoot into the hub
    }
    else{
      //will later run the code that makes the ball miss the hub
    }
  }

  //Runs everything
  public void runColorSensor(){
      
  }

  
  
  //Deleted methods we will probably never use, but will never forget :( 

    //*public ColorSensor(){
      /**if(currColor.equals("red")){
        weAreRed = true;
        weAreBlue = false;
      }
      if(currColor.equals("blue")){
        weAreBlue = true;
        weAreRed = false;
      }*/
    
    /**public String getOurColor(){
    System.out.println("What is our color?");
    System.out.println("Insert \"red\" if we are red and \"blue\" if we are blue: ");
    Scanner colorInsert = new Scanner(System.in);
    while(true){
      if(colorInsert.equals("red")){
        currColor = "red";
        return currColor;
      }
      if(colorInsert.equals("blue")){
        currColor = "blue";
        return currColor;
      }
      else{
        System.out.println("What is our color?");
        System.out.println("Insert \"red\" if we are red and \"blue\" if we are blue: ");
        colorInsert = new Scanner(System.in);
      }
      
      
    }
  }*/
}






