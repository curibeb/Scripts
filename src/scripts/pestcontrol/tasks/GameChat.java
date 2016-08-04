package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.NPCChat;

import scripts.pestcontrol.api.antiban.Antiban;
import scripts.pestcontrol.api.conditions.Conditions;
import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.Vars;

public class GameChat extends Task {

	@Override
	public int priority() {
		return Priorities.GAME_CHAT.getPriority();
	}

	@Override
	public boolean validate() {
		return Interface.INSIDE_GAME.text().contains("You must defend");
	}

	@Override
	public void execute() {
		Vars.status = "Click npc chat.";
		Antiban.getReactionTime();
		if (NPCChat.clickContinue(false)) {
			Timing.waitCondition(Conditions.get().interface_Open(Interface.INSIDE_GAME.getParent(),
					Interface.INSIDE_GAME.getChild()), General.random(4000, 7000));
			Antiban.sleepReactionTime();
		}
	}

}
