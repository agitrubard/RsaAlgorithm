import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * -------- RSA Algorithm --------
 * message : "Hello World"
 * Bit Length : 1024
 * p : Generated Random Prime Number
 * q : Generated Random Prime Number
 * N : p * q
 * temp : (p-1) * (q-1)
 * <p>
 * --- public key ---
 * e : 2^16 + 1
 * <p>
 * --- private key ---
 * d : e^-1 mod(temp)
 * <p>
 * m : (Big Integer) message
 * c : Encrypted Message
 * <p>
 * --- encryption ---
 * c = m^e mod(N)
 * <p>
 * --- decryption ---
 * m = c^d mod(N)
 */
public class Rsa {
    private static final Integer BIT_LENGTH = 1024;
    private static final BigInteger e = BigInteger.valueOf(2).pow(16).subtract(BigInteger.ONE);

    public static void encryption(String message) {
        BigInteger p = getRandomPrimeNumber();
        BigInteger q = getRandomPrimeNumber();
        BigInteger N = p.multiply(q);
        BigInteger temp = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d;
        while (true) {
            try {
                d = e.modInverse(temp);
                break;
            } catch (ArithmeticException arithmeticException) {
                p = getRandomPrimeNumber();
                q = getRandomPrimeNumber();
                N = p.multiply(q);
                temp = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            }
        }
        BigInteger encryptedMessage = getEncryptedMessage(message, N);
        BigInteger decryptedMessage = getDecryptedMessage(encryptedMessage, d, N);

        print(message, encryptedMessage, decryptedMessage, p, q, N, temp, d);
    }

    private static void print(
            String message, BigInteger encryptedMessage, BigInteger decryptedMessage,
            BigInteger p, BigInteger q, BigInteger N, BigInteger temp, BigInteger d) {

        System.out.println("\n\n**************** RSA ****************");
        Main.printValues(p, q, N, temp, e, d);
        printMessages(message, encryptedMessage, decryptedMessage);
    }

    private static BigInteger getEncryptedMessage(String message, BigInteger N) {
        BigInteger m = new BigInteger(message.getBytes());
        return m.modPow(e, N);
    }

    private static BigInteger getDecryptedMessage(BigInteger c, BigInteger d, BigInteger N) {
        return c.modPow(d, N);
    }

    private static BigInteger getRandomPrimeNumber() {
        return BigInteger.probablePrime(BIT_LENGTH, new SecureRandom());
    }

    private static void printMessages(String message, BigInteger encryptedMessage, BigInteger decryptedMessage) {
        System.out.println("");
        System.out.println("Message : " + message);
        System.out.println("EncryptedMessage : " + encryptedMessage);
        System.out.println("DecryptedMessage : " + new String(decryptedMessage.toByteArray()));
    }
}
