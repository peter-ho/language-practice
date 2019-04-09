/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

object CountValley {

    /** A valley is a sequence of consecutive steps below sea level,
      * starting with a step down from sea level and ending with a 
      * step up to sea level.
      *
      *  @param n number of character in input string
      *  @param s input string with valley information
      *  @return number of valley contains the string 
      */
    def countingValleys(n: Int, s: String): Int = {
      val s1 = s.map(_ match { case 'D' => -1 case _ => 1})
        .scan(0) { (r,x) => r+x }.tail
      val v1 = {if (s1.length > 0) 
        s1.zip(s1.tail).filter(x => x._1 == -1 && x._2 == 0).length
        else 0}

      val v2 = {if (s1.length > 0)
        s1.sliding(2).filter(x => x.head == -1 && x.takeRight(1).head == 0).length 
        else 0}

      println(s1.sliding(2))
      if (v1 == v2) v1 else throw new Exception(s"Version 1 ($v1) and 2 ($v2) mismatch")
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val n = stdin.readLine.trim.toInt

        val s = stdin.readLine

        val result = countingValleys(n, s)

        printWriter.println(result)

        printWriter.close()
    }
}
