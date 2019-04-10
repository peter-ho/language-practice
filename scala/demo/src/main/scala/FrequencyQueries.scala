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
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.collection.parallel.immutable._
import scala.collection.parallel.mutable._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object FrequencyQueries {

    // Complete the freqQuery function below.
    def freqQuery(queries: Array[Array[Int]]): Array[Int] = {
      val m = scala.collection.mutable.Map[Int, Int]()
      var out = scala.collection.immutable.List[Int]()
      queries.foreach(x => {
        x(0) match {
          case 1 => m(x(1)) = m.getOrElse(x(1), 0) + 1
          case 2 => {
            val v = m.get(x(1))
            if (m.get(x(1)) != None ) {
              m(x(1)) -= 1
            }
          }
          case _ => {
            println(m)
            out = out :+ (if (m.values.filter(_ == x(1)).size > 0) 1 else 0)
          }
        }
      })
      out.toArray
    }

    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val q = StdIn.readLine.trim.toInt

        val queries = Array.ofDim[Int](q, 2)

        for (i <- 0 until q) {
            queries(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
        }

        val ans = freqQuery(queries)

        printWriter.println(ans.mkString("\n"))

        printWriter.close()
    }
}