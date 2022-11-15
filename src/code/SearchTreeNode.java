package code;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchTreeNode {

    private SearchTreeNode parent;//map every action to a new node

    public ArrayList<String> getActionsSequence() {
        return actionsSequence;
    }

    private ArrayList<String> actionsSequence;//sequence of actions from the root till this node
    private int pathCost;//the number of passengers who sinked + the number of black boxes that expired till this state

    private State state;
//    String operator;
//    //children??
    public SearchTreeNode(SearchTreeNode parent, ArrayList<String> actionsSequence, int pathCost, State state)
    {
        this.parent=parent;
        this.actionsSequence= (ArrayList<String>) actionsSequence.clone();
        this.pathCost=pathCost;
        this.state=state;
    }

    public SearchTreeNode getParent() {
        return parent;
    }

    public void setParent(SearchTreeNode parent) {
        this.parent = parent;
    }

    public void addAction(String action)
    {
        actionsSequence.add(action);
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
