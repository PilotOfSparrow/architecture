package datasource;

import java.util.List;

interface Repository<T> {
    void add(T item);

    void update(T item);

    void remove(T item);

    T get(int id);

    List<T> query();
}
