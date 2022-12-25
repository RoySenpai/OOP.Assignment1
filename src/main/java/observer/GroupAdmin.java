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
            obj.update(usb);
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
    }

    @Override
    public void append(String obj) {
        this.usb.append(obj);
    }

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
    }

    @Override
    public void undo() {
        this.usb.undo();
    }

    @Override
    public String toString() {
        String ret = "Members:\n";

        for (Member m: this.membersList)
            ret += m.toString() + "\n";

        return ret;
    }
}
