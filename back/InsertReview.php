<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // 端末からのPOSTで送信されたデータを受け取る
    $userid = $_POST['userid'];
    $reviewcontents = $_POST['reviewcontents'];
    $shopid = $_POST['shopid'];

    // 時刻を取得する
    $reviewdate = date('Y-m-d');
    $reviewtime = date('H:i:s');

    // データベースに接続する
    require_once 'connect.php';

    // SQL文を変数へ格納する
    $sql = "INSERT INTO ReviewTable (userid, reviewcontents, reviewdate, reviewtime) VALUES ('$userid', '$reviewcontents', '$reviewdate', '$reviewtime')";

    // 成功
    if ( mysqli_query($conn, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success"

        // 返り値をjsonで出力する
        echo json_encode($result);

    // 失敗
    } else {
        $result["success"] = "0";
        $result["message"] = "error";

        // 返り値をjsonで出力する
        echo json_encode($result);

        // データベースとの接続を切る
        mysqli_close($conn);

        // エラーメッセージを表示して終了する
        exit('クエリーが失敗しました。'.mysql_error());
    }

    // データベースとの接続を切る
    mysqli_close($conn);
}
?>
