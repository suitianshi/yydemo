package com.talk51.dasheng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

public class WeakContainer<E> implements Iterable<E> {

    private final WeakHashMap<E, Object> mMap = new WeakHashMap<E, Object>();
    private final Object mValue = new Object();

    public void add(E obj) {
        if (obj== null) {
            // trigger WeakHashMap.poll()
            mMap.size();
            return;
        }
        mMap.put(obj, mValue);
    }

    public void clear() {
        mMap.clear();
    }

    public boolean isEmpty() {
        return mMap.isEmpty();
    }

    public int size() {
        return mMap.size();
    }

    public void remove(E obj) {
        if (obj == null) {
            // trigger WeakHashMap.poll()
            mMap.size();
            return;
        }
        mMap.remove(obj);
    }

    /**
     * return a read-only iterator.
     */
    @Override
    public Iterator<E> iterator() {
        ArrayList<E> list = new ArrayList<E>(mMap.size());
        for (E key: mMap.keySet()) {
            if (key != null)
                list.add(key);
        }
        return list.iterator();
    }
}
