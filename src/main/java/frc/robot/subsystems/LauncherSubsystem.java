// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;
public class LauncherSubsystem extends SubsystemBase {
  /** Creates a new LauncherSubsystem. */
  public WPI_TalonFX launcherMotor;
  public WPI_TalonFX launcherMotor2;

  public LauncherSubsystem() {
    launcherMotor = new WPI_TalonFX(LauncherConstants.LAUNCHER_MOTOR_1);
    launcherMotor2 = new WPI_TalonFX(LauncherConstants.LAUNCHER_MOTOR_2);
    
    launcherMotor.setInverted(true);
    //launcherMotor2.setInverted(InvertType.OpposeMaster);

    launcherMotor.config_kP(0, .25);
    launcherMotor.config_kI(0, .0023);
  }

  public void startLauncher(double velocity) {
    //launcherMotor.set(ControlMode.Velocity, velocity);
    launcherMotor.set(ControlMode.PercentOutput,velocity);
    //launcherMotor.setInverted(true);
    launcherMotor2.follow(launcherMotor);

  }

  public void stopLauncher() {
    launcherMotor.set(ControlMode.PercentOutput, 0);
    launcherMotor2.follow(launcherMotor);
  }
  
  public double getVelocity(){
    return launcherMotor.getSelectedSensorVelocity();
  }

  public void resetLauncher(){
    launcherMotor.set(ControlMode.PercentOutput, .3);
    launcherMotor2.follow(launcherMotor);
  
  }

}
  

  

