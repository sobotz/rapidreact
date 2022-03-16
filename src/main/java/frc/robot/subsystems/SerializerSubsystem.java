package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SerializerConstants;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;


public class SerializerSubsystem extends SubsystemBase {
  /**
   * Creates a new Serializer.
   */
  WPI_TalonSRX serializerMotor1;
  //WPI_TalonSRX serializerMotor2;
  // WPI_TalonFX serializerMotor; For use with Falcon 500

  // Initializes the sensors in the serializer and launcher
  public AnalogInput serializerSensor1;
  public AnalogInput serializerSensor2;
  public AnalogInput launcherSensor;

  // Initializes variables that wiil be used in the program
  public double ballCount = 2.0;
  public boolean acceptingBalls = true;
  public boolean previousLSValue = false; // previous launcher sensor value
  public boolean previousSSValue = false; // previous serializer sensor value
  public double previousBallCount;

  public boolean hasDeployedSerializer;

  public SerializerSubsystem() {
    
    // instantiates sensor values with respect to the contants method
    serializerSensor1 = new AnalogInput(SerializerConstants.SERIALIZER_SENSOR_1);
    serializerSensor2 = new AnalogInput(SerializerConstants.SERIALIZER_SENSOR_2);
    launcherSensor = new AnalogInput(SerializerConstants.SERIALIZER_SENSOR_3);
    SmartDashboard.putNumber("Ball Count: ", ballCount);

    
    this.serializerMotor1 = new WPI_TalonSRX(SerializerConstants.SERIALIZER_MOTOR);
    serializerMotor1.configFactoryDefault();

    hasDeployedSerializer = false;
    // serializerMotor = new WPI_TalonFX(Constants.SERIALIZER_MOTOR);
  }

  
  // Called every time the Command Scheduler runs (every 20 miliseconds)
  public void periodic() {
    // Recieves possible user input from the smart dashboard
    //ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
    // outputs current value to the smart dashboard
    //System.out.println("running periodic: " + serializerSensor1.getVoltage());
    // Puts sensor voltage values on the Smart dashboard
    //SmartDashboard.putNumber("Sensor 1: ", serializerSensor1.getVoltage()); // true
    //SmartDashboard.putNumber("Sensor 2: ", serializerSensor2.getVoltage()); // true
    
    serializerMotor1.set(ControlMode.PercentOutput, ((serializerSensor1.getVoltage() < .85 || serializerSensor2.getVoltage() < .85) && launcherSensor.getVoltage() > .85 ) ? -SerializerConstants.SERIALIZER_SPEED : 0);

    if (previousLSValue && launcherSensor.getVoltage() > .85){
      
    }
    previousLSValue = launcherSensor.getVoltage() < .85;
  }
  
  public void runBelt() {
    // accepting balls is set to false to stop incorrect ball placement in the
    // serializer
    acceptingBalls = false;
    // turns serializer motor on
    serializerMotor1.set(ControlMode.PercentOutput, -SerializerConstants.SERIALIZER_SPEED);
    // lets us know if the belts are running
    //SmartDashboard.putBoolean("Belts On: ", true);
    // changes the amount of time moved forward based on the ball count

    //Timer.delay(0.5); //check

    // turns serializer motor on

    //serializerMotor1.set(ControlMode.PercentOutput, 0);

    // outputs belt state to the smart dashboard
    //SmartDashboard.putBoolean("Belts On: ", false);
  }

  public void stopBelt(){
    serializerMotor1.set(ControlMode.PercentOutput, 0);
  }

  public void reverseBelt() {
    // runs belts until sensor at the start of the serializer is triggered
    //if (serializerSensor2.getVoltage() < .85) {
      // starts belts in inverse
    serializerMotor1.set(ControlMode.PercentOutput, SerializerConstants.SERIALIZER_SPEED);
      //SmartDashboard.putBoolean("Belts On: ", true);
    //} else {
     // stops belts
    //serializerMotor1.set(ControlMode.PercentOutput, 0);
    // outputs belt states to the smart dashboard
    //SmartDashboard.putBoolean("Belts On: ", false);
    // allows balls to be intaken again
    acceptingBalls = true; 
    //}
  }
  
  public void runSerializer(double speed){
    serializerMotor1.set(ControlMode.PercentOutput, -speed *SerializerConstants.SERIALIZER_SPEED);
  }

  public void toggleSerializer(){
    if (hasDeployedSerializer) {
      runSerializer(0);
      hasDeployedSerializer = false;
    } else {
      runSerializer(1);
      hasDeployedSerializer = true;
    }
  }

  public boolean checkBallLeft(){
    return launcherSensor.getVoltage() < .85;
  }

}