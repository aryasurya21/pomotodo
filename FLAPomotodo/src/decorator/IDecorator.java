package decorator;

public interface IDecorator {
	public void start();
	public void stop();
	public IDecorator returnSelf();
}
