/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.Application;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author leoze
 */
public class InventoryManager implements ActionListener {
    
    private Application app;
    
    private Inventory inventory;
    
    public InventoryManager(Application app){
        this.app = app;
    }
    
    
    private void setKeys(){
        app.getInputManager().addMapping("click", new MouseButtonTrigger(0));
        app.getInputManager().addListener(this, "click");
        
    }

    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Vector2f getMousePosotion(){
        return app.getInputManager().getCursorPosition();
    }
    
    public void update(){

    }
}
