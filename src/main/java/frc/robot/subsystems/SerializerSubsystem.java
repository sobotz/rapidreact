package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SerializerConstants;


public class SerializerSubsystem extends SubsystemBase {
  /**
   * Creates a new Serializer.
   */
  WPI_TalonSRX serializerMotor;

  // Initializes the sensors in the serializer and launcher
  public AnalogInput serializerSensor;
  AnalogInput launcherSensor;

  // Initializes variables that wiil be used in the program
  public boolean runSerializer;
  public boolean lastSerializerVal = false; // previous launcher sensor value

  public SerializerSubsystem() {
    
    // instantiates sensor values with respect to the contants method
    serializerSensor = new AnalogInput(SerializerConstants.SERIALIZER_SENSOR_2);
    launcherSensor = new AnalogInput(SerializerConstants.SERIALIZER_SENSOR_3);

    this.serializerMotor = new WPI_TalonSRX(SerializerConstants.SERIALIZER_MOTOR);
    serializerMotor.configFactoryDefault();

    runSerializer = false;
  }

  
  // Called every time the Command Scheduler runs (every 20 miliseconds)
  public void periodic() {
    if (lastSerializerVal && serializerSensor.getVoltage() > .85) {
      runSerializer = true;
    } 
    if (launcherSensor.getVoltage() < .85) {
      runSerializer = false;
    }
    serializerMotor.set(ControlMode.PercentOutput, runSerializer ? -SerializerConstants.SERIALIZER_SPEED : 0);
    lastSerializerVal = serializerSensor.getVoltage() < .85;
  }
  
  public void runBelt() {
    // turns serializer motor on
    serializerMotor.set(ControlMode.PercentOutput, -SerializerConstants.SERIALIZER_SPEED);
  }

  public void stopBelt(){
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  public void reverseBelt() {
    serializerMotor.set(ControlMode.PercentOutput, SerializerConstants.SERIALIZER_SPEED);
  }
  
  public void runSerializer(double speed){
    serializerMotor.set(ControlMode.PercentOutput, -speed * SerializerConstants.SERIALIZER_SPEED);
  }

}