<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // 送信されたデータを受け取る
    $shopid = $_POST['shopid'];
    // $shopid = 'gc0a608';
    // データベースに接続する
    require_once 'connect_takoyaki.php';
    // SQL文を変数へ格納する
    $sql = "SELECT * FROM ReviewTable WHERE shopid='$shopid' ";
    // 接続したデータベースに対して変数に格納したSQL文を実行する
    $response = mysqli_query($conn, $sql);
    // 例外処理
    if (!$response) {
      //$result["success"] = "0";
      //$result["message"] = "error";
      // 返り値をjsonで出力する
      //echo json_encode($result);
      // データベースとの接続を切る
      //mysqli_close($conn);
      // エラーメッセージを表示して終了する
      //exit('クエリーが失敗しました。'.mysql_error());
    }else{
    // SQL文の結果を格納する配列
   // $username = array();
    //$reviewcontents = array();
    //$reviewdate = array();
    //$reviewtime = array();

$result=array();
$result['review']=array();
    //$result[] = array($username, $reviewcontents, $reviewdate, $reviewtime);
    // SQL文の結果を格納する
    while($row = mysqli_fetch_assoc($response)) {

    //$res[] = array('username'=>$row['username']);
    //$res[] = array('reviewcontents'=>$row['reviewcontents']);
    //$res[] = array('reviewdate'=>$row['reviewdate']);
    //$res[] = array('reviewtime'=>$row['reviewtime']);
    $index['username'] = $row['username'];
    $index['reviewcontents'] = $row['reviewcontents'];
    $index['reviewdate'] = $row['reviewdate'];
    $index['reviewtime']= $row['reviewtime'];
    array_push($result['review'],$index);
    }
    // 返り値を格納する配列
    //$result['review'] = $res;
   // $result["success"] = "1";
    //$result["message"] = "success"
    // 返り値をjsonで出力する
    echo json_encode($result);
    }
    // データベースとの接続を切る
    mysqli_close($conn);
}
?>
