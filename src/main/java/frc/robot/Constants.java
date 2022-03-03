// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
    public final class DriveConstants {

        public static final int RIGHT_FRONT_TALON = 4;
        public static final int LEFT_FRONT_TALON = 2;
        public static final int RIGHT_BACK_TALON = 3;
        public static final int LEFT_BACK_TALON = 1;
    
        public static final double ACCELERATION_CONSTANT = 2;
    }


    public static final class IntakeConstants{
        public static final int INTAKE_MOTOR = 8;
        public static final double MAXIMUM_INTAKE_SPEED = 0.7;
        public static final int INTAKE_SOLENOID_DEPLOY = 2;
        public static final int INTAKE_SOLENOID_RETRACT = 3;
    }


		public static final int GEAR_SHIFT_DEPLOY = 1;
		public static final int GEAR_SHIFT_RETRACT = 0;

        // Sets the sensitivity of the joystick, higher constant = more gradual acceleration
        // TO REVERT TO LINEAR ACCELERATION SET TO 1
    // public static final double ACCELERATION_CONSTANT = 2;
    public static final class SerializerConstants{
        public static final int SERIALIZER_SENSOR_1 = 0;
        public static final int SERIALIZER_SENSOR_2 = 1;
        public static final int SERIALIZER_SENSOR_3 = 2;
        public static final int SERIALIZER_MOTOR = 7;
        public static final int FEED_MOTOR = 10;
	    public static final double SERIALIZER_SPEED = 0.3;
    }
    public static final class LauncherConstants {
		public static final int LAUNCHER_MOTOR_1 = 5;
		public static final int LAUNCHER_MOTOR_2 = 6;
        public static final int ROLLER_MOTOR = 10;
    }
    
	
}
