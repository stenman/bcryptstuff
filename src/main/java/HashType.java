public enum HashType {

    PLAINTEXT(0) {
        @Override
        boolean verifyStuff(String candidate, String password) {
            System.out.println(String.format("Verifying candidate=%s against password=%s", candidate, password));
            return password.equals(candidate);
        }
    },

    BCRYPT2A12(1) {
        @Override
        boolean verifyStuff(String candidate, String password) {
            System.out.println(String.format("Verifying candidate=%s against password=%s", candidate, password));
            return bcryptPasswordHasher.verifyPassword(candidate, password);
        }
    };

    IPasswordHasher bcryptPasswordHasher = new BcryptPasswordHasher();

    HashType(int i) {
    }

    abstract boolean verifyStuff(String candidate, String password);
}
