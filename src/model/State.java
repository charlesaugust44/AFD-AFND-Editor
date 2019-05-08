package model;

import java.util.ArrayList;
import processing.data.XML;

public class State {

    public int id;
    public int x, y;
    public String name;
    public boolean isInitial;
    public boolean isFinal;
    public ArrayList<Transition> transitions;
    private int tsize;
    private Machine machine;

    public State(XML el, Machine machine) {
        tsize = 0;
        this.id = el.getInt("id");
        this.name = el.getString("name");

        if (id >= machine.currentId)
            machine.currentId = id + 1;

        this.x = (int) Float.parseFloat(el.getChildren("x")[0].getContent());
        this.y = (int) Float.parseFloat(el.getChildren("y")[0].getContent());

        this.isInitial = el.getChildren("initial").length > 0;
        this.isFinal = el.getChildren("final").length > 0;

        transitions = new ArrayList<>();
        this.machine = machine;
    }

    public State(int id, String name, int x, int y, boolean isInitial, boolean isFinal, Machine machine) {
        tsize = 0;
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
        transitions = new ArrayList<>();
        this.machine = machine;
    }

    public ArrayList<Transition> getLambdaTransitions() {
        ArrayList<Transition> result = new ArrayList<>();

        for (Transition t : transitions)
            if (t.read == 0)
                result.add(t);

        return result;
    }

    public State getStateTransition(char reads) {
        for (Transition t : transitions)
            if (t.read == reads)
                return machine.getStateById(t.to);
        return null;
    }

    public Transition getTransition(char reads) {
        for (Transition t : transitions)
            if (t.read == reads)
                return t;
        return null;
    }

    public boolean hasTransitionTo(State s) {
        for (Transition t : transitions)
            if (t.to == s.id)
                return true;
        return false;
    }

    public int getTransitonsSize() {
        return tsize;
    }

    public void addTransition(Transition t) {
        transitions.add(t);
        t.setFrom(this);
        tsize++;
    }
}
