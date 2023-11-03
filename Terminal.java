import java.io.File;
import java.io.IOException;

public class Terminal {
Parser parser;
//Implement each command in a method, for example:
  public static void touch(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("File already exists: " + filePath);
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("Unable to create the file: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }
public static void rm(String[] args) { 
    File myObj = new File("filename.txt"); 
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
public static void echo(final String... args) {
    String echo = "";
    for (final String arg : args) {
        if (arg != args[0]) echo += " ";
        echo += arg;
    }
    System.out.println(echo);
}
public static void changeDirectory(String directoryPath) {
    File newDirectory = new File(directoryPath);

    if (newDirectory.exists() && newDirectory.isDirectory()) {
        try {
            System.setProperty("user.dir", directoryPath);
            System.out.println("Current directory set to: " + directoryPath);
        } catch (Exception e) {
            System.err.println("Error changing directory: " + e.getMessage());
        }
    } else {
        System.err.println("Directory does not exist: " + directoryPath);
    }
}
public void mkdir (String directoryName){
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
public static void main(String[] args){
    
}
}
