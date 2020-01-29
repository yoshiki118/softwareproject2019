<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
  //送信されたデータを受け取る
  $shopid = $_POST['shopid'];
  //$shopid = "gc0a608";
  require_once 'connect_takoyaki.php';
  $sql = "SELECT categoryname,name_kana FROM CategoriesTable WHERE shopid = '$shopid'";
  $response = mysqli_query($conn,$sql);
  //$result[] = array();
  //$result['category'] = array();
  while ($row = mysqli_fetch_assoc($response)) {
    $db_data[] = array('categoryname'=>$row['categoryname']);
    $db_dt[] = array('categoryname'=>$row['categoryname'],'kana'=>$row['name_kana']);
    $kana[] = $row['name_kana'];
  }
  array_multisort($kana,SORT_ASC,SORT_STRING,$db_dt);
  $result['category'] = $db_dt;

  //JSONデータ出力
  echo json_encode($result,JSON_UNESCAPED_UNICODE);
  //$db=null;
  mysqli_close($conn);
}
?>
