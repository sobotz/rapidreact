// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class PathBR22 extends CommandBase {
  private final DriveSubsystem m_drive;
  private final IntakeSubsystem m_intake;
  private final LauncherSubsystem m_launcher;
  private final SerializerSubsystem m_serializer;
 
  private Timer timer;


  private boolean isFinished = false;

  public PathBR22(DriveSubsystem drive, IntakeSubsystem intake, LauncherSubsystem launcher,SerializerSubsystem serializer) {
    this.m_drive = drive;
    this.m_intake = intake;
    this.m_launcher = launcher;
    this.m_serializer = serializer;
    timer = new Timer();
    // initialize launcher, serializer + intake variables when import
    // this.m_intake = intake
    addRequirements(this.m_drive, this.m_intake, this.m_launcher, this.m_serializer);
  }
  @Override
  public void initialize() {
    timer.start();
    m_drive.setLowGear();

    this.m_launcher.startLauncher(LauncherConstants.TEAM_VELOCITY);
    timer.delay(1);
    this.m_serializer.runBelt();
 
    timer.delay(0.4);
    this.m_serializer.stopBelt();
    timer.delay(1);
    this.m_serializer.runBelt();
    timer.delay(0.5);
    this.m_launcher.stopLauncher();
    this.m_serializer.stopBelt();
    timer.delay(1);
    m_drive.drive(0.5,0);
    timer.delay(0.9);
    m_drive.drive(0,0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isFinished;
  }
}
