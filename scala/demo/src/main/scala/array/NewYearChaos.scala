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

/**
It's New Year's Day and everyone's in line for the Wonderland rollercoaster ride! There are a number of people queued up, and each person wears a sticker indicating their initial position in the queue. Initial positions increment by 1 from 1 at the front of the line to n at the back.

Any person in the queue can bribe the person directly in front of them to swap positions. If two people swap positions, they still wear the same sticker denoting their original places in line. One person can bribe at most two others. For example, if n=8 and Person5 bribes Person4, the queue will look like this: 1,2,3,5,4,6,7,8 .

Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place to get the queue into its current state!

**/
object NewYearChoas {

    // Complete the minimumBribes function below.
    def minimumBribes(q: Array[Int]) {
      val result = minBribes(q)
      if (result.isEmpty) println("Too chaotic")
      else println(result.get)
    }

    def lastIndexOf(q: Array[Int], num:Int):Option[Int] = {
      val i = num - 1
      if (q(i) == num) Some(i)
      else if (q(i - 1) == num) Some(i - 1)
      else if (q(i - 2) == num) Some(i - 2)
      else None
    }

    def minBribes(q: Array[Int]):Option[Int] = {
      var q1 = q
      var mb = 0
      for (x <- q1.length to 2 by -1) { 
          //val idx = q1.lastIndexOf(x)
          val idx1 = lastIndexOf(q1, x)
          if (idx1.isEmpty) return None
          val idx = idx1.get
          x - idx match {
            case 1 => {}
            case 2 => {
                mb += 1
                q1(idx) = q1(idx + 1)
                q1(idx+1) = x
//                if (x < q1.length * 0.75) 
//                  q1 = q1.slice(0, idx + 1) 
              }
            case 3 => {
                mb += 2
                q1(idx) = q1(idx + 1)
                q1(idx + 1) = q1(idx + 2)
                q1(idx + 2) = x
//                if (x < q1.length * 0.75) 
//                  q1 = q1.slice(0, idx + 2)
              }
            case _ => { return None }
          }
      }
      Some(mb)
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val t = stdin.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val n = stdin.readLine.trim.toInt

            val q = stdin.readLine.split(" ").map(_.trim.toInt)
            minimumBribes(q)
        }
    }
}

