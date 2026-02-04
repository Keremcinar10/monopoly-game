package cs102final;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;


public class singlyLinkedLies {
    
    private Node head;

    private class Node {
        Object data;
        Node next;
    }

    public void addLast(Object value) {
        
        Node newNode = new Node();
        newNode.data = value;

        if(head == null) {
            head = newNode;
            return ;
        }

        Node current = head;
        while(current.next != null) {
            current = current.next;
        }
        newNode = current.next;
    }
    public void modify(LinkedList<String> list){
        
        ListIterator<String> iter = list.listIterator(); 

        while (iter.hasNext()) {

            if(iter.next().equals("and")) {
                iter.add("then");
            }
            else if(iter.next().equals("the")) {
                iter.remove();
            }
        }
    }

    public void processList(LinkedList<Integer> list){
        
        ListIterator<Integer> iter = list.listIterator();

    while (iter.hasNext()) {
        int current = iter.next();

        // Check for duplicate pair
        if (iter.hasNext()) {
            int next = iter.next();
            if (current == next) {
                iter.remove(); // remove second duplicate
                iter.previous(); // go back to first duplicate
                iter.previous();
                iter.remove(); // remove first duplicate
                continue; // move to next iteration
            } else {
                iter.previous(); // go back to just after current
            }
        }

        // Insert (n + 1) after odd numbers
        if (current % 2 == 1) {
            iter.add(current + 1);
        }
    }
      

    }
}
