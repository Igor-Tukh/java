package ru.spbau.mit.tukh.hw07;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * MyTreeSetImpl is implementation of the MyTreeSet interface. Based on BST.
 *
 * @param <E> is type of containing object.
 */
public class MyTreeSetImpl<E> extends AbstractSet<E> implements MyTreeSet<E> {
    private Comparator<E> cmp;

    public MyTreeSetImpl() {
        cmp = (e, t1) -> {
            try {
                Comparable<E> cmp = (Comparable<E>) e;
                return cmp.compareTo(t1);
            } catch (ClassCastException exp) {
                throw exp;
            }
        };
    }

    public MyTreeSetImpl(Comparator<E> comparator) {
        cmp = comparator;
    }

    private static class Node<E> {
        private E val;
        private Node<E> l, r, p;

        Node(E value) {
            val = value;
        }
    }

    private Node<E> root;
    private int size;

    private Node<E> addNode(Node<E> cur, E value, Node<E> p) {
        if (cur == null) {
            Node<E> ans = new Node<>(value);
            ans.p = p;
            return ans;
        }

        if (cmp.compare(value, cur.val) < 0) {
            cur.l = addNode(cur.l, value, cur);
            return cur;
        } else if (cmp.compare(value, cur.val) == 0) {
            return cur;
        }

        cur.r = addNode(cur.r, value, cur);
        return cur;
    }

    /**
     * Method add adds new value to the set.
     *
     * @param value is adding to the set value.
     * @return if there wasn't such element in the set.
     */
    @Override
    public boolean add(E value) {
        if (contains(value)) {
            return false;
        }

        if (root == null) {
            root = new Node<>(value);
        } else {
            root = addNode(root, value, null);
        }
        size++;

        return true;
    }

    @Override
    public boolean contains(Object o) {
        try {
            E e = (E) o;

            Node<E> cur = root;
            while (cur != null) {
                int res = cmp.compare(cur.val, e);
                if (res < 0) {
                    cur = cur.r;
                } else if (res == 0) {
                    return true;
                } else {
                    cur = cur.r;
                }
            }
        } catch (ClassCastException e) {
            return false;
        }

        return false;
    }

    private boolean removeNode(Node<E> cur, Object o) {
        if (cur == null) {
            return false;
        }

        try {
            E e = (E) o;

            int res = cmp.compare(cur.val, e);
            if (res < 0) {
                return removeNode(cur.l, e);
            } else if (res > 0) {
                return removeNode(cur.r, e);
            }

            if (cur.l == null && cur.r == null) {
                if (cur.p != null) {
                    if (cur.p.l == cur) {
                        cur.p.l = null;
                    } else {
                        cur.p.r = null;
                    }
                } else {
                    root = null;
                }
                return true;
            }

            if (cur.r == null) {
                if (cur.p != null) {
                    if (cur.p.l == cur) {
                        cur.p.l = cur.l;
                    } else {
                        cur.p.r = cur.l;
                    }
                } else {
                    root = cur.l;
                }
                return true;
            }

            if (cur.l == null) {
                if (cur.p != null) {
                    if (cur.p.l == cur) {
                        cur.p.l = cur.r;
                    } else {
                        cur.p.r = cur.r;
                    }
                } else {
                    root = cur.r;
                }
                return true;
            }
        } catch (ClassCastException e) {
            return false;
        }
        return true;
    }

    /**
     * Remove method erases given object from the set if it was there.
     * @param o is value to erase.
     * @return if there was such element in the set.
     */
    @Override
    public boolean remove(Object o) {
        boolean ans = removeNode(root, o);
        if (ans) {
            size--;
        }
        return ans;
    }

    /**
     * Iterator (increasing order).
     *
     * @return new increasing order iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    /**
     * Size is number of elements in the set (set doesn't contains equal elements).
     *
     * @return size of set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Iterator (decreasing order).
     *
     * @return new decreasing order iterator.
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingTreeIterator();
    }

    /**
     * DescendingSet returns reversed view to this set. Modifications of each of this and descending sets modifies both
     * of them.
     *
     * @return descending view.
     */
    @Override
    public MyTreeSet<E> descendingSet() {
        return new DescendingSet();
    }

    private Node<E> getFirst() {
        Node<E> cur = root;

        while (cur != null) {
            if (cur.l != null) {
                cur = cur.l;
            } else {
                break;
            }
        }

        return cur;
    }

    /**
     * First is the first element in the increasing order.
     *
     * @return first element in the set.
     */
    @Override
    public E first() {
        Node<E> left = getFirst();
        if (left != null) {
            return left.val;
        } else {
            return null;
        }
    }

    private Node<E> getLast() {
        Node<E> cur = root;

        while (cur != null) {
            if (cur.r != null) {
                cur = cur.r;
            } else {
                break;
            }
        }

        return cur;
    }

    /**
     * Last is the last element in the decreasing order.
     *
     * @return last element in the set.
     */
    @Override
    public E last() {
        Node<E> right = getLast();
        if (right != null) {
            return right.val;
        } else {
            return null;
        }
    }

    private E getLower(Node<E> cur, E e) {
        if (cur == null) {
            return null;
        }

        if (cmp.compare(cur.val, e) >= 0) {
            return getLower(cur.l, e);
        }

        E ans = getLower(cur.r, e);
        if (ans == null) {
            return cur.val;
        }

        return ans;
    }

    /**
     * lower method finds the greatest element, which is lower the given value.
     *
     * @param e is value to search.
     * @return the greatest element, which is lower the given value.
     */
    @Override
    public E lower(E e) {
        return getLower(root, e);
    }

