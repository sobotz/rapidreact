// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AlignShootCommand extends SequentialCommandGroup {
  /** Creates a new AlignShootCommand. */
  public AlignShootCommand(SerializerSubsystem serializer, LauncherSubsystem launcher, VisionSubsystem vision) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new AutoAlignCommand(vision), new ActivateLauncherCommand(serializer, launcher, vision));
  }
}
