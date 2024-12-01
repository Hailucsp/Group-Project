/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chris, connor
 */
public class Character {
    // variables
    protected int health;
    protected int attack;
    // boolean variable to indicate if the character is alive or not
    public boolean alive;
    // Character constructor with health and attack parameters
    public Character(int health, int attack) {
        this.health = health;
        this.attack = attack;
        this.alive = true;
    }
// getters and setters for the variables
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    // method attacked 
    public void attacked(int damage) {
        // health = health - damage
        this.health -= damage;
        //if health is less than or equal to 0
        if (this.health <= 0) {
            // sets alive boolean value to false meaning the character is dead
            this.alive = false;
        }
    }
    
    public void attacking(Character target) {
        if (this.alive) {
            target.attacked(this.attack);
        }
    }
}
