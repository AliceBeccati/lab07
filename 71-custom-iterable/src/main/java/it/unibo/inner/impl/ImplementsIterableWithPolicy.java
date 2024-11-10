package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplementsIterableWithPolicy<T> implements IterableWithPolicy<T>{
    private final List<T> array;
    private Predicate<T> predicate;

    public ImplementsIterableWithPolicy(final T[] array, final Predicate<T> predicate){
        this.array = List.of(array);
        this.predicate = predicate;
    }

    public ImplementsIterableWithPolicy(final T[] array){
        //this.array = List.of(array);
        this(array, new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }
            
        });
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerClass();
    }

    class InnerClass implements Iterator<T>{
        private final int postion;

        public InnerClass(){
            this.postion = 0;
        }

        @Override
        public boolean hasNext() {
            
            for(int i = (this.postion + 1); i < array.size(); i++){
                if(predicate.test(array.get(i))){
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            for(int i = (this.postion + 1); i < array.size(); i++){
                if(predicate.test(array.get(i))){
                    return array.get(i);
                }
            }
            throw new NoSuchElementException();
        }

    }
}

