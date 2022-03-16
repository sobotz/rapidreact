// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;
public class LauncherSubsystem extends SubsystemBase {
  /** Creates a new LauncherSubsystem. */
  public WPI_TalonFX launcherMotor;
  public WPI_TalonFX launcherMotor2;
  WPI_TalonSRX feedMotor;
  public LauncherSubsystem() {
    launcherMotor = new WPI_TalonFX(LauncherConstants.LAUNCHER_MOTOR_1);
    launcherMotor2 = new WPI_TalonFX(LauncherConstants.LAUNCHER_MOTOR_2);
    feedMotor = new WPI_TalonSRX(LauncherConstants.ROLLER_MOTOR);


    
    launcherMotor.setInverted(true);
    


    launcherMotor2.setInverted(InvertType.OpposeMaster);

    launcherMotor.config_kP(0, .25);
    launcherMotor.config_kI(0, .0025);
    launcherMotor.config_kD(0, 100);
  }

  public void startRollers() {
    feedMotor.set(ControlMode.PercentOutput, 1);
    launcherMotor2.follow(launcherMotor);
  }

  public void stopRollers() {
    feedMotor.set(ControlMode.PercentOutput, 0);
    launcherMotor2.follow(launcherMotor);
  }

  public void startLauncher(int velocity) {
    launcherMotor.set(ControlMode.Velocity, velocity);
    launcherMotor2.follow(launcherMotor);

  }

  public void stopLauncher() {
    launcherMotor.set(ControlMode.Velocity, 0);
    launcherMotor2.follow(launcherMotor);
  }
  
  public double getVelocity(){
    return launcherMotor.getSelectedSensorVelocity();
  }

}
  

  

