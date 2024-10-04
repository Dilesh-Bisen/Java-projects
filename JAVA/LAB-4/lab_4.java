import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

public class lab_4 {
    public static void main(String[] args) {

        System.out.println("\nArrays in JAVA:");
        int[] array = { 11, 22, 33, 44, 55 };
        System.out.println(Arrays.toString(array));


        System.out.println("\nArrayList:");
        ArrayList<String> array_list = new ArrayList<>();
        array_list.add("RED");
        array_list.add("BLUE");
        array_list.add("GREEN");
        array_list.add("PURPLE");
        array_list.add("WHITE");
        System.out.println(array_list);


        System.out.println("\nLinkedList:");
        LinkedList<Integer> linked_list = new LinkedList<>();
        linked_list.add(1);
        linked_list.add(2);
        linked_list.add(3);
        linked_list.add(4);
        linked_list.add(5);
        System.out.println(linked_list);


        System.out.println("\nHashMap:");
        HashMap<String, Integer> hash_map = new HashMap<>();
        hash_map.put("Dilesh", 100000000);
        hash_map.put("Kapil", 30000);
        hash_map.put("Ajay", 45000);
        System.out.println(hash_map);

        
        System.out.println("\nHashSet:");
        HashSet<Integer> hash_set = new HashSet<>();
        hash_set.add(34);
        hash_set.add(58);
        hash_set.add(78);
        hash_set.add(66);
        hash_set.add(47);
        System.out.println(hash_set);

    }
}
