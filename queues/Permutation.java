import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("require input");
        }
        RandomizedQueue<String> sQ = new RandomizedQueue<String>();

        int pNum = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            sQ.enqueue(s);
        }
        int c = 0;
        for (String s : sQ) {
            if (c == pNum) {
                break;
            }
            System.out.println(s);
            c++;
        }
    }
}
