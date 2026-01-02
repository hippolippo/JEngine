package GameObjects;

import Game.GameManager;
import Game.InputManager;

public abstract class GameObject {

    boolean hidden = false;
    public void setHidden(boolean enable){
        hidden = enable;
    }
    public boolean isHidden(){
        return hidden;
    }
    public abstract void update(int delta);
    
    public GameManager gameManager;
    public InputManager inputManager;
    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public void setInputManager(InputManager inputManager){
        this.inputManager = inputManager;
    }
}
