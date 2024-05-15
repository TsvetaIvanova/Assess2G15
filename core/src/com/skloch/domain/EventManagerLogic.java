package com.skloch.domain;

import java.util.Arrays;
import java.util.HashMap;

public class EventManagerLogic {
    private final GameState gameState;
    public HashMap<String, Integer> activityEnergies;
    private int hours;

    public EventManagerLogic(GameState gameState) {
        this.gameState = gameState;
        activityEnergies = new HashMap<>();
        activityEnergies.put("studying", 10);
        activityEnergies.put("meet_friends", 10);
        activityEnergies.put("eating", 10);
    }

    public void event(String eventKey) {
        String[] args = eventKey.split("-");
        switch (args[0]) {
            case "piazza":
                piazzaEvent(args);
                break;
            case "comp_sci":
                compSciEvent(args);
                break;
            case "rch":
                ronCookeEvent(args);
                break;
            case "accommodation":
                accomEvent(args);
                break;
            case "ducks":
                duckEvent();
                break;
            case "fishing":
                fishingEvent();
                break;
            case "catch_up":
                catchUpEvent(args);
                break;
            default:
                break;
        }
    }

    public void piazzaEvent(String[] args) {
        int energyCost = activityEnergies.get("meet_friends");
        if (gameState.getEnergy() < energyCost) {
            // Too tired to meet friends
        } else if (args.length == 1) {
            // Fixed duration for testing
            event("piazza-2");
        } else {
            // Chatted about the topic for a fixed 2 hours
            int hours = 2;
            gameState.decreaseEnergy(energyCost * hours);
            gameState.passTime(hours * 60);
            gameState.addRecreationalHours(hours);
        }
    }

    public void compSciEvent(String[] args) {
        int energyCost = activityEnergies.get("studying");
        if (gameState.getEnergy() < energyCost) {
            // Too tired to study
        } else if (args.length == 1) {
            // Fixed duration for testing
            event("comp_sci-2");
        } else {
            hours = Integer.parseInt(args[1]);
            if (gameState.getEnergy() < hours * energyCost) {
                // Not enough energy to study
            } else {
                int[] prevDaysStudied = Arrays.copyOfRange(gameState.getDaysStudied(), 0, gameState.getDaysStudied().length - 1);
                if (Arrays.stream(prevDaysStudied).noneMatch(x -> x == 0) || gameState.isCatchUp()) {
                    gameState.decreaseEnergy(energyCost * hours);
                    gameState.addStudyHours(hours);
                    gameState.getDaysStudied()[gameState.getDaysStudied().length - 1] += hours;
                    gameState.passTime(hours * 60);
                } else {
                    // Handle catch-up scenario
                    event("catch_up-1");
                }
            }
        }
    }

    public void ronCookeEvent(String[] args) {
        int energyCost = activityEnergies.get("eating");
        if (gameState.getEnergy() < energyCost) {
            // Too tired to eat
        } else {
            gameState.decreaseEnergy(energyCost);
            gameState.passTime(60);
            gameState.addEatingScore(10); // Simplified eating score logic
        }
    }

    public void accomEvent(String[] args) {
        // Simplified sleep logic
        gameState.setEnergy(100); // Assume full energy restoration
    }

    public void duckEvent() {
        int energyCost = activityEnergies.getOrDefault("ducks", 10);
        if (gameState.getEnergy() >= energyCost) {
            gameState.decreaseEnergy(energyCost);
            gameState.passTime(30);
            gameState.addRecreationalHours(1);
        }
    }

    public void fishingEvent() {
        int energyCost = activityEnergies.getOrDefault("fishing", 10);
        if (gameState.getEnergy() >= energyCost) {
            gameState.decreaseEnergy(energyCost);
            gameState.passTime(60);
            gameState.addRecreationalHours(1);
        }
    }

    public void catchUpEvent(String[] args) {
        int energyCost = activityEnergies.get("studying");
        if (Integer.parseInt(args[1]) == 1) {
            gameState.decreaseEnergy(energyCost * hours);
            gameState.addStudyHours(hours);
            for (int i = 0; i < gameState.getDaysStudied().length; i++) {
                if (gameState.getDaysStudied()[i] == 0) {
                    gameState.getDaysStudied()[i] += 1;
                    gameState.getDaysStudied()[gameState.getDaysStudied().length - 1] += hours - 1;
                    gameState.setCatchUp(true);
                    break;
                }
            }
            gameState.passTime(hours * 60);
        } else if (Integer.parseInt(args[1]) == 2) {
            gameState.decreaseEnergy(energyCost * hours);
            gameState.addStudyHours(hours);
            gameState.getDaysStudied()[gameState.getDaysStudied().length - 1] += hours;
            gameState.passTime(hours * 60);
        }
    }
}
