package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.MathUtil;

public class driveTrain {
    private TalonSRX leftMaster;
    private TalonSRX leftSlave;
    private TalonSRX rightMaster;
    private TalonSRX rightSlave;

    double leftSpeed;
    double rightSpeed;


    public driveTrain(){
        leftSpeed = 0;
        rightSpeed = 0;
        leftMaster = new TalonSRX(3);
        rightMaster = new TalonSRX(4);
        leftSlave = new TalonSRX(5);
        rightSlave = new TalonSRX(6);

        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        leftMaster.setInverted(true);
        rightMaster.setInverted(false);

        leftSlave.setInverted(InvertType.FollowMaster);
        rightSlave.setInverted(InvertType.FollowMaster);

        leftMaster.setNeutralMode(NeutralMode.Brake);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave.setNeutralMode(NeutralMode.Brake);
    }

    public void update(){
        //arcadeDrive(Robot.controller.getRawAxis(1), Robot.controller.getRawAxis(4));
        tankDrive(Robot.controller.getRawAxis(1), Robot.controller.getRawAxis(5));
    }

    
    private void arcadeDrive(double speed, double turn){
        leftSpeed = (speed) - (turn * .5);
        rightSpeed = (speed) + (turn * .5);

        leftMaster.set(ControlMode.PercentOutput, leftSpeed);
        rightMaster.set(ControlMode.PercentOutput, rightSpeed);
    }

    private void tankDrive(double left, double right){
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);
    }

    public void setCoast(){
        leftMaster.setNeutralMode(NeutralMode.Coast);
        rightMaster.setNeutralMode(NeutralMode.Coast);
        leftSlave.setNeutralMode(NeutralMode.Coast);
        rightSlave.setNeutralMode(NeutralMode.Coast);
    }
    public void setSentry(){
        leftMaster.setNeutralMode(NeutralMode.Brake);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave.setNeutralMode(NeutralMode.Brake);
    }


}
