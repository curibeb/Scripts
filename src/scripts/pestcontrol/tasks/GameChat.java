package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.NPCChat;

import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.Conditions;
import scripts.pestcontrol.utilities.Vars;

public class GameChat extends Task {

	@Override
	public int priority() {
		return Priorities.GAMECHAT.getPriority();
	}

	@Override
	public boolean validate() {

		return Interface.INSIDEGAME.text().contains("You must defend");
	}

	@Override
	public void execute() {
		Vars.status = "Click npc chat.";
		if (NPCChat.clickContinue(false)) {
			Timing.waitCondition(Conditions.insideGameInterfaceOn(), General.random(4000, 7000));
		}
	}

}
