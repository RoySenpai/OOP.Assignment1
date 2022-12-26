# Object-Oriented Programming Course Ex1
### For Computer Science B.Sc. Ariel University

**By Roy Simanovich and Avichay Mezin**

## Description
In this assigment we learned about the Observer Design-Pattern
and how we can use it in our programs.

The program we build uses the UndoableStringBuilder class we used
in Ex0, which extends the features of the StringBuilder class to
support the undo function which undo the recent change that made
to the object using a stack.

In the Ex we have two interfaces which are used for the observer
design-pattern: Member, which is our observer interface and Sender
which is our observable (or subject) interface.