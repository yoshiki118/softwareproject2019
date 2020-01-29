<?php
//データベースに接続
$conn = mysqli_connect("example-rds-mysql-server.cqoaasc5vymv.ap-northeast-1.rds.amazonaws.com","railsuser","railspass","takoyaki");
//文字コードをutf-8に設定
mysqli_set_charset($conn,"utf8")
?>
