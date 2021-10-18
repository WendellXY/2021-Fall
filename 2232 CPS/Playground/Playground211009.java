import java.util.ArrayList;
import java.util.Collections;

class MyPriorityQueue<E extends Comparable<E>> {
    private ArrayList<E> list = new ArrayList<>();

    public void enqueue(E newObject) {
        list.add(newObject);
        Collections.sort(this.list);
    }

    public E dequeue() {
        E removed = (E) list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return removed;
    }

    public int getSize() {
        return list.size();
    }
}

public class Playground211009 {
    public static void main(String[] args) {
        Movie movie1 = new Movie("A", 2.0, 3.0, 2011, 2, 1);
        Movie movie2 = new Movie("B", 3.0, 2.0, 2011, 2, 1);
        Movie movie3 = new Movie("C", 4.0, 1.0, 2011, 1, 1);
        Movie movie4 = new Movie("D", 5.0, 0.0, 2012, 1, 1);
        Movie movie5 = new Movie("E", 5.0, 1.0, 2012, 1, 1);
        Movie movie6 = new Movie("F", 5.0, 0.0, 2012, 2, 1);
        Movie movie7 = new Movie("G", 5.0, 1.0, 2012, 2, 1);

        MyPriorityQueue<Movie> priorityQueue = new MyPriorityQueue<>();

        priorityQueue.enqueue(movie1);
        priorityQueue.enqueue(movie2);
        priorityQueue.enqueue(movie3);
        priorityQueue.enqueue(movie4);
        priorityQueue.enqueue(movie5);
        priorityQueue.enqueue(movie6);
        priorityQueue.enqueue(movie7);

        while (priorityQueue.getSize() > 0) {
            System.out.println(priorityQueue.dequeue());
        }
    }

    static class Movie implements Comparable<Movie> {
        String name;
        double rate;
        double popularity;
        int releaseYear;
        int releaseMonth;
        int releaseDay;

        public Movie(
            String name,
            double rate,
            double popularity,
            int releaseYear,
            int releaseMonth,
            int releaseDay
        ) {
            this.name = name;
            this.rate = rate;
            this.popularity = popularity;
            this.releaseYear = releaseYear;
            this.releaseMonth = releaseMonth;
            this.releaseDay = releaseDay;
        }

        @Override
        public String toString() {
            return String.format("%s rate: %.1f, pop: %.1f, %d-%d-%d", name, rate, popularity, releaseYear, releaseMonth, releaseDay);
        }

        @Override
        public int compareTo(Movie movie) {
            int ret = 0;

            ret += 10000 * Double.compare(this.rate, movie.rate);
            ret += 1000 * Double.compare(this.popularity, movie.popularity);
            ret += 100 * Integer.compare(this.releaseYear, movie.releaseYear);
            ret += 10 * Integer.compare(this.releaseMonth, movie.releaseMonth);
            ret += 1 * Integer.compare(this.releaseDay, movie.releaseDay);

            return ret;
        }
    }
}