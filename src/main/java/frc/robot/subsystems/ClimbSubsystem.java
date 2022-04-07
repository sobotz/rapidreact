// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimbConstants;
import edu.wpi.first.wpilibj.Timer;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  WPI_TalonFX rotateMotor, liftMotor;

  DoubleSolenoid armToggle;

  boolean armIn, lowRelease;
  
  Timer timer;

  public RetractStateEnum RetractState;

  public enum RetractStateEnum{
    IDLE,
    ATLIMIT,
  }

  public ClimbSubsystem() {
    this.liftMotor = new WPI_TalonFX(Constants.ClimbConstants.LIFT_MOTOR);
    RetractState = RetractStateEnum.IDLE;

    // Reset the configuration of each of the talons
    this.liftMotor.configFactoryDefault();

    this.armToggle = new DoubleSolenoid(12, PneumaticsModuleType.REVPH, ClimbConstants.ARM_IN,
    ClimbConstants.ARM_OUT);
    armToggle.set(DoubleSolenoid.Value.kForward);

    this.armIn = true;
    this.lowRelease = true;
  }

  public double getPosition(){
    return liftMotor.getSelectedSensorPosition();
  }

  public double getCurrent(){
    return liftMotor.getStatorCurrent();
  }

  public void liftExtend (){
    this.liftMotor.set(ControlMode.PercentOutput, ClimbConstants.LIFT_SPEED);
  }


  public void liftRetract (){
    this.liftMotor.set(ControlMode.PercentOutput, -(ClimbConstants.LIFT_SPEED));
  }


  public void liftStop (){
    this.liftMotor.set(ControlMode.PercentOutput, 0);
  }

  public boolean armLock() {
    return true;
  }

  public boolean armRelease(){
    armToggle.set((this.armIn) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    this.armIn = !this.armIn;
    return armIn;
  }

  @Override
  public void periodic() {
    //SmartDashboard.putBoolean("Low Lock:", this.lowLock);
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
