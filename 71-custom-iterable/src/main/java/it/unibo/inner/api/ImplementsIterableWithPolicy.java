package it.unibo.inner.api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImplementsIterableWithPolicy<T> implements IterableWithPolicy<T>{
    private final List<T> array;

    public ImplementsIterableWithPolicy(T[] array){
        this.array = List.of(array);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerClass();
    }

    class InnerClass implements Iterator<T>{
        private int postion;

        public InnerClass(){
            this.postion = 0;
        }

        @Override
        public boolean hasNext() {
            if(this.postion < array.size()){
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        public T next() {
            return array.get(++this.postion);
        }

    }
}

