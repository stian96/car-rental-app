package no.hiof.groupproject.interfaces;

//easy default method interface to return a boolean value
//compares string length to desired length and returns true if they match
public interface StrLengthCheck {
    default boolean checkLength(String item, int length) {
        return item.length() == length;
    }
}