// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class PathBR01 extends CommandBase {
  private final DriveSubsystem m_drive;

  private boolean isFinished = false;

  private Timer timer;

  public PathBR01(DriveSubsystem drive) {
    this.m_drive = drive;
    this.timer = new Timer();

    addRequirements(this.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.timer.start();
    // encoder.setDistancePerPulse(1. / 315.924339); // feet per PPR
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() < 0.7) {
      m_drive.drive(-0.5, 0);
    }
    else{
      m_drive.drive(0,0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_drive.drive(0, 0);
    this.timer.stop();
    this.timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isFinished;
  }
}
