package Lists;

public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> testList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            testList.add(i + 1);
        }  

        
        printList(testList);
        testList.revert();
        printList(testList);

    }

    public  static <T> void printList(LinkedList<T> list){
        LinkedList.Node node = list.getHead();
        System.out.println();
        while (node != null){
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
        System.out.println();
    }

    
    
}
