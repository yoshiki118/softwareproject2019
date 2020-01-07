<?php
if ($_SERVER['REQUEST_METHOD'] =='POST'){
    //端末からのPOSTで送信されたデータを受け取る
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'connect.php';

    $sql = "INSERT INTO users_table (name, email, password) VALUES ('$name', '$email', '$password')";

    if ( mysqli_query($conn, $sql) ) { //挿入に成功
        $result["success"] = "1";
        $result["message"] = "success";
	//jsonデータ出力
        echo json_encode($result);
        mysqli_close($conn);

    } else { //挿入に失敗

        $result["success"] = "0";
        $result["message"] = "error";
	//jsonデータ出力
        echo json_encode($result);
        mysqli_close($conn);
    }
}

?>
