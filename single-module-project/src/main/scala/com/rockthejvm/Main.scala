package com.rockthejvm

object Main {
    val fansiStr: fansi.Str = fansi.Color.Red("This should be red string")
    def main(args: Array[String]): Unit = println(fansiStr)
}