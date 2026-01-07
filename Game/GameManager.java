package Game;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;

import Display.Window;
import GameObjects.GameObject;

public class GameManager extends Thread{
    
    private InputManager inputManager;
    private int tickrate;
    private long lastTick;
    private boolean running = false;
    private HashMap<Integer, GameObject> gameObjects;
    private HashSet<GameObject> roots;
    private long tickCount;
    private long ticksSkipped;
    private boolean skipBehindTicks=true;
    private int nextId = 0;

    public GameManager(Window window, int tickrate){
        inputManager = new InputManager(window);
        gameObjects = new HashMap<>();
        roots = new HashSet<>();
        this.tickrate = tickrate;
    }

    public GameManager(Window window){
        this(window, 60);
    }

    private void tag(GameObject gameObject){
        gameObject.setId(nextId++);
        gameObjects.put(nextId, gameObject);
        gameObject.setGameManager(this);
        gameObject.setInputManager(this.inputManager);
        Iterator<GameObject> childrenIterator = gameObject.getChildren();
        while(childrenIterator.hasNext()){
            GameObject child = childrenIterator.next();
            tag(child);
        }
    }

    public void subscribe(GameObject gameObject){
        roots.add(gameObject);
        tag(gameObject);
    }

    private void update(GameObject gameObject, int delta){
        gameObject.tick(delta);
        Iterator<GameObject> childrenIterator = gameObject.getChildren();
        while(childrenIterator.hasNext()){
            GameObject child = childrenIterator.next();
            update(child, delta);
        }
    }

    public GameObject objectFromId(int id){
        return gameObjects.get(id);
    }

    public int idFromObject(GameObject gameObject){
        return gameObject.getId();
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
            for(GameObject gameObject : roots){
                update(gameObject, delta);
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
                if(targetTime-now > 2){
                    try {
                        sleep(targetTime-now-2);
                    } catch (InterruptedException e) {
                        running = false;
                    }
                }
                while(System.currentTimeMillis() < targetTime);
            }
        }
    }
}
