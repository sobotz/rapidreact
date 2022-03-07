// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {
  /** Creates a new LauncherSubsystme. */
  WPI_TalonFX leaderLauncherDriver, followerLauncherDriver;

  public LauncherSubsystem() {
    this.leaderLauncherDriver = new WPI_TalonFX(5);
    this.followerLauncherDriver = new WPI_TalonFX(7);

  }
  public void runLauncher(){
    this.leaderLauncherDriver.set(ControlMode.PercentOutput, 1);
    this.followerLauncherDriver.set(ControlMode.PercentOutput, -1);
  }
  public void stopLauncher(){
    this.leaderLauncherDriver.set(ControlMode.PercentOutput, 0);
    this.followerLauncherDriver.set(ControlMode.PercentOutput, 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
