package io.github.fbiville;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import io.github.fbiville.configuration.Beans;
import io.github.fbiville.repository.PackagesRepository;

public class Application {

    @Parameter(names = "--app-id", description = "Application ID", required = true, password = true)
    private String appId;

    @Parameter(names = "--app-key", description = "Application key", required = true, password = true)
    private String appKey;

    @Parameter(names = "--host", description = "Host (e.g. http://api.vidal.fr)")
    private String host = "http://api.vidal.fr";

    public static void main(String[] args) {
        Application application = parseArguments(args);
        PackagesRepository repository = beans(application).packagesRepository();
        repository.findAll()
                .stream()
                .forEach(System.out::println);
    }

    private static Application parseArguments(String[] args) {
        Application application = new Application();
        new JCommander(application, args);
        return application;
    }

    private static Beans beans(Application application) {
        return new Beans(
                application.getHost(),
                application.getAppId(),
                application.getAppKey()
        );
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getHost() {
        return host;
    }
}
