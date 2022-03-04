// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// https://github.com/thordogzaan/Frc-2021-Falcon-500-Code/blob/main/Robot.java 
// Motion Magic: https://docs.ctre-phoenix.com/en/stable/ch16_ClosedLoop.html 

package frc.robot.auto;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class PathBR11 extends CommandBase {
  private final DriveSubsystem m_drive;

  private Timer timer;

  private final IntakeSubsystem m_intake;

  private final LauncherSubsystem m_launcher;

  private final SerializerSubsystem m_serializer;

  private final DriveSubsystem driveFinished;

  private boolean isFinished = false;

  public PathBR11(DriveSubsystem drive ) {
    this.m_drive = drive;

    timer = new Timer();
    // initialize launcher, serializer + intake variables when import
    this.m_intake = new IntakeSubsystem();
    this.m_launcher = new LauncherSubsystem();
    this.m_serializer = new SerializerSubsystem();

    driveFinished = new DriveSubsystem();
    
    addRequirements(this.m_drive, this.m_intake, this.m_launcher, this.m_serializer);
  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.testDrive(-1.0, 4.0); // move 4 ft ~ takes approximately 2 seconds

    if(m_drive.finishDrive()){
      timer.start();
      if(timer.get()<1.5){ // in 1.5 seconds, intake ball
        m_intake.runIntake(1.0);
      }
    }
    
    timer.stop();
    
    this.m_intake.runIntake(0.0); // stop running intake
    
    /*m_drive.testDrive(-1.0, 2.0); // move 2 ft

    timer.reset();
    timer.start();
    
    if (timer.get()<3 ){ // shoot
      this.m_launcher.startLauncher();
      this.m_launcher.startRollers();
      this.m_serializer.runBelt();
      this.m_serializer.acceptingBalls = false;
    }
    else{
      this.m_launcher.stopLauncher();
      this.m_launcher.stopRollers();
      this.m_serializer.stopBelt();
      this.m_serializer.acceptingBalls = true;
      this.isFinished = true;
    }*/
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
