package scripts.pestcontrol.taskframework;


public abstract class Task {

	public abstract int priority();

	public abstract boolean validate();

	public abstract void execute();


}
