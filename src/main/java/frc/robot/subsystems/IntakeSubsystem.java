// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.SerializerConstants;

public class IntakeSubsystem extends SubsystemBase {

  private Boolean lastintakeSensor;
  private boolean lastserializerSensor;
  private boolean lastlauncherSensor;

  public WPI_TalonSRX intakeTalon;

  private DoubleSolenoid intakeDeploy;

  private SensorSubsystem sensors;
  
  
  public boolean hasDeployed;
  private boolean notAccepting;
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem(SensorSubsystem sensors) {

    intakeTalon = new WPI_TalonSRX(IntakeConstants.INTAKE_MOTOR);
    intakeTalon.configFactoryDefault();

    this.sensors = sensors;
    
    intakeDeploy = new DoubleSolenoid(12, PneumaticsModuleType.REVPH, IntakeConstants.INTAKE_SOLENOID_RETRACT, IntakeConstants.INTAKE_SOLENOID_DEPLOY);

    hasDeployed = false;
    notAccepting = false;

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /*if (sensors.getLauncherVal() && sensors.getSerializerVal()){
      if(!lastserializerSensor || !lastlauncherSensor){
        retractIntake();
      } else {
        runIntake(0);
      }
    } else { 
      notAccepting = false;
      if (sensors.getIntakeVal()) {
        System.out.println("firstTripped");
        runIntake(1);
      } else if (!hasDeployed) {
        runIntake(0);
      }
    }*/
    /*if (sensors.getLauncherVal() && sensors.getSerializerVal()){
      runIntake(0);
    } else {
      if (sensors.getIntakeVal()) {
        System.out.println("firstTripped");
        runIntake(1);
      } else if (!hasDeployed) {
        runIntake(0);
      }
    }*/
    if (sensors.getLauncherVal()){
      if (sensors.getSerializerVal() && !lastserializerSensor) {
        runIntake(0);
        intakeDeploy.set(Value.kForward);
        hasDeployed = false;
        notAccepting = true;
      }
    } else if (!sensors.getLauncherVal() && lastlauncherSensor){
      notAccepting = false;
    }

    lastintakeSensor = sensors.getIntakeVal();
    lastserializerSensor = sensors.getSerializerVal();
    lastlauncherSensor = sensors.getLauncherVal();
    SmartDashboard.putBoolean("HasDeployed", this.hasDeployed);
    SmartDashboard.putBoolean("NotAccepting", this.notAccepting);
  }
  public void deployIntake() {
    runIntake(0.0);
    intakeDeploy.set(Value.kForward);
  }
  
  public void runIntake(double speed) {
    intakeTalon.set(ControlMode.PercentOutput, (notAccepting) ? 0.0 : speed * IntakeConstants.MAXIMUM_INTAKE_SPEED);
  }
  
  public void retractIntake() {
    intakeDeploy.set(Value.kReverse);
  }

  public boolean toggleIntake() {
    if (hasDeployed) {
      intakeDeploy.set(DoubleSolenoid.Value.kForward);
      runIntake(0);
      hasDeployed = false;
    } else {
      intakeDeploy.set(DoubleSolenoid.Value.kReverse);
      runIntake(1);
      hasDeployed = true;
    }
    return hasDeployed;
  }
}

