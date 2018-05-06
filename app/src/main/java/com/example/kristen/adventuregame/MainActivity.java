package com.example.kristen.adventuregame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView informationText, playerHealth, enemyHealthText;
    Button button1, button2, button3;

    Hero hero;
    int enemyModifier;
    int enemyIndex;
    int enemyHealth;
    Enemy enemy;
    Random rand;
    int healthPotionHealAmount;
    int healthPotionDropChance;
    Scanner in;
    Enemy[] enemies;
    Enemy[] bosses;
    String input;

    int stageNumber = 1;

    boolean running = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        informationText = findViewById(R.id.information_text);
        playerHealth = findViewById(R.id.players_hp_text);
        enemyHealthText = findViewById(R.id.enemy_hp_text);

        button1 = findViewById(R.id.action_button_1);
        button2 = findViewById(R.id.action_button_2);
        button3 = findViewById(R.id.action_button_3);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    //Game variables
    //Enemy dummy = new Enemy("Training Dummy", 1, 1, 1, 1000);
    Enemy skeleton = new Enemy("Skeleton", 3, 0, 20, 50);
    Enemy zombie = new Enemy("Zombie", 1, 5, 20, 50);
    Enemy barbarian = new Enemy("Barbarian", 3, 3, 25, 50);
    Enemy assassin = new Enemy("Assassin", 5, 0, 30, 50);

    Enemy dragon = new Enemy("Migi the Deathly Dragon", 3, 10, 50, 100);
    Enemy beast = new Enemy("Sinatra the Giant", 3, 10, 50, 100);

    Enemy[]{ skeleton, zombie, barbarian, assassin, /*dummy*/};
    Enemy[]{dragon, beast};

    //Drop Chance
    healthPotionDropChance =50;



    @Override
    public void onClick(View v) {
        System.out.println("CLICK!");
        switch(v.getId()) {

            //attacks
            case R.id.action_button_1:
                attack();
                break;

            //drink potion
            case R.id.action_button_2:
                drinkHealthPotion();
                break;

            //run away
            case R.id.action_button_3:
                escape();
                break;
        }
    }


    public void attack(){
        int damageDealt = rand.nextInt(10) + hero.getDamage();
        int damageTaken = rand.nextInt(10) + enemy.getDamage();

        enemyHealth -= damageDealt;
        hero.takeDamage(damageTaken);

        String message = hero.getName() + " strikes the " + enemy.getName() + " for " + damageDealt + " damage.";

        if (hero.getCritStrike()) {
            message += ("\n It was a critical hit!");
        }
        message += ("\n " + hero.getName() + " recieves " + damageTaken + " damage in retaliation!");

        if (enemy.getCritStrike()) {
            message += ("\n It was a critical hit!");
        }

        informationText.setText(message);
        enemyHealthText.setText(getResources().getString(R.string.enemy_s_hp) + String.valueOf(enemyHealth));
        playerHealth.setText(getResources().getString(R.string.player_s_hp) + String.valueOf(hero.getHealth()));

        if (enemyHealth <= 0) {
            enemyDefeated();
        }
    }


    public void drinkHealthPotion() {

        if(hero.getPotions() > 0) {
            hero.drinkPotion(hero.getMaxHealth() / 2);
            String message =("\n" + hero.getName() + " drank a health potion, healing themself for " + hero.getMaxHealth() / 2 + ". ");
            message += ("\n They now have " + hero.getHealth() + " HP and " + hero.getPotions() + " health potions left.\n");
            informationText.setText(message);
            } else {
               String message = ("\n " + hero.getName() + " has no health potions left! Defeat enemies for a chance to get one!\n");
               informationText.setText(message);
            }
        }

    public void escape() {
        String message = "\n"+ hero.getName() + " runs away from the " + enemy.getName() + "!";
        informationText.setText(message);
    }


    public void enemyDefeated() {

        String message = enemy.getName() + " was defeated! ";

        hero.receiveExperience(enemy.getExpReward());
        message +=  hero.getName() + "\n gained " + enemy.getExpReward() + " experience. ";

        boolean levelUp = false;

        while (hero.getExperience() >= hero.getRequiredExperience()) {
            int experienceDifference = hero.getExperience() - hero.getRequiredExperience();
            hero.setExperience(experienceDifference);
            hero.gainLevel();
            hero.setHealth(hero.getMaxHealth());
            levelUp = true;
        }
        if(levelUp) {
            message += "\n Congratulations, " + hero.getName() + " is now level " + hero.getLevel() + "! ";
        }
        message += "\n They have " + hero.getExperience() + "/" + hero.getRequiredExperience() + " experience. ";

        //Loot
        if(rand.nextInt(100) < healthPotionDropChance) {
            hero.receivePotion();
            message += "\n The " + enemy.getName() + " dropped a health potion!  ";
            message += "\n" + hero.getName() + " now has " + hero.getPotions() + " health potion(s). ";
        }
        informationText.setText(message);
    }
}

