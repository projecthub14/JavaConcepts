package com.example.collections.map;

import java.util.Random;

public class Owner {

    private String ownerName;

    public Owner(String ownerName) {
        this.ownerName = ownerName;
    }
     public Owner(){
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        StringBuffer name = new StringBuffer();
         Random random = new Random();
         for (int i = 0; i <6 ; i++) {
             name.append(alphabet.charAt(random.nextInt(N)));
         }
         ownerName = name.toString();

     }



    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return this.ownerName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Owner){
            String ownerName = ((Owner) obj).getOwnerName();
            if(ownerName.equals(this.getOwnerName())){
                return true;
            }
        }
        return false;
    }
}
