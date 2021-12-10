import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private  int nonce;

    public Block(String previousHash, String data, long timeStamp) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.hash =calculateBlockHash();
    }
    public String calculateBlockHash() {
        MessageDigest digest = null;
        byte[] bytes = null;
        String inputdata = previousHash + data + Long.toString(timeStamp) + Integer.toString(nonce);
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes  = digest.digest(inputdata.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
