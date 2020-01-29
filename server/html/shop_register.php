<?php
if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $shopid = $_POST['shopid'];
    $password = $_POST['password'];

    $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'connect_takoyaki.php';

    $sql = "INSERT INTO ShopTable (shopid, shoppassword )VALUES ('$shopid', '$password')";

    if ( mysqli_query($conn, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);

    } else {

        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }

}

?>
