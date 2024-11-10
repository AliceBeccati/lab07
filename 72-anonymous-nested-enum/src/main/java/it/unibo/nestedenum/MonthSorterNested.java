package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new sortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return sortByOrder();
    }

    public enum Month{
        GENNAIO(31),
        FEBBRAIO(28),
        MARZO(31),
        APRILE(30),
        MAGGIO(31),
        GIUGNO(30),
        LUGLIO(31),
        AGOSTO(31),
        SETTEMBRE(30),
        OTTOBRE(31),
        NOVEMBRE(30), 
        DICEMBRE(31);

        private final int days;

        private Month(final int days){
            this.days = days;
        }

        public int getNumDays(){
            return this.days;
        }

        public static Month fromString(final String str){
            try{
                return valueOf(str.toUpperCase());
            }catch(IllegalArgumentException e){
                List<Month> guessMonth = new ArrayList<>();
                for(Month month: Month.values()){
                    if(month.toString().toUpperCase().startsWith(str.toUpperCase())){
                        guessMonth.add(month);
                    }
                }

                if(guessMonth.size() == 1){
                    return guessMonth.getFirst();
                }
                else{
                    throw new IllegalArgumentException(str + " fits with different moths: " + guessMonth.toString());
                }
            }
        }
    }

    public class sortByDays implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);
            return m1.compareTo(m2);
        }
    }

    public class sortByOrder implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);
            return Integer.compare(m1.days, m2.days);
        }

    }
}
