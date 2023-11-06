package hackerank;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SimpleTextEditor {

    private static class Change {
        private boolean isDelete;
        private Object value;

        public Change(boolean isDelete, Object value) {
            this.isDelete = isDelete;
            this.value = value;
        }

        public boolean isDelete() {
            return isDelete;
        }

        public Object getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ops = Integer.parseInt(scanner.nextLine()); // number of operation
        StringBuilder sb = new StringBuilder();
        Deque<Change> undoS = new ArrayDeque<>(ops);
        undoS.push(new Change(false, ""));
        String[] parts;
        char op;
        int toDelete;
        for (int i = 0; i < ops; i++) {
            parts = scanner.nextLine().split(" ");
            op = parts[0].charAt(0);
            switch (op) {
                case '1':
                    undoS.push(new Change(false, parts[1].length()));
                    sb.append(parts[1]);
                    break;
                case '2':
                    toDelete = Integer.parseInt(parts[1]);
                    undoS.push(new Change(true, sb.substring(sb.length() - toDelete)));
                    sb.setLength(sb.length() - toDelete);
                    break;
                case '3':
                    System.out.println(sb.charAt(Integer.parseInt(parts[1]) - 1));
                    break;
                case '4':
                    Change change = undoS.pop();
                    if (change.isDelete()) {
                        sb.append(change.getValue());
                    } else {
                        sb.setLength(sb.length() - (int) change.getValue()); // undo append
                    }
                    break;
                default:
                    continue;
            }
        }
        scanner.close();
    }
}
