package ConsoleUI;

import Core.IState;

public class AnimatorState implements IState {
    public final Runnable TickCommand;
    public final Runnable OnEnterCommand;
    public final Runnable OnExitCommand;

    public AnimatorState(Runnable tickCommand, Runnable onEnterCommand, Runnable onExitCommand) {
        TickCommand = tickCommand;
        OnEnterCommand = onEnterCommand;
        OnExitCommand = onExitCommand;
    }

    @Override
    public void Tick() {
        if (TickCommand != null) TickCommand.run();
    }

    @Override
    public void OnEnter() {
        if (OnEnterCommand != null) OnEnterCommand.run();
    }

    @Override
    public void OnExit() {
        if (OnExitCommand != null) OnExitCommand.run();
    }
}
