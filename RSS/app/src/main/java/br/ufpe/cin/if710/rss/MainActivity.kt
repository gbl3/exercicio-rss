package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"
    //Trocar por ListView
    private val conteudoRSS:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conteudoRSS = findViewById(R.id.conteudoRSS)
    }

    override fun onStart() {
        super.onStart()
        try
        {
          //Esse código dá pau, por fazer operação de rede na thread principal...
          val feedXML = getRssFeed(RSS_FEED)
          conteudoRSS.setText(feedXML)
        }
        catch (e:IOException) {
          e.printStackTrace()
        }
    }

    private fun getRssFeed(feed:String):String {
        val in:InputStream = null
        val rssFeed = ""
        try {
            val url = URL(feed)
            val conn = url.openConnection() as HttpURLConnection
            in = conn.getInputStream()
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            val count:Int
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count)
            }
            val response = out.toByteArray()
            rssFeed = String(response, "UTF-8")
        } finally {
            if (in != null)
            {
                in.close()
            }
        }
        return rssFeed
    }
}
