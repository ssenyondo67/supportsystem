import java.io.*;
import java.util.*;

public class Filemanager {
    public String file_name;
    public File fileObj;

    Filemanager(String name){
        this.file_name=name;
        }

    public void openFile(){

    }

    public boolean createNew_file(String command){
        try {
            fileObj  = new File(file_name);
            if (fileObj.createNewFile()) {
                wrietToFile(command);
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
      BufferedWriter out = new BufferedWriter(
        new FileWriter(file_name, true));
        out.write(command);
        out.write("\n");
        out.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }

    public void updateFile(String command,String f_name){
      try{
        File fileObj = new File(f_name);
        try (Scanner myReader = new Scanner(fileObj)) {
          ArrayList<String> data = new ArrayList<String>(); 
          // String  data = myReader.nextLine();
          while (myReader.hasNext()) {
            data.add(myReader.nextLine()); 
          }
          String storable = "";
          int i =0;
          while (i < data.size()) {
            storable = storable + data.get(i)+"\n";
            if (data.get(i).split(" ")[1].equals(command.split(" ")[1])) {
              System.out.println("Product already in table, use the update method to change item details");
              return;
            }
            i++;
          }
          System.out.println(storable);
          command = storable + command;
          myReader.close();
        }   
        FileWriter myWriter = new FileWriter(f_name);
          BufferedWriter myWriter2 = new BufferedWriter(myWriter);
          PrintWriter myWriter3 = new PrintWriter(f_name);
          myWriter3.println(command);
          myWriter3.close();
          myWriter2.close();
          myWriter.close();
          System.out.println("Successfully Updated the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }

      public String updateFileUser(String command,String f_name){
        String[] cmds = command.split(" ");
        String path=RequestHand.storage_path;
          String name =path.concat(cmds[3]);
          String new_name = name.concat(".txt");
        try{
          
          command = "Register:";
          for(int i=0;i<cmds.length;i++){
            if (i == 0) {
              continue;
            }
            command = command + " " + cmds[i];
          }
          
          File new_file = new File(new_name);
          if (new_file.exists()==true) {
            System.out.println(command);
            System.out.println("Failed");
            System.out.println("User already exists, Try different name");
            return f_name;
          }
          File fileObj = new File(f_name);
          Scanner myReader = new Scanner(fileObj);   
          ArrayList<String> data = new ArrayList<String>(); 
          // String  data = myReader.nextLine();
          while (myReader.hasNext()) {
            data.add(myReader.nextLine()); 
          }
          String storable = "";
          int i =0;
          while (i < data.size()) {
            if (i == 0) {
              storable = storable + command+"\n";  
              
            }else{
              storable = storable + data.get(i)+"\n";
            }
            i++;
          }
          System.out.println(f_name);
          File file = new File(f_name);
          file.deleteOnExit();
          System.out.println(storable);
          myReader.close();
          if (new_file.createNewFile()) {
            PrintWriter myWriter3 = new PrintWriter(new_name);
          myWriter3.println(storable);
          myWriter3.close();
        }
            
            System.out.println("Successfully Updated user infomation the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          System.out.println(command);
          return new_name;
        }

        public void updateFileProduct(String command,String f_name){
          try{
            File fileObj = new File(f_name);
            Scanner myReader = new Scanner(fileObj);   
            ArrayList<String> data = new ArrayList<String>(); 
            // String  data = myReader.nextLine();
            while (myReader.hasNext()) {
              data.add(myReader.nextLine()); 
            }
            String storable = "";
            int i =0;
            while (i < data.size()) {
              String[] s = data.get(i).split(" ");
              if (s[1].equals(command.split(" ")[1])) {
                String record = "Post_product:"+" "+command.split(" ")[1]+" "+command.split(" ")[2]+" "+command.split(" ")[3]+" "+command.split(" ")[4];
                storable = storable + record+"\n";
                System.out.println("Product updated in table");
              }else{
                storable = storable + data.get(i)+"\n";
              }
              i++;
            }
            System.out.println(storable);
            command = storable + command;
            myReader.close();
              FileWriter myWriter = new FileWriter(f_name);
              BufferedWriter myWriter2 = new BufferedWriter(myWriter);
              PrintWriter myWriter3 = new PrintWriter(f_name);
              myWriter3.println(storable);
              myWriter3.close();
              myWriter2.close();
              myWriter.close();
              System.out.println("Successfully Updated the file.");
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
          }
    

    public void closeFile(){

    }
}
