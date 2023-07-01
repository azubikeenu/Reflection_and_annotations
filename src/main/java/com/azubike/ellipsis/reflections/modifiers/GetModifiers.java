package com.azubike.ellipsis.reflections.modifiers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GetModifiers {
    public static void main(String[] args) throws Exception{
        Entity entity  = new Entity(10, "id");
        final Class<? extends Entity> aClass = entity.getClass();
        final int classModifier = aClass.getModifiers();
        System.out.println((classModifier & Modifier.PUBLIC) ==1); // true

        final Method getVal = aClass.getMethod("getVal");
        final int methodMod = getVal.getModifiers();
        System.out.println((methodMod & Modifier.PRIVATE ) ); //0


        /////////////////////////////
        // Using Static methods from Modifier class
        /////////////////////////////////////
        System.out.println(Modifier.isAbstract(classModifier)); // false
        System.out.println(Modifier.isPrivate(methodMod)); // false

        /////////////////////////////////////////////
        // to Get the String value of the modifier
        ////////////////////////////////////////////

        System.out.println(Modifier.toString(classModifier)); // public
        System.out.println(Modifier.toString(methodMod)); // public

    }
}
