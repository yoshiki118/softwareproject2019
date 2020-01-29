<?php
date_default_timezone_set('Asia/Tokyo');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  // 端末からのPOSTで送信されたデータを受け取る
  $shopid = $_POST['shopid'];
  $commentcontents=$_POST['commentcontents'];
  $commentcontenrs=mb_convert_encoding($commentcontents,"UTF-8");
  // 時刻を取得する
  $commentdate = date('Y-m-d');
  $commenttime = date('H:i:s');

  // データベースに接続する
  require_once 'connect_takoyaki.php';
  //以前の情報を削除
  $sql = "DELETE FROM BoardTable  WHERE shopid = '$shopid'";
  if(mysqli_query($conn,$sql)) {
    $result['success']="1";
    $result['message']="success";
    echo json_encode($result);
  }else{
    $result['success']="2";
    $result['message'] ="error";
    echo json_encode($result);
  }

  // SQL文を変数へ格納する
  $sql = "INSERT INTO BoardTable (commentcontents, commentdate, commenttime,shopid) VALUES ('$commentcontents', '$commentdate', '$commenttime','$shopid')";

  // 成功
  if ( mysqli_query($conn, $sql) ) {
    $result["success"] = "1";
    $result["message"] = "success";
    // 返り値をjsonで出力する
    echo json_encode($result);
    // 失敗
  } else {
    $result["success"] = "3";
    $result["message"] = "error";

    // 返り値をjsonで出力する
    echo json_encode($result);
  }
  // データベースとの接続を切る
  mysqli_close($conn);
}
?>
