// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class RunAllCommand extends CommandBase {
  private ColorSensorSubsystem colorsensor;
  private LauncherSubsystem launcher;
  private SerializerSubsystem serializer;
  private Timer timer;
  /** Creates a new RunAll. */
  public RunAllCommand(ColorSensorSubsystem colors, LauncherSubsystem launcher, SerializerSubsystem serializer) {
    this.colorsensor = colors;
    this.launcher = launcher;
    this.serializer = serializer;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //launcher.resetLauncher();
    this.launcher.startLauncher(LauncherConstants.TEAM_VELOCITY);
    /**this.timer.delay(2.0);
    this.launcher.stopLauncher();
    this.serializer.runBelt();

    if(serializer.getLauncherSensorVal()){
      this.serializer.stopBelt();
    }
    
    this.launcher.startLauncher(LauncherConstants.TEAM_VELOCITY);
    this.timer.delay(2.0);
    this.launcher.stopLauncher();*/

    serializer.interrupted = false;
    colorsensor.clearBallValues();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    launcher.stopLauncher();
    serializer.stopBelt();
  }

  // Returns true when the command should end.
  //@Override
  //public boolean isFinished() {
    //return false;
}

