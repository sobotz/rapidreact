/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class LaunchSerializerCommand extends CommandBase {
  private SerializerSubsystem serializer;
  private SensorSubsystem sensors;

  /**
   * Creates a new LaunchAllCommand.
   */
  public LaunchSerializerCommand(SerializerSubsystem serializer1, SensorSubsystem sensors1) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.serializer = serializer1;
    
    this.sensors = sensors1;
    addRequirements(serializer,sensors);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    serializer.getCommandMode();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.serializer.runSerializer(1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    serializer.runSerializer(0);
    serializer.getSerializerMode();
  }


}