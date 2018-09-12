package mbaas.com.nifcloud.androidncmbgame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.nifcloud.mbaas.core.NCMB
import java.util.*

class MainActivity : AppCompatActivity() {
    //カウントタイム設定
    internal val COUNT_TIME = 10
    internal val myHandler = Handler()
    internal var myTimer = Timer()
    lateinit var tv: TextView
    lateinit var sv: TextView
    lateinit var iv: ImageView
    //タイマー用
    internal var countTimer = -4
    //タッブスコア
    internal var score = 0

    internal val myRunnable: Runnable = Runnable {
        if (countTimer > 0) {
            tv.text = countTimer.toString()
        } else {
            if (countTimer < 0) {
                tv.text = "スタート準備 " + (countTimer + 4).toString()
            } else if (countTimer == 0) {
                tv.text = "スタート"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById<View>(R.id.txtTimer) as TextView
        iv = findViewById<View>(R.id.imageView) as ImageView
        sv = findViewById<View>(R.id.txtScore) as TextView

        /********* mBaaS初期化  */
        NCMB.initialize(this, "YOUR_APPLICATION_KEY", "YOUR_CLIENT_KEY")
    }

    /********
     * START ボタンを処理する実装
     */
    fun doStartBtn(view: View) {
        //１秒１回タイマーを実行
        myTimer.schedule(object : TimerTask() {
            override fun run() {
                UpdateGUI()
            }
        }, 0, 1000)
    }

    /********
     * HIT ボタンを処理する実装
     */
    fun doHit(view: View) {
        if (countTimer > 0 && countTimer <= COUNT_TIME) {
            score++
            sv.text = "Score: " + score.toString()
        }
    }

    /********
     * RANKINGを見るボタンを処理する実装
     */
    fun btnRankingAction(view: View) {
        val intent = Intent(applicationContext, RankingActivity::class.java)
        startActivity(intent)
    }

    /********
     * mBaaSデータを保存
     */
    fun saveScore(name: String, score: Int) {

        // **********【問題１】名前とスコアを保存しよう！**********












        // **************************************************

    }

    private fun UpdateGUI() {
        countTimer++
        if (countTimer <= COUNT_TIME) {
            myHandler.post(myRunnable)
        } else {
            myTimer.cancel()
            this@MainActivity.runOnUiThread {
                //名前を入力するためのテキストを準備
                val input = EditText(this@MainActivity)
                //アラートを表示
                AlertDialog.Builder(this@MainActivity)
                        .setTitle("スコア登録")
                        .setView(input)
                        .setMessage("名前を入力してください")
                        .setPositiveButton("OK") { dialog, whichButton ->
                            val name: String
                            //入力から名前を取得
                            name = input.text.toString()
                            saveScore(name, score)
                            //画面を初期化
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                        }.show()
            }

        }
    }
}
