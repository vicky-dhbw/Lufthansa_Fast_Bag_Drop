package security;

public interface IEncryptAlgorithm {
    byte[] encrypt(String message) throws Exception;
    String decrypt(byte[] message)throws Exception;
}
