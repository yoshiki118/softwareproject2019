<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
  //送信されたデータを受け取る
  //選択された文字
  $char=$_POST['char'];
  //$char = "い";
  $char .= "%";
  $result=array();
  $result['category']=array();
  require_once 'connect_takoyaki.php';
  $sql = "SELECT DISTINCT categoryname,name_kana FROM CategoriesTable WHERE name_kana LIKE '$char'";
  $response = mysqli_query($conn,$sql);
  while ($row = mysqli_fetch_assoc($response)) {
    $db_data[] = array('name'=>$row['categoryname']);
    $kana[] = $row['name_kana'];
    $db_dt[] = array('name'=>$row['categoryname'],'kana'=>$row['name_kana']);
  }
  //var_dump($db_data);
  array_multisort($kana,SORT_ASC,SORT_STRING,$db_dt);
  $result['category'] = $db_dt;
  //JSONデータ出力
  echo json_encode($result,JSON_UNESCAPED_UNICODE);
  mysqli_close($conn);
}
?>
