package com.example.threadingexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@SpringBootApplication
public class ThreadingExampleApplication {

    //Közös erőforrás:
    private static final Set<String> sharedSet = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        SpringApplication.run(ThreadingExampleApplication.class, args);
        // Két szál létrehozása, egy amely hozzáad, és egy amely eltávolít elemeket
        Thread addThread = new Thread(new AddTask());
        Thread removeThread = new Thread(new RemoveTask());

        // Szálak indítása
        addThread.start();
        removeThread.start();

        try {
            // Szálak csatlakoztatása (várakozás a befejezésükre)
            addThread.join();
            removeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // A végső halmaz tartalmának kiíratása
        System.out.println("Final set contents: " + sharedSet);
    }

    // Feladat, amely elemeket ad a halmazhoz
    static class AddTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (sharedSet) {
                    sharedSet.add("Element " + i);
                    System.out.println("Added: Element " + i);
                    try {
                        System.out.println("Added: is sleeping ... ");
                        Thread.sleep(100); // Rövid várakozás a szimuláció kedvéért
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Feladat, amely elemeket távolít el a halmazból
    static class RemoveTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (sharedSet) {
                    if (!sharedSet.isEmpty()) {
                        // A halmazból egy elemet eltávolítunk, de mivel a halmaz nem biztosít sorrendet,
                        // először át kell alakítanunk egy listává, hogy eltávolíthassuk az első elemet.
                        String element = sharedSet.iterator().next();
                        sharedSet.remove(element);
                        System.out.println("Removed: " + element);
                    } else {
                        System.out.println("Set is empty, cannot remove element.");
                    }
                    try {
                        System.out.println("Removed: is sleeping ... ");
                        Thread.sleep(150); // Rövid várakozás a szimuláció kedvéért
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
