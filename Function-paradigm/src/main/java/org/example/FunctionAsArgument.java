package org.example;

import javax.lang.model.type.NullType;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionAsArgument {
    public static void register(String userName, Consumer<String> callBack){
        // some logic
        callBack.accept(userName + "@domain.com");
    }
    public static void main(String[] args){
        Consumer<String> sendWelcomeMail = (mail) -> System.out.println("Mailing " + mail);

        register("sersawy", sendWelcomeMail);
    }
}
