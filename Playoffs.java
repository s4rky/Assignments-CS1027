import java.util.Iterator;

public class Playoffs {
	private LinkedBinaryTree<String> tree;
	private HockeySeries[] standings;
	
	public Playoffs() {
		String[] array = new String[31];
		
		for(int i = 0; i<15; i++) {
			array[i] = "TBD " + i;
		}
		
		MyFileReader teams = new MyFileReader("teams.txt");
		
		for(int i = 15; i<31; i++) {
			array[i] = teams.readString();
		}
		
		teams = new MyFileReader("teams.txt");
		
		standings = new HockeySeries[15];
		
		for(int i = 0; i<=8; i++) {		
			standings[i] = new HockeySeries(teams.readString(), teams.readString(), 0, 0);
		}
		
		TreeBuilder playoffs = new TreeBuilder(array);
		
		tree = playoffs.getTree();
	}
	
	public LinkedBinaryTree<String> getTree(){
		return tree;
	}
	
	public HockeySeries[] getStandings() {
		return standings;
	}
	
	public String updateStandings(String teamOne, String teamTwo, int teamOneWins, int teamTwoWins) {
		for(int i = 0; i<standings.length; i++) {
			if(standings[i] == null) {
				continue;
			}
			
			if(standings[i] == null || standings[i].getTeamA() == null || standings[i].getTeamB() == null) {
				continue;
			}
			
			String teamA = standings[i].getTeamA();
			String teamB = standings[i].getTeamB();
			
			int teamAWins = 0;
			int teamBWins = 0;
			
			if(teamA.equals(teamOne)) {
				if(teamB.equals(teamTwo)) {
					
					if(teamOneWins>teamTwoWins) {
						teamAWins++;
					}else if(teamTwoWins>teamOneWins) {
						teamBWins++;
					}
					
					standings[i].incrementWins(teamAWins, teamBWins);
					
					if(standings[i].getTeamAWins() == 4) {
						return teamOne;
					}
					if(standings[i].getTeamBWins() == 4) {
						return teamTwo;
					}
				}
			}
		}
		return null;
	}
	
	public void updateRound(int roundNum) {
		MyFileReader round = null;
		if(roundNum == 1) {
			round = new MyFileReader("scores1.txt");
		}else if(roundNum == 2) {
			round = new MyFileReader("scores2.txt");
		}else if(roundNum == 3) {
			round = new MyFileReader("scores3.txt");
		}else if(roundNum == 4) {
			round = new MyFileReader("scores4.txt");
		}
		
		String[] line = round.readString().split(",");
		String winner = updateStandings(line[0],line[1],Integer.valueOf(line[2]),Integer.valueOf(line[3]));
		
		while(!round.endOfFile()) {
			line = round.readString().split(",");
			winner = updateStandings(line[0],line[1],Integer.valueOf(line[2]),Integer.valueOf(line[3]));
			
			if(winner != null) {
				if(winner == line[0] || winner == line[1]) {
					BinaryTreeNode parent = findParent(line[0],line[1]);
					parent.setData(winner);
				}
			}
		}
//		System.out.println(tree);
	}
	
	public BinaryTreeNode<String> findParent(String parentOne, String parentTwo) {
		if(tree.getRoot() == null) return null;
		
		LinkedQueue<BinaryTreeNode> Q = new LinkedQueue<BinaryTreeNode>();
		Q.enqueue(tree.getRoot());
		
		while(!Q.isEmpty()) {
			BinaryTreeNode v = Q.dequeue();
			
			if(v.getLeft() != null) Q.enqueue(v.getLeft());
			if(v.getRight() != null) Q.enqueue(v.getRight());
			
			if(v.getLeft() == null || v.getRight() == null) {
				continue;
			}
			
			String left = (String) v.getLeft().getData();
			String right = (String) v.getRight().getData();
						
			if(left.contains(parentOne) || left.contains(parentTwo)) {
				if(right.contains(parentOne) || right.contains(parentTwo)) {
					return v;
				}
			}
		}
		
		return null;
	}
	/**
	 * This method adds the new series to the standings array before a new round begins. It does this using an iterator
	 * from the tree and skipping over the nodes that are still unknown until it gets to the nodes from the new round.
	 * It then takes two teams at a time from the iterator and creates a new series in the standings array for those
	 * two teams. The series standings (number of wins for each team) are set to 0 by default. 
	 */
	public void addNewStandings (int numSkips, int sIndex, int eIndex) {
		Iterator<String> iter = tree.iteratorLevelOrder();
		int i;
		String team1, team2;
		for (i = 0; i < numSkips; i++) {
			iter.next();
		}
		for (i = sIndex; i <= eIndex; i++) {
			team1 = iter.next();
			team2 = iter.next();
			standings[i] = new HockeySeries(team1, team2, 0, 0);
		}
	}
	
	/**
	 * This method simply prints out the standings table in a cleanly formatted table structure.
	 */
	public void printStandings () {
		String str;
		for (int k = 0; k < standings.length; k++) {
			if (standings[k] != null) {
				str = String.format("%-15s\t%-15s\t%3d-%d", standings[k].getTeamA(), standings[k].getTeamB(), standings[k].getTeamAWins(), standings[k].getTeamBWins());
				System.out.println(str);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Playoffs pl = new Playoffs();
		pl.updateRound(1);

		// Uncomment each pair of lines when you are ready to run the subsequent rounds. 
		
		//pl.addNewStandings(7, 8, 11); // Ensure you execute this line before calling updateRound(2).
		//pl.updateRound(2);

		
		//pl.addNewStandings(3, 12, 13); // Ensure you execute this line before calling updateRound(3).
		//pl.updateRound(3);

		
		//pl.addNewStandings(1, 14, 14); // Ensure you execute this line before calling updateRound(4).
		//pl.updateRound(4);
	}

}
