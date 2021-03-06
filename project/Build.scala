import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "computer-database"
    val appVersion      = "1.0"

    val appDependencies = Seq(
    	jdbc,
    	anorm,
      "com.softwaremill.macwire" %% "core" % "0.1",
      "org.mockito" % "mockito-all" % "1.9.0"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
    )

}
            
