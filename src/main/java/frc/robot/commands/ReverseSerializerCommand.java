// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class ReverseSerializerCommand extends CommandBase {
  /** Creates a new ReverseSerializer. */
  private SerializerSubsystem serializer;
  private IntakeSubsystem intake;
  

  public ReverseSerializerCommand(IntakeSubsystem r_intake, SerializerSubsystem r_serializer) {
    // Use addRequirements() here to declare subsystem dependencies.
    intake =  r_intake;
    serializer = r_serializer;
    addRequirements(serializer, intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    serializer.acceptingBalls = false;
    if (intake.hasDeployed)
      this.intake.toggleIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.serializer.reverseBelt();
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    serializer.stopBelt();
    this.serializer.acceptingBalls = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
