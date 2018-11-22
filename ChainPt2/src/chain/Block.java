/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

import java.util.Date;

import java.util.ArrayList;


/**
 *
 * @author a120091
 */
public class Block {

    private String hash;
    private String data;
    private String hashanterior;
    private long time;
    private int nonce;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    
   
    
    
    /*public Block(String data , String hashAnterior){
        this.data = data;
        this.hashanterior = hashAnterior;
        this.time = new Date().getTime();
        this.hash = calculateHash();
    }*/
    
    public Block(String hashanterior ) {
		this.hashanterior = hashanterior;
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
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
    
    public boolean addTransaction(Transaction transaction) {
		
		if(transaction == null) return false;		
		if((hashanterior != "0")) {
			if((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}
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
}


