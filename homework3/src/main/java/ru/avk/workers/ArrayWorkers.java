package ru.avk.workers;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayWorkers implements Iterable<Worker>{
    private final Worker[] workers;

    public ArrayWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    @Override
    public Iterator<Worker> iterator() {
        return new  WorkerIterator();
    }

    private class WorkerIterator implements Iterator<Worker> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < workers.length;
        }

        @Override
        public Worker next() {
            Worker w = workers[i];
            i++;
            return w;
        }
    }
}
