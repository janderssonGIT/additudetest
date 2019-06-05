package com.additude.model;

public class Synonym {

	private int synid;
	private String strval;
	
	public Synonym(int synid, String name) {
		this.synid = synid;
		this.strval = name;
	}

	public int getSynid() {
		return synid;
	}
	
	public void setSynid(int synid) {
		this.synid = synid;
	}

    public String getStrval() {
        return strval;
    }

    public void setStrval(String name) {
        this.strval = name;
    }
}