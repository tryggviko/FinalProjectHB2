import org.junit.Before;
import org.junit.Test;
import vinnsla.Playlist;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlaylistTest {

    private Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist("Test Playlist");
    }

    @Test
    public void testGetName() {
        assertEquals("Test Playlist", playlist.getName());
    }

    @Test
    public void testSetName() {
        playlist.setName("New Playlist Name");
        assertEquals("New Playlist Name", playlist.getName());
    }

    @Test
    public void testAddSong() {
        playlist.addSong("song1.mp3");
        playlist.addSong("song2.mp3");
        assertEquals(2, playlist.getSongPaths().size());
    }

    @Test
    public void testSetSongPaths() {
        List<String> songs = Arrays.asList("song1.mp3", "song2.mp3");
        playlist.setSongPaths(songs);
        assertEquals(songs, playlist.getSongPaths());
    }
}
