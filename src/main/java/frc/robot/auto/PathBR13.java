// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import frc.robot.Constants;
import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class PathBR13 extends CommandBase {
  private final DriveSubsystem m_drive;
  private final IntakeSubsystem m_intake;

  private final LauncherSubsystem m_launcher;

  private final SerializerSubsystem m_serializer;

  private boolean isFinished = false;

  private Timer timer;

  private boolean driveState = false;

  private boolean atPosition = false;

  public PathBR13(DriveSubsystem drive, IntakeSubsystem intake, LauncherSubsystem launcher,SerializerSubsystem serializer) {
    this.m_drive = drive;
    this.m_intake = intake;
    this.m_launcher = launcher;
    this.m_serializer = serializer;
    this.timer = new Timer();

    addRequirements(this.m_drive, this.m_intake, this.m_launcher, this.m_serializer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // encoder.setDistancePerPulse(1. / 315.924339); // feet per PPR
    timer.start();
    m_drive.setLowGear();

    this.m_launcher.slowLauncher();
    timer.delay(0.5); // 1
    this.m_serializer.runBelt();
 
    timer.delay(0.4);
    this.m_serializer.stopBelt();
    timer.delay(1);
    this.m_serializer.runBelt();
    timer.delay(0.5);
    this.m_launcher.stopLauncher();
    this.m_serializer.stopBelt();
    timer.delay(0.5);

    // m_drive.drive(0.5,0);
    m_intake.toggleIntake();
    // timer.delay(0.5); 1
    while(!m_drive.pidLoop(-4)){
      timer.delay(.05);
    }
    timer.delay(1);
    m_intake.toggleIntake();
    
    timer.delay(0.5);

    while(!m_drive.pidLoop(-1)){
      timer.delay(.05);
    }

    timer.delay(0.5);

    // this.m_launcher.slowLauncher();
    this.m_launcher.startLauncher(LauncherConstants.TEAM_VELOCITY);
    timer.delay(0.5);
    this.m_serializer.runBelt();
 
    timer.delay(0.4);
    this.m_serializer.stopBelt();
    timer.delay(1);
    this.m_serializer.runBelt();
    timer.delay(0.8);
    this.m_launcher.stopLauncher();
    this.m_serializer.stopBelt();
    this.isFinished = true;

    /*timer.delay(1);
    m_drive.drive(0,0);*/
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
