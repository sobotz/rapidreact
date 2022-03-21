// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.SensorsConstants;

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
    if(intakeVal <= SensorsConstants.SENSORS_THRESHOLD){
      intakeTripped = true;
    }
    if(serializerVal <= SensorsConstants.SENSORS_THRESHOLD){
      serializerTripped = true;
    }
    if(launcherVal <= SensorsConstants.SENSORS_THRESHOLD){
      launcherTripped = true;
    }

    if(intakeVal > SensorsConstants.SENSORS_THRESHOLD){
      intakeTripped = false;
    }
    if(serializerVal > SensorsConstants.SENSORS_THRESHOLD){
      serializerTripped = false;
    }
    if(launcherVal > SensorsConstants.SENSORS_THRESHOLD){
      launcherTripped = false;
    }
    //test sensors

    //System.out.println("intake " + getIntakeVal() + " " + intakeVal);
    //System.out.println("serializer " + getSerializerVal()+ " " + serializerVal);
    //System.out.println("launcher " + getLauncherVal()+ " " + launcherVal); 

    
    
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
