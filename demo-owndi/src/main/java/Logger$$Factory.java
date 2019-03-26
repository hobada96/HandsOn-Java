import java.io.PrintStream;


public class Logger$$Factory extends GeneratedFactory<Logger> {
    Factory<PrintStream> printStreamFactory;

    protected Logger$$Factory() {
        super(false);
    }

    @Override
    protected void link(Linker linker) {
        printStreamFactory = linker.factoryFor(PrintStream.class);
    }

    @Override
    public Logger get() {
        return new Logger(printStreamFactory.get());
    }
}
