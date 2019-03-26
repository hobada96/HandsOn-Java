public abstract class GeneratedFactory<T> extends Factory<T> {

    private final boolean singleton;

    protected GeneratedFactory(boolean singleton) {
        this.singleton = singleton;
    }

    public static <T> Factory<T> loadFactory(Class<T> key) {
        String generatedClass = key.getName() + "$$Factory";
        try {
            Class<GeneratedFactory<T>> factoryClass = (Class<GeneratedFactory<T>>) Class.forName(generatedClass);
            GeneratedFactory<T> factory = factoryClass.newInstance();
            if (factory.singleton) {
                return SingleFactory.of(factory);
            } else {
                return factory;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
