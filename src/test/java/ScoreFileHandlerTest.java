import com.studentlogger.*;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreFileHandlerTest {

    private static final String TEST_FILE = "test_scores.txt";

    @AfterEach
    void cleanUp() throws IOException {
    // Delete the test file if it exists
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    void testSingleRecordWrite() throws IOException {
    // Create a ScoreFileHandler instance
        ScoreFileHandler handler = new ScoreFileHandler(TEST_FILE);

    // Create a sample student record
        StudentRecord record = new StudentRecord("TestStudent", 999, 95.6);

    // Write the record to the file
        handler.logRecord(record);

    // Read the file contents
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE));

    // Verify the record was written correctly
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("TestStudent"));
    }

    @Test
    void testMultiThreadedWrites() throws InterruptedException, IOException {
    // Create a ScoreFileHandler instance
        ScoreFileHandler handler = new ScoreFileHandler(TEST_FILE);

    // Create two student records and submission tasks
        StudentRecord record1 = new StudentRecord("X", 1, 80.0);
        StudentRecord record2 = new StudentRecord("Y", 2, 90.0);
        StudentSubmissionTask task1 = new StudentSubmissionTask(record1, handler);
        StudentSubmissionTask task2 = new StudentSubmissionTask(record2, handler);

    // Create and start two threads for the tasks
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();

    // Wait for both threads to finish
        t1.join();
        t2.join();

    // Read the file contents
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE));

    // Verify at least two records were written
        assertTrue(lines.size() >= 2);
    }
}