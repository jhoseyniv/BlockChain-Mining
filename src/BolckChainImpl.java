import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BolckChainImpl {
    List<Block> blockchain = new ArrayList<>();
    int prefix = 4;
    String prefixString = new String(new char[prefix]).replace('\0', '0');



    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        Block block1 = new Block("The is a First Block.", "The is a First Block.", new Date().getTime());
        blockchain.add(block1);

        Block newBlock = new Block(block1.calculateBlockHash(), blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        newBlock.mineBlock(prefix);
        assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
        blockchain.add(newBlock);
    }


    public static void main(String[] args){
        BolckChainImpl bolckChainImpl= new BolckChainImpl();
        bolckChainImpl.givenBlockchain_whenNewBlockAdded_thenSuccess();
    }
}
