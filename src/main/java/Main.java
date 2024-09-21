import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        List<String> names = Arrays.asList("Jake", "Bob", "Charlie", "David", "Eve");

        // 7 stream operations with terminal and non-terminal operations
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .sorted()
                .distinct()
                .peek(System.out::println)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Filtered Names: " + filteredNames);

        try {
            // Loading the class with reflection
            Class<?> cls = Class.forName("Person");

            // Getting class fields constructors and methods
            System.out.println("Fields:");
            for (Field field : cls.getDeclaredFields()) {
                System.out.println("Name: " + field.getName() + ", Type: " + field.getType());
            }

            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : cls.getConstructors()) {
                System.out.println("Constructor: " + constructor);
                System.out.println("Parameters: " + Arrays.toString(constructor.getParameterTypes()));
            }

            System.out.println("\nMethods:");
            for (Method method : cls.getDeclaredMethods()) {
                System.out.println("Method: " + method.getName());
                System.out.println("Return Type: " + method.getReturnType());
                System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));
                System.out.println("Parameters: " + Arrays.toString(method.getParameterTypes()));
            }

            // creating an instance of the class
            Constructor<?> constructor = cls.getConstructor(String.class, int.class);
            Object personObj = constructor.newInstance("Jimmy", 25);

            // calling a method using reflection
            Method setNameMethod = cls.getDeclaredMethod("setName", String.class);
            setNameMethod.invoke(personObj, "Timmy");

            // Getting the updated name
            Method getNameMethod = cls.getDeclaredMethod("getName");
            String name = (String) getNameMethod.invoke(personObj);
            System.out.println("\nUpdated Name: " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
