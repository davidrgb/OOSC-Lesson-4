package controller;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import model.shapes.Circle;
import model.shapes.IShapeDraw;
import model.shapes.Rectangle;
import model.shapes.Shape;
import model.shapes.Triangle;
import view.DrawingDemoPanel;
import view.MenuScreen;

public class ShapeDemoListener implements MouseListener, ActionListener {

    public static final int SIZE_SMALL = 40;
    public static final int SIZE_MID = 60;
    public static final int SIZE_LARGE = 80;
    private DrawingDemoPanel panel;

    public ShapeDemoListener(DrawingDemoPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Color color = getColor();
        int size = getSize();
        boolean filled = getFilled();
        IShapeDraw shape = getShape(e.getX(), e.getY(), color, size, filled);
        panel.getCanvas().getShapes().add(shape);
        panel.getCanvas().repaint();
    }

    private Color getColor() {
        if (panel.getWhiteColor().isSelected()) {
            return Color.white;
        } else if (panel.getRedColor().isSelected()) {
            return Color.red;
        } else if (panel.getYellowColor().isSelected()) {
            return Color.yellow;
        }
        return Color.white;
    }

    private int getSize() {
        if (panel.getSmallSize().isSelected()) {
            return SIZE_SMALL;
        } else if (panel.getMidSize().isSelected()) {
            return SIZE_MID;
        } else if (panel.getLargeSize().isSelected()) {
            return SIZE_LARGE;
        }
        return SIZE_SMALL;
    }

    private IShapeDraw getShape(int x, int y, Color color, int size, boolean filled) {
        if (panel.getCirButton().isSelected()) {
            return new Circle(x, y, size, color, filled);
        } else if (panel.getRecButton().isSelected()) {
            return new Rectangle(x, y, size, size, color, filled);
        } else if (panel.getTriButton().isSelected()) {
            return new Triangle(x, y, size, color, filled);
        }
        return new Circle(x, y, size, color, filled);
    }

    private boolean getFilled() {
        return panel.getFilledBox().isSelected();
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == panel.getClearButton()) {
            panel.getCanvas().getShapes().clear();
            panel.getCanvas().repaint();
        } else if (e.getSource() == panel.getExitButton()) {
            JFrame window = panel.getWindow();
            window.getContentPane().removeAll();
            var menu = new MenuScreen(window);
            menu.init();
            window.pack();
            window.revalidate();
        } else if (e.getSource() == panel.getShowOrderButton()) {
            panel.getCanvas().incrementSingleStepIndex();
            panel.getCanvas().repaint();
        } else if (e.getSource() == panel.getShowAllButton()) {
            panel.getCanvas().setSingleStepIndex(-1);
            panel.getCanvas().repaint();
        }
    }
    
}
