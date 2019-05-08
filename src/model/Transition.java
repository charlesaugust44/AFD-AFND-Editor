package model;

import processing.data.XML;

public class Transition {

    public int to;
    public State from;
    public Character read;
    public int x, y, w, h;

    public Transition(XML el) {
        this.to = Integer.parseInt(el.getChildren("to")[0].getContent());

        XML read = el.getChildren("read")[0];
        char[] value = read.getContent().toCharArray();

        if (value.length == 0)
            this.read = 0;
        else
            this.read = value[0];
    }

    public Transition() {
    }

    public Transition(State from, int to, Character read) {
        this.to = to;
        this.read = read;
        this.from = from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public void remove() {
        from.transitions.remove(this);
    }

}
