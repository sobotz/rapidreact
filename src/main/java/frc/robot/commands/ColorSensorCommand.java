// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensorSubsystem;

public class ColorSensorCommand extends CommandBase {
  private ColorSensorSubsystem colorSensorSubsystem;
  /** Creates a new ColorSensorCommand. */
  public ColorSensorCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    colorSensorSubsystem = new ColorSensorSubsystem();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(colorSensorSubsystem.weAreBlue){
        colorSensorSubsystem.weAreBlue = false;
      }
      if(colorSensorSubsystem.weAreBlue == false){
        colorSensorSubsystem.weAreBlue = true;
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
