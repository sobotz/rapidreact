package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  NetworkTable table;

  NetworkTableEntry tx;
  NetworkTableEntry tArea;
  NetworkTableEntry tv;

  boolean hasTarget, lightOn;

  double xOffset;
  double yOffset;
  double area;
  double defaultSpeed;
  double sumOffest;
  double lastOffset;

  // TalonSRX actuationMotor;
  TalonFX actuationMotor;

  public VisionSubsystem() {

    this.table = NetworkTableInstance.getDefault().getTable("limelight");

    this.tv = table.getEntry("tv");
    this.tx = table.getEntry("tx");
    this.tArea = table.getEntry("ta");

    table.getEntry("ledMode").setNumber(1);

    this.lightOn = false;

    this.actuationMotor = new TalonFX(VisionConstants.ACTUATION_MOTOR);
    this.actuationMotor.configForwardSoftLimitThreshold(VisionConstants.MAX_ROTATION_VALUE, 0);
    this.actuationMotor.configReverseSoftLimitThreshold(-VisionConstants.MAX_ROTATION_VALUE, 0);
    this.actuationMotor.configForwardSoftLimitEnable(true, 0);
    this.actuationMotor.configReverseSoftLimitEnable(true, 0);

    defaultSpeed = -1;
  }

  public void correctX () {

    double speedPercent = 0;

    double proportionalOffset = this.xOffset / VisionConstants.LIMELIGHT_HALF_X_FOV;

    this.sumOffest += this.xOffset;

    double slope = this.xOffset - this.lastOffset;

    speedPercent = proportionalOffset * VisionConstants.kP + this.sumOffest * VisionConstants.kI + slope * VisionConstants.kD;

    this.actuationMotor.set(ControlMode.PercentOutput, VisionConstants.MAX_SPEED * speedPercent);

    this.lastOffset = this.xOffset;
  }

  public void stopMotor () {
    this.actuationMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void toggleLight () {
    this.table.getEntry("ledMode").setNumber(lightOn ? 1 : 3);
    
  }

  public double targetDistance () {
    return (this.hasTarget) ? VisionConstants.LIMELIGHT_TO_HUB_HEIGHT / Math.tan(Math.toRadians(VisionConstants.LIMELIGHT_ANGLE + this.yOffset)): -1;
  }

  public boolean inRange() {
    return this.hasTarget && this.targetDistance() > VisionConstants.MIN_DISTANCE && this.targetDistance() < VisionConstants.MAX_DISTANCE;
  }

  public boolean isAligned() {
    return this.hasTarget && Math.abs(this.getXOffset()) < VisionConstants.ALIGNMENT_RANGE;
  }

  @Override
  public void periodic() {
    this.hasTarget = tv.getDouble(0.0) == 1.0;

    this.xOffset = tx.getDouble(0.0);
    this.area = tArea.getDouble(0.0);

    SmartDashboard.putBoolean("LimelightTarget", this.hasTarget);
    SmartDashboard.putBoolean("In Range", this.inRange());
    SmartDashboard.putBoolean("Turret Aligned", this.isAligned());
    SmartDashboard.putNumber("LimelightX", this.xOffset);

    SmartDashboard.putNumber("TargetDistance", this.targetDistance());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public double getXOffset() {
    return this.xOffset;
  }
}
