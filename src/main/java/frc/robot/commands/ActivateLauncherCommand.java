// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class ActivateLauncherCommand extends CommandBase {
  private SerializerSubsystem serializer;
  private LauncherSubsystem launcher;
  private ColorSensorSubsystem colorSensor;


  private int targetVelocity;

  /**
   * Creates a new LaunchAllCommand.
   */
  public ActivateLauncherCommand(SerializerSubsystem serializer1, LauncherSubsystem launcher1) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.serializer = serializer1;
    this.launcher = launcher1;
    this.targetVelocity = 0;

    addRequirements(serializer, launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.launcher.startLauncher(targetVelocity);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.targetVelocity = (colorSensor.allyBall()) ? (LauncherConstants.TEAM_VELOCITY) : LauncherConstants.ENEMY_VELOCITY;
    if (launcher.getVelocity() > LauncherConstants.TEAM_VELOCITY - 200 && launcher.getVelocity() < LauncherConstants.TEAM_VELOCITY + 200) {
      this.serializer.runBelt();
    } else {
      this.serializer.stopBelt();
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.launcher.stopLauncher();
    this.serializer.stopBelt();
  }

}

