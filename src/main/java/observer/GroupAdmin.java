package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ArrayList<Member> membersList = new ArrayList<>();

    @Override
    public void register(Member obj) {
        if (!membersList.contains(obj))
        {
            this.membersList.add(obj);
            System.out.println("Added member.");
        }
    }

    @Override
    public void unregister(Member obj) {
        if (membersList.contains(obj))
        {
            this.membersList.remove(obj);
            System.out.println("Removed member.");
        }
    }

    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notifyMembers();
    }

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notifyMembers();
    }

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        notifyMembers();
    }

    @Override
    public void undo() {
        this.usb.undo();
        notifyMembers();
    }

    private void notifyMembers() {
        for (Member m: this.membersList)
            m.update(this.usb);
    }

    @Override
    public String toString() {
        String ret = "Members:\n";

        for (Member m: this.membersList)
            ret += m.toString() + "\n";

        return ret;
    }
}
