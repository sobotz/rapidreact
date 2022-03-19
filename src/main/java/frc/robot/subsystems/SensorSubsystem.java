// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SensorSubsystem extends SubsystemBase {
  private AnalogInput intakeSensor;
  private AnalogInput serializerSensor;
  private AnalogInput launcherSensor;
  private double intakeVal;
  private double serializerVal;
  private double launcherVal;
  private boolean intakeTripped;
  private boolean serializerTripped;
  private boolean launcherTripped;

  /** Creates a new SensorSubsystem. */
  public SensorSubsystem() {
    intakeSensor = new AnalogInput(0);
    serializerSensor = new AnalogInput(1);
    launcherSensor = new AnalogInput(2);
    
    intakeTripped = false;
    serializerTripped = false;
    launcherTripped = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    intakeVal = intakeSensor.getVoltage();
    serializerVal = serializerSensor.getVoltage();
    launcherVal = launcherSensor.getVoltage();
    if(intakeVal <= 1){
      intakeTripped = true;
    }
    if(serializerVal <= 1){
      serializerTripped = true;
    }
    if(launcherVal <= 1){
      launcherTripped = true;
    }

    if(intakeVal > 1){
      intakeTripped = false;
    }
    if(serializerVal > 1){
      serializerTripped = false;
    }
    if(launcherVal > 1){
      launcherTripped = false;
    }
    System.out.println(launcherVal);
    //System.out.println(getIntakeVal());
    //System.out.println(intakeSensor.getVoltage());
  }
  
  public boolean getIntakeVal(){
    return intakeTripped;
  }

  public boolean getSerializerVal(){
    return serializerTripped;
  }
  
  public boolean getLauncherVal(){
    return launcherTripped;
  }
}
