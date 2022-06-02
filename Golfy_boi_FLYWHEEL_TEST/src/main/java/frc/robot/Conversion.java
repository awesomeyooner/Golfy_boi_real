package frc.robot;

public class Conversion {


    public Conversion(){
        
    }

    public double RPMtoStepsPerDeciSec(double RPM){
        double stepsPerDeciSec = (RPM * 2048) / 600;
        return stepsPerDeciSec; 
    }
}
