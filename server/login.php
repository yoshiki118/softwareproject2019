<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    //送信されたデータを受け取る
    $email = $_POST['email'];
    $password = $_POST['password'];

    require_once 'connect.php';

    $sql = "SELECT * FROM users_table WHERE email='$email' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();

    if ( mysqli_num_rows($response) === 1 ) {
        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['password']) ) {
            $index['name'] = $row['name'];
            $index['email'] = $row['email'];

            array_push($result['login'], $index);
	    
            $result['success'] = "1";
            $result['message'] = "success";
            //json出力
	    echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            //json出力
            echo json_encode($result);

            mysqli_close($conn);

        }

    }

}

?>
