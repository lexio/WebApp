/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.gcm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public final class Datastore {

    private static final List<String> regIds = new ArrayList<String>();
    private static final Logger logger =
            Logger.getLogger(Datastore.class.getName());

    private Datastore() {
        throw new UnsupportedOperationException();
    }

    /**
     * Registers a device.
     */
    public static void register(String regId) {
        logger.info("Registering " + regId);
        synchronized (regIds) {
            regIds.add(regId);
        }
    }

    /**
     * Unregisters a device.
     */
    public static void unregister(String regId) {
        logger.info("Unregistering " + regId);
        synchronized (regIds) {
            regIds.remove(regId);
        }
    }

    /**
     * Updates the registration id of a device.
     */
    public static void updateRegistration(String oldId, String newId) {
        logger.info("Updating " + oldId + " to " + newId);
        synchronized (regIds) {
            regIds.remove(oldId);
            regIds.add(newId);
        }
    }

    /**
     * Gets all registered devices.
     */
    public static List<String> getDevices() {
        synchronized (regIds) {
            return new ArrayList<String>(regIds);
        }
    }
}
