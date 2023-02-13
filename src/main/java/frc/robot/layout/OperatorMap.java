package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ElevatorArm;
import frc.robot.subsystems.MotorIntake;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import frc.robot.subsystems.ElevatorArm.ElevatorPosition;
import frc.robot.subsystems.ElevatorArm.PivotPosition;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class OperatorMap extends CommandMap {

  public OperatorMap(GameController controller) {
    super(controller);
  }
  
  public abstract JoystickButton getForwardIntakeButton();
  
  public abstract JoystickButton getReverseIntakeButton();

  public abstract double getLeftXAxis();

  public abstract double getLeftYAxis();

  public abstract double getRightXAxis();

  public abstract double getRightYAxis();

  @Override
  public void registerCommands() {
    ElevatorArm elevatorArm = ElevatorArm.getInstance();

    elevatorArm.setDefaultCommand(
        new RepeatCommand(new RunCommand(() -> elevatorArm.moveElevatorAndPivot(getLeftYAxis(), getRightYAxis()),
    elevatorArm)));

    MotorIntake motorIntake = MotorIntake.getInstance();
    getForwardIntakeButton().onTrue(motorIntake.moveIntake(1));
    getReverseIntakeButton().onTrue(motorIntake.moveIntake(-1));

  }
}
