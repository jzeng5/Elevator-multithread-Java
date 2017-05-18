/*
 * Phan, Tony (43284383)
 * Zeng, Jessica (57314018)
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ElevatorSimulation {
	
	private BuildingManager BM;
	private int simTime;
	private int simRate;
	private ArrayList<ArrayList<PassengerArrival>> passengerBehavior;
	private Scanner s;

	private Elevator[] Elevators;
	
	public ElevatorSimulation(){
		BM = new BuildingManager();
		simTime = 0;
		simRate = 0;
		passengerBehavior =  new ArrayList<ArrayList<PassengerArrival>>(5);
		
		
	}
	
	
	public void start() throws InterruptedException, FileNotFoundException{
		readFile();
		Elevator e0 = new Elevator(0,BM);
		Elevator e1 = new Elevator(1,BM);
		Elevator e2 = new Elevator(2,BM);
		Elevator e3 = new Elevator(3,BM);
		Elevator e4 = new Elevator(4,BM);
		Thread t0 = new Thread(e0);
		Thread t1 = new Thread(e1);
		Thread t2 = new Thread(e2);
		Thread t3 = new Thread(e3);
		Thread t4 = new Thread(e4);
		Elevator[] ele = {e0,e1,e2,e3,e4};
		Elevators = ele;
		//initialize threads
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		while(SimClock.getTime() <= simTime && !Thread.interrupted()){
			updateFloors();	
			Thread.sleep(simRate);
			SimClock.tick();
			
		}
		t0.interrupt();
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
	}
	
	//update floors 
	public void updateFloors(){
		for(int i = 0; i < passengerBehavior.size(); i++){
			for(int j = 0; j < passengerBehavior.get(i).size();j++){
				PassengerArrival pa = passengerBehavior.get(i).get(j);
				if (pa.getExpectedTimeOfArrival() == SimClock.getTime()){
					System.out.println("Time:  "+SimClock.getTime()+"  "+ pa.getNumPassengers() + 
							" passengers are requesting an elevator from floor " + i + " to floor " + pa.getDestinationFloor());
					//reset the arrival time of passenger
					pa.setExpectedTimeOfArrival(SimClock.getTime() + pa.getTimePeriod());
					
					//call BM for current request passengers
					BM.setCurrentRequest(i,pa.getDestinationFloor(),pa.getNumPassengers());
					//call BM for setting total request passengers
					BM.setFloorRequest(i, pa.getDestinationFloor(), pa.getNumPassengers());
				}
			}
		}
	}
	
	public void printBuildingState(){
		BM.printStates();
		for(int i = 0; i < 5; ++i)
		{
			Elevators[i].printElevatorState();
			System.out.println();
		}
	}
	
	public void readFile() throws FileNotFoundException{
		File text = new File("src/ElevatorConfig.txt");
		s = new Scanner(text);
		simTime = Integer.parseInt(s.nextLine());
		simRate = Integer.parseInt(s.nextLine());
		int floor = 0;
		while (s.hasNextLine()){
			passengerBehavior.add(new ArrayList<PassengerArrival>());
			String line = s.nextLine();
			String[] parts = line.split(";");	
			for (int i = 0; i < parts.length;i++){
				String[] pieces = parts[i].split(" ");
				int passengers = Integer.parseInt(pieces[0]);
				int destination = Integer.parseInt(pieces[1]);
				int time = Integer.parseInt(pieces[2]);
				PassengerArrival PA = new PassengerArrival(passengers,destination,time,time);
				passengerBehavior.get(floor).add(PA);
				
			}
			floor++;
		}
		s.close();
	}
	
	
	
	
	

}
