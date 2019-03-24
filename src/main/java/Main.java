public class Main {

    public static void main(String[] args) {

        IPasswordHasher bcryptPasswordHasher = new BcryptPasswordHasher();

        String candidate = "kalle";

        // CASE 1 - PLAINTEXT
        String passFromDb = "kalle";
        int hashType = 0;
        System.out.println(HashType.values()[hashType].verifyStuff(candidate, passFromDb) && saveToDb(passFromDb, 1));

        // CASE 2 - HASHED
        passFromDb = "$2a$12$jLXVS6NTkebmF2P5XNA9O.r8aZQlIfQo64bJNihP2kqb3X3pHKi3.";
        hashType = 1;
        System.out.println(HashType.values()[hashType].verifyStuff(candidate, passFromDb) && saveToDb(passFromDb, 1));
    }

    private static boolean saveToDb(String password, int hashType) {
        System.out.println(String.format("Saving to DB: password=%s, hashType=%d", password, hashType));
        return true;
    }
}
