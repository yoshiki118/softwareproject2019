<?php
date_default_timezone_set('Asia/Tokyo');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

  // 端末からのPOSTで送信されたデータを受け取る
  $categoryname = $_POST['categoryname'];
  $name_kana = $_POST['name_kana'];
  $shopid = $_POST['shopid'];
  $username = $_POST['username'];

  // データベースに接続する
  require_once 'connect_takoyaki.php';

  // SQL文を変数へ格納する
  $sql = "INSERT INTO CategoriesTable (categoryname, name_kana, shopid, username) VALUES ('$categoryname', '$name_kana', '$shopid', '$username')";
  // 成功
  if ( mysqli_query($conn, $sql) ) {
    $result["success"] = "1";
    $result["message"] = "success";
    // 返り値をjsonで出力する
    echo json_encode($result);
    // 失敗
  } else {
    $result["success"] = "0";
    $result["message"] = "error";

    // 返り値をjsonで出力する
    echo json_encode($result);
  }

  // データベースとの接続を切る
  mysqli_close($conn);
}
?>
