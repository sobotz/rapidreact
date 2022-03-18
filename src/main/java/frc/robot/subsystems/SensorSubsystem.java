// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AnalogInput;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SensorSubsystem extends SubsystemBase {
  private AnalogInput intakeSensor;
  private AnalogInput serializerSensor;
  private AnalogInput launcherSensor;
  private boolean intakeVal;
  private boolean serializerVal;
  private boolean launcherVal;

  /** Creates a new SensorSubsystem. */
  public SensorSubsystem() {
   /** intakeSensor = new AnalogInput(0);
    serializerSensor = new AnalogInput(1);
    launcherSensor = new AnalogInput(2);*/
    intakeVal = false;
    serializerVal = false;
    launcherVal = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /**intakeVal = intakeSensor.get();
    serializerVal = serializerSensor.get();
    launcherVal = launcherSensor.get();
    System.out.println("intake value: " + intakeVal);*/
  }
  
  public boolean getIntakeVal(){
    return intakeVal;
  }

  public boolean getSerializerVal(){
    return serializerVal;
  }
  
  public boolean getLauncherVal(){
    return launcherVal;
  }
}
