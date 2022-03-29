import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BolckChainImpl {
    List<Block> blockchain = new ArrayList<>();
    List<Long> timeOfMining = new ArrayList<Long>();
    public static int difficulty = 5;
    String difficultyString = new String(new char[difficulty]).replace('\0', '0');



    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        Block block_1 = new Block("The is a First Block.", "0");
        blockchain.add(block_1);

        System.out.println("Trying to Mine block 1...");
        long startTime1=new Date().getTime();
        block_1.mineBlock(difficulty);
        long endtime1= new Date().getTime();
        timeOfMining.add(endtime1-startTime1);
        System.out.println("Minig Time is : " + (endtime1 - startTime1) + " mili seconds");

        Block block_2 = new Block(block_1.getHash(),"Block 2");
        blockchain.add(block_2);
        System.out.println("Trying to Mine block 2... ");

        long startTime2=new Date().getTime();
        block_2.mineBlock(difficulty);
        long endTime2=new Date().getTime();
        timeOfMining.add(endTime2-startTime2);

        Block block_3 = new Block(block_2.getHash(),"Block 3");
        blockchain.add(block_3);
        System.out.println("Trying to Mine block 3... ");
        long startTime3=new Date().getTime();
        block_3.mineBlock(difficulty);
        long endTime3=new Date().getTime();
        timeOfMining.add(endTime3- startTime3);

        Block block_4 = new Block(block_3.getHash(),"Block 4");
        blockchain.add(block_4);
        System.out.println("Trying to Mine block 4... ");

        long startTime4=new Date().getTime();
        block_4.mineBlock(difficulty);
        long endTime4=new Date().getTime();
        timeOfMining.add(endTime4- startTime4);

        assertTrue(isChainValid());
        System.out.println("Chain is Valid....");
    //  assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
    }
    public  Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.getHash().equals(currentBlock.calculateBlockHash()) ){
                System.out.println("Current Hashes not equal");
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }


        return true;
    }

    public static void main(String[] args){
        BolckChainImpl bolckChainImpl= new BolckChainImpl();
        bolckChainImpl.givenBlockchain_whenNewBlockAdded_thenSuccess();
    }
}
