<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {

  $item = $_POST['item'];
  $text = $_POST['text'];
  $to = "example@example.com";
  $subject = "$item";
  $message = "$text";
  //$add_header = "From":
  if(mb_send_mail($to,$item,$text))
  {
    $result['success'] = "1";
    echo json_encode($result);
  }else{
    $result['success']="1";
    echo json_encode($result);
  }
}
?>
