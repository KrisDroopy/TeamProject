package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class GameStoreTest {


    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldContainsWithoutThisGame() {
        GameStore store = new GameStore();
        GameStore storeForNewGame = new GameStore();

        Game game = store.publishGame("Дикая охота", "экшн");
        Game game1 = storeForNewGame.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertFalse(store.containsGame(game1));
    }

    @Test
    public void shouldContainsGame() {
        GameStore store = new GameStore();

        Game game = store.publishGame("Дикая охота", "экшн");
        Game game1 = store.publishGame("Cyberpunk 2077", "Ролевая игра");
        Game game2 = store.publishGame("Dead Space", "хоррор");
        Game game3 = store.publishGame("Disco Elysium", "детектив");
        Game game4 = store.publishGame("Doom Eternal", "шутер от первого лица");

        assertEquals(true, store.containsGame(game1));
    }


    @Test
    public void shouldGetMostPlayer() {
        GameStore store = new GameStore();

        Game game = store.publishGame("Дикая охота", "экшн");
        Game game1 = store.publishGame("Cyberpunk 2077", "Ролевая игра");
        Game game2 = store.publishGame("Dead Space", "хоррор");
        Game game3 = store.publishGame("Disco Elysium", "детектив");
        Game game4 = store.publishGame("Doom Eternal", "шутер от первого лица");
        Game game5 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        store.addPlayTime("Petr", 27);
        store.addPlayTime("Svetlana", 7);
        store.addPlayTime("Igor", 14);
        store.addPlayTime("Egor", 17);
        store.addPlayTime("Ivan", 11);
        store.addPlayTime("Veronika", 28);

        String expected = "Veronika";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostPlayerMore() {
        GameStore store = new GameStore();

        store.addPlayTime("Petr", 27);
        store.addPlayTime("Svetlana", 10);
        store.addPlayTime("Igor", 14);
        store.addPlayTime("Svetlana", 18);


        String expected = "Svetlana";
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostPlayerWithoutTime() {
        GameStore store = new GameStore();

        store.addPlayTime("Petr", 0);
        store.addPlayTime("Svetlana", 0);
        store.addPlayTime("Igor", 0);


        String expected = null;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSumPlayedTimeMoreWithoutPlayers() {
        GameStore store = new GameStore();

        int expected = 0;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSumPlayedTimeMore() {
        GameStore store = new GameStore();

        store.addPlayTime("Petr", 27);
        store.addPlayTime("Svetlana", 3);
        store.addPlayTime("Igor", 10);

        int expected = 40;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSumPlayedTimeMore1() {
        GameStore store = new GameStore();

        store.addPlayTime("Petr", 27);

        int expected = 27;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
}
