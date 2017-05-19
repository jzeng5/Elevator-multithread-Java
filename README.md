# Elevator control system

This project uses Multithreading,	File	IO,	Arrays,	and	ArrayLists.	It simulates	an	
elevator	system	in	a	five-story	building	with	five	elevators.	To	make	indexing	easier,	the	elevators	
will	be	labeled	from	0	– 4	(5	elevators	total)	and	the	building’s	floors	will	be	labeled	from	0	– 4	(5	
floors	total).	

# Rules
Each elevator will be executed in a separate thread. Each elevator will have access to a BuildingManager object keeping state of the building’s floors. Each elevator can detect if a passenger is waiting at any given time for every floor in the building via the BuildingManager.

# Syncronization 
Since	we	are	dealing	with	multi-threaded behavior	and	shared	data	among	the	entire	simulation.	I apply	java syncronized method	among the Elevator threads to avoid race condition acessing the BuildingManager object.

# Author 
1.Jessica Zeng
2.Tony Phan
