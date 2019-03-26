public class SingleFactory<T> extends Factory {

    private final Factory<T> factory;

    private SingleFactory(Factory<T> factory) {
        this.factory = factory;
    }

    public static <T> Factory<T> of(Factory<T> factory) {
        return new SingleFactory<>(factory);
    }

    @Override
    public T get() {
        return factory.get();
    }
}
