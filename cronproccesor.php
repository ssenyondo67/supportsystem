<?php 
   mysqli_report(MYSQLI_REPORT_ALL ^ MYSQLI_REPORT_INDEX);
   $conn = '';
   $writefile="files\\result.txt";
   $readfile="files\\store.txt";
   $registered_participants=array();
   $posted_products=array();
   $row=array();
   
   function fetchAllparticipant(){
	    $registered_participants;
	    
		$conn = connect();
		$sql = "SELECT username FROM participants";
		$result = $conn->query($sql);

		if ($result->num_rows > 0) {
		// output data of each row
			while($row = $result->fetch_assoc()) {
				$registered_participants[]=$row["username"];
			
			}
		} else {
		echo "0 results";
		}
		$conn->close();
		return $registered_participants;

	}

	function fetchAllproduct(){
        $posted_products;
		$conn = connect();
		$sql = "SELECT participant_id FROM products";
		$result = $conn->query($sql);

		if ($result->num_rows > 0) {
		// output data of each row
			while($row = $result->fetch_assoc()) {
				$posted_products[]=$row["participant_id"];
			}
		} else {
		echo "0 results";
		}
		$conn->close();
		return $posted_products;

	}
  
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
    $registered_participants=fetchAllparticipant();
	$posted_products=fetchAllproduct();
	
    function addparticipant($fname, $lname, $uname, $pass){

      $conn = connect();
	  try{
			$query = "INSERT INTO participants (first_name, last_name, username, password)
			VALUES (?, ?, ?, ?)";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("ssss", $fname, $lname,$uname,$pass);
			$stmt->execute();
		} catch (mysqli_sql_exception $e) { 
			var_dump($e);
			exit; 
		} 

      $conn->close();
    }

    function selectparticipant($username){		

      $conn = connect();	  
      $query="SELECT id,current_score, rank FROM participants WHERE username=?";
      $stmt = $conn->prepare($query);
	  $stmt->bind_param("s",$username);
	  $stmt->execute();

	  $result = $stmt->get_result();

	  $row=$result->fetch_assoc();
	  
   	  $conn->close();
      return $row;

    }
	
	function post_product($name, $description,  $stock_available, $rate_per_item,$participant_id){
        
      $conn = connect();
     try{
			$query = "INSERT INTO products (name, description,  stock_available, rate_per_item, participant_id)
			VALUES (?, ?, ?, ?,?)";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("ssddi", $name, $description,  $stock_available, $rate_per_item,$participant_id);
			$stmt->execute();
	   } catch (mysqli_sql_exception $e) { 
		var_dump($e);
		exit; 
	   } 

	  if ($conn->query($query) === TRUE) {
	     echo "New record created successfully";
	  } else {
	     echo "Error: " . $query . "<br>" . $conn->error;
      }

      $conn->close();
    }
   
   
	function readfromfile ($file, $command){
	   $myfile = fopen($file, "r") or die("Unable to open file!");
	    while(!feof($myfile)) {
            $data= fgets($myfile);
			
            if(str_starts_with($data, $command)){
				$details = explode(" ", $data);
			
          	    return $details;
            }
        }
	    fclose($myfile);
	    return ;
    }
    
	function writeTofile($file,$data){
		$myfile = fopen($file, "a") or die("Unable to open file!");
        fwrite($myfile, $data.PHP_EOL );
        fclose($myfile);
	}
	
	
//    the main 
    $myfile = fopen($readfile, "r") or die("Unable to open file!");
    while(!feof($myfile)) {
	    $data= fgets($myfile);
		
		$details = explode(" ", $data);

        if(count($details)>1){
			if($details[0]=="Register:"){
				
				if(!in_array($details[3],$registered_participants)){
		
					 addparticipant($details[1],$details[2],$details[3],$details[4]);
					
				}
			}elseif($details[0]=="Post_product:"){
				
				$row=selectparticipant($details[5]);
				$id=$row["id"];
				
				if(!in_array($id,$posted_products)){
					
				    post_product($details[1],$details[2],$details[3],$details[4],$id);
					
				}
			}elseif($details[0]=="Performance:"){
			    
				 $row=selectparticipant($details[1]);
				if(isset($row)){
					echo "thisis \n";
					$score=$row["current_score"];
					$rank=$row["rank"];
					$data="Score: $details[1] $score $rank";
					writeTofile($writefile,$data);  

				}          
		     }
		}

	}
	fclose($myfile);
	
?>