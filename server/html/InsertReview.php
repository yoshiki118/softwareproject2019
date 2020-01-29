<?php
date_default_timezone_set('Asia/Tokyo');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // 端末からのPOSTで送信されたデータを受け取る
    $username = $_POST['username'];
    $reviewcontents = $_POST['reviewcontents'];
    $shopid = $_POST['shopid'];
    //$username = "sssssss";
    //$reviewcontents = "aaaaa";
    //$shopid = "gc0a608";
    // 時刻を取得する
    $reviewdate = date('Y-m-d');
    $reviewtime = date('H:i:s');

    // データベースに接続する
    require_once 'connect_takoyaki.php';

    // SQL文を変数へ格納する
    $sql = "INSERT INTO ReviewTable (username, reviewcontents, reviewdate, reviewtime,shopid) VALUES ('$username', '$reviewcontents', '$reviewdate', '$reviewtime','$shopid')";

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

        // エラーメッセージを表示して終了する
       // exit('クエリーが失敗しました。'.mysql_error());
    }

    // データベースとの接続を切る
    mysqli_close($conn);
}
?>
