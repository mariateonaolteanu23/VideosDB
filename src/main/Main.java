package main;

import action.Action;
import checker.Checker;
import checker.Checkstyle;
import common.Constants;
import common.Database;
import common.Helpers;
import factory.ActionFactory;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            System.out.println(file.toString());
            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        // Load database
        Helpers.addMovies(input.getMovies());
        Helpers.addShows(input.getSerials());
        Helpers.addUsers(input.getUsers());
        Helpers.addActors(input.getActors());

        //  Update views and favorite picks for every video
        Helpers.videoViewsHandler(Database.getDatabase().getAllVideos());
        Helpers.videoFavoriteHandler(Database.getDatabase().getAllVideos());

        //  Update wins for every actor
        Helpers.actorWinsHandler(Database.getDatabase().getActors());

        //  Execute all actions specified by the input
        for (ActionInputData actionInputData : input.getCommands()) {
            //  Create action
            Action action = ActionFactory.createAction(actionInputData);
            assert action != null;

            //  Execute action
            //  Get action result
            String actionResult = action.executeAction();
            try {
                //  Add the action result/output
                arrayResult.add(fileWriter.writeFile(
                        actionInputData.getActionId(),
                        null,
                        actionResult));
                //  Catch exception
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  Write in file
        fileWriter.closeJSON(arrayResult);
    }
}
