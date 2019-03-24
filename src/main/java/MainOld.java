import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

public class MainOld {
    public static void main(String[] args) {
        String password = "somepassword";

        int cost = 12;

        // SALT
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
            SecureRandom secureRandomGenerator = SecureRandom.getInstanceStrong();

            byte[] randomBytes = new byte[128];
            secureRandomGenerator.nextBytes(randomBytes);

            int seedByteCount = 5;
            byte[] seed = secureRandomGenerator.generateSeed(seedByteCount);

            secureRandom = SecureRandom.getInstanceStrong();
            secureRandom.setSeed(seed);

        } catch (NoSuchAlgorithmException e) {
        }
        return secureRandom;
    }
}
