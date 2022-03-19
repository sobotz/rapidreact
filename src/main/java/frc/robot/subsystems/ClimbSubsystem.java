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

  DoubleSolenoid armLock, armRelease;

  boolean lowLock, lowRelease;
  
  Timer timer;

  public ClimbSubsystem() {
    this.liftMotor = new WPI_TalonFX(Constants.ClimbConstants.LIFT_MOTOR);

    // Reset the configuration of each of the talons
    this.liftMotor.configFactoryDefault();

    this.armLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbConstants.ARM_LOCK_DEPLOY,
    Constants.ClimbConstants.ARM_LOCK_RETRACT);

    this.armRelease = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbConstants.ARM_RELEASE_DEPLOY,
  5);

    this.lowLock = true;
    this.lowRelease = true;
  }

  public void liftExtend (){
    this.liftMotor.set(ControlMode.PercentOutput, ClimbConstants.LIFT_SPEED);
  }


  public void liftRetract (){
    this.liftMotor.set(ControlMode.PercentOutput, (ClimbConstants.LIFT_SPEED) * (-1));
  }


  public void liftStop (){
    this.liftMotor.set(ControlMode.PercentOutput, 0);
  }

  public boolean armLock() {
    armLock.set((this.lowLock) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    this.lowLock = !this.lowLock;
    return lowLock;
  }

  public boolean armRelease(){
    armRelease.set((this.lowRelease) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    this.lowRelease = !this.lowRelease;
    return lowRelease;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Low Lock:", this.lowLock);
    /*double exceeds = 10.0;    //Represents the voltage that it can't exceed. 
    int count = 0;            //Represents the time that it has exceeded limit.
    int limitCount = 10;      //Represents the amount of time count should be to stop retract
    if(this.rotateMotor.getStatorCurrent() >= exceeds){
      // count++;
      timer.get();
      if(count == limitCount)
        liftStop();
    } else {
      count = 0;
    }*/
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
