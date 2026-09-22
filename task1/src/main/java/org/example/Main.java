public class Main {
    public static String getPath(int n, int m) {
        String path = "";
        int current = 1;

        while (true) {
            path += current;

            current = (current + m - 1) % n;
            if (current == 0) current = n;

            if (current == 1) break;
        }

        return path;
    }

    public static void main(String[] args) {
        String result = "";

        for (int i = 0; i < args.length; i += 2) {
            int n = Integer.parseInt(args[i]);
            int m = Integer.parseInt(args[i + 1]);
            result += getPath(n, m);
        }

        System.out.println(result);
    }
}