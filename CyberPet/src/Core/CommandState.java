package Core;

public class CommandState implements IState {
    private final Runnable _command;

    public CommandState(Runnable command) {
        _command = command;
    }

    @Override
    public void Tick() {
        _command.run();
    }

    @Override
    public void OnEnter() {}

    @Override
    public void OnExit() {}
}
