package com.example.kristen.adventuregame;

public class Enemy extends Character {

	private int expReward;
	
	public Enemy(String name, int strength, int vitality, int expReward, int agility) {
		setName(name);
		setStrength(strength);
		setVitality(vitality);
		setExpReward(expReward);
	}
	
	//Getter & Setter for Player Escape Chance.
	
	
	public int getExpReward() {
		return expReward;
	}
	public void setExpReward(int expReward) {
		this.expReward = expReward;
	}
}