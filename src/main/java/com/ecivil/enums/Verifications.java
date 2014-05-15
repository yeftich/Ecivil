
package com.ecivil.enums;

/**
 *
 * @author Milan
 */
public enum Verifications {
    Verified ("Eπιβεβαιωμένο"),
    Unverified ("Ανεπιβεβαιωμένο");

    private final String name;       

    private Verifications(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }
    public String inGreek(){
        return name;
    }

    @Override
    public String toString(){
       return name;
    }

}
