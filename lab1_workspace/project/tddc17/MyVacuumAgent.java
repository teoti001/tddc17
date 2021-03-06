package tddc17;


import aima.core.environment.liuvacuum.*;
import aima.core.util.datastructure.Queue;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.State;
import aima.core.agent.impl.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.omg.CORBA.UNKNOWN;

import sun.net.www.content.audio.x_aiff;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.FollowingIterator;

class Vector {
	public Vector(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int x;
	public int y;
	@Override
	public int hashCode(){
		return this.x * 307 + this.y;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Vector))return false;
	    Vector otherVector = (Vector)other; 
		return this.x == otherVector.x && this.y == otherVector.y;
	}
}

class MyAgentState
{
	public int[][] world = new int[30][30];
	public int initialized = 0;
	final int UNKNOWN 	= 0;
	final int WALL 		= 1;
	final int CLEAR 	= 2;
	final int DIRT		= 3;
	final int HOME		= 4;
	final int ACTION_NONE 			= 0;
	final int ACTION_MOVE_FORWARD 	= 1;
	final int ACTION_TURN_RIGHT 	= 2;
	final int ACTION_TURN_LEFT 		= 3;
	final int ACTION_SUCK	 		= 4;
	
	public int agent_x_position = 1;
	public int agent_y_position = 1;
	public int agent_last_action = ACTION_NONE;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public int agent_direction = EAST;
	
	public ArrayList<Vector> pathToFollow = new ArrayList<Vector>();
	
	MyAgentState()
	{
		for (int i=0; i < world.length; i++)
			for (int j=0; j < world[i].length ; j++)
				world[i][j] = UNKNOWN;
		world[1][1] = HOME;
		agent_last_action = ACTION_NONE;
	}
	// Based on the last action and the received percept updates the x & y agent position
	public void updatePosition(DynamicPercept p)
	{
		Boolean bump = (Boolean)p.getAttribute("bump");

		if (agent_last_action==ACTION_MOVE_FORWARD && !bump)
	    {
			switch (agent_direction) {
			case MyAgentState.NORTH:
				agent_y_position--;
				break;
			case MyAgentState.EAST:
				agent_x_position++;
				break;
			case MyAgentState.SOUTH:
				agent_y_position++;
				break;
			case MyAgentState.WEST:
				agent_x_position--;
				break;
			}
	    }
	}
	
	public int atPos(Vector pos) {
		return world[pos.x][pos.y];
	}
	
	public ArrayList<Vector> findTile(java.util.function.Function<Integer, Boolean> finished){
		ArrayDeque<Vector> queue = new ArrayDeque();
		Vector initPos = new Vector(agent_x_position, agent_y_position);
		HashMap<Vector, Vector> pathToNode = new HashMap();
		HashSet<Vector> visited = new HashSet();
		
		queue.push(initPos);
		visited.add(initPos);
		
		while (!queue.isEmpty()) {
			Vector pos = queue.remove();
			int tileType = atPos(pos);
			if (tileType == WALL)
				continue;
			
			if (finished.apply(tileType)) {
				System.out.println("FOUND NEREST UNKOWN AT: " + pos.x + ":" + pos.y);
			    ArrayList<Vector> path = new ArrayList();
			    while (pos != initPos) {
			        path.add(pos);
			        pos = pathToNode.get(pos);
                }
				return path;
			}
			else {
			    Vector[] newPositions = new Vector[] {
                    new Vector(pos.x - 1, pos.y),
                    new Vector(pos.x + 1, pos.y),
                    new Vector(pos.x, pos.y - 1),
                    new Vector(pos.x, pos.y + 1)
                };

			    for (Vector newPos : newPositions) {
			    	if (!visited.contains(newPos)) { 
				        queue.add(newPos);
				        if (!pathToNode.containsKey(newPos)) {
				            pathToNode.put(newPos, pos);
	                    }
						visited.add(newPos);
			    	}
                }
			}
		}
		
		// Everything is explored
		return new ArrayList();
	}
	
	public Vector infront(){
		int frontx = agent_x_position;
		int fronty = agent_y_position;
		
		switch (agent_direction) {
		case MyAgentState.NORTH:
			fronty--;
			break;
		case MyAgentState.EAST:
			frontx++;
			break;
		case MyAgentState.SOUTH:
			fronty++;
			break;
		case MyAgentState.WEST:
			frontx--;
			break;
		}
		return new Vector(frontx, fronty);
	}
	
	public boolean unexploredInfront(){
		Vector ahead = infront();
		return UNKNOWN == world[ahead.x][ahead.y];
	}
	
	public boolean isInfront(Vector pos){
		Vector ahead = infront();	
		return pos.x == ahead.x && pos.y == ahead.y;
	}
	
	public boolean isLeft(Vector pos) {
		int leftx = agent_x_position;
		int lefty = agent_y_position;
		
		switch (agent_direction) {
		case MyAgentState.NORTH:
			leftx--;
			break;
		case MyAgentState.EAST:
			lefty--;
			break;
		case MyAgentState.SOUTH:
			leftx++;
			break;
		case MyAgentState.WEST:
			lefty++;
			break;
		}
		return leftx == pos.x && lefty == pos.y;
	}
	
	public void updateWorld(int x_position, int y_position, int info)
	{
		world[x_position][y_position] = info;
	}
	
	public void printWorldDebug()
	{
		for (int i=0; i < world.length; i++)
		{
			for (int j=0; j < world[i].length ; j++)
			{
				if (world[j][i]==UNKNOWN)
					System.out.print(" ? ");
				if (world[j][i]==WALL)
					System.out.print(" # ");
				if (world[j][i]==CLEAR)
					System.out.print(" . ");
				if (world[j][i]==DIRT)
					System.out.print(" D ");
				if (world[j][i]==HOME)
					System.out.print(" H ");
			}
			System.out.println("");
		}
	}
}

