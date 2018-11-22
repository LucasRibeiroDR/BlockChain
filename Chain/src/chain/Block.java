/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

import java.util.Date;



/**
 *
 * @author a120091
 */
public class Block {

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHashanterior() {
        return hashanterior;
    }

    public void setHashanterior(String hashanterior) {
        this.hashanterior = hashanterior;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    private String hash;
    private String data;
    private String hashanterior;
    private long time;
    private int nonce;
    
    public Block(String data , String hashAnterior){
        this.data = data;
        this.hashanterior = hashAnterior;
        this.time = new Date().getTime();
        this.hash = calculateHash();
    }
    
    public String calculateHash() {
	String calculatedhash = StringUtil.applySha256( 
            hashanterior +
            Long.toString(time) +
            Integer.toString(nonce) +
            data 
            );
	return calculatedhash;
    }
    
    public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
    
}


