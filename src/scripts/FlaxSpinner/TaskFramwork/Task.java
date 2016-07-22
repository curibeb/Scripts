package scripts.FlaxSpinner.TaskFramwork;

public abstract class Task {

	public abstract int priority();

	public abstract boolean validate();

	public abstract void execute();

	public abstract String status();

}
