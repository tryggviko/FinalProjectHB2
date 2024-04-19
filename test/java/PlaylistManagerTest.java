import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vinnsla.Playlist;
import vinnsla.PlaylistManager;
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
This test class creates a test playlists and then puts them in the playlist folder witch
stores all the playlist (along with the already existing user playlists) and then
it deletes them when the test is done.
 */

public class PlaylistManagerTest {

    private List<Playlist> testPlaylists;

    //test playlists created
    @Before
    public void setUp() {
        testPlaylists = new ArrayList<>();
        Playlist playlist1 = new Playlist("Test Playlist 11231231332131234123143132124");
        Playlist playlist2 = new Playlist("Test Playlist 112312313321312341231431321423");
        testPlaylists.add(playlist1);
        testPlaylists.add(playlist2);
    }

    //tested together because they kind of do the same thing in the back end
    @Test
    public void testSaveAndLoadPlaylists() {
        PlaylistManager.savePlaylists(testPlaylists);
        File[] playlistFiles = new File(PlaylistManager.PLAYLISTSFOLDER).listFiles();
        assertNotNull("Playlist folder should't be null", playlistFiles);

        // the tests that go through the folder and count
        int expectedPlaylistCount = 0;
        for (Playlist playlist : testPlaylists) {
            String expectedFileName = playlist.getName() + ".ser";
            boolean fileFound = false;
            for (File file : playlistFiles) {
                if (file.getName().equals(expectedFileName)) {
                    fileFound = true;
                    expectedPlaylistCount++;
                    break;
                }
            }
            assertTrue("Playlist file not found: " + expectedFileName, fileFound);
        }
        assertEquals("Number of playlists found should match number of test playlists", testPlaylists.size(), expectedPlaylistCount);
    }



    @Test
    public void testRemovePlaylist() {
        // Test removing the playlists
        PlaylistManager.savePlaylists(testPlaylists);
        Playlist playlistToRemove = testPlaylists.get(0);
        PlaylistManager.removePlaylist(playlistToRemove);
        List<Playlist> remainingPlaylists = PlaylistManager.loadPlaylists();
        assertFalse("Removed playlist should shoudl't be in loaded playlists", remainingPlaylists.contains(playlistToRemove));
    }

    // the cleanup for deleting the test playlists
    @After
    public void deletePlaylists() {
        File[] playlistFiles = new File(PlaylistManager.PLAYLISTSFOLDER).listFiles();
        if (playlistFiles != null) {
            for (File file : playlistFiles) {
                if (file.isFile() && file.getName().startsWith("Test Playlist") && file.getName().endsWith(".ser")) {
                    assertTrue("Failed to delete playlist file: " + file.getName(), file.delete());
                }
            }
        }
    }


}
