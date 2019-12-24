このふぁいるはりーどみーです

#gitの使い方
  ##FIRST
  1. gitをダウンロードします(https://git-for-windows.github.io/)
  > gitコマンドが使えるようになります

  2. git cloneします
  >git clone https://github.com/yoshiki118/softwareproject2019.git
  >↑をコマンドラインでにゅうりょく(入力の際はフォルダの位置がクローンしたところにできるためcdで移動推奨)

  3. フォルダが生成されたか確認
  > 作業場はmasterではなくworkなのでgit chckout workで移動してください！

  ##SECOND
  ###基本的な作業
  1. git checkout で作業する場所に移動
  > git checkout work
  > git checkout だけだとどの場所に居るかを表示します

  2. git pull --rebaseで変更を反映
  > ほかでpushされていてこちらでファイル編集していた場合エラーが出ますがコミットまでするとできます
  > **必ずする！！！！！！！！！！！！！！！！！！！！！！！**

  3. git add　ファイル名で変更したファイルをaddする

  4. git commit する
  >git commit -m "こめんと"
  >コメントを書くのにエディタ起動するのめんどい場合↑でもいける

  5. git push
  > ログインを求められる場合があります

  ##THIRD
  git関連でエラー出てわからなかったらslackにご連絡ください
