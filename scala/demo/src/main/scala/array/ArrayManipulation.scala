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

object ArrayManipulation {

    /** Evaluate the minimum jump to finish
Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each of the array element between two given indices, inclusive. Once all operations have been performed, return the maximum value in your array. 

For example, the length of your array of zeros . Your list of queries is as follows

      *
      *  @param arr two dimension array with values to compute hourglass
      *  @return largest hourglass sum found in arr
      */

    def exeQuery(buf:scala.collection.mutable.ListBuffer[(Int, Int, Long)], query:Array[Int]):Unit = {
      if (query(1) < query(0)) return
      val q = query
      var idx = buf.indexWhere(i => i._1 > q(0))
      val prev = idx match {
        // if row not found, previous is last row
        case -1 => buf.last
        case 0 => null
        case _ => buf(idx-1)
      }
      val next = idx match {
        case -1 => null
        case _ => buf(idx)
      }
      if (prev == null && next == null) buf.append((q(0), q(1), q(2).toLong))
      else if (prev == null) {
        if (next._1 <= q(1)) {
          // overlap with next
          buf.prepend((q(0), next._1-1, q(2)))
          exeQuery(buf, Array(prev._1, q(1), q(2)))
        } else {
          buf.prepend((q(0), q(1), q(2)))
        }
      } else {
      // merge with row before
        if (prev._1 == q(0) && prev._2 == q(1)) {
          //prev._3 += q(2)
          buf.update(idx-1, (prev._1, prev._2, prev._3 + q(2)))
        } else if (prev._1 == q(0)) {
          // overlap with the prev, so can update the prev
          if (prev._2 > q(1)) {
            // doesn't span outside prev
            buf.insert(idx, (q(1)+1, prev._2, prev._3))
            //prev._3 += q(2)
            buf.update(idx-1, (prev._1, prev._2, prev._3+q(2)))
          } else {
            // extend to outside of prev
            //prev._3 += q(2)
            if (next == null) 
              buf.update(buf.size-1, (prev._1, prev._2, prev._3 + q(2)))
            else
              buf.update(idx-1, (prev._1, prev._2, prev._3 + q(2)))
            exeQuery(buf, Array(prev._2+1, q(1), q(2)))
          }
        } else if (prev._2 >= q(0)) {
          // overlap in prev
          // insert new and update prev 
          val na = (q(0), q(1).min(prev._2), q(2) + prev._3)
          if (next == null) {
            buf.append(na) 
            buf.update(buf.size-1, (prev._1, q(0)-1, prev._3))
          } else {
            buf.insert(idx, na)
            buf.update(idx-1, (prev._1, q(0)-1, prev._3))
          }
          //prev._2 = q(0) - 1
          if (prev._2 > q(1)) {
            // doesn't span outside prev 
            buf.insert(idx+1, (q(1), prev._2, prev._3))
          } else if (prev._2 < q(1)) {
            // span outside prev
            exeQuery(buf, Array(prev._2+1, q(1), q(2)))
          }
        } else { //if (prev._2 >= q(0) 
          // out of bound, add to the end
          if (next == null)
            buf.append((q(0), q(1), q(2).toLong))
          else {
            if (q(1) < next._1) {
              buf.insert(idx, (q(0), q(1), q(2)))
            } else {
              buf.insert(idx, (q(0), next._1 - 1, q(2)))
              exeQuery(buf, Array(next._1, q(1), q(2)))
            }
          }
        }
      }
    }

    // Complete the arrayManipulation function below.
    def arrayManipulation(n: Int, queries: Array[Array[Int]]): Long = {

      val l = scala.collection.mutable.ListBuffer[(Int, Int, Long)]()
      var idx = 0
      l.append((queries(0)(0), queries(0)(1), queries(0)(2).toLong))
      queries.tail.foreach(q => {
        exeQuery(l, q)
      })
      println(l)
      l.map(_._3).max
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val nm = stdin.readLine.split(" ")

        val n = nm(0).trim.toInt

        val m = nm(1).trim.toInt

        val queries = Array.ofDim[Int](m, 3)

        for (i <- 0 until m) {
            queries(i) = stdin.readLine.split(" ").map(_.trim.toInt)
        }

        val result = arrayManipulation(n, queries)

        printWriter.println(result)

        printWriter.close()
    }
}

