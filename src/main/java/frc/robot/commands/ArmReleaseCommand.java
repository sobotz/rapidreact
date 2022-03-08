/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ArmReleaseCommand extends InstantCommand {
  private final ClimbSubsystem m_ClimbSubsystem;

    /**
     * Creates a new ShiftGearCommand.
     */
    public ArmReleaseCommand(ClimbSubsystem subsystem) {
        this.m_ClimbSubsystem = subsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_ClimbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ClimbSubsystem.armRelease();
  }

  /*public void end(){
  }*/
}