class MyAgentProgram implements AgentProgram {

	private int initnialRandomActions = 10;
	private Random random_generator = new Random();
	
	// Here you can define your variables!
	public int iterationCounter = 10;
	public MyAgentState state = new MyAgentState();
	
	// moves the Agent to a random start position
	// uses percepts to update the Agent position - only the position, other percepts are ignored
	// returns a random action
	private Action moveToRandomStartPosition(DynamicPercept percept) {
		int action = random_generator.nextInt(6);
		initnialRandomActions--;
		state.updatePosition(percept);
		if(action==0) {
		    state.agent_direction = ((state.agent_direction-1) % 4);
		    if (state.agent_direction<0) 
		    	state.agent_direction +=4;
		    state.agent_last_action = state.ACTION_TURN_LEFT;
			return LIUVacuumEnvironment.ACTION_TURN_LEFT;
		} else if (action==1) {
			state.agent_direction = ((state.agent_direction+1) % 4);
		    state.agent_last_action = state.ACTION_TURN_RIGHT;
		    return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
		} 
		state.agent_last_action=state.ACTION_MOVE_FORWARD;
		return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
	}
	
	public Action followPath(){
		int last = state.pathToFollow.size() - 1;
		Vector pos = state.pathToFollow.get(last);
		System.out.println(pos.x +" : "+ pos.y);
		if(state.isInfront(pos)){
    		state.agent_last_action=state.ACTION_MOVE_FORWARD;
    		state.pathToFollow.remove(last);
    		return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
		} else if (state.isLeft(pos)) {
			return turnLeft();
		} else {
			return turnRight();
		}
	}

	private Action turnLeft() {
		state.agent_direction = state.agent_direction - 1;
		if (state.agent_direction < 0) {
			state.agent_direction = 3;
		}
		state.agent_last_action=state.ACTION_TURN_LEFT;
		return LIUVacuumEnvironment.ACTION_TURN_LEFT;
	}

	private Action turnRight() {
		state.agent_direction = state.agent_direction + 1;
		if (state.agent_direction > 3) {
			state.agent_direction = 0;
		}
		state.agent_last_action=state.ACTION_TURN_RIGHT;
		return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
	}
	
	@Override
	public Action execute(Percept percept) {
		
		// DO NOT REMOVE this if condition!!!
    	if (initnialRandomActions>0) {
    		return moveToRandomStartPosition((DynamicPercept) percept);
    	} else if (initnialRandomActions==0) {
    		// process percept for the last step of the initial random actions
    		initnialRandomActions--;
    		state.updatePosition((DynamicPercept) percept);
			System.out.println("Processing percepts after the last execution of moveToRandomStartPosition()");
			state.agent_last_action=state.ACTION_SUCK;
	    	return LIUVacuumEnvironment.ACTION_SUCK;
    	}
		
    	// This example agent program will update the internal agent state while only moving forward.
    	// START HERE - code below should be modified!
    	    	
    	System.out.println("x=" + state.agent_x_position);
    	System.out.println("y=" + state.agent_y_position);
    	System.out.println("dir=" + state.agent_direction);
    	

	    DynamicPercept p = (DynamicPercept) percept;
	    Boolean bump = (Boolean)p.getAttribute("bump");
	    Boolean dirt = (Boolean)p.getAttribute("dirt");
	    Boolean home = (Boolean)p.getAttribute("home");
	    System.out.println("percept: " + p);
	    
	    // State update based on the percept value and the last action
	    state.updatePosition((DynamicPercept)percept);
	    if (bump) {
			switch (state.agent_direction) {
			case MyAgentState.NORTH:
				state.updateWorld(state.agent_x_position,state.agent_y_position-1,state.WALL);
				break;
			case MyAgentState.EAST:
				state.updateWorld(state.agent_x_position+1,state.agent_y_position,state.WALL);
				break;
			case MyAgentState.SOUTH:
				state.updateWorld(state.agent_x_position,state.agent_y_position+1,state.WALL);
				break;
			case MyAgentState.WEST:
				state.updateWorld(state.agent_x_position-1,state.agent_y_position,state.WALL);
				break;
			}
	    }
	    if (dirt)
	    	state.updateWorld(state.agent_x_position,state.agent_y_position,state.DIRT);
	    else if (!home)
	    	state.updateWorld(state.agent_x_position,state.agent_y_position,state.CLEAR);
	    
	    state.printWorldDebug();
	    
	    
	    // Next action selection based on the percept value
	    if (dirt)
	    {
	    	System.out.println("DIRT -> choosing SUCK action!");
	    	state.agent_last_action=state.ACTION_SUCK;
	    	return LIUVacuumEnvironment.ACTION_SUCK;
	    } 
	    else
	    {
	    	if (!state.pathToFollow.isEmpty()){
		    	System.out.println("ON A PATH -> following path!");
	    		return followPath();
	    	}
	    	else
	    	{
		    	System.out.println("NO PATH -> find closest unexplored");
	    		state.pathToFollow = state.findTile(tile -> tile == state.UNKNOWN);
	    		if (state.pathToFollow.isEmpty()){
	    			if(home)
	    				return NoOpAction.NO_OP;
	    			state.pathToFollow = state.findTile(tile -> tile == state.HOME);
	    		}
	    		for (Vector v : state.pathToFollow)
	    			System.out.println(v.x + " : " + v.y);
	    		System.out.println("NEW PATH -> follow path!");
	    		return followPath();
	    	}
	    }
	}
}



public class MyVacuumAgent extends AbstractAgent {
    public MyVacuumAgent() {
    	super(new MyAgentProgram());
	}
}
