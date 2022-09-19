<?php 
   mysqli_report(MYSQLI_REPORT_ALL ^ MYSQLI_REPORT_INDEX);
   $conn = '';
   
    function connect(){
	   $DB_DATABASE="supportdatabase";
	   $DB_USERNAME="admin";
	   $DB_PASSWORD="SS@ny0nd0";
	   $DB_HOSTNAME="localhost";
	   	$conn = new mysqli($DB_HOSTNAME,
	           $DB_USERNAME, $DB_PASSWORD,$DB_DATABASE);
		 
		// For checking if connection is
		// successful or not
		if ($conn->connect_error) {
		  die("Connection failed: "
		      . $conn->connect_error);
		  echo "failed to connect";
		 
		}else{
		echo "Connected successfully";
		
        }
        return $conn;
	}
    
 
    function addparticipant($fname, $lname, $uname, $pass){

      $conn = connect();
      $query = "INSERT INTO participants (first_name, last_name, username, password)
	  VALUES (?, ?, ?, ?)";
      $stmt = $conn->prepare($query);
	  $stmt->bind_param("ssss", $fname, $lname,$uname,$pass);
	  $stmt->execute();

	  if ($conn->query($query) === TRUE) {
	     echo "New record created successfully";
	  } else {
	     echo "Error: " . $query . "<br>" . $conn->error;
      }

      $conn->close();
    }

    function selectparticipant($username){

      $conn = connect();
      $query="SELECT id,current_score, rank FROM participants WHERE username=?";
      $stmt = $conn->prepare($query);
	    $stmt->bind_param("s",$username);
	    $stmt->execute();

	    // if ($conn->query($query) === TRUE) {
	    //   echo "New record created successfully";
	    // } else {
	    //    echo "Error: " . $query . "<br>" . $conn->error;
     //  }

      $result = $stmt->get_result();
	    $row=$result->fetch_assoc();
   	  $id=$row["id"];

      $conn->close();
      return $id;

    }
	
	function post_product($name, $description,  $stock_available, $rate_per_item,$participant_id){
        
      $conn = connect();
      $query = "INSERT INTO products (name, description,  stock_available, rate_per_item, participant_id)
	  VALUES (?, ?, ?, ?,?)";
      $stmt = $conn->prepare($query);
	  $stmt->bind_param("ssddi", $name, $description,  $stock_available, $rate_per_item,$participant_id);
	  $stmt->execute();

	  if ($conn->query($query) === TRUE) {
	     echo "New record created successfully";
	  } else {
	     echo "Error: " . $query . "<br>" . $conn->error;
      }

      $conn->close();
    }
   
   
		finction readfromfile($file,$command){
			   $data=" ";
		   
		  	$myfile = fopen($file, "r") or die("Unable to open file!");
		    while(!feof($myfile)) {
            $data= fgets($myfile);
            if(str_starts_with($data, $command)){
            	break;
            }
        }
		    fclose($myfile);
		    return $data;
    }
    $file="files\micho.txt";
		$data = readfromfile($file,"Post_product: ");
		$details = explode(" ", $data);
	  addparticipant($details[1],$details[2],$details[3],$details[4]);

	
	 $id=selectparticipant("marks");
	 post_product("capboard","double cupboard with glasses",20,10000,$id);  
  
?>