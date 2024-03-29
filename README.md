# Object-Oriented Programming Course Ex1
### For Computer Science B.Sc. Ariel University

**By Roy Simanovich and Avichay Mezin**

## Description
### Observer Design-Patten
In this assigment we learned about the Observer Design-Pattern
and how we can use it in our programs.

The program we build uses the UndoableStringBuilder class we used
in Ex0, which extends the features of the StringBuilder class to
support the undo function which undo the recent change that was made
to the object using a stack.

In the assignment we have two interfaces which are used for the observer
design-pattern: Member, which is our observer interface and Sender
which is our observable (or subject) interface.

### ConcreteMember
The ConcreteMember class implements the Member interface, and is
our observer. Each ConcreteMember object holds a name and an
UndoableStringBuilder object reference. Once a ConcreteMember object
has been registered to a GroupAdmin object, everytime, the GroupAdmin
object sends an update, it will update its own UndoableStringBuilder
pointer reference to the GroupAdmin's UndoableStringBuilder object,
which means that we use a shallow copy instead of a deep copy. The
class have a contracture which sets its name, an update method and
a toString method to represent the ConcreteMember class as a string.

### GroupAdmin
The GroupAdmin class implements the Sender interface, and is our
observable (or subject). Each GroupAdmin object holds an
UndoableStringBuilder object and an ArrayList object of all
registered members. Once a member has been registered to the
GroupAdmin, it's added to the list of members and for every change
made in the UndoableStringBuilder object, the GroupAdmin object will
notify (via notifyMembers method) to all the registered members that
a change has been made, and it will pass down a reference to its own
UndoableStringBuilder object. The GroupAdmin object support those actions
on its own UndoableStringBuilder object: insert, append, delete and undo.

## JvmUtilities tests (Tests.java)
All the tests were made with the help of JUnit 5.

In this class, we'd tested how our objects interact with
the main memory and how efficient there are. We've used the JvmUtilities
class and put all those tests in the Tests.java file.

## Running
To run the Ex, you must have JDK & Maven installed.

Clone the repository using the follow command:
```
git clone https://github.com/RoySenpai/OOP.Assignment1
```

Then, open the project via IntelliJ and select build -> Rebuild project.
Let the project be rebuilt, and then you can run the Tests.java class to
see the functionality of the project.