package GameObjects;

import java.util.Iterator;

import Game.GameManager;
import Game.InputManager;

public abstract class GameObject {

    private int id;
    boolean hidden = false;

    public void setHidden(boolean enable){
        hidden = enable;
    }
    public boolean isHidden(){
        return hidden;
    }
    public abstract void update(int delta);
    public abstract void tick(int delta);
    
    public GameManager gameManager;
    public InputManager inputManager;
    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public void setInputManager(InputManager inputManager){
        this.inputManager = inputManager;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public abstract GameObject getParent();
    public abstract Iterator<GameObject> getChildren();

    @Override
    public int hashCode() {
        return id;
    }
}
