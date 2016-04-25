package student;


import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import game.EscapeState;
import game.ExplorationState;
import game.Node;
import game.NodeStatus;

public class Explorer {
	
	
    /** Explore the cavern, trying to find the 
     * orb in as few steps as possible. Once you find the 
     * orb, you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather 
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb, 
     * it will count as a failure.
     * 
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * 
     * At every step, you only know your current tile's ID and the ID of all 
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles). 
     * 
     * In order to get information about the current state, use functions
     * getCurrentLocation(), getNeighbors(), and getDistanceToTarget() in ExplorationState.
     * You know you are standing on the orb when getDistanceToTarget() is 0.
     * 
     * Use function moveTo(long id) in ExplorationState to move to a neighboring 
     * tile by its ID. Doing this will change state to reflect your new position.
     * 
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.
     * 
     * @param state the information available at the current state
     */
	
	
	
	Set<Long> visited = new HashSet<Long>();
	Set<Node> escape = new HashSet<Node>();
	List<Node> paths = new ArrayList<Node>();
	
    public void explore(ExplorationState state) {
        //TODO : Explore the cavern and find the orb

    	
    	visited.add(state.getCurrentLocation());    	
    	dfs(state);
    	
    }
    
    private boolean dfs(ExplorationState state){    
    	
    	boolean find = false;
    	long Location = state.getCurrentLocation();
    	
    	if(state.getDistanceToTarget()==0){
    		return true;
    	}
    	Heap<NodeStatus> heap = new Heap<NodeStatus>();
    	visited.add(state.getCurrentLocation());
    	
    	for(NodeStatus nStatus: state.getNeighbors()){
    		if(!visited.contains(nStatus.getId())){
    			heap.add(nStatus, nStatus.getDistanceToTarget());
    		}

		}
    	while(heap.size()!=0 && find == false){
    		NodeStatus nStatus = heap.poll();
    		if(!visited.contains(nStatus)){
    			visited.add(nStatus.getId());
    			state.moveTo(nStatus.getId());
    			find = dfs(state);	
    		}
			if(find == false){
				state.moveTo(Location);
			}
    	}
    	return find;
 	
    }

    /**  Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * 
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph. 
     * 
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * 
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * 
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold. But for this reason, using 
     * Dijkstra's to plot the shortest path to the exit is a good starting solution.
     * 
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
        //TODO: Escape from the cavern before time runs out
    	
    	pickGold(state);
    	
    	paths = Paths.dijkstra(state.getCurrentNode(), state.getExit());
    	
    	
    	if(paths.get(0).getTile().getGold()>0){
			state.pickUpGold();
		}
    	for(int i = 1; i < paths.size(); i++){
    		state.moveTo(paths.get(i));
    		if(paths.get(i).getTile().getGold()>0){
    			state.pickUpGold();
    		}
    	}

    }
       
    private void pickGold(EscapeState state){
    	int n = 100;
    	Paths paths = new Paths();
    	while(paths.pathLength(Paths.dijkstra(state.getCurrentNode(),state.getExit()))<state.getTimeRemaining()&& n>0){
    	n--;
    	Heap<Node> heap = new Heap<Node>();
    	Collection<Node> vertices = state.getVertices();
    	

        for(Node node : vertices){
            if(node.getTile().getGold()>0 && !state.getCurrentNode().equals(node)){
              List<Node> pathsList=Paths.dijkstra(state.getCurrentNode(), node);
            int a = paths.pathLength(Paths.dijkstra(state.getCurrentNode(), node));
            int goldnum = 0;
            for(Node nodelist : pathsList){
              goldnum += nodelist.getTile().getGold();
            }
            //System.out.println("length is "+ a + " gold is " + goldnum);
            heap.add(node, -goldnum/a);
            }
          }
    	
    	Node future = null;

    	
    	while(heap.size()>0){
    		future = heap.poll();
    		
    		int a = paths.pathLength(Paths.dijkstra(state.getCurrentNode(), future));
    		int b = paths.pathLength(Paths.dijkstra(future, state.getExit()));

		if(a+b < state.getTimeRemaining() && a<=200){
//    		if(a+b < state.getTimeRemaining()){	
    			break;
    		}
    		future = null; 
    	}

    	
    	if(future==null){
    		return;
    	}
    	List<Node> list = new ArrayList<Node>();
    	list = Paths.dijkstra(state.getCurrentNode(), future);
    	for(int i=1; i<list.size(); i++){
    		state.moveTo(list.get(i));
    		if(state.getCurrentNode().getTile().getGold()>0){
    			state.pickUpGold();
    		}
    	}
    	
    	}
    }     
}
