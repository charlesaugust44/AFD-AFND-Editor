package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import model.Machine;
import model.State;
import model.Transition;
import processing.core.PApplet;

public class Drawer extends Applet implements MouseListener, MouseMotionListener {

    private Machine machine;
    private final Font bold15;
    public State selected;
    public Transition selectedTransition;
    private Point diff;
    private Point mouseClicked, mouseMovingPos;
    private final Main parent;
    private boolean addingTransition = false;

    public Drawer(Machine machine, Main parent) {
        this.machine = machine;
        bold15 = new Font("sansserif", Font.BOLD, 15);
        this.parent = parent;
    }

    @Override
    public void init() {
        super.init();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
        repaint();
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(bold15);
        g.clearRect(0, 0, 2000, 2000);

        g.setColor(Color.red);
        if (mouseMovingPos != null)
            g.drawLine(selected.x + 25, selected.y + 25, mouseMovingPos.x, mouseMovingPos.y);

        // Draw Transitions
        for (State s : machine.states) {
            int sameTransitionCount = 0;
            for (Transition t : s.transitions) {
                State n = machine.getStateById(t.to);
                g.setColor((selectedTransition == t) ? Color.cyan : parent.transitionColor);

                if (n == s) {
                    g.drawArc(s.x + 8, s.y - 30, 32, 40, 0, 360);
                    g.setColor(Color.white);
                    t.x = s.x + 15;
                    t.y = s.y - 60 - sameTransitionCount * 25;
                    t.w = 20;
                    t.h = 20;
                    g.fillRect(t.x, t.y, 20, 20);
                    String display = t.read.toString();

                    g.setColor(Color.black);
                    if (t.read == 0)
                        display = "λ";
                    g.drawChars(display.toCharArray(), 0, 1, s.x + 20, s.y - 44 - sameTransitionCount * 25);
                    g.drawLine((int) s.x + 25, (int) s.y + 25, (int) n.x + 25, (int) n.y + 25);
                    sameTransitionCount++;
                } else {
                    int midx = (s.x + n.x + 25) / 2;
                    int midy = (s.y + n.y + 25) / 2;
                    Point p;
                    if (n.hasTransitionTo(s))
                        p = bezier(new Point(s.x + 25, s.y + 25), new Point(n.x + 25, n.y + 25), new Point(midx, midy), g);
                    else {
                        p = new Point(midx, midy);
                        float d = PApplet.dist(s.x, s.y, n.x, n.y);
                        float nx = ((n.x - s.x - 5) / d) * -1;
                        float ny = ((n.y - s.y - 8) / d) * -1;

                        float x = nx * 30, y = ny * 30;

                        g.fillArc((int) x - 5 + n.x + 25, (int) y - 5 + n.y + 25, 10, 10, 0, 360);

                        g.drawLine((int) s.x + 25, (int) s.y + 25, (int) n.x + 25, (int) n.y + 25);
                    }

                    g.setColor(Color.white);
                    t.x = p.x - 5;
                    t.y = p.y - 15  - sameTransitionCount * 25;
                    t.w = 20;
                    t.h = 20;
                    g.fillRect(t.x, t.y, 20, 20);
                    g.setColor(Color.black);
                    String display = (char) t.read + "";
                    if (t.read == 0)
                        display = "λ";
                    g.drawChars(display.toCharArray(), 0, 1, p.x, p.y - sameTransitionCount * 25);
                }
            }
        }

        // Draw States
        for (State s : machine.states) {
            g.setColor((selected == s) ? Color.cyan : Color.yellow);
            g.fillArc((int) s.x, (int) s.y, 50, 50, 0, 360);
            g.setColor(Color.black);
            g.drawArc((int) s.x, (int) s.y, 50, 50, 0, 360);
            g.setColor(Color.red);
            g.drawChars(s.name.toCharArray(), 0, s.name.length(), (int) s.x + 15, (int) s.y + 30);

            if (s.isInitial) {
                int[] xPoints = {s.x - 15, s.x - 15, s.x},
                        yPoints = {s.y + 40, s.y + 10, s.y + 25};
                g.setColor(Color.white);
                g.fillPolygon(xPoints, yPoints, 3);
                g.setColor(Color.black);
                g.drawPolygon(xPoints, yPoints, 3);
            }

            if (s.isFinal) {
                g.setColor(Color.black);
                g.drawArc((int) s.x + 4, (int) s.y + 4, 42, 42, 0, 360);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = e.getPoint();
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mouseClicked = e.getPoint();
                State next = intersectState(mouseClicked);
                selectedTransition = intersectTransition(mouseClicked);
                mouseMovingPos = null;

                if (next == null)
                    addingTransition = false;
                else if (addingTransition) {
                    String read = JOptionPane.showInputDialog(parent, "Type the transition symbol:");
                    char r;
                    if (read.isEmpty())
                        r = 0;
                    else
                        r = read.charAt(0);

                    Transition t = new Transition(selected, next.id, r);

                    selected.transitions.add(t);

                    selected = null;
                    addingTransition = false;
                }
                break;
            case MouseEvent.BUTTON3:
                addingTransition = false;
                selected = intersectState(e.getPoint());
                selectedTransition = intersectTransition(e.getPoint());
                if (selectedTransition != null)
                    parent.getTransitionMenu().show(e.getComponent(), e.getX(), e.getY());
                else if (selected != null) {
                    parent.getItemMenuIsFinal().setSelected(selected.isFinal);
                    parent.getItemMenuIsInitial().setSelected(selected.isInitial);
                    parent.getStateMenu().show(e.getComponent(), e.getX(), e.getY());
                } else
                    parent.getOutsideMenu().show(e.getComponent(), e.getX(), e.getY());
                break;
            default:
                addingTransition = false;
        }
        mouseMovingPos = null;
        repaint();
    }

    public void addState(String name) {
        machine.addState(name, mouseClicked);
        repaint();
    }

    public void startTransitionAdd() {
        addingTransition = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!addingTransition)
            selected = null;
        selectedTransition = null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mouse = e.getPoint();
        if (selected == null) {
            selected = intersectState(mouse);

            if (selected != null)
                diff = new Point(mouse.x - selected.x, mouse.y - selected.y);
        }

        if (selected != null) {
            selected.x = mouse.x - diff.x;
            selected.y = mouse.y - diff.y;
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (addingTransition) {
            mouseMovingPos = e.getPoint();
            repaint();
        } else
            mouseMovingPos = null;
    }

    private int getPt(int n1, int n2, float perc) {
        int diff = n2 - n1;

        return (int) (n1 + (diff * perc));
    }

    private Point bezier(Point s, Point e, Point mid, Graphics g) {
        float d = PApplet.dist(s.x, s.y, e.x, e.y);
        float nx = (s.x - e.x) / d;
        float ny = (s.y - e.y) / d;
        float tmp;

        tmp = nx;
        nx = ny;
        ny = tmp * -1;

        float x2 = mid.x + nx * 40.0f,
                y2 = mid.y + ny * 40.0f;

        Point p = new Point(s.x, s.y);
        Point c = null;

        for (float i = 0; i < 1; i += 0.01) {
            // The Green Line
            int xa = getPt(s.x, (int) x2, i);
            int ya = getPt(s.y, (int) y2, i);
            int xb = getPt((int) x2, e.x, i);
            int yb = getPt((int) y2, e.y, i);

            // The Black Dot
            int x = getPt(xa, xb, i);
            int y = getPt(ya, yb, i);

            g.drawLine(p.x, p.y, x, y);

            p = new Point(x, y);
            if (i >= (30 / (d / 100)) / 100 && c == null)
                c = p;
        }

        g.fillArc(c.x - 5, c.y - 5, 10, 10, 0, 360);
        return new Point((int) x2, (int) y2);
    }

    private State intersectState(Point p) {
        for (State s : machine.states)
            if (p.x > s.x && p.x < (s.x + 50) && p.y > s.y && p.y < s.y + 50)
                return s;

        return null;
    }

    private Transition intersectTransition(Point p) {
        for (State s : machine.states)
            for (Transition t : s.transitions)
                if (p.x > t.x && p.x < (t.x + t.w) && p.y > t.y && p.y < t.y + t.h)
                    return t;
        return null;
    }
}
