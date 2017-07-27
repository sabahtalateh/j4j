package com.sabahtalateh.j4j.collections_advanced.generics;

/**
 * @param <T> Type.
 */
public class CommonStore<T extends Base> implements Store<T> {

    private SimpleArray<T> store = new SimpleArray<>();

    /**
     * @param element element.
     */
    @Override
    public void add(T element) {
        this.store.add(element);
    }

    /**
     * @param element element.
     */
    @Override
    public void delete(T element) {
        this.store.delete(element);
    }

    /**
     * @param element element.
     */
    @Override
    public void update(T element) {
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId().equals(element.getId())) {
                this.store.update(i, element);
            }
        }
    }

    /**
     * @param id id.
     * @return element.
     */
    @Override
    public T findById(String id) {
        return this.store.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
