// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// https://github.com/thordogzaan/Frc-2021-Falcon-500-Code/blob/main/Robot.java 
// Motion Magic: https://docs.ctre-phoenix.com/en/stable/ch16_ClosedLoop.html 

package frc.robot.auto;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.IntakeSubsystem
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class PathBR61 extends CommandBase {
  private final DriveSubsystem m_drive;
  private final IntakeSubsystem m_intake;
  private Timer timer;

  // private final IntakeSubsystem m_intake;

  private boolean isFinished = false;

  public PathBR61(DriveSubsystem drive, IntakeSubsystem intake) {
    this.m_drive = drive;
    this.m_intake = intake;
    timer = new Timer();
    // initialize launcher, serializer + intake variables when import
    // this.m_intake = intake
    addRequirements(this.m_drive, this.m_intake);
  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.testDrive(-1.0, 3.25); // sets speed paramater as 4 feet

    if (timer.get() < 2){
      m_intake.runIntake(1.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_drive.drive(0, 0);
    // this.m_intake.runIntake(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isFinished;
  }
}
