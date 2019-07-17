package wethinkcode.com.avaj.simulator;

import wethinkcode.com.avaj.Flyable;
import wethinkcode.com.avaj.simulator.exceptions.IncorrectAircraftTypeException;
import wethinkcode.com.avaj.simulator.exceptions.IncorrectFileNameException;
import wethinkcode.com.avaj.simulator.exceptions.InsufficientSimulationsException;
import wethinkcode.com.avaj.simulator.exceptions.InvalidInputException;
import wethinkcode.com.avaj.simulator.vehicles.AircraftFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] arg) {
        try {
            if (!arg[0].equals("scenario.txt")) {
                throw new IncorrectFileNameException("Invalid input file, expected scenario.txt");
            }
            BufferedReader reader = new BufferedReader(new FileReader(arg[0]));
            String line = reader.readLine();
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations <= 0) {
                    throw new InsufficientSimulationsException("Simulator requires at least 1 simulation to run");
                }

                while ((line = reader.readLine()) != null) {
                    int count = 0;
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ' ') {
                            count++;
                        }
                    }
                    if (count < 4) {
                        throw new InvalidInputException("Invalid input given for aircraft data");
                    }
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    if (flyable == null) {
                        throw new IncorrectAircraftTypeException(line.split("")[0]);
                    }
                    flyables.add(flyable);
                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 0; i <= simulations; i++) {
                    weatherTower.changeWeather();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file " + arg[0]);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read file " + arg[0]);
            e.printStackTrace();
        } catch (IncorrectFileNameException e) {
            System.out.println("Invalid input file, expected scenario.txt got " + arg[0]);
            e.printStackTrace();
        } catch (InsufficientSimulationsException e) {
            System.out.println("Received invalid simulations to run input");
            e.printStackTrace();
        } catch (IncorrectAircraftTypeException e) {
            System.out.println("Invalid Aircraft type, expected Balloon, Helicopter or JetPlane");
            e.printStackTrace();
        } catch (InvalidInputException e) {
            System.out.println("Invalid Input detected");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer can not be parsed");
            e.printStackTrace();
        }
    }
}
