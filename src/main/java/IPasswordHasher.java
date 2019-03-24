public interface IPasswordHasher {
    String hashPassword(String password);

    boolean verifyPassword(String password, String hashed);
}
