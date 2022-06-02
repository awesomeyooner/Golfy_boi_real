package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    
    private WPI_TalonSRX frontLeft, frontRight, rearLeft, rearRight;

    public Drive(){
        frontLeft = new WPI_TalonSRX(3);
        frontRight = new WPI_TalonSRX(6);
        rearLeft = new WPI_TalonSRX(5);
        rearRight = new WPI_TalonSRX(4);

        frontLeft.setNeutralMode(NeutralMode.Brake);
        rearLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        rearRight.setNeutralMode(NeutralMode.Brake);

        rearLeft.follow(frontLeft);
        rearRight.follow(frontRight);
    }

    public void tankDrive(double left, double right){
        frontLeft.set(left);
        frontRight.set(right);
    }
}
