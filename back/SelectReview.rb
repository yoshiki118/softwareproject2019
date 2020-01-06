class SelectReview < AccessDatabase
  def selectReview(userID)

    # データベースへデータを挿入する
    r = connection.query("SELECT reviewcontents, shopid, reviewdate, reviewtime FROM ReviewTable WHERE userid = #{userID}")

    # 接続を切る
    connection.close

    return r
  end
end

selectReview = SelectReview.new
selectReview.selectReview()
