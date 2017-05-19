

public class ElevatorEvent {
	//destination of this event
	private int destination;
	//the expected arrival time of this event
	private int expectedArrival;
	
	//initialize an elevator event
	public ElevatorEvent(int d, int t){
		destination = d;
		expectedArrival = t;
	}
	//set the destination of the event
	public void setDestination(int d){
		destination = d;
	}
	//get the destination of the event
	public int getDestination(){
		return destination;
	}
	//set the expected arrival time of this event
	public void setArrival(int a){
		expectedArrival = a;
	}
	
	//get the expected arrival time of this event
	public int getArrival(){
		return expectedArrival;
	}

}
