// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;


import com.stuypulse.stuylib.input.Gamepad;
import com.stuypulse.stuylib.math.SLMath;
import com.stuypulse.stuylib.streams.IStream;
import com.stuypulse.stuylib.streams.booleans.BStream;
import com.stuypulse.stuylib.streams.booleans.filters.BDebounceRC;
import com.stuypulse.stuylib.streams.filters.LowPassFilter;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final DriveSubsystem m_drivetrain;

  private Joystick joystick;

  private double acceleration_constant;
  private double rotation_constant;

  private Timer timer;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */    
  public DriveCommand(DriveSubsystem drivetrain, Gamepad driver) {
    this.m_drivetrain = drivetrain;
    this.driver = driver;

    this.stalling =
            Stalling.STALL_DETECTION
                    .and(drivetrain::isStalling)
                    .filtered(new BDebounceRC.Both(Settings.Drivetrain.Stalling.DEBOUNCE_TIME));

    this.speed =
            IStream.create(() -> driver.getRightTrigger() - driver.getLeftTrigger())
                    .filtered(
                            x -> SLMath.deadband(x, Settings.Drivetrain.SPEED_DEADBAND.get()),
                            x -> SLMath.spow(x, Settings.Drivetrain.SPEED_POWER.get()),
                            new LowPassFilter(Settings.Drivetrain.SPEED_FILTER));

    this.angle =
            IStream.create(() -> driver.getLeftX())
                    .filtered(
                            x -> SLMath.deadband(x, Settings.Drivetrain.ANGLE_DEADBAND.get()),
                            x -> SLMath.spow(x, Settings.Drivetrain.ANGLE_POWER.get()),
                            new LowPassFilter(Settings.Drivetrain.ANGLE_FILTER));

    addRequirements(drivetrain);
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.acceleration_constant = SmartDashboard.getNumber("Acceleration Constant: ", DriveConstants.ACCELERATION_CONSTANT);
    this.rotation_constant = SmartDashboard.getNumber("Rotation Constant: ", DriveConstants.ROTATION_CONSTANT);
    double speed = this.joystick.getY();
    double rotation = this.joystick.getX() * this.rotation_constant;

    double normalizedSpeed = Math.signum(speed) * Math.pow(speed, this.acceleration_constant);

    

    this.m_drivetrain.drive(normalizedSpeed, -rotation);
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
