// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  NetworkTable table;

  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry tArea;
  NetworkTableEntry tv;

  boolean hasTarget;

  double xOffset;
  double yOffset;
  double area;

  TalonSRX actuationMotor;

  public VisionSubsystem() {

    this.table = NetworkTableInstance.getDefault().getTable("limelight");

    this.tv = table.getEntry("tv");
    this.tx = table.getEntry("tx");
    this.ty = table.getEntry("ty");
    this.tArea = table.getEntry("ta");

    this.actuationMotor = new TalonSRX(VisionConstants.ACTUATION_MOTOR);
  }

  public void correctX () {
    double speed = 0;
    if (this.hasTarget) {
      speed = 2.0/(1.0 + Math.pow(Math.E, VisionConstants.LOGISTIC_GROWTH_RATE * this.xOffset)) - 1.0;
      if (speed < VisionConstants.MIN_ADJUST_SPEED){
        speed = VisionConstants.MIN_ADJUST_SPEED;
      } else if (this.xOffset < VisionConstants.DEADBAND_RANGE) {
        speed = 0.0;
      }
    }
    if (Math.abs(this.actuationMotor.getSelectedSensorPosition()) > VisionConstants.MAX_ROTATION_VALUE){
      speed = 0;
    }
    this.actuationMotor.set(ControlMode.PercentOutput, speed);
  }

  public void stopMotor () {
    this.actuationMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    this.hasTarget = tv.getDouble(0.0) == 1.0;

    this.xOffset = tx.getDouble(0.0);
    this.yOffset = ty.getDouble(0.0);
    this.area = tArea.getDouble(0.0);

    SmartDashboard.putBoolean("LimelightTarget", this.hasTarget);
    SmartDashboard.putNumber("LimelightX", this.xOffset);
    SmartDashboard.putNumber("LimelightY", this.yOffset);
    SmartDashboard.putNumber("LimelightArea", area);
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
