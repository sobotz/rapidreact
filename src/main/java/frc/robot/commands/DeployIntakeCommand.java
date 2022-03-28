// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;


public class DeployIntakeCommand extends InstantCommand {
  private final IntakeSubsystem m_intake;
  private SerializerSubsystem serializer;
  /** Creates a new DeployIntakeCommand. */
  
    public DeployIntakeCommand(IntakeSubsystem subsystem,SerializerSubsystem serializer1) {
      m_intake = subsystem;
      serializer = serializer1;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_intake, serializer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.toggleIntake();
  }

  public void execute () {
  }
  
  public void end() {
    m_intake.toggleIntake();
  }
}

