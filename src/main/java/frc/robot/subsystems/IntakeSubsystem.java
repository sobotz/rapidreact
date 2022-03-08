// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;


public class IntakeSubsystem extends SubsystemBase {

  public WPI_TalonSRX intakeTalon;
  CANSparkMax intakeController;
  private DoubleSolenoid intakeDeploy;

  public boolean hasDeployed;
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    //intakeController = new CANSparkMax(IntakeConstants.INTAKE_MOTOR, MotorType.kBrushless);
    intakeTalon = new WPI_TalonSRX(IntakeConstants.INTAKE_MOTOR);
    //Change CTREPCM to REVPM
    intakeDeploy = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.INTAKE_SOLENOID_DEPLOY,IntakeConstants.INTAKE_SOLENOID_RETRACT);

    //intakeController.configFactoryDefault();

    hasDeployed = false;


  }

  
  
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void deployIntake() {
    runIntake(0.0);
    //intakeDeploy.set(Value.kForward);
    intakeDeploy.set(Value.kForward);
  }
  
  public void runIntake(double speed) {
    intakeController.set(speed * IntakeConstants.MAXIMUM_INTAKE_SPEED);
  }
  
  public void retractIntake() {
    intakeDeploy.set(Value.kReverse);
  }

  
  public boolean toggleIntake() {
    if (hasDeployed) {
      intakeDeploy.set(DoubleSolenoid.Value.kForward);
      runIntake(0);
      hasDeployed = false;
    } else {
      intakeDeploy.set(DoubleSolenoid.Value.kReverse);
      runIntake(1);
      hasDeployed = true;
    }
    
    return !hasDeployed;
  }


  



}
