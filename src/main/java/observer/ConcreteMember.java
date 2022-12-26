package observer;

public class ConcreteMember implements Member{
    private UndoableStringBuilder currentString;
    private String name;

    /**
     *
     * A constructor with the default StringBuilder values.
     *
     * @param name  Name of the member.
     *
     */
    public ConcreteMember(String name) {
        this.name = name;
        this.currentString = null;
    }

    /**
     *
     * Updates the pointer of the UndoableStringBuilder to
     * the one from the GroupAdmin.
     *
     * @param usb  A pointer to the UndoableStringBuilder object
     *             from the GroupAdmin.
     *
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.currentString = usb;
        System.out.println(this.name + " notified.");
    }

    /**
     *
     * A toString method to print the member's data.
     *
     * @return  A string representation of the object.
     *
     */
    public String toString() {
        return "Name: " + this.name + "; String: " + this.currentString;
    }
}
