/*
 * Phan, Tony (43284383)
 * Zeng, Jessica (57314018)
*/

public class PassengerArrival {
	//set the number of number of passengers for the request
	private int numPassengers;
	//destination floor of this request
	private int destinationFloor;
	//period that repeating request
	private int timePeriod;
	//expected arrival time of this request
	private int expectedTimeOfArrival;
	
	//constructor
	public PassengerArrival(int p, int f, int r,int eta) {
		numPassengers = p;
		destinationFloor = f;
		timePeriod = r;
		expectedTimeOfArrival = eta;
	}
	
	//set number of passengers for the request
	public void setNumPassengers(int p){
		numPassengers = p;
	}
	//get number of passengers for the request
	public int getNumPassengers(){
		return numPassengers;
	}
	//set the destination floor for this request
	public void setDestinationFloor(int f){
		destinationFloor = f;
	}
	//get the destination floor for this request
	public int getDestinationFloor(){
		return destinationFloor;
	}
	//set period that repeating request
	public void setTimePeriod(int t){
		timePeriod = t;
	}
	//get period that repeating request
	public int getTimePeriod(){
		return timePeriod;
	}
	//set the expected arrival time of this request
	public void setExpectedTimeOfArrival(int p){
		expectedTimeOfArrival = p;
	}
	//get the expected arrival time of this request
	public int getExpectedTimeOfArrival(){
		return expectedTimeOfArrival;
	}
	
	
	



}
