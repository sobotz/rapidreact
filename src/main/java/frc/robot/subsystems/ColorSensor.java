// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//for declarations and stuff
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

//Color sensor imports
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor extends SubsystemBase {
  //port
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  //color sensor
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  //color matcher
  private final ColorMatch colorMatcher = new ColorMatch();
  //blue and red rgb values
  private final Color blueBall = ColorMatch.makeColor(0.143, 0.427, 0.429); //someone get rid of these errors pls, idk how
  private final Color redBall = ColorMatch.makeColor(0.561, 0.232, 0.114);
  //our color
  private boolean weAreBlue;

  
  public ColorSensor(){
    weAreBlue = SmartDashboard.getBoolean("weAreBlue", true);
  }

  @Override
  public void robotInit() {
    colorMatcher.addColorMatch(blueBall);
    colorMatcher.addColorMatch(redBall);
  }

  @Override
  public void robotPeriodic() {

    Color detectedColor = colorSensor.getColor();

    boolean currBallBlue; //if the balls are blue, blue = true and if the balls are red, red = false
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == blueBall && weAreBlue) {
      //will run the code that shoots the ball into the hub
      currBallBlue = true;
    } 
    else if (match.color == redBall && !weAreBlue) {
      //will run the code that shoots the ball into the hub
      currBallBlue = false;
    } 
    else{
      //will run the code to make the ball miss
      if (match.color == blueBall){
        currBallBlue = true;
      }
      else{
        currBallBlue = false;
      }
    }

    //for testing
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    //SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putBoolean("Detected Color", currBallBlue);
  }
}

  
