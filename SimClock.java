/*
 * Phan, Tony (43284383)
 * Zeng, Jessica (57314018)
*/

public class SimClock {
	//static field
	private static int simTime;
	
	//constructor, init the time as 0
	public SimClock(){
		simTime = 0;
	
	}
	//increment 1 at a time
	public static void tick(){
		simTime+=1;
	}
	//ge the current time of this object
	public static int getTime(){
		return simTime;
	}
	

}
