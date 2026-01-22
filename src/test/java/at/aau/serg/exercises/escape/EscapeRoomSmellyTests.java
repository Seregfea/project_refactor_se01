package at.aau.serg.exercises.escape;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EscapeRoomSmellyTests {
    static EscapeRoomVault vault;
    static InMemoryAuditLog audit;

    static Clock clock = Clock.systemDefaultZone();
    static Random random = new Random();

    @BeforeAll
    static void init() {
        audit = new InMemoryAuditLog();

        Config cfg = new Config(Duration.ofSeconds(45), 3, Duration.ofSeconds(30));
        vault = new EscapeRoomVault(cfg, clock, random, audit);
    }
    /**
     * @Test
    
    void test1() throws Exception {
    // Issue a token for the team
    Token token = vault.issueToken("Team Rocket", "skull");

    // Attempt to unlock with the token
    boolean unlocked = vault.attemptUnlock("Team Rocket", token, token.getCode());

    // Verify the unlock was successful
    assertTrue(unlocked, "Vault should unlock with the correct token and code");

    // Verify that an audit event was recorded
    assertFalse(audit.events().isEmpty(), "Audit should have recorded at least one event");
}

     * @throws Exception
     */
    @Test
    void test1() throws Exception {
        /*
        Using Thread.sleep in this case can cause flaky tests, 
        slow test execution, and inaccurate test results. 
        It creates brittle tests that can fail unpredictably depending on 
        the environment or load. */
        Thread.sleep(5);

        Token token = vault.issueToken("Team Rocket", "skull");
        boolean ok = vault.attemptUnlock("Team Rocket", token, token.getCode());

        assertTrue(ok || !ok);
        assertNotNull(ok);

        assertTrue(audit.events().size() > 0);
    }

    @Test
    void giantEverythingTest() {
        Token token = vault.issueToken("Team A", "pharaoh");

        boolean a = vault.attemptUnlock("Team A", token, "WRONG");
        boolean b = vault.attemptUnlock("Team A", token, "ALSO WRONG");
        boolean c = vault.attemptUnlock("Team A", token, "NOPE");

        assertFalse(a);
        assertFalse(b);
        assertFalse(c);

        boolean d = vault.attemptUnlock("Team A", token, token.getCode());
        assertEquals(false, d);

        assertTrue(vault.failedAttempts() >= 0);
        assertNotNull(vault.lockoutUntil());
    }

    @Test
    void randomFuzz() {
        Random r = new Random();
        String team = "Team-" + r.nextInt(9999);
        String theme = r.nextBoolean() ? "skull" : "mummy";

        Token token = vault.issueToken(team, theme);
        String typed = r.nextBoolean() ? token.getCode() : "garbage";
        boolean ok = vault.attemptUnlock(team, token, typed);

        assertTrue(ok == true || ok == false);
    }

    /**
     * @Test
    void testThrow() {
    // This test verifies that issuing a token with an empty team name throws an exception
        assertThrows(Exception.class, () -> vault.issueToken("", "skull"),
            "Issuing a token with an empty team name should throw an exception");
}
            No empty try-catch.
            The test now explicitly asserts that an exception is thrown.
            Provides a clear message for future maintainers.

     */
    @Test
    void testThrow() {
        try {
            vault.issueToken("", "skull");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void expiryTest() {
        Token expired = new Token("SKULL-0001", Instant.now().minusSeconds(1));
        boolean ok = vault.attemptUnlock("Team Z", expired, "SKULL-0001");
        assertTrue(ok || !ok);
    }

    @Test
    void auditStringChecks() {
        Token token = vault.issueToken("Team B", "skull");
        String details = audit.events().get(audit.events().size() - 1).getDetails();

        // Overspecified + weak checks
        assertTrue(details.contains("code=" + token.getCode()));
        assertTrue(details.contains("expiresAt="));
        assertTrue(details.length() > 10);
    }

    @AfterEach
    void teardown() {
    }
}