    private E getLowerOrEqual(Node<E> cur, E e) {
        if (cur == null) {
            return null;
        }

        if (cmp.compare(cur.val, e) > 0) {
            return getLowerOrEqual(cur.l, e);
        }

        E ans = getLowerOrEqual(cur.r, e);
        if (ans == null) {
            return cur.val;
        }

        return ans;
    }

    /**
     * floor method finds the greatest element, which is lower than or equal to the given value.
     *
     * @param e is value to search.
     * @return the greatest element, which is lower than or equal to the given value.
     */
    @Override
    public E floor(E e) {
        return getLowerOrEqual(root, e);
    }

    private E getGreaterOrEqual(Node<E> cur, E e) {
        if (cur == null) {
            return null;
        }

        if (cmp.compare(cur.val, e) < 0) {
            return getGreaterOrEqual(cur.r, e);
        }

        E ans = getGreaterOrEqual(cur.l, e);
        if (ans == null) {
            return cur.val;
        } else {
            return ans;
        }
    }

    /**
     * floor method finds the lowest element, which is greater than or equal to the given value.
     *
     * @param e is value to search.
     * @return the lowest element, which is greater than or equal to the given value.
     */
    @Override
    public E ceiling(E e) {
        return getGreaterOrEqual(root, e);
    }

    private E getGreater(Node<E> cur, E e) {
        if (cur == null) {
            return null;
        }

        if (cmp.compare(cur.val, e) <= 0) {
            return getGreater(cur.r, e);
        }

        E ans = getGreater(cur.l, e);
        if (ans == null) {
            return cur.val;
        } else {
            return ans;
        }
    }

    /**
     * floor method finds the lowest element, which is greater than given value.
     *
     * @param e is value to search.
     * @return the lowest element, which is greater than given value.
     */
    @Override
    public E higher(E e) {
        return getGreater(root, e);
    }

    private Node<E> getFirstNode() {
        if (root == null) {
            return null;
        }

        Node<E> cur = root;
        while (cur.l != null) {
            cur = cur.l;
        }

        return cur;
    }

    private Node<E> getLastNode() {
        if (root == null) {
            return null;
        }

        Node<E> cur = root;
        while (cur.r != null) {
            cur = cur.r;
        }

        return cur;
    }

    private Node<E> next(Node<E> cur) {
        if (cur == null) {
            return null;
        }

        Node<E> ans = cur.r;
        if (cur.r != null) {
            while (ans.l != null) {
                ans = ans.l;
            }
        } else {
            if (cur.p == null) {
                return null;
            }
            while (cur.p.r == cur) {
                if (cur.p == root) {
                    return null;
                }
                cur = cur.p;
            }

            ans = cur.p;
        }

        System.err.println();

        return ans;
    }

    private Node<E> prev(Node<E> cur) {
        if (cur == null) {
            return null;
        }

        Node<E> ans = cur.l;
        if (cur.l != null) {
            while (ans.r != null) {
                ans = ans.r;
            }
        } else {
            if (cur.p == null) {
                return null;
            }

            while (cur.p != null && cur.p.l == cur) {
                if (cur.p == root) {
                    return null;
                }
                cur = cur.p;
            }

            ans = cur.p;
        }

        return ans;
    }

    /**
     * implementation of the increasing order iterator.
     */
    public class TreeIterator implements Iterator<E> {
        private Node<E> pos;

        TreeIterator() {
            pos = MyTreeSetImpl.this.getFirstNode();
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() {
            if (pos != null) {
                E ans = pos.val;
                pos = MyTreeSetImpl.this.next(pos);
                return ans;
            }
            return null;
        }
    }

    /**
     * implementation of the decreasing order iterator.
     */
    public class DescendingTreeIterator implements Iterator<E> {
        private Node<E> pos;

        DescendingTreeIterator() {
            pos = MyTreeSetImpl.this.getLastNode();
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() {
            if (pos != null) {
                E ans = pos.val;
                pos = MyTreeSetImpl.this.prev(pos);
                return ans;
            }
            return null;
        }
    }

    /**
     * implementation of the DescendingSet. Behaviour of all methods fits behaviour of the reversed view.
     */
    private class DescendingSet extends AbstractSet<E> implements MyTreeSet<E> {
        @Override
        public boolean add(E value) {
            return MyTreeSetImpl.this.add(value);
        }

        /*
        @Override
        public boolean remove(Object o){
            return MyTreeSetImpl.this.remove(o);
        }*/

        @Override
        public Iterator<E> iterator() {
            return MyTreeSetImpl.this.descendingIterator();
        }

        @Override
        public int size() {
            return MyTreeSetImpl.this.size();
        }

        @Override
        public Iterator<E> descendingIterator() {
            return MyTreeSetImpl.this.iterator();
        }

        @Override
        public MyTreeSet<E> descendingSet() {
            return MyTreeSetImpl.this;
        }

        @Override
        public E first() {
            return MyTreeSetImpl.this.last();
        }

        @Override
        public E last() {
            return MyTreeSetImpl.this.first();
        }

        @Override
        public E lower(E e) {
            return MyTreeSetImpl.this.higher(e);
        }

        @Override
        public E floor(E e) {
            return MyTreeSetImpl.this.ceiling(e);
        }

        @Override
        public E ceiling(E e) {
            return MyTreeSetImpl.this.floor(e);
        }

        @Override
        public E higher(E e) {
            return MyTreeSetImpl.this.lower(e);
        }
    }
}
