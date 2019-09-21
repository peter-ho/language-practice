import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

object RepeatedString {

    // Complete the repeatedString function below.
    def repeatedString(s: String, n: Long): Long = {
      val c1:Long = s.filter(_ =='a').length
      val n1 = s.length
      n/n1*c1 + s.slice(0,(n % n1).toInt).filter(_ == 'a').length
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val s = stdin.readLine

        val n = stdin.readLine.trim.toLong

        val result = repeatedString(s, n)

        printWriter.println(result)

        printWriter.close()
    }
}

