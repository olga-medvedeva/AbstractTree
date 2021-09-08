import java.io.Serializable;
import java.util.*;



public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        this.root = new Entry<>("0");
    }

    @Override
    public boolean add(String s) {
        Entry<String> lastEntry = findEmptyNode();
        if (lastEntry != null) {
            Entry<String> newEntry = new Entry<>(s);
            newEntry.parent = lastEntry;
            if (lastEntry.leftChild == null) {
                lastEntry.leftChild = newEntry;
            } else {
                lastEntry.rightChild = newEntry;
            }
            return true;
        }
        return false;
    }

    private Entry<String> findEmptyNode() {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> newNode;
        Entry<String> result = null;

        do {
            newNode = queue.remove();
            if (newNode.leftChild != null) {
                queue.add(newNode.leftChild);
            } else {
                result = newNode;
            }
            if (newNode.rightChild != null) {
                queue.add(newNode.rightChild);
            } else {
                result = newNode;
            }
        } while (result == null);
        return result;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        Entry<String> parent = getParentEntry(o.toString());
        if (parent.leftChild != null && parent.leftChild.elementName.equals(o.toString())) {
            parent.leftChild = null;
            return true;
        }
        if (parent.rightChild != null && parent.rightChild.elementName.equals(o.toString())) {
            parent.rightChild = null;
            return true;
        }
        return false;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return countingSize(root) - 1;
    }

    private int countingSize(Entry<String> entry) {
        int count = 1;
        if (entry.leftChild != null) {
            count = count + countingSize(entry.leftChild);
        }
        if (entry.rightChild != null) {
            count = count + countingSize(entry.rightChild);
        }
        return count;
    }

    public String getParent(String s) {
        Entry<String> parent = getParentEntry(s);
        return parent == null ? null : parent.elementName;
    }

    private Entry<String> getParentEntry(String s) {
        Stack<Entry<String>> stack = new Stack<>();
        stack.push(root);
        Entry<String> newNode;
        Entry<String> result = null;

        do {
            newNode = stack.pop();
            if (newNode.leftChild != null) {
                if (!newNode.leftChild.elementName.equals(s)) {
                    stack.push(newNode.leftChild);
                } else {
                    result = newNode;
                    break;
                }
            }
            if (newNode.rightChild != null) {
                if (!newNode.rightChild.elementName.equals(s)) {
                    stack.push(newNode.rightChild);
                } else {
                    result = newNode;
                    break;
                }
            }
        } while (!stack.isEmpty());
        return result;
    }


    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        Entry(String name) {
            elementName = name;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }


        public boolean isAvailableToAddChildren() {
            return this.availableToAddRightChildren || this.availableToAddLeftChildren;
        }
    }
}
