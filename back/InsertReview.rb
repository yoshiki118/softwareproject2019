class InsertReview < AccessDatabase
  def insertReview(userID, restaurantID, reviewContent)

    # データベースへデータを挿入する
    connection.query("INSERT INTO ReviewTable (userid, reviewcontents, shopid, ) VALUES(#{userID}, #{reviewContent}, #{restaurantID})")

    # 接続を切る
    connection.close
  end
end

insertReview = InsertReview.new
insertReview.insertReview()
