/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/**
 *
 * @author leoze
 */
public class SettingState extends AbstractAppState{
    
    private SimpleApplication app;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;

    }


    @Override
    public void update(float tpf) {


        
    }

    void display() {

    }
    
    public void enterState(){
        setEnabled(true);
    }

    public void exitState() {
        setEnabled(false);
    }
}