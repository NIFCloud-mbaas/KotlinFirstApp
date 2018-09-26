package mbaas.com.nifcloud.androidncmbgame

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_page)
        checkRanking()
    }

    protected fun checkRanking() {

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

    }

    fun btnBackAction(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
