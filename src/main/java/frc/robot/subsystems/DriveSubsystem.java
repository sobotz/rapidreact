// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  WPI_TalonFX frontLeftController, frontRightController, backLeftController, backRightController;

  DoubleSolenoid gearShifter;

  double totalSensorPosition;

  boolean lowGear;

  boolean finishDrive;

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



    this.frontLeftController.configOpenloopRamp(0.5);
    this.backLeftController.configOpenloopRamp(0.5);
    this.frontRightController.configOpenloopRamp(0.5);
    this.backRightController.configOpenloopRamp(0.5);

    this.frontLeftController.configClosedloopRamp(0);
    this.backLeftController.configClosedloopRamp(0);
    this.frontRightController.configClosedloopRamp(0);
    this.backRightController.configClosedloopRamp(0);

    this.frontLeftController.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 15, 0.5));

     /* Set Motion Magic gains in slot0 - see documentation */
		this.frontLeftController.selectProfileSlot(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kPIDLoopIdx);
    this.frontLeftController.config_kF(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kF, Constants.AutoConstants.kTimeoutMs);
		this.frontLeftController.config_kP(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kP, Constants.AutoConstants.kTimeoutMs);
		this.frontLeftController.config_kI(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kI, Constants.AutoConstants.kTimeoutMs);
		this.frontLeftController.config_kD(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kD, Constants.AutoConstants.kTimeoutMs);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.selectProfileSlot(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kPIDLoopIdx);
    this.backLeftController.config_kF(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kF, Constants.AutoConstants.kTimeoutMs);
		this.backLeftController.config_kP(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kP, Constants.AutoConstants.kTimeoutMs);
		this.backLeftController.config_kI(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kI, Constants.AutoConstants.kTimeoutMs);
		this.backLeftController.config_kD(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kD, Constants.AutoConstants.kTimeoutMs);

    this.frontRightController.selectProfileSlot(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kPIDLoopIdx);
    this.frontRightController.config_kF(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kF, Constants.AutoConstants.kTimeoutMs);
		this.frontRightController.config_kP(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kP, Constants.AutoConstants.kTimeoutMs);
		this.frontRightController.config_kI(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kI, Constants.AutoConstants.kTimeoutMs);
		this.frontRightController.config_kD(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kD, Constants.AutoConstants.kTimeoutMs);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.selectProfileSlot(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kPIDLoopIdx);
    this.backRightController.config_kF(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kF, Constants.AutoConstants.kTimeoutMs);
		this.backRightController.config_kP(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kP, Constants.AutoConstants.kTimeoutMs);
		this.backRightController.config_kI(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kI, Constants.AutoConstants.kTimeoutMs);
		this.backRightController.config_kD(Constants.AutoConstants.kSlotIdx, Constants.AutoConstants.kD, Constants.AutoConstants.kTimeoutMs);

    this.frontLeftController.setNeutralMode(NeutralMode.Brake);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.setNeutralMode(NeutralMode.Brake);
    this.frontRightController.setNeutralMode(NeutralMode.Brake);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.setNeutralMode(NeutralMode.Brake);

    /* Set acceleration and vcruise velocity - see documentation */
		this.frontLeftController.configMotionCruiseVelocity(11000, Constants.AutoConstants.kTimeoutMs);
		this.frontLeftController.configMotionAcceleration(11000, Constants.AutoConstants.kTimeoutMs);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.configMotionCruiseVelocity(11000, Constants.AutoConstants.kTimeoutMs);
		this.backLeftController.configMotionAcceleration(11000, Constants.AutoConstants.kTimeoutMs);

    this.frontRightController.configMotionCruiseVelocity(-11000, Constants.AutoConstants.kTimeoutMs);
		this.frontRightController.configMotionAcceleration(-11000, Constants.AutoConstants.kTimeoutMs);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.configMotionCruiseVelocity(-11000, Constants.AutoConstants.kTimeoutMs);
		this.backRightController.configMotionAcceleration(-11000, Constants.AutoConstants.kTimeoutMs);

    /* Zero the sensor once on robot boot up */
		this.frontRightController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.backRightController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.frontLeftController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.backLeftController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);

    // this.gearShifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.DriveConstants.GEAR_SHIFT_DEPLOY,
       // Constants.DriveConstants.GEAR_SHIFT_RETRACT);
    
    this.gearShifter = new DoubleSolenoid(12, PneumaticsModuleType.REVPH, Constants.DriveConstants.GEAR_SHIFT_DEPLOY, Constants.DriveConstants.GEAR_SHIFT_RETRACT);

    this.lowGear = true;

    this.finishDrive = false;

    this.totalSensorPosition = 0;

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
    /*this.frontLeftController.setNeutralMode(NeutralMode.Coast);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.setNeutralMode(NeutralMode.Coast);
    this.frontRightController.setNeutralMode(NeutralMode.Coast);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.setNeutralMode(NeutralMode.Coast);*/
    this.frontRightController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.backRightController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.frontLeftController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.backLeftController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
  // if (true || joystick) {
    // double targetPosition = (22788.5556*speed*distance); // 48 3/8 inches desired
    double targetPosition = (Constants.AutoConstants.autoDrive*speed*distance);
    /* 2000 RPM in either direction */
    this.frontLeftController.set(ControlMode.MotionMagic, targetPosition);
    this.backLeftController.set(ControlMode.MotionMagic, targetPosition);
    // this.backLeftController.follow(this.frontLeftController);

    this.frontRightController.set(ControlMode.MotionMagic, -targetPosition);
    // this.backRightController.follow(this.frontRightController);//}
    this.backRightController.set(ControlMode.MotionMagic, -targetPosition);

    this.frontRightController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.backRightController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.frontLeftController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);
    this.backLeftController.setSelectedSensorPosition(0, Constants.AutoConstants.kPIDLoopIdx, Constants.AutoConstants.kTimeoutMs);

    this.frontLeftController.setNeutralMode(NeutralMode.Brake);
    // this.backLeftController.follow(this.frontLeftController);
    this.backLeftController.setNeutralMode(NeutralMode.Brake);
    this.frontRightController.setNeutralMode(NeutralMode.Brake);
    // this.backRightController.follow(this.frontRightController);
    this.backRightController.setNeutralMode(NeutralMode.Brake);

			
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

  public void setLowGear () {
    gearShifter.set(Value.kForward);
  }

  public void setHighGear () {
    gearShifter.set(Value.kReverse);
  }

  public boolean shiftGear() {
    gearShifter.set((this.lowGear) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
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