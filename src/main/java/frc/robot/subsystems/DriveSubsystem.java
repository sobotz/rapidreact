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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

     /* Set Motion Magic gains in slot0 - see documentation */
		this.frontLeftController.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    this.frontLeftController.config_kF(Constants.kSlotIdx, Constants.kF, Constants.kTimeoutMs);
		this.frontLeftController.config_kP(Constants.kSlotIdx, Constants.kP, Constants.kTimeoutMs);
		this.frontLeftController.config_kI(Constants.kSlotIdx, Constants.kI, Constants.kTimeoutMs);
		this.frontLeftController.config_kD(Constants.kSlotIdx, Constants.kD, Constants.kTimeoutMs);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    this.backLeftController.config_kF(Constants.kSlotIdx, Constants.kF, Constants.kTimeoutMs);
		this.backLeftController.config_kP(Constants.kSlotIdx, Constants.kP, Constants.kTimeoutMs);
		this.backLeftController.config_kI(Constants.kSlotIdx, Constants.kI, Constants.kTimeoutMs);
		this.backLeftController.config_kD(Constants.kSlotIdx, Constants.kD, Constants.kTimeoutMs);

    this.frontRightController.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    this.frontRightController.config_kF(Constants.kSlotIdx, Constants.kF, Constants.kTimeoutMs);
		this.frontRightController.config_kP(Constants.kSlotIdx, Constants.kP, Constants.kTimeoutMs);
		this.frontRightController.config_kI(Constants.kSlotIdx, Constants.kI, Constants.kTimeoutMs);
		this.frontRightController.config_kD(Constants.kSlotIdx, Constants.kD, Constants.kTimeoutMs);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    this.backRightController.config_kF(Constants.kSlotIdx, Constants.kF, Constants.kTimeoutMs);
		this.backRightController.config_kP(Constants.kSlotIdx, Constants.kP, Constants.kTimeoutMs);
		this.backRightController.config_kI(Constants.kSlotIdx, Constants.kI, Constants.kTimeoutMs);
		this.backRightController.config_kD(Constants.kSlotIdx, Constants.kD, Constants.kTimeoutMs);

    /* Set acceleration and vcruise velocity - see documentation */
		this.frontLeftController.configMotionCruiseVelocity(11000, Constants.kTimeoutMs);
		this.frontLeftController.configMotionAcceleration(11000, Constants.kTimeoutMs);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.configMotionCruiseVelocity(11000, Constants.kTimeoutMs);
		this.backLeftController.configMotionAcceleration(11000, Constants.kTimeoutMs);

    this.frontRightController.configMotionCruiseVelocity(11000, Constants.kTimeoutMs);
		this.frontRightController.configMotionAcceleration(11000, Constants.kTimeoutMs);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.configMotionCruiseVelocity(11000, Constants.kTimeoutMs);
		this.backRightController.configMotionAcceleration(11000, Constants.kTimeoutMs);

    /* Zero the sensor once on robot boot up */
		this.frontRightController.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    this.backRightController.follow(this.frontRightController);
    this.frontLeftController.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    this.backLeftController.follow(this.frontLeftController);

    // this.gearShifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.DriveConstants.GEAR_SHIFT_DEPLOY,
       // Constants.DriveConstants.GEAR_SHIFT_RETRACT);
    
     this.gearShifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.DriveConstants.GEAR_SHIFT_DEPLOY, Constants.DriveConstants.GEAR_SHIFT_RETRACT);

    this.lowGear = true;

  }

  public void drive(double speed, double rotation) {
    this.frontLeftController.set(ControlMode.PercentOutput, speed,
        DemandType.ArbitraryFeedForward, rotation);
    this.backLeftController.follow(this.frontLeftController);

    this.frontRightController.set(ControlMode.PercentOutput, -speed,
        DemandType.ArbitraryFeedForward, rotation);
    this.backRightController.follow(this.frontRightController);
  }

  public void testDrive(double speed, double distance){

    // if (true || joystick) {
      double targetPosition = 22788.5556*speed*distance; // 48 3/8 inches desired
			/* 2000 RPM in either direction */
      this.frontLeftController.set(ControlMode.MotionMagic, targetPosition);
      this.backLeftController.follow(this.frontLeftController);

      this.frontRightController.set(ControlMode.MotionMagic, -targetPosition);
      this.backRightController.follow(this.frontRightController);//}
			/* Velocity Closed Loop */

			/**
			 * Convert 2000 RPM to units / 100ms.
			 * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
      // System.out.print("Button is being pressed");
			// double targetVelocity_UnitsPer100ms = speed * 11000;
			// /* 2000 RPM in either direction */
      // this.frontLeftController.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms,
      //   DemandType.ArbitraryFeedForward, rotation);
      // this.backLeftController.follow(this.frontLeftController);

      // this.frontRightController.set(ControlMode.Velocity, -targetVelocity_UnitsPer100ms,
      //   DemandType.ArbitraryFeedForward, rotation);
      // this.backRightController.follow(this.frontRightController);

		/*} else {
			/* Percent Output */
      /*System.out.print("Button is being not pressed");
      this.frontLeftController.set(ControlMode.PercentOutput, speed,
        DemandType.ArbitraryFeedForward, rotation);
      this.backLeftController.follow(this.frontLeftController);

      this.frontRightController.set(ControlMode.PercentOutput, -speed,
        DemandType.ArbitraryFeedForward, rotation);
      this.backRightController.follow(this.frontRightController);
		}*/
  }

  public boolean shiftGear() {
    gearShifter.set((this.lowGear) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    this.lowGear = !this.lowGear;
    return lowGear;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Low Gear:", this.lowGear);
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}