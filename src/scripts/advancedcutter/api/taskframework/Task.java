package scripts.advancedcutter.api.taskframework;


public abstract class Task {

	public abstract int priority();

	public abstract boolean validate();

	public abstract void execute();


}
