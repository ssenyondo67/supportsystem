
import java.util.*;
public class Commmands {

    public String method;
    public String command;
    public HashMap<String,String> commandMap;
    Scanner scan = new Scanner(System.in);

    public void displayCommands(){
        System.out.println(
            "This is the Menu: \n"+
            "Register: first_name last_name username password \n"+
            "Login: username password \n"+
            "Post_product: name description stock(float) price(float)\n"+
            "Performance:\n"+
            "Update_product: name description stock(float) price(float)\n"+
            "Update_participant: first_name last_name username password \n"+
            "Logout:\n"
        );
    }
    public HashMap hashmap() throws NoSuchMethodException, SecurityException{
        
        commandMap = new HashMap<String,String>();
        commandMap.put("Register:","register");
        commandMap.put("Login:","login");
        commandMap.put("Post_product:","post_product");
        commandMap.put("Performance:","performance");
        commandMap.put("Update_product:","Update_product");
        commandMap.put("Update_participant:","Update_participant");
        commandMap.put("Logout:","logout");
        return commandMap;

    }

    // public String getCommand(){
    //     
        
    //     command = scan.nextLine();
       
    //     return command;
        
    // }

    public void handleCommands(String command) throws NoSuchMethodException, SecurityException{
        
        String command_map_key=command.split(" ")[0];
      //   commandMap =hashmap();
      //   if (commandMap.containsKey(command_map_key)){
      //       method =commandMap.get(command_map_key);
      //   }else{
      //       System.out.println(command + "is invalid \n");
      //       method="logout";
      //   }        
       RequestHandler handle =new RequestHandler();
       switch(command_map_key){
         case "Register:":
               handle.register(command);
               break;
         case "Login:":
               handle.login(command);
               break;
         case "Logout:":
               handle.logout(command);
               break;
         case "Post_product:":
               handle.post_product(command);
               break;
         case "Update_product:":
               handle.Update_product(command);
               break;
         case "Update_participant:":
               handle.Update_participant(command);
               break;
         case "Performance:":
               handle.performance(command_map_key);
               break;
         default:
              handle.logout(command);
              break;
       }
    }
}
