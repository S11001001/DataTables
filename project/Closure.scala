package com.clarifi.datatablesstatic.project

import scala.collection.JavaConverters._

import sbt.{File, Level, Logger}
import sbt.std.TaskStreams
import com.google.javascript.jscomp

object Closure {
  def compiler(s: TaskStreams[_]) =
    new jscomp.Compiler(new SbtClosureErrors(s.log))

  def compile(c: jscomp.Compiler, inf: File) = {
    val res = c.compile(List(jscomp.SourceFile.fromCode("/dev/null", "")).asJava,
                        List(jscomp.SourceFile.fromFile(inf)).asJava,
                        stdOptions)
    if (res.success) Right(c.toSource)
    else Left(res.errors.toSeq)
  }

  // simulate command line from scripts/make.sh with our defaults
  private def stdOptions = {
    val v = new jscomp.CompilerOptions()
    val lvl = jscomp.CompilationLevel.SIMPLE_OPTIMIZATIONS
    lvl.setOptionsForCompilationLevel(v)
    v
  }
}

/** Report Closure errors via sbt. */
private[project] final class SbtClosureErrors(log: Logger)
    extends jscomp.BasicErrorManager {
  override def println(l: jscomp.CheckLevel, e: jscomp.JSError): Unit = {
    import jscomp.CheckLevel._
    log log (l match {case ERROR => Level.Error
                      case OFF => Level.Debug
                      case WARNING | _ => Level.Warn},
             e.toString)
  }

  // very much like sbt.LoggerReporter#printSummary.
  override def printSummary(): Unit = {
    import sbt.LoggerReporter.countElementsAsString
    if (getWarningCount > 0)
      log warn (countElementsAsString(getWarningCount, "warning") + " found")
    if (getErrorCount > 0)
      log error (countElementsAsString(getErrorCount, "error") + " found")
  }
}
