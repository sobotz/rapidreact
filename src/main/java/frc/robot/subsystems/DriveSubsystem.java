// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  WPI_TalonFX frontLeftController, frontRightController, backLeftController, backRightController;

  DoubleSolenoid gearShifter;

  boolean lowGear;

  public DriveSubsystem() {
    this.frontLeftController = new WPI_TalonFX(Constants.DriveConstants.LEFT_FRONT_TALON);
    this.frontRightController = new WPI_TalonFX(Constants.DriveConstants.RIGHT_FRONT_TALON);
    this.backLeftController = new WPI_TalonFX(Constants.DriveConstants.LEFT_BACK_TALON);
    this.backRightController = new WPI_TalonFX(Constants.DriveConstants.RIGHT_BACK_TALON);
    
    this.frontLeftController.setInverted(TalonFXInvertType.Clockwise);
    this.backLeftController.setInverted(TalonFXInvertType.Clockwise);

    this.frontRightController.setInverted(TalonFXInvertType.Clockwise);
    this.backRightController.setInverted(TalonFXInvertType.Clockwise);

    // Reset the configuration of each of the talons
    this.frontLeftController.configFactoryDefault();
    this.frontRightController.configFactoryDefault();
    this.backLeftController.configFactoryDefault();
    this.backRightController.configFactoryDefault();

    
    this.gearShifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.DriveConstants.GEAR_SHIFT_DEPLOY, Constants.DriveConstants.GEAR_SHIFT_RETRACT);
    this.lowGear = false;

  }

  public void drive (double speed, double rotation){
    this.frontLeftController.set(ControlMode.PercentOutput, speed,
            DemandType.ArbitraryFeedForward, rotation);
    this.backLeftController.follow(this.frontLeftController);

    this.frontRightController.set(ControlMode.PercentOutput, -speed,
            DemandType.ArbitraryFeedForward, rotation);
    this.backRightController.follow(this.frontRightController);
  }

  public boolean shiftGear() {
    gearShifter.set((this.lowGear) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    this.lowGear = !this.lowGear;
    return lowGear;
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
