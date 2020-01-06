class AccessDatabase
  def initialize

    # ライブラリを参照する
    require 'mysql'

    # データベースへ接続する
    connection = Mysql::connect("example-rds-mysql-server.cqoaasc5vymv.ap-northeast-1.rds.amazonaws.com", "railsuser", "railspass", "takoyaki")

    # 文字コードをUTF8に設定
    connection.query("set character set utf8")
  end
end
