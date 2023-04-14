package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.RobotMap;
import frc.robot.subsystems.Swerve;
import frc.robot.util.controllers.ButtonMap.Axis;
import frc.robot.util.controllers.GameController;

public class TwoJoyStickDriverMap extends DriverMap {

  public TwoJoyStickDriverMap(GameController controller) {
    super(controller);
  }

  @Override
  public ChassisSpeeds getChassisSpeeds() {
    var x = Math.signum(controller.getAxis(Axis.AXIS_LEFT_X)) * Math.pow(controller.getAxis(Axis.AXIS_LEFT_X), 2) * RobotMap.DriveMap.MAX_VELOCITY;
    var y = Math.signum(controller.getAxis(Axis.AXIS_LEFT_Y)) * Math.pow(controller.getAxis(Axis.AXIS_LEFT_Y), 2) * RobotMap.DriveMap.MAX_VELOCITY;
    var rot = controller.getAxis(Axis.AXIS_RIGHT_X) * RobotMap.DriveMap.MAX_ANGULAR_VELOCITY;

    var swerve = Swerve.getInstance();
    return ChassisSpeeds.fromFieldRelativeSpeeds(-y, -x, -rot, swerve.getYaw());
  }

  @Override
  public void registerCommands() {
    super.registerCommands();
  }
}
