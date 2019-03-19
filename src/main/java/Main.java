import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        String password = "somepassword";

        int cost = 12;

        // CHOOSE SALT
//        String salt = BCrypt.gensalt();
//        String salt = BCrypt.gensalt(cost);
        String salt = BCrypt.gensalt(cost, generateSecureRandom());

        // HASH
        Instant start = Instant.now();
        String hashedPassword = BCrypt.hashpw(password, salt);
        Instant end = Instant.now();
        System.out.println(hashedPassword);
        System.out.println(String.format("Hashing took: %sms\n", Duration.between(start, end).toMillis()));

        // TEST
        start = Instant.now();
        System.out.println(BCrypt.checkpw("wrongpass", hashedPassword));
        end = Instant.now();
        System.out.println(String.format("Checking took: %sms\n", Duration.between(start, end).toMillis()));
        start = Instant.now();
        System.out.println(BCrypt.checkpw("somepassword", hashedPassword));
        end = Instant.now();
        System.out.println(String.format("Checking took: %sms\n", Duration.between(start, end).toMillis()));

    }

    private static SecureRandom generateSecureRandom() {
        SecureRandom secureRandom = null;
        try {
            // Create a secure random number generator using the SHA1PRNG algorithm
            SecureRandom secureRandomGenerator = SecureRandom.getInstanceStrong();

            // Get 128 random bytes
            byte[] randomBytes = new byte[128];
            secureRandomGenerator.nextBytes(randomBytes);

            // Create two secure number generators with the same seed
            int seedByteCount = 5;
            byte[] seed = secureRandomGenerator.generateSeed(seedByteCount);

            secureRandom = SecureRandom.getInstanceStrong();
            secureRandom.setSeed(seed);

        } catch (NoSuchAlgorithmException e) {
        }
        return secureRandom;
    }
}
