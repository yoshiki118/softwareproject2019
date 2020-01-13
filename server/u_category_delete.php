<?php
if ($_SERVER['REQUEST_METHOD'] =='POST'){
     //端末からPOSTで送信されたデータを受け取る
     $selectedItem = $_POST['selectedItem'];
     require_once 'connect_takoyaki.php';
     $sql = "DELETE FROM CategoryTable WHERE categoryname = '$selectedItem'";


     if ( mysqli_query($conn, $sql)) { //削除に成功
         $row = mysqli_fetch_assoc($response);
         $result["success"] = "1";
         $result["message"] = "success";
         //jsonデータ出力
         echo json_encode($result);
         mysqli_close($conn);
     } else { //削除に失敗
         $result["success"] = "0";
         $result["message"] = "error";
         //jsonデータ出力
         echo json_encode($result);
         mysqli_close($conn);
     }
}
?>
