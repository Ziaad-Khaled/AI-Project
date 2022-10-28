package code;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchTreeNode {

    HashMap<String,SearchTreeNode> children;//map every action to a new node
    int depth;//increment by one for every action
    int pathCost;//the number of passengers who sinked + the number of black boxes that expired till this state

    State state;
//    String operator;
//    //children??
    public SearchTreeNode(HashMap<String,SearchTreeNode>children, int depth, int pathCost, State state)
    {
        this.children=children;
        this.depth=depth;
        this.pathCost=pathCost;
        this.state=state;
    }
}
