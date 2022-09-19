import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.AnnotatedType;
import java.util.*;

public class RequestHandler {
    public boolean session =false;
    public String path="C:\\project\\supportSystemCommad\\files\\";
    public File fileObj;

    public void register(String command){
        String [] registraion_details =command.split(" ",0);
        try (Scanner in = new Scanner(System.in)) {
            while(registraion_details.length != 5){
                System.out.println("Enter Register: Command with correct arguments");
                command = in.nextLine();
                registraion_details =command.split("\\s",0);
            }
        }
        String first_name,last_name,username,password;
        first_name =registraion_details[1];
        last_name=registraion_details[2];
        username=registraion_details[3];
        password=registraion_details[4];


        System.out.println("You have entered:\n"+"first_name: "+first_name
                                                +"\n last_name: "+last_name
                                                +"\nusername: "+username
                                                +"\npassword: "+password );
       
        String name =path.concat(username);
        Filemanager manageObj =new Filemanager(name);
        manageObj.createNew_file();
        manageObj.wrietToFile(command);

    }
        
    public void login(String command){
        String [] login_details =command.split("\\s",0);
        try (Scanner in = new Scanner(System.in)) {
            while(login_details.length != 3){
               
                System.out.println("Enter Login: Command with correct arguments");
                command = in.nextLine();
                login_details =command.split("\\s",0);
            }
        }

        String username,password;
        username=login_details[1];
        password=login_details[2];
        
        String name =path.concat(username);
        Filemanager manageObj =new Filemanager(name);
        String read_username =" ";
        String read_password=" ";
        
        try {            
            Scanner myReader = new Scanner(fileObj);
            
            String  data = myReader.nextLine();
            String [] details =data.split("\\s",0);
            read_username=details[1];
            read_password=details[2];
            
            myReader.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
    
       
        System.out.println(read_password +" "+password);
        System.out.println(read_username +" "+username);
        if (read_username == username){
            if(read_password==password){
                System.out.println(" You have logined successfuly");
            }else{
                System.out.println("Incorrect password");
                }
        }
        
    }
    public void performance(String command){
        System.out.println("performance");
    }

    public void post_product(String command){
        String [] product_details =command.split("\\s",0);
        try (Scanner in = new Scanner(System.in)) {
            while(product_details.length != 5){
            
                System.out.println("Enter Post_product: Command with correct arguments");
                command = in.nextLine();
                product_details =command.split("\\s",0);
            }
        }

        String name,description,stock,rate_per_item;
        name =product_details[1];
        description=product_details[2].replaceAll("-"," ");
        stock=product_details[3];
        rate_per_item=product_details[4];


        System.out.println("You have entered:\n"+"   name: "+name
                                                +"\n description: "+description
                                                +"\n stock: "+stock
                                                +"\n rate_per_item: "+rate_per_item );
    }
    
    public void Update_participant(String command){
        // String [] registraion_details =command.split("\\s",0);
        // String first_name,last_name,username,password;
        // first_name =registraion_details[1];
        // last_name=registraion_details[2];
        // username=registraion_details[3];
        // password=registraion_details[4];


        // System.out.println("You have entered:\n"+"first_name: "+first_name
        //                                         +"\n last_name: "+last_name
        //                                         +"\nusername: "+username
        //                                         +"\npassword: "+password );
        System.out.println("Opio");
    }

    public void Update_product(String command){
        String [] product_details =command.split("\\s",0);
        String name,description,stock,rate_per_item;
        name =product_details[1];
        description=product_details[2];
        stock=product_details[3];
        rate_per_item=product_details[4];


        System.out.println("You have entered:\n"+"   name: "+name
                                                +"\n description: "+description
                                                +"\n stock: "+stock
                                                +"\n rate_per_item: "+rate_per_item );
    }

    public boolean logout(String command){
        System.out.println(command);
        if (command=="Logout:"){
            session=true;
        }
        return session;
    }

    public static void main(String [] m) throws NoSuchMethodException, SecurityException, IOException{
        
        Commmands cmd =new Commmands();   
        cmd.displayCommands();
        String command="";  
        Scanner input;
        while(!command.equalsIgnoreCase("Logout:")){    
            
        // InputStreamReader r;    
        // BufferedReader br;           
         System.out.println("Enter Command:"); 
        //  r=new InputStreamReader(System.in); 
        //  br=new BufferedReader(r);
        //  command = br.readLine();     
        //   r.close();
        //   br.close();
        input = new Scanner(System.in);
          command = input.nextLine();     
          System.out.println(command);

         cmd.handleCommands(command);  
         
        }              
          
        
        

    }
}
