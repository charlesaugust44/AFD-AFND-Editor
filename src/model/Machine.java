package model;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.data.XML;

public class Machine {

    public ArrayList<State> states;
    public File file;
    private State currentState;
    public String word;
    public int currentId;

    public Machine(File file) throws Exception {
        states = new ArrayList<>();
        this.file = file;
        currentId = 0;
        load();
    }

    public Machine() {
        states = new ArrayList<>();
    }

    public Machine(String[] alphabet, int states) {
        this.states = new ArrayList<>();

        for (int i = 0; i < states; i++)
            this.states.add(new State(i, "q" + (i + 1), 100 * i + 100, 200, false, false, this));
    }

    public void removeState(State toRemove) {

        for (State s : states) {
            ArrayList<Transition> removePile = new ArrayList<>();
            for (Transition t : s.transitions)
                if (t.to == toRemove.id)
                    removePile.add(t);
            for (Transition t : removePile)
                s.transitions.remove(t);
        }

        states.remove(toRemove);
    }

    public boolean simulateFiniteAutomaton(String word) {
        return simulateFiniteAutomaton(word, getInitialState());
    }

    private boolean simulateFiniteAutomaton(String word, State current) {
        // false: falhou | true: acabou palavra e esta no estado final
        boolean result = false;

        if (current.isInitial)
            result = simulateLambda(word, current) || result;

        if (word.length() == 0)
            if (current.isFinal)
                return true;
            else
                return result;

        char reading = word.charAt(0);

        for (Transition t : current.transitions)
            if (t.read == reading) {
                State next = getStateById(t.to);
                System.err.println(current.name + ">" + t.read + ">" + next.name);

                result = simulateLambda(word.substring(1, word.length()), next) || result;

                result = simulateFiniteAutomaton(word.substring(1, word.length()), next) || result;
            }

        return result;
    }

    public boolean simulateLambda(String word, State current) {
        boolean result = false;

        for (Transition lambda : current.getLambdaTransitions()) {
            State nextLambda = getStateById(lambda.to);
            
            result = simulateFiniteAutomaton(word, nextLambda) || result;
        }

        return result;
    }

    public void addState(String name, Point position) {
        State newState = new State(currentId++, name, position.x, position.y, false, false, this);
        states.add(newState);
    }

    public State getStateById(int id) {
        for (State s : states)
            if (s.id == id)
                return s;

        return null;
    }

    public State getInitialState() {
        for (State s : states)
            if (s.isInitial)
                return s;

        return null;
    }

    private void load() throws Exception {
        XML machine = new XML(file);
        XML[] XMLStates = machine.getChildren("automaton")[0].getChildren("state");
        XML[] XMLTransitions = machine.getChildren("automaton")[0].getChildren("transition");

        for (XML el : XMLStates)
            states.add(new State(el, this));

        for (XML el : XMLTransitions) {
            int id = Integer.parseInt(el.getChildren("from")[0].getContent());
            State from = getStateById(id);
            Transition t = new Transition(el);
            from.addTransition(t);
        }

        for (State s : states)
            if (s.isInitial) {
                this.currentState = s;
                break;
            }
    }

    public void save() {
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        String ts = "";
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
                + "<structure>\n"
                + "\t<type>fa</type>\n"
                + "\t<automaton>\n";
        for (State s : states) {

            result = result
                    + "\t\t<state id=\"" + s.id + "\" name=\"" + s.name + "\">\n"
                    + "\t\t\t<x>" + s.x + "</x>\n"
                    + "\t\t\t<y>" + s.y + "</y>\n"
                    + ((s.isInitial) ? "\t\t\t<initial/>\n" : "")
                    + ((s.isFinal) ? "\t\t\t<final/>\n" : "")
                    + "\t\t</state>\n";
            for (int k = 0; k < s.transitions.size(); k++) {
                Transition t = s.transitions.get(k);
                ts = ts
                        + "\t\t<transition>\n"
                        + "\t\t\t<from>" + s.id + "</from>\n"
                        + "\t\t\t<to>" + t.to + "</to>\n";
                ts += ((t.read == 0) ? ("\t\t\t<read/>") : ("\t\t\t<read>" + t.read + "</read>\n"));
                ts += "\t\t</transition>\n";
            }
        }

        result = result + ts + "\t</automaton>\n</structure>";
        PApplet p = new PApplet();
        PrintWriter fout = p.createWriter(file.getAbsolutePath());
        fout.println(result);
        fout.flush();
        fout.close();
    }

    @Override
    public String toString() {
        String result = "";
        for (State s : states) {
            result = result + s.name + "| ";
            for (Transition t : s.transitions)
                if (t.to != -1)
                    result = result + getStateById(t.to).name + " ";
            result = result + "\n";
        }
        return result;
    }
}
