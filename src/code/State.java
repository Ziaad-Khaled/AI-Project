package code;

import java.awt.geom.Point2D;

public class State extends SearchTreeNode{
    private Point2D[] ship;
    private int[] passengersNum;
    private Point2D coastGuard;

    public Point2D getCoastGuard() {
        return coastGuard;
    }
}
