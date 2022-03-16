// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.Constants.LauncherConstants;

public class PathBR02 extends CommandBase {
  private final DriveSubsystem m_drive;

  private Timer timer;

  private final IntakeSubsystem m_intake;

  private final LauncherSubsystem m_launcher;

  private final SerializerSubsystem m_serializer;

  private boolean isFinished = false;

  public PathBR02(DriveSubsystem drive, IntakeSubsystem intake, LauncherSubsystem launcher,SerializerSubsystem serializer) {
    this.m_drive = drive;

    this.timer = new Timer();
    // initialize launcher, serializer + intake variables when import
    this.m_intake = intake;
    this.m_launcher = launcher;
    this.m_serializer = serializer;
    
    addRequirements(this.m_drive, this.m_intake, this.m_launcher, this.m_serializer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    m_drive.testDrive(-1.0, 6.0); // move 6 ft 
    timer.delay(2);

    this.m_launcher.startLauncher(LauncherConstants.TEAM_VELOCITY);
    this.m_launcher.startRollers();
    this.m_serializer.runBelt();
    this.m_serializer.acceptingBalls = false;
    
    timer.delay(1);
    this.m_launcher.stopLauncher();
    this.m_launcher.stopRollers();
    this.m_serializer.stopBelt();
    this.m_serializer.acceptingBalls = true;
    this.isFinished = true;

    timer.reset();
  }
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
