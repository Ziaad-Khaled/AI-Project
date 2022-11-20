package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import code.CoastGuard;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CoastGuardPublicChanged {
	
	String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
	String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
	String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
	String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
	String grid4 = "5,7;63;4,2;6,2,6,3;0,0,17,0,2,73,3,0,30;";
	String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
	String grid6 = "7,5;86;0,0;1,3,1,5,4,2;1,1,42,2,5,99,3,5,89;";
	String grid7= "6,7;82;1,4;2,3;1,1,58,3,0,58,4,2,72;";
	String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
	String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,1,6,55,3,2,94,4,3,46;";
	String grid10= "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";


	@Test(timeout = 10000)
	public void testa0() throws Exception {
		String solution = CoastGuard.solve(grid0, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testa1() throws Exception {
		String solution = CoastGuard.solve(grid1, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testa2() throws Exception {
		String solution = CoastGuard.solve(grid2, "BF", true);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 10000)
	public void testa3() throws Exception {
		String solution = CoastGuard.solve(grid3, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 60000)
	public void testa4() throws Exception {
		String solution = CoastGuard.solve(grid4, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testa5() throws Exception {
		String solution = CoastGuard.solve(grid5, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 60000)
	public void testa6() throws Exception {
		String solution = CoastGuard.solve(grid6, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 60000)
	public void testa7() throws Exception {
		String solution = CoastGuard.solve(grid7, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	
	@Test(timeout = 60000)
	public void testa8() throws Exception {
		String solution = CoastGuard.solve(grid8, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 100000)
	public void testa9() throws Exception {
		String solution = CoastGuard.solve(grid9, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	

	
	@Test(timeout = 10000)
	public void testb0() throws Exception {
		String solution = CoastGuard.solve(grid0, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testb1() throws Exception {
		String solution = CoastGuard.solve(grid1, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testb2() throws Exception {
		String solution = CoastGuard.solve(grid2, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 10000)
	public void testb3() throws Exception {
		String solution = CoastGuard.solve(grid3, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void testb4() throws Exception {
		String solution = CoastGuard.solve(grid4, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testb5() throws Exception {
		String solution = CoastGuard.solve(grid5, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void testb6() throws Exception {
		String solution = CoastGuard.solve(grid6, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 10000)
	public void testb7() throws Exception {
		String solution = CoastGuard.solve(grid7, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	@Test(timeout = 10000)
	public void testb8() throws Exception {
		String solution = CoastGuard.solve(grid8, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	@Test(timeout = 60000)
	public void testb9() throws Exception {
		String solution = CoastGuard.solve(grid9, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	
	@Test(timeout = 60000)
	public void testb10() throws Exception {
		String solution = CoastGuard.solve(grid10, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	
	@Test(timeout = 10000)
	public void testc0() throws Exception {
		String solution = CoastGuard.solve(grid0, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testc1() throws Exception {
		String solution = CoastGuard.solve(grid1, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testc2() throws Exception {
		String solution = CoastGuard.solve(grid2, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 10000)
	public void testc3() throws Exception {
		String solution = CoastGuard.solve(grid3, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void testc4() throws Exception {
		String solution = CoastGuard.solve(grid4, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testc5() throws Exception {
		String solution = CoastGuard.solve(grid5, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void testc6() throws Exception {
		String solution = CoastGuard.solve(grid6, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 60000)
	public void testc7() throws Exception {
		String solution = CoastGuard.solve(grid7, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	
	@Test(timeout = 60000)
	public void testc8() throws Exception {
		String solution = CoastGuard.solve(grid8, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	@Test(timeout = 60000)
	public void testc9() throws Exception {
		String solution = CoastGuard.solve(grid9, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	
	@Test(timeout = 60000)
	public void testc10() throws Exception {
		String solution = CoastGuard.solve(grid10, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	@Test(timeout = 10000)
	public void testd0() throws Exception {
		String solution = CoastGuard.solve(grid0, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testd1() throws Exception {
		String solution = CoastGuard.solve(grid1, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testd2() throws Exception {
		String solution = CoastGuard.solve(grid2, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 40000)
	public void testd3() throws Exception {
		String solution = CoastGuard.solve(grid3, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 60000)
	public void testd4() throws Exception {
		String solution = CoastGuard.solve(grid4, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testd5() throws Exception {
		String solution = CoastGuard.solve(grid5, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void testd6() throws Exception {
		String solution = CoastGuard.solve(grid6, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 10000)
	public void testd7() throws Exception {
		String solution = CoastGuard.solve(grid7, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	@Test(timeout = 10000)
	public void testd8() throws Exception {
		String solution = CoastGuard.solve(grid8, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	@Test(timeout = 100000)
	public void testd9() throws Exception {
		String solution = CoastGuard.solve(grid9, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	

	

	@Test(timeout = 10000)
	public void teste0() throws Exception {
		String solution = CoastGuard.solve(grid0, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void teste1() throws Exception {
		String solution = CoastGuard.solve(grid1, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void teste2() throws Exception {
		String solution = CoastGuard.solve(grid2, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 30000)
	public void teste3() throws Exception {
		String solution = CoastGuard.solve(grid3, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void teste4() throws Exception {
		String solution = CoastGuard.solve(grid4, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void teste5() throws Exception {
		String solution = CoastGuard.solve(grid5, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void teste6() throws Exception {
		String solution = CoastGuard.solve(grid6, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 10000)
	public void teste7() throws Exception {
		String solution = CoastGuard.solve(grid7, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	@Test(timeout = 10000)
	public void teste8() throws Exception {
		String solution = CoastGuard.solve(grid8, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	@Test(timeout = 60000)
	public void teste9() throws Exception {
		String solution = CoastGuard.solve(grid9, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	
	@Test(timeout = 60000)
	public void teste10() throws Exception {
		String solution = CoastGuard.solve(grid10, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	
	@Test(timeout = 10000)
	public void testf0() throws Exception {
		String solution = CoastGuard.solve(grid0, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testf1() throws Exception {
		String solution = CoastGuard.solve(grid1, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testf2() throws Exception {
		String solution = CoastGuard.solve(grid2, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}


	@Test(timeout = 100000)
	public void testf4() throws Exception {
		String solution = CoastGuard.solve(grid4, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testf5() throws Exception {
		String solution = CoastGuard.solve(grid5, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void testf6() throws Exception {
		String solution = CoastGuard.solve(grid6, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 60000)
	public void testf7() throws Exception {
		String solution = CoastGuard.solve(grid7, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	
	@Test(timeout = 10000)
	public void testf8() throws Exception {
		String solution = CoastGuard.solve(grid8, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	

	
	
	@Test(timeout = 10000)
	public void testg0() throws Exception {
		String solution = CoastGuard.solve(grid0, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testg1() throws Exception {
		String solution = CoastGuard.solve(grid1, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testg2() throws Exception {
		String solution = CoastGuard.solve(grid2, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 10000)
	public void testg3() throws Exception {
		String solution = CoastGuard.solve(grid3, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void testg4() throws Exception {
		String solution = CoastGuard.solve(grid4, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testg5() throws Exception {
		String solution = CoastGuard.solve(grid5, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void testg6() throws Exception {
		String solution = CoastGuard.solve(grid6, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 10000)
	public void testg7() throws Exception {
		String solution = CoastGuard.solve(grid7, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	@Test(timeout = 10000)
	public void testg8() throws Exception {
		String solution = CoastGuard.solve(grid8, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	@Test(timeout = 60000)
	public void testg9() throws Exception {
		String solution = CoastGuard.solve(grid9, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	


	@Test(timeout = 10000)
	public void testh0() throws Exception {
		String solution = CoastGuard.solve(grid0, "AS2", false);
		solution = solution.replace(" ", "");
		System.out.println(solution);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testh1() throws Exception {
		String solution = CoastGuard.solve(grid1, "AS2", false);
		solution = solution.replace(" ", "");
		System.out.println(solution);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testh2() throws Exception {
		String solution = CoastGuard.solve(grid2, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	@Test(timeout = 10000)
	public void testh3() throws Exception {
		String solution = CoastGuard.solve(grid3, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 60000)
	public void testh4() throws Exception {
		String solution = CoastGuard.solve(grid4, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}
	@Test(timeout = 10000)
	public void testh5() throws Exception {
		String solution = CoastGuard.solve(grid5, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	@Test(timeout = 10000)
	public void testh6() throws Exception {
		String solution = CoastGuard.solve(grid6, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	@Test(timeout = 10000)
	public void testh7() throws Exception {
		String solution = CoastGuard.solve(grid7, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}	@Test(timeout = 10000)
	public void testh8() throws Exception {
		String solution = CoastGuard.solve(grid8, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	@Test(timeout = 60000)
	public void testh9() throws Exception {
		String solution = CoastGuard.solve(grid9, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}	

	
static class Checker{
		
		byte height;
		byte width;
		HashMap<String, Byte> coordinate_numberPassengers = new HashMap<String, Byte>();
		ArrayList<String> stationsCoordinates_array = new ArrayList<String>();
		byte totalRescuedPassengers;
		byte retrievedBlackBoxes;
		int deaths;
		byte cg_x;
		byte cg_y;
		byte maxPassengersInCG;
		byte passengersInCG;



		public Checker(byte height, byte width, byte maxPassengersInCG, byte cg_x, byte cg_y,  ArrayList<String> stationsCoordinates_array, HashMap<String, Byte> coordinate_numberPassengers) {
			this.height=height;
			this.width=width;
			this.maxPassengersInCG=maxPassengersInCG;
			this.cg_x=cg_x;
			this.cg_y=cg_y;
			this.stationsCoordinates_array=stationsCoordinates_array;
			this.coordinate_numberPassengers=coordinate_numberPassengers;
			
		}
		
		
		boolean move(int z, int k) {
			if (!canMove(cg_x+z,cg_y+k)) {
				performTimeStep();
				return false;
			}
		
			this.cg_x+=z;
			this.cg_y+=k;
			performTimeStep();
			return true;
		}
		boolean pickup() {

			if(!coordinate_numberPassengers.containsKey(cg_x+","+cg_y)) {
				performTimeStep();//Why perform a time step
				return false;
			}
			byte passengersInShip = coordinate_numberPassengers.get(cg_x+","+cg_y);
			byte cgCapacity = (byte) (maxPassengersInCG- passengersInCG);
			if(cgCapacity >= passengersInShip) {
				passengersInCG += passengersInShip;
				coordinate_numberPassengers.replace(cg_x+","+cg_y, (byte)-100);//WHY -100
			}
			else {
				passengersInCG =maxPassengersInCG;
				int passengersRemainingInShip = passengersInShip - cgCapacity;
				coordinate_numberPassengers.replace(cg_x+","+cg_y,(byte) passengersRemainingInShip);
			}
			performTimeStep();
			return true;
		}
		boolean drop() {

			if(!stationsCoordinates_array.contains(cg_x+","+cg_y)) {
				performTimeStep();
				return false;
			}
			totalRescuedPassengers += passengersInCG;
			passengersInCG = 0;
			performTimeStep();
			return true;
		}
		boolean retrieve() {

			if(!coordinate_numberPassengers.containsKey(cg_x+","+cg_y)) {
				performTimeStep();
				return false;
			}
			retrievedBlackBoxes +=1;
			coordinate_numberPassengers.replace(cg_x+","+cg_y,(byte)0);
			performTimeStep();
			return true;

		}
		boolean canMove(int i, int j) {

			return i >= this.width || i < 0 || j >= this.height || j < 0 ? false : true;
			
		}
		void performTimeStep() {
			ArrayList<String> toclean = new ArrayList<String>();
			for (String coordinate : coordinate_numberPassengers.keySet()) {
				byte numberPassengers = coordinate_numberPassengers.get(coordinate);
				if (numberPassengers <=(byte)-1 && numberPassengers >=(byte)-20) numberPassengers++;//Why -20 not -100????
				else {
					if (numberPassengers ==1) {
						numberPassengers =(byte)-20;
						deaths++;}
					else {
						if(numberPassengers >(byte)1) { numberPassengers--; deaths++;}}
				}

				//if number of passengers becomes zero, remove from list
				if(numberPassengers ==0) {
					toclean.add(coordinate);
				}else {
					coordinate_numberPassengers.replace(coordinate, numberPassengers);}
				
			}
			//remove from list
			for (String c : toclean) {
				coordinate_numberPassengers.remove(c);
			}
			
		}

		void clean() {
			for (String k : coordinate_numberPassengers.keySet()) {
				if (coordinate_numberPassengers.get(k).equals((byte)0)) coordinate_numberPassengers.remove(k);
			}
		}

		public boolean IsGoal() {
			return coordinate_numberPassengers.size()== 0 && passengersInCG == 0 ;
		}
		
		
	}
	public static boolean applyPlan(String grid, String solution){
		boolean couldPerformAction = true;
		String[] solutionArray  = solution.split(";");
		String plan = solutionArray[0];
		int deaths = Integer.parseInt(solutionArray[1]);
		int retrievedBlackBoxes = Integer.parseInt(solutionArray[2]);
		
		plan.replace(" ", "");
		plan.replace("\n", "");
		plan.replace("\r", "");
		plan.replace("\n\r", "");
		plan.replace("\t", "");

		String[] actions = plan.split(",");
		
		String[] gridArray=  grid.split(";");
		String[] dimensions = gridArray[0].split(",");
		byte height = Byte.parseByte(dimensions[0]);
		byte width = Byte.parseByte(dimensions[1]);
		
		byte maxPassengers = Byte.parseByte(gridArray[1]);
		
		String[] cgCoordinates_Array = gridArray[2].split(",");
		byte cg_x = Byte.parseByte(cgCoordinates_Array[0]);
		byte cg_y = Byte.parseByte(cgCoordinates_Array[1]);

		String[] stationsCoordinates = gridArray[3].split(",");
		ArrayList<String> stationsCoordinates_array = new ArrayList<String>();
		for(int i = 0;i< stationsCoordinates.length -1; i+=2) {
			stationsCoordinates_array.add(stationsCoordinates[i]+","+stationsCoordinates[i+1]);
		}
//		
		String[] shipsCoordinates = gridArray[4].split(",");
		HashMap<String, Byte> coordinate_numberPassengers = new HashMap<String, Byte>();
		for(int i = 0;i< shipsCoordinates.length -1; i+=3) {
			coordinate_numberPassengers.put(shipsCoordinates[i]+","+shipsCoordinates[i+1],Byte.parseByte(shipsCoordinates[i+2]));
		}
		Checker checker = new	Checker(height, width, maxPassengers, cg_x, cg_y, stationsCoordinates_array, coordinate_numberPassengers);
		for (int i = 0; i < actions.length; i++) {

			switch (actions[i]) {
			case "up":
				couldPerformAction = checker.move(-1,0);
				break;
			case "down":
				couldPerformAction = checker.move(1,0);
				break;
			case "right":
				couldPerformAction = checker.move(0,1);
				break;
			case "left":
				couldPerformAction = checker.move(0,-1);
				break;
			case "pickup":
				couldPerformAction = checker.pickup();
				break;
			case "drop":
				couldPerformAction = checker.drop();
				break;
			case "retrieve":
				couldPerformAction = checker.retrieve();
				break;
			default: couldPerformAction = false; break;
						
			}

			if(!couldPerformAction)
			{
				System.out.println(Arrays.toString(actions));
				System.out.println("could not perform action " + i + " : " + actions[i] );
				return false;
			}
	}

		System.out.println("Deaths Should be: " + checker.deaths);
		System.out.println("Deaths Are: " + deaths);
		System.out.println("retrievedBlackBoxes Should be: " + checker.retrievedBlackBoxes);
		System.out.println("retrievedBlackBoxes Are: " + retrievedBlackBoxes);

		return checker.IsGoal() && checker.deaths ==deaths && checker.retrievedBlackBoxes == retrievedBlackBoxes;
	}
}
	

