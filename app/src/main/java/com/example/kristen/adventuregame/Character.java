package com.example.kristen.adventuregame;

import java.util.Random;

public abstract class Character {
	protected String name;
	protected int strength;
	protected int vitality;
	protected int damage = strength;
	protected int maxHealth = vitality;
	protected int health = maxHealth;
	protected int hitChance;
	protected int agility;
	protected boolean critStrike;
	protected String enemyAppear;
	

	
	// Getter & Setter for Name.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	// Getters & Setters for Stats.
	
	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}
	
	// Getters for Dmg/Health
	public int getDamage() {
		damage = strength * 5;
		return damage + getCritDamage();
	}
	
	public int getMaxHealth() {
		maxHealth = (vitality * 10) + 100;
		return maxHealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHitChance() {
		return hitChance;
	}
	public void setHitChance(int hitChance) {
		this.hitChance = hitChance;
	}
	
	//escape chance & crit chance.
	
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	
	public double getEscapeChance() {
		return (100 - agility) / 100;
	}
	public int getCritDamage() {
		int critChance = (agility / 2);
		Random rand = new Random();
		int critNumber = rand.nextInt(100);
		critStrike = (critNumber <= critChance);
		
		if (critStrike) {
			return strength;
		}else {
			return 0;
		}
	}
	public boolean getCritStrike() {
		return critStrike;
	}
}