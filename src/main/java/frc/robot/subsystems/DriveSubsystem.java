// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  WPI_TalonFX frontLeftController, frontRightController, backLeftController, backRightController;

  public DriveSubsystem() {
    this.frontLeftController = new WPI_TalonFX(2);
    this.frontRightController = new WPI_TalonFX(4);
    this.backLeftController = new WPI_TalonFX(1);
    this.backRightController = new WPI_TalonFX(3);
    
    this.frontLeftController.setInverted(TalonFXInvertType.Clockwise);
    this.backLeftController.setInverted(TalonFXInvertType.Clockwise);

    this.frontRightController.setInverted(TalonFXInvertType.Clockwise);
    this.backRightController.setInverted(TalonFXInvertType.Clockwise);

    // Reset the configuration of each of the talons
    this.frontLeftController.configFactoryDefault();
    this.frontRightController.configFactoryDefault();
    this.backLeftController.configFactoryDefault();
    this.backRightController.configFactoryDefault();

  }

  public void drive (double speed, double rotation){
    this.frontLeftController.set(ControlMode.PercentOutput, speed,
            DemandType.ArbitraryFeedForward, rotation);
    this.backLeftController.follow(this.frontLeftController);

    this.frontRightController.set(ControlMode.PercentOutput, -speed,
            DemandType.ArbitraryFeedForward, rotation);
    this.backRightController.follow(this.frontRightController);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}