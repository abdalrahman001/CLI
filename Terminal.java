import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

public class Terminal {
Parser parser;
int a= 0;
//Implement each command in a method, for example:
public static void touch(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
        System.out.println("File already exists: " + filePath);
    } else {
        if (filePath.contains(".")) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("Unable to create the file: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid file path: " + filePath);
        }
    }
}
public static void rm(String args) { 
    File myObj = new File(args); 
    if (myObj.delete()) { 
      System.out.println("Deleted file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
  }
public static void listCurrentDirectory() {
    File currentDirectory = new File(System.getProperty("user.dir"));

    File[] contents = currentDirectory.listFiles();
    if (contents != null) {
        for (int i = contents.length -1; i >= 0; i--) {
            System.out.println(contents[i].getName());
        }
    }
}
public  static void echo(String args) {
    
    System.out.println(args);
}
public static void changeDirectory(String directoryPath) {
    File newDirectory = new File(directoryPath);

    if (newDirectory.exists() && newDirectory.isDirectory()) {
        try {
            System.setProperty("user.dir", directoryPath);
            System.out.println("Current directory set to: " + directoryPath);
        } catch (SecurityException e) {
            System.err.println("Error changing directory: " + e.getMessage());
        }
    } else {
        System.err.println("Directory does not exist: " + directoryPath);
    }
}
public static void mkdir (String directoryName){
    
	if (directoryName.length()==0){
		System.out.println("you should enter a directory as a parameter!");
		
	}
	else {
		File newDirectory=new File(directoryName);
		if (!newDirectory.exists()){
			newDirectory.mkdir();
			System.out.println(directoryName+ "Directory creating has been succseed");
		}
		else{
			System.out.println("The directory is already exist");
		}
	}


}
public static void lsr() {
    File currentDirectory = new File(System.getProperty("user.dir"));

    File[] contents = currentDirectory.listFiles();
    if (contents != null) {
        for (int i = contents.length -1; i >= 0; i--) {
            System.out.println(contents[i].getName());
        }
    }
}
public static void cp(String sourceFilePath, String destinationFilePath) {
    Path sourcePath = Path.of(sourceFilePath);
    Path destinationPath = Path.of(destinationFilePath);
    
    try {
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File copied successfully from " + sourceFilePath + " to " + destinationFilePath);
    } catch (IOException e) {
        System.err.println("Error copying file: " + e.getMessage());
    }
}
public static void pwd() {
    String currentDirectory = System.getProperty("user.dir");
    System.out.println("Current directory is: " + currentDirectory);
}
public static void catFile(String filePath) {
        try {
            String fileContent = readFile(filePath);
            System.out.println(fileContent);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

private static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist.");
        }

        return Files.lines(path)
                .collect(Collectors.joining(System.lineSeparator()));
    }
public static void printCurrentDirectory(String[] args) {
    for (String arg : args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current directory is: " + currentDirectory);
    }
}    
public static void wc(String filePath) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                String[] words = line.split("\\s+"); // Split the line into words
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        System.out.println(lineCount + " " + wordCount + " " + charCount + " " + filePath);
    }

public static void main(String[] args) {
    if (args.length == 0) {
        System.out.println("Please provide a command to execute.");
        return;
    }

    // Create a Parser instance
    Parser parser = new Parser();

    // Get the input command from the first argument
    String input = args[0];

    // Parse the input to extract command, options, and arguments
    if (parser.parse(input)) {
        String commandName = parser.getCommandName();
        String[] commandArgs = parser.getArgs();

        // Check the command name and invoke the corresponding method
        switch (commandName) {
            case "touch":
                // Example: touch file.txt
                if (commandArgs.length > 0) {
                    touch(commandArgs[0]);
                } else {
                    System.out.println("Usage: touch <file_path>");
                }
                break;
            case "wc":
                wc(commandArgs[0]);
            case "rm":
                // Example: rm file.txt
                if (commandArgs.length > 0) {
                    rm(commandArgs[0]);
                } else {
                    System.out.println("Usage: rm <file_path>");
                }
                break;

            case "ls":
                // Example: ls
                listCurrentDirectory();
                break;

            case "echo":
                // Example: echo "Hello, World!"
                if (commandArgs.length > 0) {
                    echo(commandArgs[0]);
                } else {
                    System.out.println("Usage: echo <message>");
                }
                break;
            case "cd":
                // Example: cd /path/to/directory
                if (commandArgs.length > 0) {
                    changeDirectory(commandArgs[0]);
                } else {
                    System.out.println("Usage: cd <directory_path>");
                }
                break;

            case "mkdir":
                // Example: mkdir new_directory
                if (commandArgs.length > 0) {
                    mkdir(commandArgs[0]);
                } else {
                    System.out.println("Usage: mkdir <directory_name>");
                }
                break;

            case "lsr":
                // Example: lsr
                lsr();
                break;

            case "cp":
                // Example: cp source.txt destination.txt
                if (commandArgs.length == 2) {
                    cp(commandArgs[0], commandArgs[1]);
                } else {
                    System.out.println("Usage: cp <source_path> <destination_path>");
                }
                break;

            case "pwd":
                // Example: pwd
                pwd();
                break;

            case "cat":
                // Example: cat file.txt
                if (commandArgs.length > 0) {
                    catFile(commandArgs[0]);
                } else {
                    System.out.println("Usage: cat <file_path>");
                }
                break;

            default:
                System.out.println("Unknown command: " + commandName);
                break;
        }
    } else {
        System.out.println("Invalid command input: " + input);
    }
}}
