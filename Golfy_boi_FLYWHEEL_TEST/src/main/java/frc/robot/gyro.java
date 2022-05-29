package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;

public class gyro {
    AHRS navX;
//=============================================================================================
//THIS IS ALL THEORETICAL DO NOT ACTUALLY USE UNTIL PROVEN THAT THIS IS CORRECT!!!!!!!!!!!
//=============================================================================================
    public gyro(){
        navX = new AHRS(SerialPort.Port.kMXP);
    }

    public void calibrateGyro(){
        navX.calibrate();
    }

    public double getAngle(){
        double angle;
        angle = navX.getAngle();
        return angle;
    }

    
    
}
