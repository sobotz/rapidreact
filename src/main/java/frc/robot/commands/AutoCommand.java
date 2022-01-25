// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
// would need in the future to import launcher + serializer + intake subsystems

public class AutoCommand extends CommandBase {
  private final DriveSubsystem m_drive;
  private boolean isFinished = false;
  private Timer timer;
  // Initializes an encoder on DIO pins 0 and 1
  // Defaults to 4X decoding and non-inverted
  Encoder encoder = new Encoder(0, 1);

  public AutoCommand(DriveSubsystem drive) {
    this.m_drive = drive;
    this.timer = new Timer();
    // initialize launcher, serializer + intake variables when import

    addRequirements(this.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Configures the encoder's distance-per-pulse
    // The robot moves forward 1 foot per encoder rotation
    // There are 256 pulses per encoder rotation
    encoder.setDistancePerPulse(4. / 256.);
    // nav x to stay straight
    // motor turn & distance
    // use encoders
    // https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/encoders-software.html

    /*
     * move 40 3/8 in forward;
     * start intake;
     * turn turret; move back ____;
     * start launcher; start rollers;
     * run belts;
     * ( possibly collect another ball after shooting done?)
     */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.timer.stop();
    this.timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isFinished;
  }
}
