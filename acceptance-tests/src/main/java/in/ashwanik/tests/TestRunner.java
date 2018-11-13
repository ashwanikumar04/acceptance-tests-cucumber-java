package in.ashwanik.tests;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.commons.cli.*;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Cucumber-JVM, runs the feature files using a JUnit Runner. We can use other test Runners as well (like TestNG).
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = "in.ashwanik.tests",
        plugin = {
                "pretty",
                "html:build/reports/cucumber",
                "json:build/test/cucumber.json"
        },
        tags = {"@runTest"}   //only feature files with this tag will be run
)
public class TestRunner {
    private static String ENV = "local";

    public static void main(String[] args) throws Throwable {
        Options options = buildOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp("acceptance-tests", options);
            System.exit(1);
            return;
        }

        List<String> cucumberArgs = setOptions(cmd);

        boolean runOnlySmokeTests = cmd.hasOption("st");
        if (runOnlySmokeTests) {
            cucumberArgs.add("-t");
            cucumberArgs.add("@smoke-test");
        }

        byte exitStatus = cucumber.api.cli.Main
                .run(cucumberArgs
                        .toArray(new String[cucumberArgs.size()]), Thread
                        .currentThread().getContextClassLoader());
        System.exit(exitStatus);
    }

    private static List<String> setOptions(CommandLine cmd) {
        ENV = cmd.getOptionValue("env", "local");
        String nameSpaceValue = cmd.getOptionValue("n");
        String featureFileLocationValue = cmd.getOptionValue("f");

        List<String> cucumberArgs = new ArrayList<>();
        cucumberArgs.add("-g");
        cucumberArgs.add(nameSpaceValue);
        cucumberArgs.add(featureFileLocationValue);
        cucumberArgs.add("-p");
        cucumberArgs.add("pretty");
        cucumberArgs.add("-p");
        cucumberArgs.add("html:reports");
        return cucumberArgs;
    }

    private static Options buildOptions() {
        Options options = new Options();

        Option st = new Option("st", "Run only smoke tests");
        options.addOption(st);

        Option env = new Option("env", "environment", true, "Environment to run against");
        env.setRequired(false);
        options.addOption(env);

        Option namespace = new Option("n", "namespace", true, "Cucumber namespace");
        namespace.setRequired(true);
        options.addOption(namespace);

        Option featureFileLocation = new Option("f", "feature", true, "Feature file location");
        featureFileLocation.setRequired(true);
        options.addOption(featureFileLocation);
        return options;
    }
}