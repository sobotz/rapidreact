// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// https://github.com/thordogzaan/Frc-2021-Falcon-500-Code/blob/main/Robot.java 
// Motion Magic: https://docs.ctre-phoenix.com/en/stable/ch16_ClosedLoop.html 

package frc.robot.auto;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PathBR11 extends CommandBase {
  private final DriveSubsystem m_drive;

  private boolean isFinished = false;

  public PathBR11(DriveSubsystem drive) {
    this.m_drive = drive;
    // initialize launcher, serializer + intake variables when import

    addRequirements(this.m_drive);
  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.testDrive(1.0); // sets speed paramater as 4 feet
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_drive.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isFinished;
  }
}
