<?php

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // 送信されたデータを受け取る
    $shopid = $_GET['shopid'];

    // データベースに接続する
    require_once 'connect.php';

    // SQL文を変数へ格納する
    $sql = "SELECT * FROM ReviewTable WHERE shopid='$shopid' ";

    // 接続したデータベースに対して変数に格納したSQL文を実行する
    $response = mysqli_query($conn, $sql);

    // 例外処理
    if (!$response) {
      $result["success"] = "0";
      $result["message"] = "error";

      // 返り値をjsonで出力する
      echo json_encode($result);

      // データベースとの接続を切る
      mysqli_close($conn);

      // エラーメッセージを表示して終了する
      exit('クエリーが失敗しました。'.mysql_error());
    }

    // SQL文の結果を格納する配列
    $userid = array();
    $reviewcontents = array();
    $reviewdate = array();
    $reviewtime = array();

    // SQL文の結果を格納する
    while($row = mysqli_fetch_assoc($response)) {
      array_push($userid, $row['userid']);
      array_push($reviewcontents, $row['reviewcontents']);
      array_push($reviewdate, $row['reviewdata']);
      array_push($reviewtime, $row['reviewtime']);
    }

    // 返り値を格納する配列
    $result = array($userid, $reviewcontents, $reviewdate, $reviewtime);

    $result["success"] = "1";
    $result["message"] = "success"

    // 返り値をjsonで出力する
    echo json_encode($result);

    // データベースとの接続を切る
    mysqli_close($conn);
}
?>
