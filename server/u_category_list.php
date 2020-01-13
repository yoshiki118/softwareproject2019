<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
    //送信されたデータを受け取る
	$shopid = $_POST['shopid'];
	require_once 'connect_takoyaki.php';
	$sql = "SELECT categoryname FROM CategoryTable WHERE shopid = '$shopid'";
	$response = mysqli_query($conn,$sql);
	while ($row = mysqli_fetch_assoc($response)) {
  		$db_data[] = array('categoryname'=>$row['categoryname']);
  	}
	$result['category'] = $db_data;

	//JSONデータ出力
  echo json_encode($result);
  //データベースから切断  
  mysqli_close($conn);

}
?>
