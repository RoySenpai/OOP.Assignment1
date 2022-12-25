package observer;

import java.util.Stack;

public class UndoableStringBuilder {
    private StringBuilder str;
    private Stack<String> stk;

    /**
     *
     * A constructor with the default StringBuilder values.
     *
     */
    public UndoableStringBuilder() {
        this.str = new StringBuilder();
        this.stk = new Stack<>();
    }

    /**
     *
     * Makes an undo command - replaces the current string with
     * the previous string that was stored in memory.
     * Works in the current methods: append, delete, insert,
     * replace and reverse.
     *
     */
    public void undo() {
        if (!stk.empty())
            this.str.replace(0,this.str.length(),this.stk.pop());
    }

    /**
     *
     * Appends the specified string to this character sequence.
     * Supports undo method.
     *
     * @param str  a string
     *
     * @return  a reference to this object.
     *
     */
    public UndoableStringBuilder append(String str) {
        stk.push(this.str.toString());
        this.str.append(str);
        return this;
    }

    /**
     *
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * Supports undo method.
     *
     * @param start  The beginning index, inclusive.
     * @param end  The ending index, exclusive.
     *
     * @return  a reference to this object.
     *
     */
    public UndoableStringBuilder delete(int start, int end) {
        try
        {
            stk.push(this.str.toString());
            this.str.delete(start, end);
        }

        catch(StringIndexOutOfBoundsException e)
        {
            System.err.println("Out of bounds error!");
            e.printStackTrace();
        }

        return this;
    }

    /**
     *
     * Inserts the string into this character sequence.
     * Supports undo method.
     *
     * @param offset  the offset
     * @param str  a string
     *
     * @return  a reference to this object.
     *
     */
    public UndoableStringBuilder insert(int offset, String str) {
        try
        {
            stk.push(this.str.toString());
            this.str.insert(offset, str);
        }

        catch(StringIndexOutOfBoundsException e)
        {
            System.err.println("Out of bounds error!");
            e.printStackTrace();
        }

        return this;
    }

    /**
     *
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     * Supports undo method.
     *
     * @param start  The beginning index, inclusive.
     * @param end  The ending index, exclusive.
     * @param str  String that will replace previous contents.
     *
     * @return  a reference to this object.
     *
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        try
        {
            this.stk.push(this.str.toString());
            this.str.replace(start, end, str);
        }

        catch(StringIndexOutOfBoundsException e)
        {
            System.err.println("Out of bounds error!");
            e.printStackTrace();
        }

        catch (NullPointerException e)
        {
            System.err.println("String can't be null!");
            e.printStackTrace();
        }

        return this;
    }

    /**
     *
     * Causes this character sequence to be replaced by the reverse of the
     * sequence. Supports undo method.
     *
     * @return  a reference to this object.
     *
     */
    public UndoableStringBuilder reverse() {
        if (this.str.length() != 0)
        {
            this.stk.push(this.str.toString());
            this.str.reverse();
        }

        return this;
    }

    /**
     *
     * A to string method that returns the current string that's stored.
     *
     * @return String
     *
     */
    public String toString() {
        return this.str.toString();
    }
}
