// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  WPI_TalonFX frontLeftController, frontRightController, backLeftController, backRightController;
  WPI_TalonFX rotateMotor, liftMotor;

  DoubleSolenoid armLock;

  boolean lowGear;

  public ClimbSubsystem() {
    this.rotateMotor = new WPI_TalonFX(Constants.ClimbConstants.ROTATE_MOTOR);
    this.liftMotor = new WPI_TalonFX(Constants.ClimbConstants.LIFT_MOTOR);

    // Reset the configuration of each of the talons
    this.rotateMotor.configFactoryDefault();
    this.liftMotor.configFactoryDefault();

    this.armLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbConstants.ARM_LOCK_DEPLOY,
    Constants.ClimbConstants.ARM_LOCK_RETRACT);
    this.lowGear = true;
  }

  public void rotateClockwise (){
    this.rotateMotor.set(ControlMode.PercentOutput, ClimbConstants.ROTATE_SPEED);
  }

// unfinished methods
  public void rotateCounterclockwise (){
    this.rotateMotor.set(ControlMode.PercentOutput, (ClimbConstants.ROTATE_SPEED) * (-1));
  }


  public void liftExtend (){
    this.rotateMotor.set(ControlMode.PercentOutput, ClimbConstants.ROTATE_SPEED);
  }


  public void liftRetract (){
    this.rotateMotor.set(ControlMode.PercentOutput, (ClimbConstants.ROTATE_SPEED) * (-1));
  }


  public void rotateStop (){
    this.rotateMotor.set(ControlMode.PercentOutput, 0);
  }


  public void liftStop (){
    this.rotateMotor.set(ControlMode.PercentOutput, 0);
  }

  public boolean armLock() {
    armLock.set((this.lowGear) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
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
