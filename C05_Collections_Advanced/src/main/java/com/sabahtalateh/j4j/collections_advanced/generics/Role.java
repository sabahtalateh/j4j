package com.sabahtalateh.j4j.collections_advanced.generics;

import java.util.Set;
import java.util.TreeSet;

/**
 * Role.
 */
public class Role extends Base {

    private final Set<String> permissions = new TreeSet<>();

    private String name;

    /**
     * @param permission permission.
     */
    public void grant(String permission) {
        this.permissions.add(permission);
    }

    /**
     * @return permission.
     */
    public Set<String> getPermissions() {
        return this.permissions;
    }

    /**
     * @param name name.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return name.
     */
    @Override
    public String getName() {
        return this.name;
    }
}
