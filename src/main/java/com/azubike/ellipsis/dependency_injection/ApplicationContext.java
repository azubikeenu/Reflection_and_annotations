package com.azubike.ellipsis.dependency_injection;

import com.azubike.ellipsis.dependency_injection.annotations.Autowired;
import com.azubike.ellipsis.dependency_injection.annotations.Component;
import com.azubike.ellipsis.dependency_injection.annotations.ComponentScan;
import com.azubike.ellipsis.dependency_injection.annotations.Configuration;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext<T> {
    private static final Map<Class<?>, Object> map = new HashMap<>();

    public ApplicationContext(final Class<T> appConfigClass) {
        Spring.initializeSpringContext(appConfigClass);
    }

    public <V> V getBean(final Class<? extends V> cls) {
        // Gets the stored class from the inMemory storage
        final V obj = (V) map.get(cls);
        // Gets all declared fields from the class to check for dependencies
        Field[] declaredFields = cls.getDeclaredFields();
        injectBeans(obj, declaredFields);
        return obj;
    }

    private static <V> void injectBeans(final V obj, final Field[] declaredFields) {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                final Class<?> fieldType = field.getType();
                final Object fieldObject = map.get(fieldType);
                final Field[] dF = fieldType.getDeclaredFields();
                // Recursively checks for innerDependencies till there are no Autowired extensions present
                injectBeans(fieldObject, dF);
                try {
                    field.set(obj, fieldObject);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    static class Spring {
        public static <T> void initializeSpringContext(final Class<?> appConfigClass) {
            if (!appConfigClass.isAnnotationPresent(Configuration.class)) {
                throw new RuntimeException("Please pass a configuration file");
            }
            // get the componentScan parameter of the AppConfig file
            final ComponentScan componentScanAnnotation = appConfigClass.getAnnotation(ComponentScan.class);
            String packageName = componentScanAnnotation.value();
            // Check for the class files in the target directory , they are usually present after compilation
            String fileStructure = "target/classes/" + packageName.replace(".", "/");
            // Create a file representation of the class files
            File file = new File(fileStructure);
            final File[] classFiles = findClasses(file);
            for (File cFile : classFiles) {
                // the .class extension is replaced with an empty string to enable class loading by reflection
                String qualifiedName = packageName + "." + cFile.getName().replace(".class", "");
                try {
                    final Class<?> loadedClass = Class.forName(qualifiedName);
                    // This only adds classes with the @Component annotation to the in-memory storage
                    if (loadedClass.isAnnotationPresent(Component.class)) {
                        // this creates an instance of the class
                        final Object instance = loadedClass.getConstructor().newInstance();
                        // stores the class name as the key and the created instance as the value
                        map.put(loadedClass, instance);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // This method searches the classes folder recursively and filters files with .class extension
        private static File[] findClasses(File file) {
            if (!file.exists()) {
                throw new RuntimeException("File does not exist");
            }
            return file.listFiles(f -> f.getName().endsWith(".class"));
        }
    }

}

