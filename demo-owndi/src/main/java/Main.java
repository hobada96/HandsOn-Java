import java.io.PrintStream;

public class Main {
    public static void main(String... args) {
        ObjectGraph objectGraph = buildObjectGraph();
        VisitHandler visitHandler = objectGraph.get(VisitHandler.class);
        VisitHandler visitHandler2 = objectGraph.get(VisitHandler.class);
    }

    private static ObjectGraph buildObjectGraph() {
        Linker linker = new Linker();
        installFactories(linker);
        return new ObjectGraph(linker);
    }

    private static void installFactories(Linker linker) {

        // 1
        linker.install(VisitHandler.class, new Factory<VisitHandler>() {
            Factory<Counter> counterFactory;
            Factory<Logger> loggerFactory;

            @Override
            public void link(Linker linker) {
                counterFactory = linker.factoryFor(Counter.class);
                loggerFactory = linker.factoryFor(Logger.class);
            }

            @Override
            public VisitHandler get() {
                return new VisitHandler(counterFactory.get(), loggerFactory.get());
            }

        });

        // 2
        linker.install(Logger.class, new Factory<Logger>() {

            Factory<PrintStream> printStreamFactory;

            @Override
            protected void link(Linker linker) {
                printStreamFactory = linker.factoryFor(PrintStream.class);
            }

            @Override
            public Logger get() {
                return new Logger(printStreamFactory.get());
            }
        });

        // 5
        linker.install(Counter.class, SingleFactory.of(new Factory<Counter>() {
            @Override
            public Counter get() {
                return new Counter();
            }
        }));

        // 6
        linker.install(PrintStream.class, ValueFactory.of(System.out));
    }
}
