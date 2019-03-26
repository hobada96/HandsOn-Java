import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Linker {

    private final Map<Class<?>, Factory<?>> factories = new HashMap<>();
    private final Map<Class<?>, Factory<?>> linkedFactories = new HashMap<>();

    public <T> void install(Class<T> key, Factory<T> factory) {
        factories.put(key, factory);
    }

    public <T> Factory<T> factoryFor(Class<T> key) {
        System.out.println("get factory for" + key);
        Factory<?> factory = linkedFactories.get(key);

        if (factory == null) {
            System.out.println("link factory for" + key);
            factory = loadFactory(key);
            factory.link(this);
            linkedFactories.put(key, factory);
        }

        return (Factory<T>) factory;
    }

    private <T> Factory<?> loadFactory(Class<T> key) {

        Factory<?> factory = factories.get(key);

        if (factory != null) {
            return factory;
        }

        factory = GeneratedFactory.loadFactory(key);

        if (factory != null) {
            return factory;
        }

        throw new IllegalStateException("No factory for" + key);
    }

    private <T> Constructor<T> findAtInjectConstructor(Class<T> type) {
        for (Constructor<?> constructor : type.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }
}
