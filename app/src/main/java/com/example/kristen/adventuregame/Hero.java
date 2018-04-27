package com.example.kristen.adventuregame;

public class Hero extends Character {
	private int potions;
	private int experience;
	private int requiredExperience;
	private int level;
	
	public Hero(String name) {
		setName(name);
		setStrength(5);
		setVitality(5);
		setAgility(80);
		setPotions(3);
		setExperience(0);
		setRequiredExperience();
		setLevel(1);
		setHealth(maxHealth);
		
	
	}
	public void gainLevel() {
		this.level++;
	}
	
	public void receiveExperience(int expReward) {
		this.experience += expReward;
	}

	public void takeDamage(int damageTaken){
		this.health -= damageTaken;
	}
	
	public void drinkPotion(int healthPotionHealAmount) {
		this.health += healthPotionHealAmount;
		this.potions--;
	}
	
	public void receivePotion() {
		this.potions++;
	}

	// Getter & Setter for Potions
	public int getPotions() {
		return potions;
	}

	public void setPotions(int potions) {
		this.potions = potions;
	}
	
	//Getter & Setter for experience
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public int getRequiredExperience() {
		int levelModifier = this.level * 10;
		this.requiredExperience = 90 + levelModifier;
		return this.requiredExperience;
	}
	
	public void setRequiredExperience() {
		int levelModifier = this.level * 10;
		this.requiredExperience = 90 + levelModifier;
	}
	
	//Getter & Setter for level
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
