/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.anim.AnimComposer;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * characters that can move and attack 
 * @author leoze
 */
public abstract class Character extends GameObject implements Action, ChangeHealth, Collidable{


    
    private int health;
    private CharacterState state;
    private double speed;
    private double range;
    private int damage;
    private float attackSpeed; // interval between each attack
    private float coolDown; // time remaining on interval
    
    private AnimComposer animComposer; // animation 
    
    private CharacterControl characterControl;
    
    protected BulletAppState bulletAppState; 
    private CollisionShape collisionMesh; // mesh to map collision 
    private RigidBodyControl rigidBody; // rigidbody to simulate physical object 
    
    private Vector3f walkDirection = new Vector3f(); // direction of walking (change in position, not current position)
    
    public Character(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        
        super(app, position, name);
        this.health = health;
        this.state = CharacterState.IDLE;
        
        this.bulletAppState = bulletAppState;
        
    }
    

    
    @Override
    public void initCollision() {

        Vector3f extent = ((BoundingBox) getModel().getWorldBound()).getExtent(new Vector3f());
        BoxCollisionShape collisionShape = new BoxCollisionShape(extent);
        setCharacterControl(new CharacterControl(collisionShape, 0.05f));
        getCharacterControl().setFallSpeed(10);

        bulletAppState.getPhysicsSpace().add(getCharacterControl());
        getCharacterControl().setGravity(new Vector3f(0, -60f, 0));

        getCharacterControl().setPhysicsLocation(getPosition());

        // init rigidbody here for all characters 
    }
    
    @Override
    public void updateCollision(){
        
    }
    
    @Override
    public void deleteCollision(){
        
    }
    
    
    @Override
    public void move(Vector3f change){
        walkDirection.set(0, 0, 0); // reset walk direction change 
        
        
        walkDirection.addLocal(change);
        
        walkDirection.y = 0; // make sure player does not increase in y axis (up)
        
        
        getCharacterControl().setWalkDirection(walkDirection);
 
        setPosition(getCharacterControl().getPhysicsLocation());
    }
    
    public void behaviour(float tpf){
        
        setCoolDown(getCoolDown() - tpf);
        
    }
    
    @Override
    public void attack(Character character) {
        
        if(state == CharacterState.ATTACKING){
            if(getCoolDown() <= 0){
                character.removeHealth(damage);
                coolDown = getAttackSpeed();
            }
            
        }
        
    }
    
    @Override
    public void addHealth(int amount) {
        
    }

    @Override
    public void removeHealth(int amount) {
        System.out.println(health);
        health -= amount;
    }
    
    @Override
    public void takeDamage(int amount){
        
    }
    
    
    
    
    public CharacterControl getCharacterControl() {
        return characterControl;
    }


    public void setCharacterControl(CharacterControl characterControl) {
        this.characterControl = characterControl;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the state
     */
    public CharacterState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(CharacterState state) {
        this.state = state;
    }
    
    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(double range) {
        this.range = range;
    }

        /**
     * @return the collisionMesh
     */
    public CollisionShape getCollisionMesh() {
        return collisionMesh;
    }

    /**
     * @param collisionMesh the collisionMesh to set
     */
    public void setCollisionMesh(CollisionShape collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    /**
     * @param rigidBody the rigidBody to set
     */
    public void setRigidBody(RigidBodyControl rigidBody) {
        this.rigidBody = rigidBody;
    }

    /**
     * @return the rigidBody
     */
    public RigidBodyControl getRigidBody() {
        return rigidBody;
    }

    /**
     * @param attackSpeed the attackSpeed to set
     */
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * @param coolDown the coolDown to set
     */
    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * @return the coolDown
     */
    public float getCoolDown() {
        return coolDown;
    }

    private float getAttackSpeed() {
        return attackSpeed;
    }

    
}
