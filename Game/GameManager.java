package Game;

import java.util.HashSet;

import Display.Window;
import GameObjects.GameObject;

public class GameManager extends Thread{
    
    private InputManager inputManager;
    private int tickrate;
    private long lastTick;
    private boolean running = false;
    private HashSet<GameObject> gameObjects;
    private long tickCount;
    private long ticksSkipped;
    private boolean skipBehindTicks=true;

    public GameManager(Window window, int tickrate){
        inputManager = new InputManager(window);
        gameObjects = new HashSet<>();
        this.tickrate = tickrate;
    }

    public GameManager(Window window){
        this(window, 60);
    }

    public void subscribe(GameObject gameObject){
        gameObjects.add(gameObject);
        gameObject.setGameManager(this);
        gameObject.setInputManager(this.inputManager);
    }

    @Override
    public void run(){
        long start = System.currentTimeMillis();
        running = true;
        lastTick = System.currentTimeMillis();
        tickCount = 0;
        while(running){
            long now = System.currentTimeMillis();
            int delta = (int) (now-lastTick);
            tickCount++;
            inputManager.update();
            for(GameObject gameObject : gameObjects){
                gameObject.update(delta);
            }
            lastTick = now;
            now = System.currentTimeMillis();
            long targetTime = (tickCount+1+ticksSkipped)*1000/tickrate+start;
            if(targetTime < now){
                long target = tickrate*(now-start)/1000-ticksSkipped;
                if(target-tickCount > 1 && skipBehindTicks){
                    System.out.println("Ticks Skipped");
                    System.out.println(target-tickCount-1);
                    ticksSkipped += target-tickCount-1;
                    tickCount = target-1;
                }
            }else{
                try {
                    sleep(targetTime-now);
                } catch (InterruptedException e) {
                    running = false;
                }
            }
            
            
        }
    }
}
