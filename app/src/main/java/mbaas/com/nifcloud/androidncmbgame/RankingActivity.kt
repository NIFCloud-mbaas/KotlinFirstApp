package mbaas.com.nifcloud.androidncmbgame

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_page)
        checkRanking()

    }

    protected fun checkRanking() {

        // **********【問題２】ランキングを表示しよう！**********








        // **************************************************

    }

    fun btnBackAction(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
