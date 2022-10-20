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

		public static final int GEAR_SHIFT_DEPLOY = 1;
		public static final int GEAR_SHIFT_RETRACT = 0;

        // Sets the sensitivity of the joystick, higher constant = more gradual acceleration
        // TO REVERT TO LINEAR ACCELERATION SET TO 1
        public static final double ACCELERATION_CONSTANT = 2;
    }
    public static final class VisionConstants{
        public static final int ACTUATION_MOTOR = 7;
        public static final int MAX_ROTATION_VALUE = 36000;// at 90 degrees, unit is 150,000; at 
        public static final double MAX_SPEED = .6;
        public static final double LOGISTIC_GROWTH_RATE = .45;
        public static final double MIN_ADJUST_SPEED = .2;
        public static final double DEADBAND_RANGE = 0.05;
        public static final double LIMELIGHT_TO_HUB_HEIGHT = 65.5; //inches
        public static final double LIMELIGHT_ANGLE = 36;
        public static final double LIMELIGHT_HALF_X_FOV = 29.8;        
        public static final double MIN_DISTANCE = 43; //inches
        public static final double MAX_DISTANCE = 79; //inches
        public static final double ALIGNMENT_RANGE = 3;
        public static final double kP = .1;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    
}
