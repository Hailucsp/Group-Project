/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.simulation;

/**
 *
 * @author chris, connor
 */
import java.util.ArrayList;
import java.util.Random;
import com.mycompany.Character;
import com.mycompany.Child;
import com.mycompany.CommonInfected;
import com.mycompany.Soldier;
import com.mycompany.Tank;
import com.mycompany.Teacher;

public class Simulation {
    public static void main(String[] args) {
        // ArrayList to contain the survivors 
        ArrayList<Character> survivors = new ArrayList<>();
        // ArrayList to contain the zombies 
        ArrayList<Character> zombies = new ArrayList<>();
        // ArrayList to contain the random survivor types
        ArrayList<String> survivorTypes = new ArrayList<>();
        // ArrayList to contain the random zombie types
        ArrayList<String> zombieTypes = new ArrayList<>();
        // creating a random object
        Random random = new Random();
        // setting the count for child, teacher, and soldier to 0
        int childCount = 0, teacherCount = 0, soldierCount = 0;
        for (int i = 0; i < 5; i++) {
            int survivorType = random.nextInt(3);
            if (survivorType == 0) {
                survivors.add(new Child());
                survivorTypes.add("Child");
                childCount++;
            } else if (survivorType == 1) {
                survivors.add(new Teacher());
                survivorTypes.add("Teacher");
                teacherCount++;
            } else {
                survivors.add(new Soldier());
                survivorTypes.add("Soldier");
                soldierCount++;
            }
        }

        int commonInfectedCount = 0;
        int tankCount = 0;
        for (int i = 0; i < 9; i++) {
            int zombieType = random.nextInt(2);
            if (zombieType == 0) {
                zombies.add(new CommonInfected());
                zombieTypes.add("CommonInfected");
                commonInfectedCount++;
            } else {
                zombies.add(new Tank());
                zombieTypes.add("Tank");
                tankCount++;
            }
        }

        System.out.println("We have " + survivors.size() + " survivors trying to make it to safety (" +
                childCount + " children, " + teacherCount + " teachers, " + soldierCount + " soldiers)\n");

        System.out.println("But there are " + zombies.size() + " zombies waiting for them (" +
                commonInfectedCount + " common infected, " + tankCount + " tanks)\n");

        //Initialize variables for tracking survivors and zombieduring the battle
        int survivorIndex = 0; //Track the index of the current survivor
        int zombieIndex = 0; //Track the index of the current zombie
        ArrayList<String> killList = new ArrayList<>(); //Store the message of who killed whom

        while (true) {
            //Loop through each survivor in the list
            for (int i = 0; i < survivors.size(); i++) {
                //check if the survivor is still alive
                if (survivors.get(i).isAlive()) {
                    //loop through each zombies in the list
                    for (int j = 0; j < zombies.size(); j++) {
                        //check if the zombie is still alive
                        if (zombies.get(j).isAlive()) {
                            //survivor attacks the zombie decreasing its health
                            survivors.get(i).attacking(zombies.get(j));

                            //if the zombie is killed from this attack 
                            if (!zombies.get(j).isAlive()) {
                                //add the message to killist array of which survivor killed which zombie
                                killList.add(survivorTypes.get(i) + " " + i + " killed " +
                                        zombieTypes.get(j) + " " + j);
                            }
                        }
                    }
                }
            }
            //iterate through each zombie in the list
            for (int i = 0; i < zombies.size(); i++) {
                //check if the zombie is still alive
                if (zombies.get(i).isAlive()) {
                    //iterate through the survivor
                    for (int j = 0; j < survivors.size(); j++) {
                        //check if the current survivor is still alive
                        if (survivors.get(j).isAlive()) {
                            //Zombie attacks the survior and their health reduces.
                            zombies.get(i).attacking(survivors.get(j));
                            //if the survivor dies
                            if (!survivors.get(j).isAlive()) {
                                //add the message to killist showing which zombie killed which survivor
                                killList.add(zombieTypes.get(i) + " " + i + " killed " +
                                        survivorTypes.get(j) + " " + j);
                            }
                        }
                    }
                }
            }
            // Gets alive survivors and zombies, checks for who has remaining counts of zombies and survivors
            int aliveSurvivors = 0;
            for (Character survivor : survivors) {
                if (survivor.isAlive()) {
                    aliveSurvivors++;
                }
            }
            
            int aliveZombies = 0;
            for (Character zombie : zombies) {
                if (zombie.isAlive()) {
                    aliveZombies++;
                }
            }
            // Break condition if no one is left alive, zombies or survivors
            if (aliveSurvivors == 0 || aliveZombies == 0) {
                break;
            }
        }
        // Prints killList's report
        for (String report : killList) {
            System.out.println(report);
        }
        // Checks for count of survivors
        int survivingCount = 0;
        for (Character survivor : survivors) {
            if (survivor.isAlive()) {
                survivingCount++;
            }
        }
        //Prints scenarios depending on if any survivors made it or not
        if (survivingCount == 0) {
            System.out.println("None of the survivors made it.");
        } else {
            System.out.println(survivingCount + " survivors made it to safety.");
        }
    }
}
