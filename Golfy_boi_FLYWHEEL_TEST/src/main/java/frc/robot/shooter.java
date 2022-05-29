package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import edu.wpi.first.math.MathUtil;

public class shooter {
    TalonFX topFW;
    TalonFX botFW;
    TalonFXConfiguration topConfig;
    TalonFXConfiguration botConfig;

    double topSpeed;
    double botSpeed;

    double increment;

    public shooter(){
        topFW = new TalonFX(1);
        botFW = new TalonFX(2);
        
        topConfig = new TalonFXConfiguration();
        botConfig = new TalonFXConfiguration();

        //topConfig.slot0.kP = 0;
        //topConfig.slot0.kI = 0;
        //topConfig.slot0.kD = 0;
        topConfig.slot0.kF = .01;

        //botConfig.slot0.kP = 0;
        //botConfig.slot0.kI = 0;
        //botConfig.slot0.kD = 0;
        botConfig.slot0.kF = .01;
        
        topFW.configAllSettings(topConfig);
        botFW.configAllSettings(botConfig);
        topSpeed = 0;
        botSpeed = 0;

        topFW.setNeutralMode(NeutralMode.Coast);
        topFW.setNeutralMode(NeutralMode.Coast);

        topFW.configVoltageCompSaturation(12);
        botFW.configVoltageCompSaturation(12);
    
        topFW.enableVoltageCompensation(true);
        botFW.enableVoltageCompensation(true);

        topFW.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 70, 1));
        botFW.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 70, 1));

        topFW.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
        botFW.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

        topFW.setInverted(true);
        botFW.setInverted(true);

        MathUtil.clamp(topSpeed, -1, 1);
        MathUtil.clamp(botSpeed, -1, 1);
    }

    public void adjustSpeed(double topAdjust, double botAdjust){
        topSpeed += topAdjust;
        botSpeed += botAdjust;
    }

    public void setShooter(){
        topFW.set(ControlMode.PercentOutput, topSpeed);
        botFW.set(ControlMode.PercentOutput, botSpeed);
    }

    public void setShooterOFF(){
        topFW.set(ControlMode.PercentOutput, 0);
        botFW.set(ControlMode.PercentOutput, 0);
    }

    public double getTopAmp(){
        return topFW.getStatorCurrent();
    }

    public double getBotAmp(){
        return botFW.getStatorCurrent();
    }

    public double getTopRPM(){
        return (topFW.getSelectedSensorVelocity() * 600) / 2048;
    }

    public double getBotRPM(){
        return (botFW.getSelectedSensorVelocity() * 600) / 2048;
    }
}
