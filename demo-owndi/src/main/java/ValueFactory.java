public final class ValueFactory<T> extends Factory<T> {

    private final T value;

    private ValueFactory(T value) {
        this.value = value;
    }

    public static <T> Factory<T> of(T instance) {
        return new ValueFactory<T>(instance);
    }

    @Override
    public T get() {
        return null;
    }
}
