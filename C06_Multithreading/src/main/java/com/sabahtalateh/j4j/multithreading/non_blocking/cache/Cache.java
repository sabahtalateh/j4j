package com.sabahtalateh.j4j.multithreading.non_blocking.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache.
 */
@ThreadSafe
public class Cache {

    private ConcurrentHashMap<String, Model> models = new ConcurrentHashMap<>();

    /**
     * @param key   key.
     * @param model model.
     * @return is added.
     */
    public boolean add(String key, Model model) {
        return models.putIfAbsent(key, model) != model;
    }

    /**
     * @param key key.
     * @return is deleted.
     */
    public boolean delete(String key) {
        return models.remove(key) != null;
    }

    /**
     * @param key   key.
     * @param model model.
     * @return is updated.
     * @throws OptimisticUpdateException exception.
     */
    public boolean update(String key, Model model) throws OptimisticUpdateException {
        boolean updated = false;
        Model oldModel = models.get(key);
        if (oldModel != null) {
            int newVersion = oldModel.version + 1;

            Model newModel = new Model(model.getName(), newVersion);
            models.put(key, newModel);

            Model newModelFromStorage = models.get(key);
            if (newModelFromStorage == null || newModelFromStorage.version != newVersion) {
                throw new OptimisticUpdateException("Model was updated from other thread.");
            }
            updated = true;

        }
        return updated;
    }

    /**
     * @param key key.
     * @return model.
     */
    public Model get(String key) {
        return models.get(key);
    }

}
