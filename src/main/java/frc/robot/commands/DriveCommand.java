// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  private final DriveSubsystem m_drivetrain;

  private Joystick joystick;

  private double acceleration_constant;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveSubsystem drivetrain, Joystick joystick) {
    m_drivetrain = drivetrain;
    this.joystick = joystick;
    this.acceleration_constant = DriveConstants.ACCELERATION_CONSTANT;
    // If acceleration constant is less then 2 robot starts to act weird
    // If number less then 2 robot starts to act weird
    SmartDashboard.putNumber("Acceleration Constant: ", this.acceleration_constant);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    this.acceleration_constant = SmartDashboard.getNumber("Acceleration Constant: ",
        DriveConstants.ACCELERATION_CONSTANT);

    this.acceleration_constant = SmartDashboard.getNumber("Acceleration Constant: ", DriveConstants.ACCELERATION_CONSTANT);

    double speed = this.joystick.getRawAxis(5);
    double rotation = this.joystick.getRawAxis(4);

    // double normalizedSpeed = Math.signum(speed) * Math.pow(Math.abs(speed)*DriveConstants.MAX_ROTATION_SPEED, this.acceleration_constant);
    // double normalizedRotation = -1 * Math.signum(rotation) * Math.pow(Math.abs(rotation) * DriveConstants.MAX_ROTATION_SPEED, this.acceleration_constant);
    double normalizedSpeed = Math.signum(speed) * Math.pow(Math.abs(speed), this.acceleration_constant);
    double normalizedRotation = Math.signum(rotation) * Math.pow(Math.abs(rotation) * DriveConstants.MAX_ROTATION_SPEED, this.acceleration_constant);
    System.out.println("Speed: " + normalizedSpeed);
    System.out.println("Rotation: " + normalizedRotation);

    this.m_drivetrain.drive(normalizedSpeed, -normalizedRotation);
    // System.out.println("Button1 : " + joystick.getRawButton(1) + " Button2 : " + joystick.getRawButton(2)+ " Button3 : " + joystick.getRawButton(3)+ " Button4 : " + joystick.getRawButton(4));
    // this.m_drivetrain.testDrive(speed, rotation, joystick.getRawButton(1));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}