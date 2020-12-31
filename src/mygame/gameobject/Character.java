/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import mygame.Main;

/**
 *
 * @author leoze
 */
public abstract class Character extends GameObject{
    
    int health;
    CharacterState state;
    
    public Character(Main main, int x, int y, int z, String name, int health, CharacterState state){
        super(main, x, y, z, name);
        this.health = health;
        this.state = state;
        
    }
    
}