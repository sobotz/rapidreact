// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//for declarations and stuff
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorConstants;
//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

//Color sensor imports
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;

import java.util.ArrayList;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3.RawColor;


public class ColorSensor extends SubsystemBase{
  //port
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  //color sensor
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  //color matcher
  private final ColorMatch colorMatcher = new ColorMatch();

  public ArrayList <String> ballColors  = new ArrayList <String>();

  private double lastRed = 0.0;
  private double lastBlue = 0.0;
  private int intakeCount = 0;

  //blue and red rgb values
  //private final Color blueBallValues = ColorMatch.RawColor(0, 0, 225); //someone get rid of these errors pls, idk how
  //private final Color redBallValues = ColorMatch.makeColor(0.561, 0.232, 0.114);
  //our color
  private boolean weAreBlue;
  //private boolean shoot;

  
  public ColorSensor(){
    weAreBlue = SmartDashboard.getBoolean("weAreBlue", true);
  }

  //@Override
  /**public void robotInit() {
    colorMatcher.addColorMatch(blueBallValues);
    colorMatcher.addColorMatch(redBallValues);
  }*/

  //@Override
  public void periodic() {
    Color detectedColor = colorSensor.getColor();
    SmartDashboard.putString("ballColors", ballColors.toString());

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Blue", detectedColor.blue);

    String currBall;
    String[] _ballColors = new String[2];
    SmartDashboard.putStringArray("Ball Color Values", ballColors.toArray(_ballColors));
    /**if(!ballColors.isEmpty()){
      SmartDashboard.putString("ball 1 color", ballColors.get(0));
    }*/
    
    
    if(detectedColor.red > ColorConstants.COLOR_THRESHOLD && lastRed < ColorConstants.COLOR_THRESHOLD){
        currBall = "red";
        ballColors.add(currBall);
        //SmartDashboard.putStringArray("Ball Color Values", ballColors.toArray(_ballColors));
        /**if(ballColors.size() == 0 && intakeCount == 0){
          ballColors.add(currBall);
          intakeCount++;
        }
        else if(ballColors.size() == 1 && intakeCount == 1){
          ballColors.add(currBall);
          intakeCount++;
        }
        else if(ballColors.size() == 2){
          ballColors.remove(1);
        }
        else if(ballColors.size() == 3){
          ballColors.remove(2);
        }*/
      
    }
    else if(detectedColor.blue > ColorConstants.COLOR_THRESHOLD && lastBlue < ColorConstants.COLOR_THRESHOLD){
        currBall = "blue";
        ballColors.add(currBall);
        //SmartDashboard.putStringArray("Ball Color Values", ballColors.toArray(_ballColors));
        /**if(ballColors.size() == 0 && intakeCount == 0){
          ballColors.add(currBall);
          intakeCount++;
        }
        else if(ballColors.size() == 1 && intakeCount == 1){
          ballColors.add(currBall);
          intakeCount++;
        }
        else if(ballColors.size() == 2){
          ballColors.remove(1);
        }
        else if(ballColors.size() == 3){
          ballColors.remove(2);
        }*/
    }
    lastRed = detectedColor.red;
    lastBlue = detectedColor.blue;
  }
  public ArrayList getBallValues(){
    return ballColors;
  }
  public String getBallOne(){
    return ballColors.get(0);
  }
  public String getBallTwo(){
    return ballColors.get(1);
  }
  public void removeLastBall(){
    ballColors.remove(ballColors.size() - 1);
  }
  public void removeFirstBall(){
    ballColors.remove(0);
  }

  public void clearBallValues(){
    ballColors.remove(0);
    ballColors.remove(1);
    intakeCount = 0;
  }

  /**public boolean shootInHub(){
      return shoot;
  }*/
}

  
