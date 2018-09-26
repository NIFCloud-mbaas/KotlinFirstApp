## 【問題２】答え合わせ

### ランキング画面の確認

* ランキング画面を確認しましょう
 * アプリで「ランキングを見る」をタップすると以下のようにランキングが表示されます

![ans2-1](/readme-img/ans2-1.png)

* 上図は３回遊んだ場合の例です。複数回遊んで、ランキングが表示されることを確認しましょう！

### コードの答え合わせ

![Android](/readme-img/icon_androidstudio.png)

* 模範解答は以下です

```java
// **********【問題２】ランキングを表示しよう！**********

//HighScoreクラスを検索するクエリを作成
val query = NCMBQuery<NCMBObject>("GameScore")

//Scoreフィールドの降順でデータを取得
query.addOrderByDescending("score")

//検索件数を5件に設定
query.setLimit(5)

//データストアでの検索を行う
query.findInBackground { objects, e ->
    if (e != null) {
        //エラー時の処理
        Log.e("NCMB", "検索に失敗しました。エラー:" + e.message)
    } else {
        //成功時の処理
        Log.i("NCMB", "検索に成功しました。")
        //ListViewオブジェクトの取得
        val lv = findViewById<View>(R.id.lstRanking) as ListView
        // ループカウンタ
        val adapter = ArrayAdapter<String>(this@RankingActivity, android.R.layout.simple_list_item_1)

        var i = 0
        val n = objects.size
        while (i < n) {
            val o = objects[i]
            Log.i("NCMB", o.getString("name"))
            // 処理
            val name = o.getString("name")
            val score = o.getInt("score")
            adapter.add(name + " さん : " + score.toString() + " (point)")
            i++
        }

        lv.adapter = adapter
    }
}

// **************************************************
```
