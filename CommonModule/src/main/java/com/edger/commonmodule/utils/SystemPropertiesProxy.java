package com.edger.commonmodule.utils;

import java.lang.reflect.Method;

public class SystemPropertiesProxy {
    /**
     * This class cannot be instantiated
     */
    private SystemPropertiesProxy() {
        throw new RuntimeException("This class cannot be instantiated");
    }

    /**
     * Get the value for the given key.
     *
     * @return an empty string if the key isn't found
     * @exception IllegalArgumentException if the key exceeds 32 characters
     */
    public static String get(String key) throws IllegalArgumentException {
        String ret = "";
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Class[] paramTypes = new Class[1];
            paramTypes[0] = String.class;
            Method get = systemProperties.getMethod("get", paramTypes);
            //Parameters
            Object[] params = new Object[1];
            params[0] = new String(key);
            ret = (String) get.invoke(systemProperties, params);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            ret = "";
        }

        return ret;

    }

    /**
     * Get the value for the given key.
     *
     * @return if the key isn't found, return def if it isn't null, or an empty string otherwise
     * @exception IllegalArgumentException if the key exceeds 32 characters
     */
    public static String get(String key, String def) throws IllegalArgumentException {
        String ret = def;
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = String.class;
            Method get = systemProperties.getMethod("get", paramTypes);
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new String(def);
            ret = (String) get.invoke(systemProperties, params);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            ret = def;
        }

        return ret;

    }

    /**
     * Get the value for the given key, and return as an integer.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as an integer, or def if the key isn't found or
     * cannot be parsed
     * @exception IllegalArgumentException if the key exceeds 32 characters
     */
    public static Integer getInt(String key, int def) throws IllegalArgumentException {
        Integer ret = def;
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = systemProperties.getMethod("getInt", paramTypes);
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Integer(def);
            ret = (Integer) getInt.invoke(systemProperties, params);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            ret = def;
        }

        return ret;

    }

    /**
     * Get the value for the given key, and return as a long.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a long, or def if the key isn't found or
     * cannot be parsed
     * @exception IllegalArgumentException if the key exceeds 32 characters
     */
    public static Long getLong(String key, long def) throws IllegalArgumentException {
        Long ret = def;
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = long.class;
            Method getLong = systemProperties.getMethod("getLong", paramTypes);
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Long(def);
            ret = (Long) getLong.invoke(systemProperties, params);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            ret = def;
        }
        return ret;
    }

    /**
     * Get the value for the given key, returned as a boolean.
     * Values 'n', 'no', '0', 'false' or 'off' are considered false.
     * Values 'y', 'yes', '1', 'true' or 'on' are considered true.
     * (case insensitive).
     * If the key does not exist, or has any other value, then the default
     * result is returned.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a boolean, or def if the key isn't found or is
     * not able to be parsed as a boolean.
     * @exception IllegalArgumentException if the key exceeds 32 characters
     */
    public static Boolean getBoolean(String key, boolean def) throws IllegalArgumentException {
        Boolean ret = def;
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = boolean.class;
            Method getBoolean = systemProperties.getMethod("getBoolean", paramTypes);
            //Parameters
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Boolean(def);
            ret = (Boolean) getBoolean.invoke(systemProperties, params);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            ret = def;
        }
        return ret;
    }

    /**
     * Set the value for the given key.
     *
     * @exception IllegalArgumentException if the key exceeds 32 characters
     * @exception IllegalArgumentException if the value exceeds 92 characters
     */
    public static void set(String key, String val) throws IllegalArgumentException {
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = String.class;
            Method set = systemProperties.getMethod("set", paramTypes);
            //Parameters
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new String(val);
            set.invoke(systemProperties, params);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
