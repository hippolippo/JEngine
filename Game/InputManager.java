package Game;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

import Display.Window;
import Spatial.Vector2;
import Spatial.Point2;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, FocusListener{
    
    private Window window;
    // Live, this tick, and last tick mouse position
    private int x, y;
    private int xState, yState;
    private int lastXState, lastYState;
    // Live and this tick wheel deltas both full and precise
    private int scroll, scrollDelta;
    private double preciseScroll, preciseScrollDelta;
    // Live, this tick, and last tick state of keys
    private boolean[] keys = new boolean[1024];
    private boolean[] keyStick = new boolean[1024];
    private boolean[] keyState = new boolean[1024]; 
    private boolean[] lastKeyState = new boolean[1024];
    // Live, this tick, and last tick state of mouse buttons
    private boolean[] mouseButtons = new boolean[5];
    private boolean[] mouseButtonsStick = new boolean[5];
    private boolean[] mouseButtonsState = new boolean[5];
    private boolean[] lastMouseButtonsState = new boolean[5];
    // Live, this tick, and last tick focus state
    private boolean focused;
    private boolean focusedState;
    private boolean lastFocusedState;
    // Live, this tick, and last tick in-window state
    private boolean inWindow;
    private boolean inWindowState;
    private boolean lastInWindowState;
    // Live and this tick state of typed keys
    private LinkedList<Integer> typed = new LinkedList<>();
    private LinkedList<Integer> typedState = new LinkedList<>();


    public InputManager(Window window){
        this.window = window;
        this.window.addKeyListener(this);
        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);
        this.window.addMouseWheelListener(this);
        this.window.addFocusListener(this);
    }

    public void reset(){
        // Keys
        for(int i = 0; i < keys.length; i++){
            keys[i] = false;
            keyState[i] = false;
            keyStick[i] = false;
            lastKeyState[i] = false;
        }
        // Mouse Buttons
        for(int i = 0; i < mouseButtons.length; i++){
            mouseButtons[i] = false;
            mouseButtonsState[i] = false;
            mouseButtonsStick[i] = false;
            lastMouseButtonsState[i] = false;
        }
    }

    protected void update(){
        // Move live states into tick
        // Keys
        for(int i = 0; i < keys.length; i++){
            lastKeyState[i] = keyState[i];
            keyState[i] = keys[i] || keyStick[i];
            keyStick[i] = false;
        }
        // Mouse Buttons
        for(int i = 0; i < mouseButtons.length; i++){
            lastMouseButtonsState[i] = mouseButtonsState[i];
            mouseButtonsState[i] = mouseButtons[i] || mouseButtonsStick[i];
            mouseButtonsStick[i] = false;
        }
        // Window Focus
        lastFocusedState = focusedState;
        focusedState = focused;
        // In Window
        lastInWindowState = inWindowState;
        inWindowState = inWindow;
        // Typed
        if(!typed.isEmpty()){
            typedState = typed;
            typed = new LinkedList<>();
        }
        // Mouse Position
        lastXState = xState;
        xState = x;
        lastYState = yState;
        yState = y;
        // Mouse Scroll
        scrollDelta = scroll;
        scroll = 0;
        preciseScrollDelta = preciseScroll;
        preciseScroll = 0;
    }

    // Events:

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        inWindow = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        inWindow = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // pass
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if(0 <= button && button < mouseButtons.length){
            mouseButtons[button] = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        if(0 <= button && button < mouseButtons.length){
            mouseButtons[button] = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        inWindow = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inWindow = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        typed.add(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(0 <= key && key < keys.length){
            keys[key] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(0 <= key && key < keys.length){
            keys[key] = false;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        focused = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        focused = false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll += e.getWheelRotation();
        preciseScroll += e.getPreciseWheelRotation();
    }

    // Getters

    // Key Getters
    public boolean keyDown(int keyCode){
        return keyState[keyCode];
    }

    public boolean keyPressed(int keyCode){
        return keyState[keyCode] && !lastKeyState[keyCode];
    }

    public boolean keyReleased(int keyCode){
        return !keyState[keyCode] && lastKeyState[keyCode];
    }

    // Mouse Getters
    public boolean mouseButtonDown(int buttonCode){
        return mouseButtonsState[buttonCode];
    }

    public boolean mouseButtonPressed(int buttonCode){
        return mouseButtonsState[buttonCode] && !lastMouseButtonsState[buttonCode];
    }

    public boolean mouseButtonReleased(int buttonCode){
        return !mouseButtonsState[buttonCode] && lastMouseButtonsState[buttonCode];
    }

    public Point2 mousePosition(){
        return new Point2(xState-window.getSize().getWidth()/2, yState-window.getSize().getHeight()/2);
    }

    public Point2 liveMousePosition(){
        return new Point2(x-window.getSize().getWidth()/2, y-window.getSize().getHeight()/2);
    }

    public boolean mouseMoved(){
        return lastXState != xState || lastYState != yState;
    }

    public Vector2 mouseMovement(){
        return new Vector2(xState-lastXState, yState-lastYState);
    }

    public int getScrollClicks(){
        return scrollDelta;
    }

    public double getScrollMovement(){
        return preciseScrollDelta;
    }

    public boolean mouseInWindow(){
        return inWindowState;
    }

    public boolean liveMouseInWindow(){
        return inWindow;
    }

    public boolean mouseMovedInWindow(){
        return inWindowState && !lastInWindowState;
    }

    public boolean mouseMovedOutWindow(){
        return !inWindowState && lastInWindowState;
    }

    // Focus Getters
    public boolean isFocused(){
        return focusedState;
    }

    public boolean gainedFocus(){
        return focusedState && !lastFocusedState;
    }

    public boolean lostFocus(){
        return !focusedState && lastFocusedState;
    }

    // Typed Getters
    public LinkedList<Integer> getTyped(){
        return typedState;
    }

}
