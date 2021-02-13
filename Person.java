class Person {
    private boolean state;
    private boolean cleared = false;
    
    public Person(boolean state) {
        this.state = state;
    }
    
    public boolean getState() {
        return state;
    }
    
    public String toString() {
        return "" + state;
    }
    public void clear() {
	    cleared = true;
    }
    public boolean isCleared() {
	    return cleared;
    }
}
