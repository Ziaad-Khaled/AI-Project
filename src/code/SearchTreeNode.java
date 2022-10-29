package code;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchTreeNode {

    private SearchTreeNode parent;//map every action to a new node
    private int depth;//increment by one for every action
    private int pathCost;//the number of passengers who sinked + the number of black boxes that expired till this state

    private State state;
//    String operator;
//    //children??
    public SearchTreeNode(SearchTreeNode parent, int depth, int pathCost, State state)
    {
        this.parent=parent;
        this.depth=depth;
        this.pathCost=pathCost;
        this.state=state;
    }

    public SearchTreeNode getParent() {
        return parent;
    }

    public void setParent(SearchTreeNode parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
