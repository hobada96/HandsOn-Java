public class ObjectGraph {
    private Linker linker;

    public ObjectGraph(Linker linker) {
        this.linker = linker;
    }

    public <T> T get(Class<T> key) {
        Factory<T> factory = linker.factoryFor(key);
        return factory.get();
    }
}
