# CoastGuard Search Algorithms AI


## Project Description

As a member of the coast guard force in charge of a rescue boat, The mission is to navigate a grid of the sea to rescue sinking ships, save passengers, and retrieve black boxes. The ultimate goal is to ensure there are no living passengers left to rescue, all black boxes are retrieved, and the rescue boat carries no passengers. The objective is to maximize the number of saved passengers and retrieved black boxes.

### Problem Overview

- The sea is represented as an MxN grid, where ships, stations, and the coast guard boat are located.
- Ships contain passengers, and one passenger per ship expires at each time step.
- A ship becomes a wreck once all passengers are expired, and its black box becomes retrievable but starts taking damage.
- Stations are fixed and serve as drop-off points for passengers.
- The coast guard boat has a fixed capacity for passengers and can perform actions like pickup, drop, retrieve, and move.

### Implemented Search Algorithms

1. Breadth-First Search
2. Depth-First Search
3. Iterative Deepening Search
4. Greedy Search (with two heuristics)
5. A* Search (with two admissible heuristics)

### Heuristics

#### First Heuristic Function

The first heuristic function aims to minimize the number of time steps required to reach a goal state. It considers two main situations:

**Situation 1:** 
- In this situation, we assume that the ship with the maximum number of passengers (referred to as "s") will be rescued, and its black box will be retrieved. 
- The heuristic calculates the time points needed to rescue passengers from "s," move them to a station, and retrieve the black box.
- This is equal to the Manhattan distance from the coast guard to ship "s," plus the Manhattan distance to the nearest station, plus 2 time points (one for pickup and one for black box retrieval).
- Considering ship "s" ensures that we rescue the ship with the most passengers, which controls our path cost to the goal.
- If we do not consider rescuing ship "s," no goal state will be reached before it becomes a wreck.

**Situation 2:** 
- In this situation, we assume that the ship with the maximum number of passengers will sink, and all passengers will expire. We may or may not retrieve the black box.
- The heuristic calculates the time points needed, which is the number of passengers in "s" (n) plus 1 (for black box retrieval).
- Alternatively, if we do not retrieve the black box, we consider the lower bound of 1 (one time step) as an admissible heuristic.

### Second Heuristic Function

The second heuristic function builds upon the first one but adds an additional factor:
- In this heuristic, we also consider the number of black boxes that can be retrieved before they become non-retrievable (within 20 time steps).
- This accounts for the possibility of retrieving multiple black boxes in the same time frame, optimizing the retrieval process.

These heuristics guide the search algorithms to explore paths that are likely to reach the goal state more efficiently, taking into account the rescue of passengers and the retrieval of black boxes.

These heuristic functions have been designed to be admissible, ensuring that their values are less than or equal to the actual cost to reach the goal state. This property guarantees that the search algorithms using these heuristics will find optimal solutions.

For a more in-depth understanding of how these heuristics are implemented and used within the project, please refer to the source code and implementation details.

### Main Functions

#### `GenGrid()`

- Randomly generates a grid with the coast guard, stations, ships, passengers, and other elements.
- Ensures no two objects occupy the same cell.

#### `Solve(grid, strategy, visualize)`

- Uses search algorithms to find a sequence of actions to achieve the rescue boat's goal.
- `grid` is a formatted string representing the grid and initial conditions.
- `strategy` specifies the search algorithm to use.
- `visualize` is a boolean indicating whether to visualize the grid during the search.

### Output

The `Solve` function returns a string in the format: `plan;deaths;retrieved;nodes`.

- `plan`: Sequence of actions leading to the goal state.
- `deaths`: Number of passengers who have died during the solution.
- `retrieved`: Number of black boxes successfully retrieved.
- `nodes`: Number of expanded nodes during the search.

## Performance Comparison

The project includes a comparison of the implemented search strategies in terms of completeness, optimality, RAM usage, CPU utilization, and the number of expanded nodes.


