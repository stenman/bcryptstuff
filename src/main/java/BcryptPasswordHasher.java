import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptPasswordHasher implements IPasswordHasher {
    @Override
    public String hashPassword(String password) {
        int cost = 12;
        String salt = BCrypt.gensalt(cost);
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public boolean verifyPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
