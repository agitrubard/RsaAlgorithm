import java.math.BigInteger;

/**
 * -------- Basic RSA Algorithm --------
 * message : "69"
 * p : 7
 * q : 13
 * N : p * q = 91
 * temp : (p-1) * (q-1) = 72
 * <p>
 * --- public key ---
 * e : 5
 * <p>
 * --- private key ---
 * d : e^-1 mod(temp) = 5^-1 mod(72) = 29
 * <p>
 * m : (Big Integer) message
 * c : Encrypted Message
 * <p>
 * --- encryption ---
 * c = m^e mod(N) = 69^5 mod(91) = 62
 * <p>
 * --- decryption ---
 * m = c^d mod(N) = 62^29 mod(91) = 69
 */
public class BasicRsa {
    private static final BigInteger e = BigInteger.valueOf(5);

    public static void encryption(String message) {
        BigInteger p = BigInteger.valueOf(7);
        BigInteger q = BigInteger.valueOf(13);
        BigInteger N = p.multiply(q);
        BigInteger temp = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(temp);

        BigInteger encryptedMessage = getEncryptedMessage(message, N);
        BigInteger decryptedMessage = getDecryptedMessage(encryptedMessage, d, N);

        print(message, encryptedMessage, decryptedMessage, p, q, N, temp, d);
    }

    private static void print(
            String message, BigInteger encryptedMessage, BigInteger decryptedMessage,
            BigInteger p, BigInteger q, BigInteger N, BigInteger temp, BigInteger d) {

        System.out.println("\n\n************* BASIC RSA *************");
        Main.printValues(p, q, N, temp, e, d);
        printMessages(message, encryptedMessage, decryptedMessage);
    }

    private static BigInteger getEncryptedMessage(String message, BigInteger N) {
        BigInteger m = BigInteger.valueOf(Long.parseLong(message));
        return m.modPow(e, N);
    }

    private static BigInteger getDecryptedMessage(BigInteger c, BigInteger d, BigInteger N) {
        return c.modPow(d, N);
    }

    private static void printMessages(String message, BigInteger encryptedMessage, BigInteger decryptedMessage) {
        System.out.println("");
        System.out.println("Message : " + message);
        System.out.println("EncryptedMessage : " + encryptedMessage);
        System.out.println("DecryptedMessage : " + decryptedMessage);
    }
}
