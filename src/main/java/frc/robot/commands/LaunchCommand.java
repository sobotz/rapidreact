// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchCommand extends CommandBase {
  /** Creates a new LaunchCommand. */

  LauncherSubsystem launcher;

  public LaunchCommand(LauncherSubsystem launcher) {
    this.launcher = launcher;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.launcher.runLauncher();
  }

  public void execute(){
    this.launcher.runLauncher();
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.launcher.stopLauncher();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
