<?php
//メールアドレス
$to = "example@example.com";
//件名
$subject = "テスト送信";
//本文
$text = "ただいまメールのテスト中です";

//$add_headerを指定しない場合は差出人はサーバに設定されているアドレス
//$add_header = "From:....@...":
//if(mb_send_mail($to,$subject,$text,$add_header))
if(mb_send_mail($to,$subject,$text))
{
  //ブラウザ上で確認
  print"メールを送信しました";

}else{
  //ブラウザ上で確認
  print"メール送信に失敗しました";
}
?>
