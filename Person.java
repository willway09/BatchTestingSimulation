class Person {
    private boolean state;
    
    public Person(boolean state) {
        this.state = state;
    }
    
    public boolean getState() {
        return state;
    }
    
    public String toString() {
        return "" + state;
    }
}