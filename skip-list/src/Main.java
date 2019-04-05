public class Main {

    public static void main(String[] args) {
        SkipList<Integer, String> skipList = new SkipList<>();
        for (int i = 0; i < 10; i++) {
            skipList.add(i, String.valueOf(i));
        }
        System.out.println("Key is 2 and value is "+skipList.get(2));
        System.out.println("Key is 8 and value is "+skipList.get(8));
        for (int i = 0; i < 10; i++) {
            skipList.remove(i);
        }
        System.out.println("Remove all elements of skiplist...");
        System.out.println("SkipList size :"+skipList.size());
        System.out.println("SkipList isEmpty :"+skipList.empty());

    }
}
