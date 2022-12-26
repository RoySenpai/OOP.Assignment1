package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ArrayList<Member> membersList = new ArrayList<>();

    /**
     *
     * Register a member to the observers list.
     *
     * @param obj Member object pointer
     *
     */
    @Override
    public void register(Member obj) {
        if (!membersList.contains(obj))
        {
            this.membersList.add(obj);
            System.out.println("Added member.");
        }
    }

    /**
     *
     * Unregister a member from the observers list.
     *
     * @param obj Member object pointer
     *
     */
    @Override
    public void unregister(Member obj) {
        if (membersList.contains(obj))
        {
            this.membersList.remove(obj);
            System.out.println("Removed member.");
        }
    }

    /**
     *
     * Inserts the string into this character sequence.
     * Also notifies that a change has been made.
     *
     * @param offset  the offset
     * @param str  a string
     *
     */
    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notifyMembers();
    }

    /**
     *
     * Appends the specified string to this character sequence.
     * Also notifies that a change has been made.
     *
     * @param obj  a string
     *
     */
    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notifyMembers();
    }

    /**
     *
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * Also notifies that a change has been made.
     *
     * @param start  The beginning index, inclusive.
     * @param end  The ending index, exclusive.
     *
     */
    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        notifyMembers();
    }

    /**
     *
     * Makes an undo command - replaces the current string with
     * the previous string that was stored in memory.
     * Also notifies that a change has been made.
     *
     */
    @Override
    public void undo() {
        this.usb.undo();
        notifyMembers();
    }

    /**
     *
     * Notifies all members that a change to the UndoableStringBuilder
     * object has been made.
     *
     */
    private void notifyMembers() {
        for (Member m: this.membersList)
            m.update(this.usb);
    }

    /**
     *
     * A toString method to print the sender's data.
     *
     * @return  A string representation of the object.
     *
     */
    @Override
    public String toString() {
        String ret = "Members:\n";

        for (Member m: this.membersList)
            ret += m.toString() + "\n";

        return ret;
    }
}
