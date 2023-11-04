import java.util.List;
import java.util.ArrayList;

public class Parser {
    public String commandName;
    public List<String> options;
    public String[] args;

    public Parser() {
        options = new ArrayList<>();
    }

    public boolean parse(String input) {
        String[] tokens = input.split("\\s+");
        List<String> argsList = new ArrayList<>();

        for (String token : tokens) {
            if (token.startsWith("-")) {
                // Token is an option
                options.add(token);
            } else {
                // Token is either a command or a regular argument
                if (commandName == null) {
                    commandName = token;
                } else {
                    argsList.add(token);
                }
            }
        }

        args = argsList.toArray(new String[0]);

        return true;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getOptions() {
        return options;
    }

    public String[] getArgs() {
        return args;
    }
}
