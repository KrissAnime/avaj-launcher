# avaj-launcher
Wethinkcode 42 1st Java project

Program runs using the scenario.txt file

To compile the project run the following commands from the project root directory:

find wethinkcode/com/avaj/*.java >> sources.txt
find wethinkcode/com/avaj/weather/*.java >> sources.txt
find wethinkcode/com/avaj/simulator/*.java >> sources.txt
find wethinkcode/com/avaj/simulator/vehicles/*.java >> sources.txt
find wethinkcode/com/avaj/simulator/exceptions/*.java >> sources.txt

javac wethinkcode/com/avaj/simulator/Simulator.java @sources.txt

To run the program run:

java wethinkcode/com/avaj/simulator/Simulator
