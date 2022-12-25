package observer;

public class ConcreteMember implements Member{
    private UndoableStringBuilder currentString;
    private String name;

    public ConcreteMember(String name) {
        this.name = name;
        this.currentString = null;
    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.currentString = usb;
    }

    public String toString() {
        return "Name: " + this.name + "; String: " + this.currentString;
    }
}
