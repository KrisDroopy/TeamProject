package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    void shouldSumGenreIfOneMoreGameWithOneGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Hotline Miami 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 4);
        player.play(game1, 3);

        int expected = 7;
        int actual = player.sumGenre(game.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    void shouldSumGenreIfOneMoreGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Dead Space", "хоррор");
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Cyberpunk 2077", "Ролевая игра");
        Game game3 = store.publishGame("Hotline Miami 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game, 4);
        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 3);

        int expected = 6;
        int actual = player.sumGenre(game1.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    void shouldPlayInInstalledGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Dead Space", "хоррор");

        Player player = new Player("Petya");
        player.installGame(game);

        player.play(game, 3);

        int actual = player.play(game, 5);
        int expected = 8;

        assertEquals(expected, actual);
    }

    @Test
    void shouldPlayInNotInstalledGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Dead Space", "хоррор");
        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> {
            player.play(game, 3);
        });
    }

    @Test
    void shouldMostPlayerByGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Dead Space", "хоррор");
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Cyberpunk 2077", "Ролевая игра");
        Game game3 = store.publishGame("Hotline Miami 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game, 4);
        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 6);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre("Аркады");

        assertEquals(expected, actual);

    }

    @Test
    void shouldMostPlayerByGenreNotPlayInGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Dead Space", "хоррор");
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Cyberpunk 2077", "Ролевая игра");
        Game game3 = store.publishGame("Hotline Miami 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game, 0);
        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 6);

        assertNull(player.mostPlayerByGenre("хоррор"));
    }
}
