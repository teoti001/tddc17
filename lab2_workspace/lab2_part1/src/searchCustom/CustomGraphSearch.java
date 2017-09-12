package searchCustom;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

import searchShared.NodeQueue;
import searchShared.Problem;
import searchShared.SearchObject;
import searchShared.SearchNode;
import world.GridPos;

public class CustomGraphSearch implements SearchObject {

	private HashSet<SearchNode> explored;
	private NodeQueue frontier;
	protected ArrayList<SearchNode> path;
	private boolean insertFront;

	/**
	 * The constructor tells graph search whether it should insert nodes to front or back of the frontier 
	 */
    public CustomGraphSearch(boolean bInsertFront) {
		insertFront = bInsertFront;
    }

	/**
	 * Implements "graph search", which is the foundation of many search algorithms
	 */
	public ArrayList<SearchNode> search(Problem p) {
		GridPos startState = (GridPos) p.getInitialState();
		SearchNode startNode = new SearchNode(startState);
		
		frontier = new NodeQueue();
		if (insertFront) {
			frontier.addNodeToFront(startNode);
		} else {
			frontier.addNodeToBack(startNode);
		} 

		explored = new HashSet<SearchNode>();
		explored.add(startNode);
		
		path = new ArrayList<SearchNode>();
		
		while (!frontier.isEmpty()) {
			SearchNode pos = frontier.removeLast();
			
			if (p.isGoalState(pos.getState())) {
				path = pos.getPathFromRoot();
				return path;
			}
			else {
			    ArrayList<GridPos> newPositions = p.getReachableStatesFrom(pos.getState());

			    for (GridPos newPos : newPositions) {
		    		SearchNode node = new SearchNode(newPos, pos);
			    	if (!explored.contains(node)) {
			    		if (insertFront) {
			    			frontier.addNodeToFront(node);
			    		} else {
			    			frontier.addNodeToBack(node);
			    		}
						explored.add(node);
			    	}
                }
			}
		}
		
		/* Note: Returning an empty path signals that no path exists */
		return path;
	}

	/*
	 * Functions below are just getters used externally by the program 
	 */
	public ArrayList<SearchNode> getPath() {
		return path;
	}

	public ArrayList<SearchNode> getFrontierNodes() {
		return new ArrayList<SearchNode>(frontier.toList());
	}
	public ArrayList<SearchNode> getExploredNodes() {
		return new ArrayList<SearchNode>(explored);
	}
	public ArrayList<SearchNode> getAllExpandedNodes() {
		ArrayList<SearchNode> allNodes = new ArrayList<SearchNode>();
		allNodes.addAll(getFrontierNodes());
		allNodes.addAll(getExploredNodes());
		return allNodes;
	}

}
