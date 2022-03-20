// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//for declarations and stuff
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ColorSensorConstants;
import frc.robot.Constants.SensorsConstants;
import edu.wpi.first.wpilibj.AnalogInput;
/*import frc.robot.Constants.SerializerConstants;
import edu.wpi.first.wpilibj.AnalogInput;*/
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

//Color sensor imports
import com.revrobotics.ColorSensorV3;
//import com.revrobotics.ColorMatchResult;

import java.util.ArrayList;

//import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorSensorV3.RawColor;


public class ColorSensorSubsystem extends SubsystemBase{
  //port
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  //color sensor
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  //ArrayList that holds the ball's values
  public ArrayList <Alliance> ballColors  = new ArrayList <Alliance>();
  //public ArrayList <String> ballColors  = new ArrayList <String>();
  //blue and red rgb values
  private double lastRed = 0.0;
  private double lastBlue = 0.0;
  //our color
  private Alliance teamColor;
 // private AnalogInput launcherSensor;

  private boolean lastLSVal;
  
  //SensorSubsystem sensorSubsystem = new SensorSubsystem();
  private SensorSubsystem sensors;

  public ColorSensorSubsystem(SensorSubsystem sensors){
    this.teamColor = DriverStation.getAlliance();
    //launcherSensor = new AnalogInput(4);
    lastLSVal = false;
    this.sensors = sensors;
  }

  //@Override
  public void periodic() {
    Color detectedColor = colorSensor.getColor();
    
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    
    if(detectedColor.red > ColorSensorConstants.COLOR_THRESHOLD && lastRed < ColorSensorConstants.COLOR_THRESHOLD){
      ballColors.add(Alliance.Red);
    }
    else if(detectedColor.blue > ColorSensorConstants.COLOR_THRESHOLD && lastBlue < ColorSensorConstants.COLOR_THRESHOLD){
      ballColors.add(Alliance.Blue);
    }

    lastRed = detectedColor.red;
    lastBlue = detectedColor.blue;


    String[] _ballColors = new String[ballColors.size()];
    SmartDashboard.putStringArray("Ball Array", ballColors.toArray(_ballColors));

    if(!sensors.getLauncherVal() && lastLSVal){
      removeFirstBall();
    }
    
    lastLSVal = sensors.getLauncherVal();
  }

  public Boolean allyBall () {
    return ballDetected() ? teamColor.equals(ballColors.get(0)) : false;
  }

  public void removeFirstBall(){  //USED
    ballColors.remove(0);
  }

  public boolean ballDetected(){
    if(ballColors.size() != 0){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean ballTwoDetected(){
    if(ballColors.get(1) != null){
      return true;
    }
    else{
      return false;
    }
  }

  public ArrayList getBallValues(){
    return ballColors;
  }

  public Alliance getBallOne(){
    return ballColors.get(0);
  }

  public void removeLastBall(){
    ballColors.remove(ballColors.size() - 1);
  }

  public void clearBallValues(){
    ballColors.remove(0);
    ballColors.remove(1);
  }
}

  