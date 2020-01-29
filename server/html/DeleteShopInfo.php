<?php
date_default_timezone_set('Asia/Tokyo');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  // 端末からのPOSTで送信されたデータを受け取る
  $shopid = $_POST['shopid'];
  // データベースに接続する
  require_once 'connect_takoyaki.php';
  //以前の情報を削除
  $sql = "DELETE FROM BoardTable  WHERE shopid = '$shopid'";
  if(mysqli_query($conn,$sql)) {
    $result['success']="1";
    $result['message']="success";
    echo json_encode($result);
  }else{
    $result['success']="0";
    $result['message'] ="error";
    echo json_encode($result);
  }
  // データベースとの接続を切る
  mysqli_close($conn);
}
?>
