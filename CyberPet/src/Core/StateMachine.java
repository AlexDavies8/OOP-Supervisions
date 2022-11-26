package Core;

import Core.IState;

import java.util.*;
import java.util.function.BooleanSupplier;

public class StateMachine {
    private IState _currentState;
    private final Dictionary<IState, List<Transition>> _transitions = new Hashtable<IState, List<Transition>>();
    private List<Transition> _currentTransitions = new ArrayList<Transition>();
    private final List<Transition> _anyTransitions = new ArrayList<Transition>();
    private final static List<Transition> _emptyTransitions = new ArrayList<Transition>(0);

    public void Tick() {
        var transition = GetTransition();
        transition.ifPresent(t -> SetState(t.To));

        if (_currentState != null) _currentState.Tick();
    }

    public void SetState(IState state) {
        if (state == _currentState) return;

        if (_currentState != null) _currentState.OnExit();
        _currentState = state;

        _currentTransitions = _transitions.get(_currentState);
        if (_currentTransitions == null) _currentTransitions = _emptyTransitions;

        _currentState.OnEnter();
    }

    public void AddTransition(IState from, IState to, BooleanSupplier predicate) {
        var transitions = _transitions.get(from);
        if (transitions == null) {
            transitions = new ArrayList<Transition>();
            _transitions.put(from, transitions);
        }

        transitions.add(new Transition(to, predicate));
    }

    public void AddAnyTransition(IState state, BooleanSupplier predicate) {
        _anyTransitions.add(new Transition(state, predicate));
    }

    private final class Transition {
        public final BooleanSupplier Condition;
        public final IState To;

        public Transition(IState to, BooleanSupplier condition) {
            Condition = condition;
            To = to;
        }
    }

    private Optional<Transition> GetTransition() {
        for (var transition : _anyTransitions) {
            if (transition.Condition.getAsBoolean()) return Optional.of(transition);
        }
        for (var transition : _currentTransitions) {
            if (transition.Condition.getAsBoolean()) return Optional.of(transition);
        }
        return Optional.empty();
    }
}
