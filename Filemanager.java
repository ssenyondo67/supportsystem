import java.io.*;
import java.util.*;

public class Filemanager {
    public String file_name;
    public File fileObj;

    Filemanager(String name){
        this.file_name=name.concat(".txt");
        }

    public void openFile(){

    }

    public boolean createNew_file(){
        try {
            fileObj  = new File(file_name);
            if (fileObj.createNewFile()) {
                
            } else {
              System.out.println("File already exists.");
              return true;
            }
           
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return false;
    }

    public void readFromFile(){
        try {
            
            Scanner myReader = new Scanner(fileObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }

    public void wrietToFile(String command){
    try{
        FileWriter myWriter = new FileWriter(file_name);
        myWriter.write(command);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }

    public void closeFile(){

    }
}
