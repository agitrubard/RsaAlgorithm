import java.math.BigInteger;

/**
 * -------- Base RSA Algorithm --------
 * message : " "
 * p : Prime Number
 * q : Prime Number
 * N : p * q
 * temp :  (p-1) * (q-1)
 *
 * --- public key ---
 * e : 3 or 5 or 2^16 + 1 or ...
 *
 * --- private key ---
 * d : e^-1 mod(temp)
 *
 * m : (Big Integer) message
 * c : Encrypted Message
 *
 * --- encryption ---
 * c = m^e mod(N)
 *
 * --- decryption ---
 * m = c^d mod(N)
 */
public class Main {
    private static final String MESSAGE_1 = "69";
    private static final String MESSAGE_2 = "Hello RSA Algorithm!";

    public static void main(String[] args) {
        BasicRsa.encryption(MESSAGE_1);
        Rsa.encryption(MESSAGE_2);
    }

    public static void printValues(BigInteger p, BigInteger q, BigInteger N, BigInteger temp, BigInteger e,
            BigInteger d) {
        System.out.println("p : " + p);
        System.out.println("q : " + q);
        System.out.println("N : " + N);
        System.out.println("Temp : " + temp);
        System.out.println("e : " + e);
        System.out.println("d : " + d);
    }
}
