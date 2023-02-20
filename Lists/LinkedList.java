package Lists;

public class LinkedList<T> {

    private Node head;
    private Node tail;
    public Node getHead() {
        return head;
    }

    class Node {

        private T value;
        private Node nextNode;
        private Node previousNode;

        public void setValue(T value) {
            this.value = value;    
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public void setPreviousNode(Node previousNode) {
            this.previousNode = previousNode;    
        }

        public T getValue(){
            return value;
        }

        public Node getNextNode(){
            return nextNode;
        }

        public Node getPrevNode(){
            return previousNode;
        }
        
    }

    public void add(T value){
        Node node = new Node();
        node.setValue(value);
        if (head != null){
            node.setPreviousNode(tail);
            tail.setNextNode(node);
            
        }
        else {
            head = node;
            
        }
        tail = node;
    }

    public void revert() {

        Node node = head;
        Node temp = head;
        head = tail;
        tail = temp;

        while (node != null) {
            temp = node.nextNode;
            node.nextNode = node.previousNode;
            node.previousNode = temp;
            node = node.previousNode;    
            temp = null;
        }
    }


    
}